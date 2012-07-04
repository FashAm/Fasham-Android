package com.example.ourfirst;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import android.widget.TabHost.TabSpec;

import java.util.ArrayList;

public class TabsActivity extends Activity {
    private static final int CAMERA_REQUEST = 1888;
    //private CameraClass cameraClass = new CameraClass(this);
    private CameraClass cameraClass = new CameraClass(this);
    private WebView mWebView;
    private ListView listview;
    private ArrayList mListItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tabs);

        TabHost tabHost = (TabHost) findViewById(R.id.TabHost01);
        tabHost.setup();

        TabSpec spec1 = tabHost.newTabSpec("Tab 1");
        spec1.setIndicator("Public");
        spec1.setContent(R.id.list_view_public);

        TabSpec spec2 = tabHost.newTabSpec("Tab 2");
        spec2.setIndicator("Friends");
        spec2.setContent(R.id.list_view_friends);

        Intent intentWeb = new Intent().setClass(this, WebMainView.class);

        TabSpec spec3 = tabHost.newTabSpec("Tab 3");
        spec3.setIndicator("Profile");
        spec3.setContent(R.id.webView1);

        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
        tabHost.addTab(spec3);


        mWebView = (WebView) findViewById(R.id.webView1);
        mWebView.getSettings().setJavaScriptEnabled(true);
        //mWebView.loadUrl("http://10.66.1.185:8888/");
        mWebView.loadUrl("http://google.com");
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setWebViewClient(new HelloWebViewClient());


       listview = (ListView) findViewById(R.id.list_view_friends);
        mListItem = ItemBO.getItems();
       Log.w("items", mListItem.toString());
        listview.setAdapter(new ListAdapter(TabsActivity.this, R.id.list_view_friends,
                mListItem));

        listview = (ListView) findViewById(R.id.list_view_public);
        mListItem = ItemBO.getItems();
        Log.w("items", mListItem.toString());
        listview.setAdapter(new ListAdapter(TabsActivity.this, R.id.list_view_public,
                mListItem));



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

    // ***ListAdapter***
    private class ListAdapter extends ArrayAdapter { //--CloneChangeRequired
        private ArrayList mList; //--CloneChangeRequired
        private Context mContext;

        public ListAdapter(Context context, int textViewResourceId,
                           ArrayList list) { //--CloneChangeRequired
            super(context, textViewResourceId, list);
            this.mList = list;
            this.mContext = context;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            try {
                if (view == null) {
                    LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = vi.inflate(R.layout.list_item, null); //--CloneChangeRequired(list_item)
                }
                final ItemBO listItem = (ItemBO) mList.get(position); //--CloneChangeRequired
                if (listItem != null) {
                    // setting list_item views
                    ((TextView) view.findViewById(R.id.tv_name))
                            .setText(listItem.getName());
                    ((TextView) view.findViewById(R.id.tv_description)).setText(listItem.getDescription());
                    ((TextView) view.findViewById(R.id.tv_description2)).setText(listItem.getDescription2());
                    view.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View arg0) { //--clickOnListItem
                            Intent myIntent = new Intent(TabsActivity.this,
                                    Activity2.class);
                            myIntent.putExtra("NAME", listItem.getName());
                            startActivity(myIntent);
                            finish();
                        }
                    });
                }
            } catch (Exception e) {
                Log.i(TabsActivity.ListAdapter.class.toString(), e.getMessage());
            }
            return view;
        }
    }

}
