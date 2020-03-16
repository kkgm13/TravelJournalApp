package com.kkgmdevelopments.traveljournalapp.images;

import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.kkgmdevelopments.traveljournalapp.R;

/**
 * This just presents the image as an activity
 */
public class SpacePhotoActivity extends AppCompatActivity {

    public static final String EXTRA_SPACE_PHOTO = "SpacePhotoActivity.SPACE_PHOTO";
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_photo);
//        getSupportActionBar().setTitle("");

        mImageView = findViewById(R.id.image);
        SpacePhoto photo = getIntent().getParcelableExtra(EXTRA_SPACE_PHOTO);

        Glide.with(this)
                .load(photo.getURL())
                .error(R.drawable.common_google_signin_btn_icon_dark)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        onPalette(Palette.from(resource));
                        ViewGroup parent = (ViewGroup) mImageView.getParent().getParent();
                        parent.setBackgroundColor(Color.GRAY);
                        mImageView.setImageDrawable(resource);
                        return false;
                    }

                    public void onPalette(Palette palette){
                        if(null != palette) {
                            ViewGroup parent = (ViewGroup) mImageView.getParent().getParent();
                            parent.setBackgroundColor(Color.GRAY);
                        }
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mImageView);
    }
}
