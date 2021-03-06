package google.login.antonio.fcmtestapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private LinearLayout ll_main;
    private String url;
    private WebView wv_main;
    public static final String TAG = "MainActivity";
    private int currentApiVersion;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentApiVersion = android.os.Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // This work only for android 4.4+
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT)
        {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                    {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility)
                        {
                            if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                            {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }
        wv_main = (WebView) findViewById(R.id.wv_main);
        ll_main = (LinearLayout) findViewById(R.id.ll_menu);
        ll_main.setOnTouchListener(this);
        ll_main.setOnTouchListener(null);
        Intent intent = getIntent();
        final Bundle bd = intent.getExtras();
        if(bd != null)
        {
            String image = (String) bd.get("image");
            String title = (String) bd.get("title");
            Log.i(TAG, title);
            try {
                WebSettings webSettings = wv_main.getSettings();
                webSettings.setJavaScriptEnabled(true);
                url = (String) bd.get("url");
                wv_main.loadUrl(url);
            }catch (Exception e){
                Log.i(TAG, e.getMessage().toString());
            }
        }

    }

    @SuppressLint("NewApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
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
                case (MotionEvent.ACTION_DOWN):
                        Log.i(TAG,"Action Down");

                    return true;
                case (MotionEvent.ACTION_MOVE):
                    Log.i(TAG,"Action Move");

                    return true;
                case (MotionEvent.ACTION_UP):
                    Log.i(TAG,"Action up");

                    return true;
                case (MotionEvent.ACTION_CANCEL):
                    Log.i(TAG,"Action cancel");

                    return true;
                case (MotionEvent.ACTION_OUTSIDE):
                    Log.i(TAG,"Action outside");
                    return true;
                default:
                    Log.i(TAG,"Action default");
                    return true;
            }
    }


}
