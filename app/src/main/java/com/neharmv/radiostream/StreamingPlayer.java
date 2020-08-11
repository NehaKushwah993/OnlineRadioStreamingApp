package com.neharmv.radiostream;

import android.content.Context;
import android.net.Uri;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.util.Map;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.utils.Lg;

/**
 * Created by nehakushwah on 28/02/18.
 */

public class StreamingPlayer {

    private Uri mUri;
    private SurfaceHolder mSurfaceHolder = null;
    private MediaPlayer mMediaPlayer = null;
    private int mVideoChroma = MediaPlayer.VIDEOCHROMA_RGBA;
    private Context mContext;
    private Map<String, String> mHeaders;
    private int mBufSize;


    public StreamingPlayer(Context context, Uri uri) {
        this.mContext = context;
        this.mUri = uri;
        mMediaPlayer = new MediaPlayer(context, false);
        mMediaPlayer.setOnPreparedListener(mPreparedListener);
        try {
            mMediaPlayer.setDataSource(mContext, mUri, mHeaders);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMediaPlayer.setDisplay(mSurfaceHolder);
        mMediaPlayer.setBufferSize(mBufSize);
        mMediaPlayer.setVideoChroma(mVideoChroma == MediaPlayer.VIDEOCHROMA_RGB565 ? MediaPlayer.VIDEOCHROMA_RGB565 : MediaPlayer.VIDEOCHROMA_RGBA);
        mMediaPlayer.setScreenOnWhilePlaying(true);
        mMediaPlayer.prepareAsync();
    }

    public void play() {
        mMediaPlayer.start();
    }

    public void pause() {
        mMediaPlayer.pause();
    }

    MediaPlayer.OnPreparedListener mPreparedListener = new MediaPlayer.OnPreparedListener() {
        public void onPrepared(MediaPlayer mp) {
            Lg.d("onPrepared");
            mMediaPlayer.start();

        }
    };


}
