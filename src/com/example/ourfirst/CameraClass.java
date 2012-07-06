package com.example.ourfirst;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;


@SuppressLint("ParserError")
public class CameraClass {

    private Activity activity;
    private String userId = "Emma";
    //	private String description= "efien i mparra m o yiannis ine pppouli";
//	private String visibility= "public;friends";
//	private String tag= "topshop;primark";	
    private int numPhotos = 0;
    private static final int CAMERA_REQUEST = 1888;
    public String path = new String();
    public Bitmap photo;
    public static Bitmap[] photos = null;


    protected CameraClass(Activity activity) {
        InitVariables();
        this.activity = activity;
    }


    public void InitVariables() {
        this.photos = new Bitmap[5];
        this.numPhotos = 0;
    }


    public void LaunchCamera() {
        // TODO Auto-generated method stub
        this.path = Environment.getExternalStorageDirectory() + "/" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        //this.path = Environment.getDataDirectory() + "/" + String.valueOf(System.currentTimeMillis()) + ".jpg" ;
        Log.w("Photo Path ", this.path);
        File file = new File(path);
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, file.toURI());
        Log.w("Photo Path in extra ", file.toString());
        activity.startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            photo = (Bitmap) data.getExtras().get("data");
            //String backgroundUrl = MediaStore.Images.Media.insertImage(this.getContentResolver(), photo, "fashioImage", null);           
            //TODO bigger picture

            //alert box for more images
            Log.w("MESSAGEBOX ", "Hello form msgbox");
            msgBoxForMorePictures();
        }
    }

    private void msgBoxForMorePictures() {
        AlertDialog morePicMsg = new AlertDialog.Builder(this.activity).create();
        morePicMsg.setTitle("More Photos");
        morePicMsg.setMessage("Take another photo?");
        morePicMsg.setButton("Yes", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                photoInArray();
                LaunchCamera();
            }
        });
        morePicMsg.setButton2("No", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                photoInArray();
                Intent textIntent = new Intent(activity, InputTextActivity.class);
                textIntent.putExtra("numPhotos", numPhotos);
                textIntent.putExtra("userId", userId);
                activity.startActivity(textIntent);

                //activity.setContentView(R.layout.activity_input_text);
                //sendData();
            }
        });
        morePicMsg.show();
    }

    private void photoInArray() {
        File file = new File(path);
        photos[numPhotos] = photo;
        numPhotos++;
    }

    /* public void sendData(String description,String visibility, String tag){
         //imageView.setImageBitmap(photo);
         //imageView.setVisibility(View.VISIBLE);
         sentClass sentclass = new sentClass(this.getUserId(),description, visibility, tag, Integer.toString(this.numPhotos), "N/A", "http://10.66.1.185:8888/mobile");
         try {
             //File file = new File(this.path);
             //Bitmap bmCamera = BitmapFactory.decodeFile(file.toString());
             //photos[0] = photo;
             String sResponse = sentclass.sendData(photos); //TODO sent url to the class
             Log.w("Response from sentClass",sResponse);
             //pop up box
             toastMsgBox(sResponse);

                 Intent textIntent= new Intent(this.activity, TabsActivity.class);
             activity.startActivity(textIntent);

             } catch (Exception e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }

     }

     private void toastMsgBox(String sResponse){
         Context context = this.activity.getApplicationContext();
         CharSequence text = sResponse;
         int duration = Toast.LENGTH_LONG;
         Toast toast = Toast.makeText(context, text, duration);
         toast.show();
     }



     public String getUserId() {
         return userId;
     }
     */


}
