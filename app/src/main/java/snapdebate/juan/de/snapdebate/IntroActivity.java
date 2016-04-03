package snapdebate.juan.de.snapdebate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class IntroActivity extends Activity {

    VideoView videoView;
    MediaController mediaController;
    Context context = IntroActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_intro);

        videoView = (VideoView) findViewById(R.id.video_viewIntro);

        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        mediaController.hide();
        Uri uri = Uri.parse("android.resource://snapdebate.juan.de.snapdebate/" + R.raw.intro);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
        new Thread() {
            public void run() {
                try{

                    sleep(8500);
                } catch (Exception e) {

                }
                Intent intent = new Intent(getBaseContext(), ExplainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
        videoView.setMediaController(null);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(false);
                mediaPlayer.setScreenOnWhilePlaying(false);

            }
        });


    }


    }
