package me.willowcheng.makerthings.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import me.willowcheng.makerthings.R;

/**
 * Created by willowcheng on 15-07-16.
 */
public class SignupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

    }

    public void createAccount(View v) {
        finish();
    }
}
