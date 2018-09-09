package com.example.secretbrowser;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	WebView web;
	ImageButton search,voice;
	EditText edit;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        web = (WebView) findViewById(R.id.web);
        
        WebSettings webSettings = web.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        web.setWebViewClient(new Callback());
        
        String s = "";
    	try{
        Bundle bundle=getIntent().getExtras();
    	s=bundle.getString("URL");
    	
    	s= clean(s);
    	web.loadUrl(s);
    	}
    	catch(Exception e){
    		s= "http://google.com";
    	};
        web.loadUrl(s);//HERE IS THE MAIN CHANGE
    
    edit=(EditText)findViewById(R.id.edit);
    edit.setText(s);
    
    search=(ImageButton)findViewById(R.id.search);
    search.setOnClickListener(this);
    
    voice=(ImageButton)findViewById(R.id.voice);
    voice.setOnClickListener(this);
    
}
@Override
public void onClick(View v) {
	
	String s="";

	WebSettings webSettings = web.getSettings();
    webSettings.setBuiltInZoomControls(true);
    webSettings.setJavaScriptEnabled(true);
    web.setWebViewClient(new Callback());  //HERE IS THE MAIN CHANGE
    
    if(v.equals(search)){
    s=edit.getText().toString().trim();
	s = clean(s);
    web.loadUrl(s);
    }
}
 private class Callback extends WebViewClient{  //HERE IS THE MAIN CHANGE. 
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) { //TO VIEW THE WEBPAGES WITHIN THE APP 
            return (false);
        }

    }

 public String clean(String s)
 {
	 
	 if(s.indexOf("http://")<0 && s.indexOf(".")>0)
			s="http://"+s.trim();
			else 
				if(s.indexOf("http://")<0 && s.indexOf(".")<0)
			s="http://google.com/search?q="+s.trim();
	 return s;
 }
    
}
