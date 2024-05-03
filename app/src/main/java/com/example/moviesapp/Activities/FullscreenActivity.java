package com.example.moviesapp.Activities;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.moviesapp.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerView;

public class FullscreenActivity extends AppCompatActivity {
  ExoPlayer exoPlayer;
  ImageView bt_fullscreen;
  boolean isFullScreen=false;
  boolean isLock = false;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen);

        PlayerView playerView = findViewById(R.id.player);
        ProgressBar  progressBar = findViewById(R.id.progress_bar);
        bt_fullscreen = findViewById(R.id.bt_fullscreen);
        ImageView bt_lockscreen = findViewById(R.id.exo_lock);
        bt_fullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFullScreen){
                    bt_fullscreen.setImageDrawable(
                            ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_fullscreen_exit));
                    // Kiểm tra xem thiết bị có đang ở chế độ ngang không
                    if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                        // Nếu đúng, thì xoay màn hình về chế độ dọc
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
                    } else {
                        // Nếu không, giữ nguyên chế độ ngang
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                    }
                } else {
                    bt_fullscreen.setImageDrawable(ContextCompat
                            .getDrawable(getApplicationContext(),R.drawable.ic_baseline_fullscreen));
                    // Xoay màn hình về chế độ dọc khi thoát chế độ toàn màn hình
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
                }
                isFullScreen = !isFullScreen;
            }
        });


        bt_lockscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLock){
                    bt_lockscreen.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_lock));
                }else {
                    bt_lockscreen.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_outline_lock_open));
                }
                isLock = !isLock;
                lockScreen(isLock);
            }

            private void lockScreen(boolean lock) {
                LinearLayout sec_mid = findViewById(R.id.sec_controlvid1);
                LinearLayout sec_bottom = findViewById(R.id.sec_controlvid2);
                if(lock){
                    sec_mid.setVisibility(View.INVISIBLE);
                    sec_bottom.setVisibility(View.INVISIBLE);
                }else {
                    sec_mid.setVisibility(View.VISIBLE);
                    sec_bottom.setVisibility(View.VISIBLE);
                }
            }
        });

        exoPlayer = new ExoPlayer.Builder(this)
                .setSeekBackIncrementMs(5000)
                .setSeekForwardIncrementMs(5000)
                .build();
        playerView.setPlayer(exoPlayer);
        playerView.setKeepScreenOn(true);

        exoPlayer.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int playbackState) {
                if(playbackState ==Player.STATE_BUFFERING){
                    progressBar.setVisibility(View.VISIBLE);
                } else if (playbackState==Player.STATE_READY) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("url");
        Uri videoUri = Uri.parse(url);
        MediaItem media = MediaItem.fromUri(videoUri);
        exoPlayer.setMediaItem(media);
        exoPlayer.prepare();
        exoPlayer.play();

    }
    @Override
    public void onBackPressed(){
       if(isLock) return;
       if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
           bt_fullscreen.performClick();
       }else super.onBackPressed();
    }
    @Override
    protected void onStop() {

        super.onStop();
        exoPlayer.stop();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();;
        exoPlayer.release();
    }
    @Override
    protected void onPause(){
        super.onPause();
        exoPlayer.pause();
    }
}
