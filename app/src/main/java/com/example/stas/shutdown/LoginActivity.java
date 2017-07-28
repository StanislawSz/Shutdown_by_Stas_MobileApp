package com.example.stas.shutdown;

import android.content.Intent;
import android.opengl.ETC1;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button buttonZaloguj = (Button) findViewById(R.id.buttonZaloguj);
        final EditText etLogin = (EditText) findViewById(R.id.etLogin);
        final EditText etHaslo = (EditText) findViewById(R.id.etHaslo);

        buttonZaloguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String login = etLogin.getText().toString();
                final String password = etHaslo.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success)
                            {


                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("login", login);
//                                intent.putExtra("password", password);


                                LoginActivity.this.startActivity(intent);
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                LoginRequest loginRequest = new LoginRequest(login, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}
