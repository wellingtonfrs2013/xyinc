package com.xyinc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.xyinc.R;

/**
 * @author wellington.fernandes
 * Tela Sobre o sistema
 */
public class SobreActivity extends Activity {
	
	WebView web;

	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sobre);

		web = (WebView) findViewById(R.id.webView);
		WebSettings settings = web.getSettings();
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		settings.setSupportMultipleWindows(true);
		settings.setSupportZoom(false);
		web.setVerticalScrollBarEnabled(true);
		web.setHorizontalScrollBarEnabled(false);

		web.loadUrl("file:///android_asset/www/sobre.html");
		
		findViewById(R.id.btVoltar);

	}
	
	public void onClickVoltar(View v){
		Intent it = new Intent(this, MainActivity.class);
		this.startActivity(it);
		finish();
	}
}
