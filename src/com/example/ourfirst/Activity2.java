package com.example.ourfirst;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: The0s
 * Date: 04/07/12
 * Time: 03:59
 * To change this template use File | Settings | File Templates.
 */
public class Activity2 extends Activity {
    private Context context;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        Button button = new Button(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            button.setText("Item name = " + bundle.getString("NAME")
                    + " --- Go Back ");
        } else {
            button.setText("Go Back");
        }
        context = this;
        button.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                startActivity(new Intent(context,TabsActivity.class));
                finish();
            }
        });

       webView = new WebView(this);
        //webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl("http://10.66.1.185:8888/");
        webView.loadUrl("http://google.com");
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new HelloWebViewClient());

        relativeLayout.addView(button);
        relativeLayout.addView(webView);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        setContentView(relativeLayout, params);
    }
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            try {
                view.loadUrl(url);
            } catch (ActivityNotFoundException e) {
                // Raise on activity not found
                toastMsgBox("Browser not found...");
            }

            return true;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        else if ( (keyCode == KeyEvent.KEYCODE_BACK) && (!webView.canGoBack()) ){
        	 startActivity(new Intent(this,TabsActivity.class));
        }
        return super.onKeyDown(keyCode, event);
    } 

   
    @Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}




	private void toastMsgBox(String sResponse) {
        Context context = this.getApplicationContext();
        CharSequence text = sResponse;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.feed_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_settings_feed:
                Intent feed = new Intent(this, TabsActivity.class);
                this.startActivity(feed);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

