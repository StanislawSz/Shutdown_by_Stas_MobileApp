package com.example.stas.shutdown;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stas on 24.07.2017.
 */

public class SetTimeRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL = "http://stasio.000webhostapp.com/ShutdownMobile.php";
    private Map<String, String> params;

    public SetTimeRequest(String login, int delay, Response.Listener<String> listener)
    {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("login", login);
        params.put("delay", Integer.toString(delay));

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
