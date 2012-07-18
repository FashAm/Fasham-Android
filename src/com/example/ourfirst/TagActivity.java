package com.example.ourfirst;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TagActivity extends Activity {
	static final String TAG = "TagActivity";
	public Bitmap photo;
	ImageView imageView;
	View myView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.tag_activity);

		ImageView imageView = (ImageView) findViewById(R.id.tagImage);

		// pass path to image instead?
		Bundle extras = getIntent().getExtras();
		photo = (Bitmap) extras.get("photo");
		imageView.setImageBitmap(photo);

		imageView.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				int x = (int)event.getX();
				int y = (int)event.getY();
				Log.i(TAG, "x " + x + " and y  " + y);
				createTag(x, y);
				
				return false;
			}
		});

		Log.i(TAG, "tag acvtivity");
	}

	public void createTag(int x, int y) {
		
		//EditText tag = new EditText(this);
		EditText tag = ( EditText)findViewById(R.id.textTag);
		
		//RelativeLayout relativelayout = (RelativeLayout)findViewById(R.id.r)
				
		tag.setHeight(100);
		tag.setWidth(100);
		//tag.layout(x, y, r, b)
		//tag.setFocusable(true);
		
		tag.setVisibility(View.VISIBLE);
		Log.i(TAG, "createTag " + tag.getText().toString() );
		
	}

	
}
















