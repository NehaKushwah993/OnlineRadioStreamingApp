package com.neharmv.radiostream;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.AnimateGifMode;

public class MainActivity extends AppCompatActivity {
    AnimationDrawable anim;
    private StreamingPlayer streamingPlayer;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        updateTitleOfSong();
        startEqualiserAnimation();
        initPlayer();

        findViewById(R.id.play_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());

                if (view.isSelected()) {

                    findViewById(R.id.equaliser_pause).setVisibility(View.GONE);
                    findViewById(R.id.equaliser_play).setVisibility(View.VISIBLE);

                    if (streamingPlayer != null) {
                        streamingPlayer.play();
                    }
                } else {

                    findViewById(R.id.equaliser_pause).setVisibility(View.VISIBLE);
                    findViewById(R.id.equaliser_play).setVisibility(View.GONE);


                    if (streamingPlayer != null) {
                        streamingPlayer.pause();
                    }
                }

            }
        });

        findViewById(R.id.play_pause).setSelected(true);


    }

    private void initPlayer() {

        String url = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_6music_mf_p";
        Uri mUri = Uri.parse(url);
        streamingPlayer = new StreamingPlayer(MainActivity.this, mUri);

    }

    private void startEqualiserAnimation() {
        Ion.with((ImageView) findViewById(R.id.equaliser_play))
                .animateGif(AnimateGifMode.ANIMATE)
                .load("file:///android_asset/equaliser.gif");

    }

    private void updateTitleOfSong() {
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        //If you have script to get the Song title, then you can add it here :
        webView.loadUrl("file:///android_asset/index.htm");
        webView.setBackgroundColor(Color.TRANSPARENT);

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (anim != null && !anim.isRunning())
            anim.start();

        if (webView != null) {
            webView.requestFocus();
            webView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (anim != null && anim.isRunning())
            anim.stop();
    }
}
