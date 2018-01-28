package com.example.bazoka.myapplication;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomAdapterRecycleview extends RecyclerView.Adapter<CustomAdapterRecycleview.ViewHolder> {
    ArrayList<Tools> toolsArrayList;
    Context context;
    String id, ten, anh;
    MenuItem edit;
    MenuItem delete;
    private String urlDel = "https://1luutru.000webhostapp.com/deldata.php";
    private int position;

    public CustomAdapterRecycleview(ArrayList<Tools> toolsArrayList, Context context) {
        this.toolsArrayList = toolsArrayList;
        this.context = context;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.tools, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.txtten.setText(toolsArrayList.get(position).getTen());
        holder.txtmieuta.setText(toolsArrayList.get(position).getMieuta());
        holder.txtpn.setText(toolsArrayList.get(position).getPn());
        holder.txtsn.setText(toolsArrayList.get(position).getSn());
        Picasso.with(context).load(toolsArrayList.get(position).getAnh()).into(holder.imageViewAnh);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                setPosition(holder.getAdapterPosition());
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return toolsArrayList.size();
    }

    public void xoaitem() {

        StringRequest stringRequestdel = new StringRequest(
                Request.Method.POST,
                urlDel,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        toolsArrayList.remove(toolsArrayList.get(position));
                        notifyItemRemoved(position);
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("ID", String.valueOf(toolsArrayList.get(position).getId()));
                params.put("TEN", toolsArrayList.get(position).getTen());
                params.put("ANH", toolsArrayList.get(position).getAnh());
                return params;
            }

        };
        {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequestdel);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        TextView txtten, txtmieuta, txtpn, txtsn;
        ImageView imageViewAnh;
        Button btn;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            txtten = (TextView) itemView.findViewById(R.id.tvten);
            txtmieuta = (TextView) itemView.findViewById(R.id.tvmieuta);
            txtpn = (TextView) itemView.findViewById(R.id.tvpn);
            txtsn = (TextView) itemView.findViewById(R.id.tvsn);
            imageViewAnh = (ImageView) itemView.findViewById(R.id.imageAnh);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(final ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Select the action");
            edit = contextMenu.add(0, view.getId(), 0, "Edit");
            delete = contextMenu.add(0, view.getId(), 0, "Delete");


            edit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {

                    Intent intent= new Intent(context, EditActivity.class);
                    intent.putExtra("aid",String.valueOf(toolsArrayList.get(position).getId()));
                    intent.putExtra("aten",toolsArrayList.get(position).getTen());
                    intent.putExtra("apn",toolsArrayList.get(position).getPn());
                    intent.putExtra("asn",toolsArrayList.get(position).getSn());
                    intent.putExtra("adec",toolsArrayList.get(position).getMieuta());
                    intent.putExtra("aanh",toolsArrayList.get(position).getAnh());
                    context.startActivity(intent);

                    return false;
                }
            });
            delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    Toast.makeText(context, String.valueOf(toolsArrayList.get(position).getId()), Toast.LENGTH_SHORT).show();

                    return false;
                }
            });


        }
    }


}
