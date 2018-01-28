package com.example.bazoka.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private ArrayList<Tools> toolsArrayList;
    private RecyclerView recyclerView;
    private CustomAdapterRecycleview toolsadapter;
    private int a = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        linearLayout = (LinearLayout) findViewById(R.id.lineartools);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);

        actionBar.setDisplayShowTitleEnabled(false);


        String url = "https://1luutru.000webhostapp.com/getdata.php";
        toolsArrayList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycleviewTools);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        );

        recyclerView.setLayoutManager(layoutManager);

        recyclerViewtools();

        getData(url);
        int i = 0;
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration, i);
    }


    private void recyclerViewtools() {
        toolsadapter = new CustomAdapterRecycleview(toolsArrayList, getApplicationContext());
        recyclerView.setAdapter(toolsadapter);


    }

    private void getData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        toolsArrayList.add(new Tools(
                                jsonObject.getInt("ID"),
                                jsonObject.getString("TEN"),
                                jsonObject.getString("MIEUTA"),
                                jsonObject.getString("PN"),
                                jsonObject.getString("SN"),
                                jsonObject.getString("ANH")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                toolsadapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutool, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemthem:
                startActivity(new Intent(MainActivity.this, ThemActivity.class));
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getTitle().toString()) {
            case "Edit":

                break;
            case "Delete":
                toolsadapter.getPosition();
                delete();
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void delete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa item");
        builder.setMessage("Bạn có chắc muốn xóa không?");
        builder.setCancelable(false);
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                toolsadapter.xoaitem();

            }


        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
