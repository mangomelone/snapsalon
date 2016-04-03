package snapdebate.juan.de.snapdebate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class PlayVideoActivity extends Activity implements GestureDetection.SimpleGestureListener{

    VideoView videoView;
    PlayVideoActivity activity;
    MediaController mediaController;
    GestureDetection detector;
    int currentPosition = 0;
    int currentVolume;
    private Uri[] mVideoIds = {
            //Uri.parse("android.resource://snapdebate.juan.de.snapdebate/" + R.raw.sample_video),
            Uri.parse("android.resource://snapdebate.juan.de.snapdebate/" + R.raw.clarissa),
            Uri.parse("android.resource://snapdebate.juan.de.snapdebate/" + R.raw.der_aslan),
            Uri.parse("android.resource://snapdebate.juan.de.snapdebate/" + R.raw.erdbeerkaese),
            Uri.parse("android.resource://snapdebate.juan.de.snapdebate/" + R.raw.greta),
            Uri.parse("android.resource://snapdebate.juan.de.snapdebate/" + R.raw.himmler),
            Uri.parse("android.resource://snapdebate.juan.de.snapdebate/" + R.raw.jan),
            Uri.parse("android.resource://snapdebate.juan.de.snapdebate/" + R.raw.lobo),
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        // set the main layout of the activity
        setContentView(R.layout.activity_play_video);

        videoView = (VideoView) findViewById(R.id.video_view);
        detector = new GestureDetection(this, this);

        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        mediaController.hide();
        Uri uri = mVideoIds[currentPosition];
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
        videoView.setMediaController(null);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
                mediaPlayer.setScreenOnWhilePlaying(false);
            }
        });

//        Gallery gallery = (Gallery) findViewById(R.id.gallery);
//        gallery.setAdapter(new VideoAdapter(this));
//
//        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView parent, View v, int position, long id) {
//                Toast.makeText(PlayVideoActivity.this, "" + position, Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent me) {
        // Call onTouchEvent of SimpleGestureFilter class
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }

    @Override
    public void onSwipe(int direction) {

        String str = "";
        Uri uri;
        currentPosition++;
        if (currentPosition >= mVideoIds.length)
        {
            Intent intent = new Intent(PlayVideoActivity.this, FinaleActivity.class);
            //intent.putExtra("afterSwipe", true);
            startActivity(intent);
        }
        else
        {
            switch (direction) {

                case GestureDetection.SWIPE_LEFT:

                    uri = mVideoIds[currentPosition];
                    videoView.setVideoURI(uri);
                    videoView.requestFocus();
                    videoView.start();
                    str = "AGREE";
                    break;

                case GestureDetection.SWIPE_RIGHT:

                    uri = mVideoIds[currentPosition];
                    videoView.setVideoURI(uri);
                    videoView.requestFocus();
                    videoView.start();
                    str = "DISAGREE";
                    break;

                case GestureDetection.SWIPE_DOWN:


                case GestureDetection.SWIPE_UP:
        }

        }
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    //@Override
    //public void onSaveInstanceState(Bundle savedInstanceState) {
      //  super.onSaveInstanceState(savedInstanceState);
        //we use onSaveInstanceState in order to store the video playback position for orientation change
        //savedInstanceState.putInt("Position", myVideoView.getCurrentPosition());
        //myVideoView.pause();
    //}

    //@Override
    //public void onRestoreInstanceState(Bundle savedInstanceState) {
        //super.onRestoreInstanceState(savedInstanceState);
        //we use onRestoreInstanceState in order to play the video playback from the stored position
        //position = savedInstanceState.getInt("Position");
        //myVideoView.seekTo(position);
    //}

}
