package com.example.ujjwalsmahapatra.firstprojectpalleuniv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Thr3dActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    YouTubePlayerView youTubePlayerView;
    String vid_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thr3d);
        //initialize
        youTubePlayerView=(YouTubePlayerView)findViewById(R.id.youtubeview1);

        youTubePlayerView.initialize("AIzaSyCYUuv3qgw9bofq2oIlVrYbF26yapSqYtA",this);

        //get video id
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        vid_id=bundle.getString("vid_id");
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(vid_id);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(Thr3dActivity.this,"We faced some error in loading",Toast.LENGTH_LONG).show();
    }
}
