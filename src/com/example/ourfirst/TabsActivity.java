package com.example.ourfirst;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabsActivity extends Activity {
    private static final int CAMERA_REQUEST = 1888;
    //private CameraClass cameraClass = new CameraClass(this);
    private CameraClass cameraClass = new CameraClass(this);
    private WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tabs);


        TabHost tabHost = (TabHost) findViewById(R.id.TabHost01);
        tabHost.setup();

        TabSpec spec1 = tabHost.newTabSpec("Tab 1");
        spec1.setIndicator("Public");
        spec1.setContent(R.id.listView1);


        TabSpec spec2 = tabHost.newTabSpec("Tab 2");
        spec2.setIndicator("Friends");
        spec2.setContent(R.id.listView2);

        Intent intentWeb = new Intent().setClass(this, WebMainView.class);

        TabSpec spec3 = tabHost.newTabSpec("Tab 3");
        spec3.setIndicator("Profile");
        spec3.setContent(R.id.webView1);

        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        tabHost.addTab(spec3);


        mWebView = (WebView) findViewById(R.id.webView1);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://10.66.1.185:8888/");
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setWebViewClient(new HelloWebViewClient());


        /*  Button buttonUpload = (Button) findViewById(R.id.buttonCapture);

            buttonUpload.setOnClickListener(new View.OnClickListener() {

                //@Override
                public void onClick(View v) {
                    Log.i("upload ","uploading...");
                   //TO-DO call method for uploading to host


                }
            });*/

    }

    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            try {
                view.loadUrl(url);


            } catch (ActivityNotFoundException e) {
                // Raise on activity not found
                //Toast toast = Toast.makeText(this, "Browser not found.", Toast.LENGTH_SHORT);
            }

            return true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tabs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_settings:
                cameraClass.InitVariables();
                cameraClass.LaunchCamera();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        cameraClass.onActivityResult(requestCode, resultCode, data);
    }
    /* protected void onActivityResult(int requestCode, int resultCode, Intent data) {
             if (requestCode == CAMERA_REQUEST) {
                 ImageView imageView= (ImageView)this.findViewById(R.id.imageView1);

                 //this.imageView = (ImageView)this.findViewById(R.id.imageView1);

                 Bitmap photo = (Bitmap) data.getExtras().get("data");
                 imageView.setImageBitmap(photo);

                 Intent textIntent= new Intent(this, InputTextActivity.class);
                 startActivity(textIntent);

                // sentClass sentclass = new sentClass(this.userId);

                 try {
                     String sResponse = sentclass.sendData(photo);
                     Log.w("Response",sResponse);

                     Context context = getApplicationContext();
                     CharSequence text = sResponse;
                     int duration = Toast.LENGTH_SHORT;
                     Toast toast = Toast.makeText(context, text, duration);
                     toast.show();


                 } catch (Exception e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                 }


             }
         } */

}
