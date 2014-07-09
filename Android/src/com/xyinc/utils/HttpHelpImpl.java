package com.xyinc.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ParseException;
import android.util.Log;

/**
 * @author wellington.fernandes
 * Parse JSON
 */
public class HttpHelpImpl implements IHttpHelp {
	
	private final String TAG = this.getClass().getName();

	private DefaultHttpClient httpclient;
    private HttpGet httpGet;
    private HttpResponse response;
    private HttpEntity entity;
    private HttpPost httpPost;

	public HttpHelpImpl() {
		this.httpclient = new DefaultHttpClient();
		this.httpGet = new HttpGet();
		this.httpPost = new HttpPost();
	}
	
	// Json GET
	public JSONObject getJson(String url, Context context) throws IOException, ClientProtocolException {
		try {

			InputStream is = null;
			JSONObject jObj = null;
			String json = "";

			httpGet.setURI(new URI(url));

			response = httpclient.execute(httpGet);

			entity = response.getEntity();

			is = entity.getContent();

			try {
				
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				
				}
				
				is.close();
				json = sb.toString();
				
			} catch (Exception e) {
				Log.e("Buffer Error", "Error converting result " + e.toString());
			}

			try {
				
				jObj = new JSONObject(json);
				
			} catch (JSONException e) {
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}

			return jObj;
		} catch (URISyntaxException ex) {
			Log.e(TAG,"informação não pôde ser analisado durante a criação da URI",ex);
			return null;
		}
	}
	
	// Json Post passando um objeto
	public Boolean postJson(String url, String nomeObjeto, JSONObject jsonObject, Context context) throws IOException, ClientProtocolException, ParseException, JSONException{

		JSONObject infoJson = new JSONObject();
		
		try {
			
			httpPost.setURI(new URI(url));

			httpPost.addHeader("Content-Type", "application/json");

			infoJson.put(nomeObjeto, jsonObject);

			StringEntity se = new StringEntity(infoJson.toString());
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
			httpPost.setEntity(se);

			response = httpclient.execute(httpPost);

			entity = response.getEntity();

			Boolean retorno = entity != null ? EntityUtils.toString(entity).equalsIgnoreCase("true") : null;
			return retorno;

		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	// Json Post passando uma lista de parametros
	public JSONObject jsonPostParameters(String url,List<NameValuePair> parametros, Context context) throws IOException {

		try {

			InputStream is = null;
			JSONObject jObj = null;
			String json = "";

			httpPost.setURI(new URI(url));
			httpPost.setEntity(new UrlEncodedFormEntity(parametros));

			response = httpclient.execute(httpPost);

			entity = response.getEntity();

			is = entity.getContent();

			try {
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
					
				}
				
				is.close();
				json = sb.toString();
				
			} catch (Exception e) {
				Log.e("Buffer Error", "Erro ao converter " + e.toString());
			}

			try {
				
				jObj = new JSONObject(json);
				
			} catch (JSONException e) {
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}

			return jObj;
		} catch (URISyntaxException ex) {
			Log.e(TAG,"informação não pôde ser analisado durante a criação da URI",ex);
			return null;
		}
	}
			
}