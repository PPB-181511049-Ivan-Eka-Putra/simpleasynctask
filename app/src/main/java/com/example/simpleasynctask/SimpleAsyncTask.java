package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {
    WeakReference<TextView> mTextView;
    int i = 0;

    SimpleAsyncTask(TextView tv, ProgressBar mProgressDialog) {
        mTextView = new WeakReference<>(tv);
    }

    @Override
    protected String doInBackground(Void... voids) {
        // Generate a random number between 0 and 10
        Random r = new Random();
        int n = r.nextInt(11);

        int i = 1;
        while(i <= n) {
            // Sleep for the random amount of time
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

            publishProgress(i);

            i++;
        }

        // Return a String result
        return "Awake at last after sleeping for " + n + " seconds!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mTextView.get().setText(values[0] + " s");
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}
