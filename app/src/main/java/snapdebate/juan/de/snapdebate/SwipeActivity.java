package snapdebate.juan.de.snapdebate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.andtinder.model.CardModel;
import com.andtinder.model.Orientations;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;

public class SwipeActivity extends Activity {

    private VideoView myVideoView;
    private int position = 0;
    private ProgressDialog progressDialog;
    private MediaController mediaControls;

    private CardContainer mCardContainer;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        final Context context = this.getApplicationContext();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // set the main layout of the activity
        setContentView(R.layout.activity_swipe);
        mCardContainer = (CardContainer) findViewById(R.id.layoutview);
        mCardContainer.setOrientation(Orientations.Orientation.Disordered);
        Resources r = getResources();
        CardModel card = new CardModel("", "description", r.getDrawable(0));
        card.setOnCardDimissedListener(new CardModel.OnCardDimissedListener() {
            @Override
            public void onLike() {
                Log.d("Swipeable Card", "I liked it");
                Toast.makeText(context, "I liked it", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDislike() {
                Log.d("Swipeable Card", "I did not liked it");
                Toast.makeText(context, "I did not liked it", Toast.LENGTH_LONG).show();
            }
        });

        card.setOnClickListener(new CardModel.OnClickListener() {
            @Override
            public void OnClickListener() {
                Log.i("Swipeable Cards", "I am pressing the card");
                Toast.makeText(context, "I am pressing the card", Toast.LENGTH_LONG).show();
            }
        });
        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this);
        adapter.add(card);
        mCardContainer.setAdapter(adapter);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //we use onSaveInstanceState in order to store the video playback position for orientation change
        savedInstanceState.putInt("Position", myVideoView.getCurrentPosition());
        myVideoView.pause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //we use onRestoreInstanceState in order to play the video playback from the stored position
        position = savedInstanceState.getInt("Position");
        myVideoView.seekTo(position);
    }

    public void playVideo()
    {
        //set the media controller buttons
        if (mediaControls == null) {
            mediaControls = new MediaController(SwipeActivity.this);
        }

        //initialize the VideoView
        myVideoView = (VideoView) findViewById(R.id.video_view);

        // create a progress bar while the video file is loading
        progressDialog = new ProgressDialog(SwipeActivity.this);
        // set a title for the progress bar
        progressDialog.setTitle("JavaCodeGeeks Android Video View Example");
        // set a message for the progress bar
        progressDialog.setMessage("Loading...");
        //set the progress bar not cancelable on users' touch
        progressDialog.setCancelable(false);
        // show the progress bar
        progressDialog.show();
        try {
            //set the media controller in the VideoView
            myVideoView.setMediaController(mediaControls);

            //set the uri of the video to be played
            myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample_video));
            myVideoView.requestFocus();
            //we also set an setOnPreparedListener in order to know when the video file is ready for playback
            myVideoView.setOnPreparedListener(new OnPreparedListener() {

                public void onPrepared(MediaPlayer mediaPlayer) {
                    // close the progress bar and play the video
                    progressDialog.dismiss();
                    //if we have a position on savedInstanceState, the video playback should start from here
                    myVideoView.seekTo(position);
                    if (position == 0) {
                        myVideoView.start();
                    } else {
                        //if we come from a resumed activity, video playback will be paused
                        myVideoView.pause();
                    }
                }
            });

        }
        catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
    }

}
