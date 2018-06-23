package com.example.ujjwalsmahapatra.notificationseg1;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void clicked(View view) {
        //code for notification
        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//it is like fragment manager
        NotificationCompat.Builder b=new NotificationCompat.Builder(this);
        b.setSmallIcon(R.mipmap.ic_launcher);//displays small icon

        b.setTicker("offer letter");//displays ticker text
        b.setContentTitle("Hello,");//displays title
        b.setContentText("please come and collect the offer letter");//text
        b.setContentInfo("1 unread");//extra info
        //To diaplay large icon
        //BitmapDrawable bmp= (BitmapDrawable) getResources().getDrawable(R.drawable.titled);
        //b.setLargeIcon(bmp.getBitmap());

        // notification click:
        Intent in=new Intent(MainActivity.this,MainActivity.class);
        PendingIntent pi =PendingIntent.getActivity(this,0,in,0);
        b.setContentIntent(pi);

        //auto cancel the notification after the user has acknowledged:
        b.setAutoCancel(true);
        /********************************************/

        manager.notify(1,b.build());//now display the notification
    }
}
