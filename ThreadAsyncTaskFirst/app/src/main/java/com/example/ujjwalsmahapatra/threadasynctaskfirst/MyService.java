package com.example.ujjwalsmahapatra.threadasynctaskfirst;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public class MyTask extends AsyncTask<String,Integer,Boolean>{
        @Override
        protected Boolean doInBackground(String... strings) {
            Log.d("See:",Thread.currentThread().getName());
            publishProgress(10);
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Toast.makeText(MyService.this,"ON-PROGRESS: "+values[0]+Thread.currentThread().getName(),Toast.LENGTH_SHORT).show();
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            Toast.makeText(MyService.this,"ON-POST : "+aBoolean+Thread.currentThread().getName(),Toast.LENGTH_SHORT).show();
            super.onPostExecute(aBoolean);
        }
    }
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MyTask m=new MyTask();
        m.execute("palle");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
