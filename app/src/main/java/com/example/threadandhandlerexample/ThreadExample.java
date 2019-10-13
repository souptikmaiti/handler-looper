package com.example.threadandhandlerexample;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.lang.ref.WeakReference;


public class ThreadExample {
    private volatile boolean stopFlag;
    WeakReference<MainActivity> weakReferenceActivity;
    private static final String TAG = "ThreadExample";

    //Handler mainHandler = new Handler();    // no looper passed in arguments. so handler created on current thread i.e main thread
    public ThreadExample(boolean stopFlag,MainActivity activity){
        this.stopFlag = stopFlag;
        weakReferenceActivity = new WeakReference<MainActivity>(activity);
    }

    public void startBackgroundThread(){
        stopFlag=false;
        MainActivity activity = weakReferenceActivity.get();
        ExampleRunnable exampleRunnable = new ExampleRunnable(activity,10);
        new Thread(exampleRunnable).start();
    }
    public void stopBackgroundThread(){
        stopFlag = true;
        Log.d(TAG, "stopBackgroundThread: "+ stopFlag);
    }

    class ExampleRunnable implements Runnable{
        MainActivity activity;
        int seconds;

        ExampleRunnable(MainActivity act,int seconds){
            this.activity = act;
            this.seconds = seconds;
        }
        @Override
        public void run() {

            for(int i=0;i<seconds;i++){
                if(stopFlag)
                    return;
                Log.d(TAG, "run: on background thread "+i);
                if(i==5) {
                    Handler threadHandler = new android.os.Handler(Looper.getMainLooper());
                    threadHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            activity.btnStart.setText("50% Completed");
                        }
                    });
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
