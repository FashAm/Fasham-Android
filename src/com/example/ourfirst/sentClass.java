package com.example.ourfirst;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.util.Base64;
import android.util.Log;


public class sentClass {
	// create a bitmap variable before anything;  	  
	private Bitmap bitmap;  
	private String url;
	private String sResponse;	  
	private String userId;
	private String description;
	private String visibility;
	private String phoneNumber;
	private String tag1;
	private String tag2;
	private String numPhotos;
	
	public sentClass(String id, String description, String visibility, String phoneNumber, String tag1, String tag2, String numPhotos , String sResponse, String url) {
		this.userId= id;
		this.description = description;
		this.tag1 = tag1;
		this.tag2 = tag2;
		this.visibility = visibility;
		this.phoneNumber = phoneNumber;
		this.numPhotos = numPhotos;
		this.sResponse = sResponse;
		this.url  = url;
	}

	public String sendData(Bitmap[] photo) throws Exception {  		
		try {  
	  
	        HttpClient httpClient = new DefaultHttpClient();  
	        HttpContext localContext = new BasicHttpContext();  
	  
	        HttpPost httpPost = new HttpPost(this.url);  
	        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);  
	        //Log.w("photoURL",photoURL);
	        //bitmap = BitmapFactory.decodeFile(photoURL);  
	        
	        Log.w("Total number of Images",this.numPhotos);
	        
	        //sending different images
	        for(int i=0;i<Integer.parseInt(this.numPhotos);i++){
	        	bitmap=photo[i];
	        	Log.w("Number of image added",Integer.toString(i));
		  
		        Bitmap bmpCompressed = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(),  true);
		        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
		        // CompressFormat set up to JPG, you can change to PNG or whatever you want;  
		  	  
		        bmpCompressed.compress(CompressFormat.JPEG, 100, bos);  
		        byte[] data = bos.toByteArray();  
		        
	        	String encodedImage = Base64.encodeToString(data, Base64.DEFAULT);			        
		        entity.addPart("myImage"+ Integer.toString(i), new StringBody(encodedImage));        	        
	        } 

	  
	        // sending image meta;  
	        entity.addPart("userId", new StringBody(this.userId));  
	        entity.addPart("visibility", new StringBody(this.visibility));
	        entity.addPart("phoneNumber", new StringBody(this.phoneNumber));
	        entity.addPart("tag1", new StringBody(this.tag1));
	        entity.addPart("tag2", new StringBody(this.tag2));
	        entity.addPart("description", new StringBody(this.description));
	        entity.addPart("numPhotos", new StringBody(this.numPhotos));
	       
	        // sending a Image;           
	        httpPost.setEntity(entity);  
	        HttpResponse response = httpClient.execute(httpPost, localContext);  
	        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));  
	        this.sResponse = reader.readLine();  
	  
		    } catch (Exception e) {  
		    	this.sResponse = "Error:  " + e.getMessage(); 
		        Log.v("myApp", "Some error came up");
		    }
			Log.w("Response after posting images",sResponse);
			return this.sResponse;  
	}  
}
