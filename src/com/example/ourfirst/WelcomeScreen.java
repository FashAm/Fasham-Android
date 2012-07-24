package com.example.ourfirst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

//welcome screen- this is the screen where the fb login button will be displayed
public class WelcomeScreen extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_screen);

		ImageButton buttonFacebook = (ImageButton) findViewById(R.id.imageButtonFacebook);
		buttonFacebook.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(WelcomeScreen.this,FacebookLogin.class);
				startActivity(intent);
			}
		});
	}

}

