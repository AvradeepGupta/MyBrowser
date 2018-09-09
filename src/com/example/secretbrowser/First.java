package com.example.secretbrowser;

import java.util.ArrayList;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class First extends Activity implements OnClickListener {

	WebView web;
	ImageButton search,facebook,youtube,twitter,google,wikipedia,gmail,yahoo,bing,voice;
	EditText edit;
	private final int SPEECH_RECOGNITION_CODE = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
    
    edit=(EditText)findViewById(R.id.edit);
    
    search=(ImageButton)findViewById(R.id.search);
    search.setOnClickListener(this);
    
    voice=(ImageButton)findViewById(R.id.voice);
    voice.setOnClickListener(this);
    
    google=(ImageButton)findViewById(R.id.google);
    google.setOnClickListener(this);
    
    youtube=(ImageButton)findViewById(R.id.youtube);
    youtube.setOnClickListener(this);
    
    facebook=(ImageButton)findViewById(R.id.facebook);
    facebook.setOnClickListener(this);
    
    bing=(ImageButton)findViewById(R.id.bing);
    bing.setOnClickListener(this);
    
    wikipedia=(ImageButton)findViewById(R.id.wikipedia);
    wikipedia.setOnClickListener(this);
    
    yahoo=(ImageButton)findViewById(R.id.yahoo);
    yahoo.setOnClickListener(this);
    
    gmail=(ImageButton)findViewById(R.id.gmail);
    gmail.setOnClickListener(this);
    
    twitter=(ImageButton)findViewById(R.id.twitter);
    twitter.setOnClickListener(this);
    
}
@Override
public void onClick(View v) {
	
	String s="";
	
    if(v.equals(search)){
    	s=edit.getText().toString().trim();
    }
    
    else if(v.equals(google)){
    	s="http://google.com";
    }
    
    else if(v.equals(facebook))
    {
    	s="http://facebook.com";
    }
    
    else if(v.equals(twitter))
    {
    	s="http://twitter.com";
    }
    
    else if(v.equals(youtube))
    {
    	s="http://youtube.com";
    }
    
    else if(v.equals(gmail))
    {
    	s="http://gmail.com";
    }
    
    else if(v.equals(yahoo))
    {
    	s="http://yahoo.com";
    }
    
    else if(v.equals(bing))
    {
    	s="http://bing.com";
    }
    
    else if(v.equals(wikipedia))
    {
    	s="http://wikipedia.com";
    }
    
    if(v.equals(voice)) {
    	 startSpeechToText();
    }
    else
    	send(s);
}

public void send(String s)
{

    Bundle b = new Bundle();	
	Intent obj=new Intent(this,MainActivity.class);
	b.putString("URL", s);
    obj.putExtras(b);
    this.startActivity(obj);
	
}
private void startSpeechToText() {
    
	Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
    intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
            "Speak something...");
    try {
        startActivityForResult(intent, SPEECH_RECOGNITION_CODE);
    } catch (ActivityNotFoundException a) {
        Toast.makeText(getApplicationContext(),
                "Sorry! Speech recognition is not supported in this device.",
                Toast.LENGTH_SHORT).show();
    }
 }
 /**
  * Callback for speech recognition activity
  * */
 @Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
     switch (requestCode) {
     case SPEECH_RECOGNITION_CODE: {
         if (resultCode == RESULT_OK && null != data) {
             ArrayList<String> result = data
                     .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
             String text = result.get(0);
             send(text);
         }
         break;
     }
     }
 }

}

    

