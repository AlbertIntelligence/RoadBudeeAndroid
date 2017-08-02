package com.codekl.roadbudee;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set video background
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.citytopview));
        videoView.setMediaController(null);
        videoView.start();

        //Video always playing
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

    /*
     * This function handles the button click listeners
     * @param v : Current view
     */
    public void onButtonClick(View v) {
        switch (v.getId()) {
            //Go to enter your pincode page
            case R.id.activateBtn:
                Intent enterYourPinView = new Intent(MainActivity.this, EnterYourPin.class);
                startActivity(enterYourPinView);
                break;

            default:
                break;
        }
    }

    /*
     * This function handles the texts click listeners
     * @param v : Current view
     */
    public void onClick(View v) {
        switch (v.getId()) {
            //redirect to roadbudee online order webpage
            case R.id.donthaveone:
                Intent orderYourRoadBudeeLink = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.roadbudee.com/shop/appareil-roadbudee/"));
                startActivity(orderYourRoadBudeeLink);
                break;

            default:
                break;
        }
    }
}
