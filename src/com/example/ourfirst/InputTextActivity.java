package com.example.ourfirst;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

@SuppressLint("ParserError")
public class InputTextActivity extends Activity {
	private EditText commentText;
	private EditText tagText;
	private EditText tagText2;
	private RadioGroup radioVisibility;
	private int numPhotos;
	private String userId;
	public Bitmap photo;
	private String visibility;
	private String phoneNumber ="";
	private RadioButton selectedRadioBtn ;
	private int radioButtonId;
	
	
    @Override
	protected void onStop() {
		// TODO Auto-generated method stub
    	finish();
		super.onStop();
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Bundle extras = getIntent().getExtras();
        numPhotos = extras.getInt("numPhotos");
        userId = extras.getString("userId");
        
        setContentView(R.layout.activity_input_text);
        
        //TextView commentView = (TextView) findViewById(R.id.commentView);
        commentText = (EditText) findViewById(R.id.commentText);
       // TextView TagView = (TextView) findViewById(R.id.TagView);
        tagText = (EditText) findViewById(R.id.TagText);
        tagText2 = (EditText) findViewById(R.id.TagText2);
        
        radioVisibility = (RadioGroup) findViewById(R.id.radioGroup1);
      //  radioVisibility
        radioButtonId = radioVisibility.getCheckedRadioButtonId();
        selectedRadioBtn = (RadioButton) findViewById(radioButtonId);
       
      
        visibility = selectedRadioBtn.getText().toString();
        Log.i("visibility",visibility);
        
        
        //setContentView(R.layout.activity_input_text);
        Button buttonUpload = (Button) findViewById(R.id.buttonUpload);
        
        
        buttonUpload.setOnClickListener(new View.OnClickListener() {

            //@Override
            public void onClick(View v) {            	
            	Log.i("upload ","uploading...");
               //TO-DO call method for uploading to host
            	visibility = selectedRadioBtn.getText().toString();
            	Log.i ("visibility",visibility);
            	sendData(commentText.getText().toString(), visibility, tagText.getText().toString(), tagText2.getText().toString(),phoneNumber);
            	 
            }
        });
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_input_text, menu);
        return true;
    }

    public void sendData(String description,String visibility, String tag1, String tag2, String phoneNumber){
        //imageView.setImageBitmap(photo);
        //imageView.setVisibility(View.VISIBLE);      
  
    	if( (description.matches("")) || (tag1.matches("")) || (tag2.matches("")) ){
    			toastMsgBox("Please complete all fields"); 
    	}
    	else{
    		Log.i("sendData", "else statement");
    		//toastMsgBox("Sending to Server..."); 
    		
    		sentClass sentclass = new sentClass(this.getUserId(),description, visibility, phoneNumber, tag1, tag2, Integer.toString(this.numPhotos), "N/A", "http://10.66.1.185:8888/mobile");       
	        try {
	        	//File file = new File(this.path);
	        	//Bitmap bmCamera = BitmapFactory.decodeFile(file.toString());           	     	
	        	//photos[0] = photo;       	
				String sResponse = sentclass.sendData(CameraClass.photos); //TODO sent url to the class
		        Log.w("Response from sentClass",sResponse);
	
		        //pop up box
		        toastMsgBox(sResponse); 
	       	 
	       	 	Intent textIntent= new Intent(this, TabsActivity.class);
	        	startActivity(textIntent); 
	        	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
    	}
    	
    }
    
    private void toastMsgBox(String sResponse){
        Context context = this.getApplicationContext();
        CharSequence text = sResponse;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    


	public String getUserId() {
		return userId;
	}
	
	public void friendsclick(View view){
		msgBoxForMorePictures();
		/*int friendsButtonId = radioVisibility.getCheckedRadioButtonId();
		RadioButton friendsButton = (RadioButton) findViewById(friendsButtonId);*/
		selectedRadioBtn = (RadioButton) findViewById(radioButtonId);
		
		//Log.i("visibility  in firends click",visibility);
	}
	
	
	
	private void msgBoxForMorePictures(){
		
		final EditText phoneText = new EditText(this);
		AlertDialog phoneMsg = new AlertDialog.Builder(this).create();
		
        phoneMsg.setTitle("Text Message Service");
        phoneMsg.setMessage("Would you like to insert a mobile phone number to text your friends?");
        phoneMsg.setView(phoneText);
        
        phoneMsg.setButton("OK", new OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {   
            	if (phoneText.getText().toString().matches("")){
            		toastMsgBox("Please fill in a phone number"); 
            	}
            	else{
            		phoneNumber = phoneText.getText().toString();
            	}
            }
        });
        
        phoneMsg.setButton2("Cancel", new OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
            	
            }
        });
        
        phoneMsg.show(); 
    }
  
    
}
