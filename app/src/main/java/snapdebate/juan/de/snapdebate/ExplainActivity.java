package snapdebate.juan.de.snapdebate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class ExplainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_explain);

        ImageView imageView = (ImageView) findViewById(R.id.imageViewMockup3);
        imageView.setImageResource(R.drawable.mockup3);

    }

    public void nextActivity(View view)
    {
        Intent i = new Intent(getBaseContext(), PlayVideoActivity.class);
        startActivity(i);
    }

}
