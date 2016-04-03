package snapdebate.juan.de.snapdebate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoAdapter extends BaseAdapter {
    int mGalleryItemBackground;
    private Context mContext;
    private ProgressDialog progressDialog;
    private MediaController mediaControls;
    private VideoView myVideoView;
    private int position = 0;

    private Uri[] mVideoIds = {
            Uri.parse("android.resource://snapdebate.juan.de.snapdebate/" + R.raw.sample_video),
            Uri.parse("android.resource://snapdebate.juan.de.snapdebate/" + R.raw.sample_video),
            Uri.parse("android.resource://snapdebate.juan.de.snapdebate/" + R.raw.sample_video),
    };

    public VideoAdapter(Context c) {
        mContext = c;
        myVideoView  = new VideoView(mContext);
        //TypedArray attr = mContext.obtainStyledAttributes(R.styleable.HelloGallery);
        //mGalleryItemBackground = attr.getResourceId(
        //        R.styleable.HelloGallery_android_galleryItemBackground, 0);
        //attr.recycle();

        //set the media controller buttons
        if (mediaControls == null) {
            mediaControls = new MediaController(mContext);
        }

        try {
            //set the media controller in the VideoView
            myVideoView.setMediaController(mediaControls);

            //set the uri of the video to be played
            //myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample_video));

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        //initialize the VideoView
        //myVideoView = (VideoView) findViewById(R.id.video_view);

//        // create a progress bar while the video file is loading
//        progressDialog = new ProgressDialog(mContext);
//        // set a title for the progress bar
//        progressDialog.setTitle("JavaCodeGeeks Android Video View Example");
//        // set a message for the progress bar
//        progressDialog.setMessage("Loading...");
//        //set the progress bar not cancelable on users' touch
//        progressDialog.setCancelable(false);
//        // show the progress bar
//        progressDialog.show();

        myVideoView.requestFocus();
        //we also set an setOnPreparedListener in order to know when the video file is ready for playback
        myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                // close the progress bar and play the video
                //progressDialog.dismiss();
                //if we have a position on savedInstanceState, the video playback should start from here
                myVideoView.seekTo(position);
                if (position == 0) {
                    myVideoView.start();
                } else {
                    //if we come from a resumed activity, video playback will be paused
                    myVideoView.pause();
                }
                mediaPlayer.setLooping(true);
            }
        });
    }

    public int getCount() {
        return mVideoIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //VideoView videoView = new VideoView(mContext);

        myVideoView.setMediaController(mediaControls);
        //myVideoView.setLayoutParams(new Gallery.LayoutParams(150, 100));
        myVideoView.setVideoURI(mVideoIds[position]);
        //imageView.setScaleType(VideoView.ScaleType.FIT_XY);
        //myVideoView.setBackgroundResource(mGalleryItemBackground);

        return myVideoView;
    }
}
