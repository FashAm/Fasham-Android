package com.example.ourfirst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessActivity extends Activity {
	 
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);

	     // Get the message from the intent
	     Intent intent = getIntent();
	     String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

	     // Create the text view
	     TextView textView = new TextView(this);
	     textView.setTextSize(40);
	     textView.setText(message);

	     setContentView(textView);
	 }
}
