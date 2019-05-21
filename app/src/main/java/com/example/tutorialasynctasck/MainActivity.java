/*
* https://codinginflow.com/tutorials/android/asynctask
*/
package com.example.tutorialasynctasck;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewHolder mViewHolder = new ViewHolder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.start = (Button) findViewById(R.id.btn_Start);
        this.mViewHolder.start.setOnClickListener(this);

        this.mViewHolder.cadencia = (ProgressBar) findViewById(R.id.progressBar);
        this.mViewHolder.cadencia.setMax(100);
        this.mViewHolder.cadencia.setProgress(50);


        //new MyTask().execute();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_Start) {
            if (this.mViewHolder.start.getText() != "start") {
                this.mViewHolder.start.setText("start");
                this.mViewHolder.cadencia.setProgress(0);



            } else if (this.mViewHolder.start.getText() != "stop") {
                this.mViewHolder.start.setText("stop");

                this.mViewHolder.cadencia.setProgress(50);


                ExampleAsyncTask task = new ExampleAsyncTask(this,this.mViewHolder.cadencia);
                task.execute(10);




            }


        }


    }


    private static class ViewHolder {
        Button start;
        ProgressBar cadencia;
    } // Fim ViewHolder

}


