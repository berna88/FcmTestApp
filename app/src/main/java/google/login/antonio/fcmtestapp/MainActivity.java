package google.login.antonio.fcmtestapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private ImageView iv_image;
    private String url;
    public static final String TAG = "MainActivity";
    private int currentApiVersion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentApiVersion = android.os.Build.VERSION.SDK_INT;
        iv_image = (ImageView) findViewById(R.id.iv_image);
        iv_image.setOnTouchListener(this);
        Intent intent = getIntent();
        final Bundle bd = intent.getExtras();
        if(bd != null)
        {
            String image = (String) bd.get("image");
            iv_image.setVisibility(View.VISIBLE);
            String title = (String) bd.get("title");
            Log.i(TAG, title);
            message(title);
            Glide.with(getApplicationContext())
                    .load(image)
                    .into(iv_image);
            iv_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse((String) bd.get("url"));
                    Intent i = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(i);
                }
            });
        }

    }
    public void message(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus)
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);

        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action){
            
        }

        return false;
    }
}
