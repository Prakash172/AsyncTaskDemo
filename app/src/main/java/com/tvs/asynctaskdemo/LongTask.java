package com.tvs.asynctaskdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

public class LongTask extends AsyncTask<String, Integer, Integer> {


    private IReportBack iReportBack;
    private String TAG;
    private WeakReference<Context> context;

    /*When the Activity stops existing,
    since it is hold through the means of a WeakReference,
    it can be collected. Therefore no memory leaks will happen.*/

    LongTask(Context context, IReportBack iReportBack, String tag) {
        this.iReportBack = iReportBack;
        this.TAG = tag;
        this.context = new WeakReference<>(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i(TAG, "onPreExecute: " + Thread.currentThread());
        iReportBack.preExecute();
    }

    protected void onProgressUpdate(Integer... progress) {
        Log.i(TAG, "onPreExecute: " + Thread.currentThread());
        Integer i = progress[0];
        iReportBack.reportBack(TAG, (i+1)*(100/5));
    }

    protected void onPostExecute(Integer result) {
//Runs on the main ui thread
        if (context.get() != null) { // if activity is not destroyed and  collected by garbage collector
            iReportBack.reportBack(TAG, result);
            iReportBack.postExecute();
            Log.i(TAG, "onPreExecute: " + Thread.currentThread());
        }
    }

    @Override
    protected Integer doInBackground(String... strings) {
        Log.i(TAG, "doInBackground: " + Thread.currentThread());
        for (String s : strings) {
            Log.i(TAG, "Processing:" + s);
        }
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(i); //this calls onProgressUpdate
        }
        return 1;
    }
}
