package snapdebate.juan.de.snapdebate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_start);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.mockup1long);

    }

    public void nextActivity(View view)
    {
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null && bundle.containsKey("afterSwipe"))
        {
            Intent i = new Intent(getBaseContext(), HighscoreActivity.class);
            startActivity(i);
        }
        else
        {
            Intent i = new Intent(getBaseContext(), IntroActivity.class);
            startActivity(i);
        }

    }

}
