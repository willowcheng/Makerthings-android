package me.willowcheng.makerthings.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import me.willowcheng.makerthings.R;

/**
 * Created by willowcheng on 15-07-16.
 */
public class SplashScreen extends Activity {

    private static final String TAG = "SplashScreen";
    private SharedPreferences perPreferences;

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;
    private String defaultURL;
    private List<ParseObject> serverList;
    private ProgressBarCircularIndeterminate mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mProgressBar = (ProgressBarCircularIndeterminate) findViewById(R.id.progressBarCircularIndeterminate);
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setEnabled(true);

        perPreferences = getSharedPreferences("local", MODE_PRIVATE);

        final ArrayList<String> serverURLs = new ArrayList<>();

        if (!getIntent().getBooleanExtra("restart", false)) {

            // Enable Local Datastore.
            Parse.enableLocalDatastore(this);

            Parse.initialize(this, "RC3lgpukUQCJEB3PnMy3tpqk3DFKZRrF0xNtfVgy", "QhHSObxptKgq4GHCOp48TxZNfpdf63S9SMksjZRV");
        }


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Server");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> parseObjectList, ParseException e) {

                if (e == null) {
                    serverList = parseObjectList;
                    Log.d(TAG, "Retrieved " + serverList.size() + " items");
                    if (serverList.size() == 0) {
                        findViewById(R.id.linearLayout_login).setVisibility(View.INVISIBLE);
                        SharedPreferences.Editor editor = perPreferences.edit();
                        editor.putString("default_URL", getResources().getString(R.string.openhab_demo_url));
                        editor.apply();
                        Intent i = new Intent(SplashScreen.this, OpenHABMainActivity.class);
                        startActivity(i);

                        // close this activity
                        finish();
                    } else {
                        findViewById(R.id.linearLayout_login).setVisibility(View.VISIBLE);
                        for (ParseObject i : serverList) {
                            serverURLs.add(i.getString("Name"));
                            Log.d(TAG, i.getString("Name"));
                            Log.d(TAG, i.getString("URL"));
                        }
                        Spinner mSpinner = (Spinner) findViewById(R.id.spinner);
                        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                defaultURL = serverList.get(position).getString("URL");
                                Log.d(TAG, "Choosed " + defaultURL);

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        ArrayAdapter<String> URLDataAdapter = new ArrayAdapter<>(getApplication(), R.layout.spinner_item, serverURLs);

                        // Set dropdown menu style
                        URLDataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        mSpinner.setAdapter(URLDataAdapter);
                    }

                } else {
                    Log.d(TAG, "Error: " + e.getMessage());
                }
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });


    }

    public void start(View v) {
        SharedPreferences.Editor editor = perPreferences.edit();
        editor.putString("default_URL", defaultURL);
        editor.apply();
        Intent intent = new Intent(this, OpenHABMainActivity.class);
        startActivity(intent);
        finish();
    }

}
