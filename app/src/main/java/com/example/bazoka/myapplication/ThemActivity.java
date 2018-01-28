package com.example.bazoka.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.MenuItem;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ThemActivity extends AppCompatActivity {

    ProgressDialog dialog;
    Bitmap bitmap;
    private ImageView imageView;
    private EditText edtten, edtmieuta, edtpn, edtsn;
    private Button btnthem, btnhuy;
    String imagea = "iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEwAACxMBAJqcGAAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAUPSURBVFiFtZhrbBVFFIC/dkuBUIJQHj6CIFANCRoRjYoPUIn+UEQJKD8UrJpqNMFWTYyGhFL8A0Z8RHloEYpioiIECkJEEysRGnygUZBXFESNoWB8ILVCWX+cM+7c6c7d3cv1JJt798zMmW8e58yZBZEqYDewERjJ/yO9gQe0j6NAqM8hYFS+hlXAj8A/wAn9fQ44o0hg/YEG4FcF+hNoAXbo+1FgeD4D+4G/gVuQ2XtPGx4GaoDgNOCmWGCt2kc34CKgTeEuSTLSrID2KG4CvlHDXwDnFQBXB5xCVudmS39xFjiAC5BlXePoy5ClCYF7MsJNVrjNwABLPxI4kgXOyAIFucHRz1D9VRlslSEbfz/QyynbCBzLCgfiEIeBr7UDIzUK+CpQmtLW1drm4ZiyvcBnGexcaiseVMNPA/fpUwa8rvpXUkJO1/pXxpTN1bJ5CTYmAh04gwmAr4jiUwgsiYEsSTB+h9adEFMWWLZ8kLcpXAgcdwtHANXAOGCFA/lGSsgRWq/BU54PcjLisHuRKNIF0DXkg1ySANmChJKKFLYN5FSF2wOcjZxsO/MBGkNmtFkgzTLXJdg2kN8DJ4FvgbOAM5Ew1ZgE6EIuTgFZiezlTuDWFLYfBbaobRMzH1H7E9MAJkEutiB7A58js3F3WuOOlAM/IPsw0zEbB7nSgbwQ+B24s0A4iMJddSGNA3JnLg4SYKA+WWUUctLsQpKKgiQf5CLgWWSDn9L/dnCfCdQjKZgrw4B9QDvOCVJsyBDYoE8IPK5tGqzyY8BrwEOIxz+vunYkUBdFAnJnbpH+b9KyADioAynXzjcB16nuuAXcCbxPAUmET0qRZbIhQ2RWzJL2RALvFgXsR25I6glcAVwLDCoWGNrZOuA37fBF/BnPM1q2GpgEnFtMEB9cs3b6BFHEX0X8qWIPwCzlPE/dosLNUt0gBVyNzF65B3IMkvZ/SNeQVLAMBa4BxiOpuoGb7dR7ieg87UCyc5/YezYp4YiVbsjd1VyY3Geup9OntOOtWm++VT4AiX2lVn07dUubqdPf6mAfssFnANOAWuCuFDa6E8W/0aqbQxR+4iBTXScqgC+Ri3sdufeSNDIbOeB7IIE2BK63YJoSIBuTIFcgm32qpatEZq8JeBfo42lbr5006/tgJJtZS7TH8kGaBGSpD3IMucnhcOAT7SREgm0IbIuBnKVl65HlNfICXb01DaQd6P+TBQo4TN/NzWshkjBWIEmlCzmNaOZsONNpnLfmgzTZ9TIXcjvitUY+RZJFV2p1IG/rezW5zuCKz1tdyMDSG8jlNuQhxPNAQkInMnuu9ELi3Jv6PlaN5UtOfd5qw/ggG0G8tYNoicarkT0xnY1FToqPLGB0QD7pREIVwP3IUteo3mTM0/X3XkvfA/lw0ALwAfCzNh4C/ISkQjc6nc3XkVXpe62+p0kufd5qz9hSq34l8tluG8BjWmGcFp6vkO1ITFyIfAgKgQOWka3AL6Q/CXzeGiDLebtTfwPwF8jd4Q/EOcxhbyBDJNy0Ihmx+TxsPLg+JZwN6fVWRzYpFyBpufEec4oMRsJMP6fh5drwO+SamVW83mrJQOR2uNkoSpA9EAIfA5fFNKoAnkSWvo2ED98ZIN8C+lplfYkyp0l2oxIFMHeFA8gJsQo5WcwXp1aioH46EhBNShvwjvZ1RHUv+xqegxz+25FpPoFcflYiS17sbLgaSVRC5CDYQRSa+BdID68M+uQ7IgAAAABJRU5ErkJggg==";
    String urlup = "https://1luutru.000webhostapp.com/setdata.php";
    private String ten, mieuta, pn, sn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);

        actionbar();
        anhxa();
        selectimage();
        setdata();
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

    private void actionbar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setdata() {
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ten = edtten.getText().toString();
                mieuta = edtmieuta.getText().toString();
                pn = edtpn.getText().toString();
                sn = edtsn.getText().toString();
                dialog = ProgressDialog.show(ThemActivity.this, "", "Please wait...", true);
                uploadtools();
            }
        });
    }


    private void uploadtools() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlup,
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

                params.put("TEN", ten);
                params.put("MIEUTA", mieuta);
                params.put("PRN", pn);
                params.put("SN", sn);
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


    private void selectimage() {
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

    private String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ba);
        byte[] imagebyte = ba.toByteArray();
        String encode = Base64.encodeToString(imagebyte, Base64.DEFAULT);
        return encode;
    }

    private void anhxa() {
        imageView = (ImageView) findViewById(R.id.imageView);
        edtten = (EditText) findViewById(R.id.editTextname);
        edtmieuta = (EditText) findViewById(R.id.editTextdec);
        edtpn = (EditText) findViewById(R.id.editTextpn);
        edtsn = (EditText) findViewById(R.id.editTextsn);
        btnthem = (Button) findViewById(R.id.buttonthem);
        btnhuy = (Button) findViewById(R.id.buttonhuy);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
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


}
