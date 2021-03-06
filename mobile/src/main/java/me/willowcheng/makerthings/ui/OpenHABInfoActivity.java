/**
 * Copyright (c) 2010-2014, openHAB.org and others.
 * <p/>
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * @author Victor Belov
 * @since 1.4.0
 */

package me.willowcheng.makerthings.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;
import com.loopj.android.image.WebImageCache;

import org.apache.http.Header;

import me.willowcheng.makerthings.R;
import me.willowcheng.makerthings.util.MyAsyncHttpClient;
import me.willowcheng.makerthings.util.Util;

public class OpenHABInfoActivity extends ActionBarActivity {

    private static final String TAG = "OpenHABInfoActivity";
    private TextView mOpenHABVersionText;
    private TextView mOpenHABUUIDText;
    private TextView mOpenHABSecretText;
    private TextView mOpenHABSecretLabel;
    private String mOpenHABBaseUrl;
    private String mUsername;
    private String mPassword;
    private static MyAsyncHttpClient mAsyncHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
//        Util.setActivityTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.openhabinfo);
        mAsyncHttpClient = new MyAsyncHttpClient(this);
        mOpenHABVersionText = (TextView) findViewById(R.id.openhab_version);
        mOpenHABUUIDText = (TextView) findViewById(R.id.openhab_uuid);
        mOpenHABSecretText = (TextView) findViewById(R.id.openhab_secret);
        mOpenHABSecretLabel = (TextView) findViewById(R.id.openhab_secret_label);
        if (getIntent().hasExtra("openHABBaseUrl")) {
            mOpenHABBaseUrl = getIntent().getStringExtra("openHABBaseUrl");
            mUsername = getIntent().getStringExtra("username");
            mPassword = getIntent().getStringExtra("password");
            mAsyncHttpClient.setBasicAuth(mUsername, mPassword);
        } else {
            Log.e(TAG, "No openHABBaseURl parameter passed, can't fetch openHAB info from nowhere");
            finish();
        }
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume()");
        super.onResume();
        Log.d(TAG, "url = " + mOpenHABBaseUrl + "static/version");
        mAsyncHttpClient.get(mOpenHABBaseUrl + "static/version", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable error) {
                mOpenHABVersionText.setText("Unknown");
                if (error.getMessage() != null) {
                    Log.e(TAG, error.getMessage());
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d(TAG, "Got version = " + responseString);
                mOpenHABVersionText.setText(responseString);
            }
        });
        mAsyncHttpClient.get(mOpenHABBaseUrl + "static/uuid", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable error) {
                mOpenHABUUIDText.setText("Unknown");
                if (error.getMessage() != null) {
                    Log.e(TAG, error.getMessage());
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d(TAG, "Got uuid = " + responseString);
                mOpenHABUUIDText.setText(responseString);
            }
        });
        mAsyncHttpClient.get(mOpenHABBaseUrl + "static/secret", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable error) {
                mOpenHABSecretText.setVisibility(View.GONE);
                mOpenHABSecretLabel.setVisibility(View.GONE);
                if (error.getMessage() != null) {
                    Log.e(TAG, error.getMessage());
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d(TAG, "Got secret = " + responseString);
                mOpenHABSecretText.setVisibility(View.VISIBLE);
                mOpenHABSecretLabel.setVisibility(View.VISIBLE);
                mOpenHABSecretText.setText(responseString);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        Util.overridePendingTransition(this, true);
    }

    public void writeEmail(View v) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "ottawazineapp@Gmail.com", null));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Makerthings Android App Feedback");
//        intent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.contact_email_text));
        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    public void clearCache(View v) {
        // Get launch intent for application
//                Intent restartIntent = getBaseContext().getPackageManager()
//                        .getLaunchIntentForPackage(getBaseContext().getPackageName());
//                restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        Intent restartIntent = new Intent(this, OpenHABMainActivity.class);
//        restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        // Finish current activity
//        finish();
        WebImageCache cache = new WebImageCache(getBaseContext());
        cache.clear();
        // Start launch activity
//        startActivity(restartIntent);
//        // Start launch activity

        Toast.makeText(this, "Images cache cleared",
                Toast.LENGTH_SHORT).show();
    }
    public void goGithub(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/willowcheng/Makerthings-android"));
        startActivity(intent);
    }
}
