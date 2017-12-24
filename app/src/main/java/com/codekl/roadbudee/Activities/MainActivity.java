package com.codekl.roadbudee.Activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v13.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.codekl.roadbudee.R;
import com.codekl.roadbudee.Service.ObservableBoolean;
import com.codekl.roadbudee.Service.SMSMonitorService;

public class MainActivity extends AppCompatActivity  {

    public final ObservableBoolean appPinLock = new ObservableBoolean();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enableLockState();




        requestSilentNotificationPermission();
        requestSmsPermission();
        createSmsMonitor();
        makeNotificationSilent();

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


    private void requestSmsPermission() {

        String permission = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if ( grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);

        }
    }

    private void createSmsMonitor(){
        Intent SMSMonitorIntent = new Intent(this, SMSMonitorService.class);
        startService(SMSMonitorIntent);
    }

    private void makeNotificationSilent(){NotificationManager n = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if(n.isNotificationPolicyAccessGranted()) {
            AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }else{
            // Ask the user to grant access
            Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                        //startActivityForResult(intent);
            startActivity(intent);

        }
    }

    private void requestSilentNotificationPermission() {
        String permission = Manifest.permission.ACCESS_NOTIFICATION_POLICY;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);

        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void enableLockState() {
        if (!isLockState()) {
            startLockTask();
        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void disableLockState() {
        if (isLockState()) {
            try {
                stopLockTask();
            }catch (SecurityException e){
                // Log.d(TAG, "securityException: " + e.getLocalizedMessage());
            }

        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean isLockState() {
        //boolean isLocked = false;
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        try {

            if (am.getLockTaskModeState() == ActivityManager.LOCK_TASK_MODE_NONE) {
                //Log.d(TAG, "Lock task mode is not active.");
            } else {
                // Log.d(TAG, "Lock task mode is active.");
                this.appPinLock.set(true);
            }
        } catch (Exception e) {
            //Log.d(TAG, "exception: ",e);
        }
        return this.appPinLock.get();
    }


    /*
     * This function handles the button click listeners
     * @param v : Current view
     */
    public void onButtonClick(View v) {

        if(isLockState()) {
            Toast.makeText(getApplicationContext(), "You can now start your car", Toast.LENGTH_SHORT).show();
            switch (v.getId()) {
                //Go to enter your pincode page
                case R.id.activateBtn:
                    Intent enterYourPinView = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(enterYourPinView);
                    break;

                default:
                    break;
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Accept pin app to start your car", Toast.LENGTH_SHORT).show();
            enableLockState();
        }
    }


}
