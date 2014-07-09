package com.xyinc.utils;

import java.io.IOException;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ParseException;

/**
 * @author wellington.fernandes
 * Interface Parse JSON
 */
public interface IHttpHelp {
	
	public JSONObject getJson(String url, Context context) throws IOException, ClientProtocolException;
	public Boolean postJson(String url, String nomeObjeto, JSONObject json, Context context) throws IOException, ClientProtocolException, ParseException, JSONException;
	public JSONObject jsonPostParameters(String url, List<NameValuePair> parametros, Context context) throws IOException;

}
