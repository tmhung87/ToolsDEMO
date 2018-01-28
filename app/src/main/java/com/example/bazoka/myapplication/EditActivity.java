package com.example.bazoka.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {
    private EditText edtten, edtmieuta, edtpn, edtsn;
    private Button btnedit, btnhuy;
    private ImageView imageView;
    private String aid, aten, apn, asn, adec, aanh;
    private String aaid, aaten, aapn, aasn, aadec, aaanh;
    private Bitmap bitmap;
    private ProgressDialog dialog;
    private String urledit="https://1luutru.000webhostapp.com/editdata.php";
    private String imagea="a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        actionbar();
        anhxa();
        nhanintent();
        setdulieuvaoview();
        chonanh();
        edit();
        huy();
    }

    private void huy() {
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void edit() {
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aaid = aid;
                aaten = edtten.getText().toString();
                aadec = edtmieuta.getText().toString();
                aapn = edtpn.getText().toString();
                aasn = edtsn.getText().toString();
                dialog = ProgressDialog.show(EditActivity.this, "", "Please wait...", true);
                uploadtools();
            }
        });
    }
    private void uploadtools() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urledit,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String s = response.trim();

                        if (s.equalsIgnoreCase("Error")) {
                            Toast.makeText(getApplicationContext(), "Setdata Error", Toast.LENGTH_SHORT).show();

                            dialog.cancel();

                        } else {
                            Toast.makeText(getApplicationContext(), "Setdata Success", Toast.LENGTH_SHORT).show();

                            dialog.cancel();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("ID",aaid);
                params.put("TEN", aaten);
                params.put("MIEUTA", aadec);
                params.put("PRN", aapn);
                params.put("SN", aasn);
                params.put("ANH", imagea);

                return params;
            }

        };
        {

            int socketTimeout = 30000;
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            stringRequest.setRetryPolicy(policy);
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }


    private void chonanh() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 999);
                    }
                });
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK && data != null) {
            Uri filepath = data.getData();

            try {
                bitmap = getResizedBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), filepath),480);
                imageView.setImageBitmap(bitmap);
                imagea = getStringImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }
    private String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ba);
        byte[] imagebyte = ba.toByteArray();
        String encode = Base64.encodeToString(imagebyte, Base64.DEFAULT);
        return encode;
    }

    private void setdulieuvaoview() {
        Picasso.with(this).load(aanh).into(imageView);
        edtten.setText(aten);
        edtpn.setText(apn);
        edtsn.setText(asn);
        edtmieuta.setText(adec);
    }

    private void nhanintent() {
        Intent intent = getIntent();
        aid = intent.getStringExtra("aid");
        aten = intent.getStringExtra("aten");
        apn = intent.getStringExtra("apn");
        asn = intent.getStringExtra("asn");
        adec = intent.getStringExtra("adec");
        aanh = intent.getStringExtra("aanh");
    }

    private void anhxa() {
        imageView = (ImageView) findViewById(R.id.imageViewedit);
        edtten = (EditText) findViewById(R.id.editTextnameedit);
        edtmieuta = (EditText) findViewById(R.id.editTextdecedit);
        edtpn = (EditText) findViewById(R.id.editTextpnedit);
        edtsn = (EditText) findViewById(R.id.editTextsnedit);
        btnedit = (Button) findViewById(R.id.buttonsua);
        btnhuy = (Button) findViewById(R.id.buttonhuyedit);

    }

    private void actionbar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
