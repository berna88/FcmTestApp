package google.login.antonio.fcmtestapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private ImageView iv_image;
    private String url;
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_image = (ImageView) findViewById(R.id.iv_image);
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
}
