package zwz.com.myLib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ShowPhotoActivity extends AppCompatActivity {
    public static void launch(AppCompatActivity activity, View transitionView, String url) {
        Intent intent = new Intent(activity, ShowPhotoActivity.class);
        intent.putExtra("url", url);
        // 这里指定了共享的视图元素
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, transitionView, "image");

        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);
        mImageView= (ImageView) findViewById(R.id.imageView);
        ViewCompat.setTransitionName(mImageView, "image");

        Intent intent  = getIntent();
        String url=intent.getStringExtra("url");
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(mImageView);
    }
}
