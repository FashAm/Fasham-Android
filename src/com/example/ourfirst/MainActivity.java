package com.example.ourfirst;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {

    protected boolean _active = true;
    protected int _splashTime = 3000; // time to display the splash screen in ms
    private Intent intent;
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("hello", "my hello");
        // thread for displaying the SplashScreen
        intent = new Intent(this, TabsActivity.class);
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(100);
                        if (_active) {
                            waited += 100;
                        }
                    }
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
                    startActivity(intent);
                }
            }
        };
        splashTread.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            _active = false;
        }
        return true;
    }

    /* @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.activity_main, menu);
            return true;
        }
    */


    //called on AcitvityFeed button - Instead of onCLickListener
    public void clicktabs(View view) {
        //take picture
        Intent intent = new Intent(this, TabsActivity.class);
        startActivity(intent);

    }

    //called on Capture button - Instead of onCLickListener
    public void takepicture(View view) {
        //take picture
        /*Intent intent= new Intent(this, TabsActivity.class);
        startActivity(intent);*/

    }


}
