package com.example.htgh;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.htgh.common.ApiService;
import com.example.htgh.common.Variables;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        VideoView video=findViewById(R.id.video);
//        Uri videoUri = Uri.parse(ApiService.BaseUrl + "/video/1.mp4");
        video.setVideoPath(ApiService.BaseUrl + "/video/1.mp4");
        video.requestFocus();
        video.setMediaController(new MediaController(this));
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i("tag", "--------------视频准备完毕,可以进行播放.......");
            }
        });

        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i("tag","视频播放完成");
            }
        });

        video.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.i("tag","视频播放失败");
                return false;
            }
        });
        video.start();

    }
}
