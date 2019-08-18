package com.eyeapp.tesmata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Yulian on 12/06/2016.
 */
public class SplashScreen extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Thread timer = new Thread(){
        public void run() {
            try{
                sleep(1500);
            } catch (InterruptedException e){
                e.printStackTrace();
            } finally {
                Intent myIntent = new Intent(SplashScreen.this, MenuUtama.class);
                startActivity(myIntent);
            }
        }
    };
        timer.start();
    }
    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}
