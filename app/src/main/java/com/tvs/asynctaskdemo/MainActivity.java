package com.tvs.asynctaskdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements IReportBack {

    private String TAG = "MainActivity";
    Button button;
    ProgressBar progressBar;
    Context context;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        button = findViewById(R.id.start_async);
        progressBar = findViewById(R.id.progressBar);
        progressDialog = new ProgressDialog(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LongTask task = new LongTask(getApplicationContext(),MainActivity.this,TAG);
                task.execute("dydh","dufyu","fufyufyu","uuyfyu","gggy");
            }
        });

    }

    @Override
    public void preExecute() {
        progressBar.setVisibility(View.VISIBLE);
        progressDialog.setTitle("Title");
        progressDialog.setMessage("Downloading");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    @Override
    public void reportBack(String tag, String result) {
        Log.i(TAG, "reportBack: "+result);
    }

    @Override
    public void postExecute() {
        progressBar.setVisibility(View.GONE);
        progressDialog.cancel();
    }
}
