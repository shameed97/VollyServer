package com.example.vollyserver;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    EditText ed_name, ed_user, ed_pass, ed_mob;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void insert(View view) {
        ed_user = findViewById(R.id.enter_id);
        ed_name = findViewById(R.id.enter_name);
        ed_mob = findViewById(R.id.enter_no);
        ed_pass = findViewById(R.id.enter_pass);
        builder = new AlertDialog.Builder(Main2Activity.this);
        final String user = ed_user.getText().toString();
        final String name = ed_name.getText().toString();
        final String mobile = ed_mob.getText().toString();
        final String password = ed_pass.getText().toString();

        String url = "http://shameed.000webhostapp.com/insert.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                builder.setTitle("Server Message");
                builder.setMessage("Success :" + response);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ed_user.setText("");
                        ed_name.setText("");
                        ed_mob.setText("");
                        ed_pass.setText("");
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                builder.setMessage("Error :" + error);
                error.printStackTrace();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("p_name", name);
                params.put("p_username", user);
                params.put("p_mob", mobile);
                params.put("p_pass", password);
                return params;
            }
        };
        Mysingleton.addinstance(Main2Activity.this).addrequest(stringRequest);
    }
}
