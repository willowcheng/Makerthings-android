package me.willowcheng.makerthings.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import me.willowcheng.makerthings.R;

/**
 * Created by willowcheng on 15-07-16.
 */
public class SplashScreen extends Activity {

    private SharedPreferences perPreferences;

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        perPreferences = getSharedPreferences("pref", this.MODE_PRIVATE);


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if (perPreferences.getBoolean("account_login", false)) {
                    Intent i = new Intent(SplashScreen.this, OpenHABMainActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                } else {
                    findViewById(R.id.linearLayout_login).setVisibility(View.VISIBLE);
                }

            }
        }, SPLASH_TIME_OUT);
    }

    public void signUp(View v) {
        Intent intent = new Intent();
        intent.setClass(this, SignupActivity.class);
        startActivity(intent);
    }

    public void logIn(View v) {
        SharedPreferences.Editor editor = perPreferences.edit();
        editor.putBoolean("account_login", true);
        editor.apply();
        Intent intent = new Intent(this, OpenHABMainActivity.class);
        startActivity(intent);
        finish();
    }

}
