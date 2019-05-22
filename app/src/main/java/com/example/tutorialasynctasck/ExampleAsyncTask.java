package com.example.tutorialasynctasck;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class ExampleAsyncTask extends AsyncTask<Integer, Integer, String> {
    private WeakReference<MainActivity> activityWeakReference;
    ProgressBar progressBar;

    // CONSTRUTOR
    ExampleAsyncTask(MainActivity activity, ProgressBar progressBar) {
        activityWeakReference = new WeakReference<MainActivity>(activity);
        this.progressBar = progressBar;
    }// Fim do CONSTRUTOR

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        MainActivity activity = activityWeakReference.get();

        if (activity == null || activity.isFinishing()) {
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
    }// Fim onPreExecute

    @Override
    protected String doInBackground(Integer... integers) {
        for (int i = 0; i < integers[0]; i++) {
            publishProgress((i * 100) / integers[0]);
            if (isCancelled()) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Finished!";
    }// Fim doInBackground

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        MainActivity activity = activityWeakReference.get();

        if (activity == null || activity.isFinishing()) {
            return;
        }
        progressBar.setProgress(values[0]);
    }// Fim onProgressUpdate

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        MainActivity activity = activityWeakReference.get();

        if (activity == null || activity.isFinishing()) {
            return;
        }
        Toast.makeText(activity, s, Toast.LENGTH_SHORT).show();
        progressBar.setProgress(0);
        progressBar.setVisibility(View.INVISIBLE);
    }// Fim onPostExecute

}// Fim da Classe ExampleAsyncTask

