package com.example.ujjwalsmahapatra.pallefirstprojectusingdynamicfragments;

import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YouTubePlay extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    String mParam1;
    FragmentThree ft=new FragmentThree();

    public void playVideo(String mParam1)

    {
        this.mParam1=mParam1;
        ft.youTubePlayerView.initialize("AIzaSyC7KCbWZX7WDtKD2QF1g9Xb-ukcgS9i9b8", this);
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        youTubePlayer.loadVideo(mParam1);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(ft.getActivity(),"Some error has been occured",Toast.LENGTH_LONG).show();
    }
}
