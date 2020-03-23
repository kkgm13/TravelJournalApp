package com.kkgmdevelopments.traveljournalapp.images;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.palette.graphics.Palette;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.libraries.places.api.model.Place;
import com.kkgmdevelopments.traveljournalapp.R;
import com.kkgmdevelopments.traveljournalapp.placeimage.PlaceImage;
import com.kkgmdevelopments.traveljournalapp.placeimage.PlaceImageViewModel;

/**
 * This just presents the image as an activity
 */
public class PhotoDetailedActivity extends AppCompatActivity {

    public static final String EXTRA_SPACE_PHOTO = "SpacePhotoActivity.SPACE_PHOTO";
    private ImageView mImageView;
    private Button mDelBtn;
    private PlaceImageViewModel placeImageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);

        //Set View Model
        placeImageViewModel = ViewModelProviders.of(this).get(PlaceImageViewModel.class);

        // Set UI elements
        mImageView = findViewById(R.id.image);
        mDelBtn = findViewById(R.id.btnDelete);

        final PlaceImage photo = getIntent().getParcelableExtra(EXTRA_SPACE_PHOTO);
        Glide.with(this)
                .load(photo.getImage().getURL())
                .asBitmap()
                .error(R.drawable.common_google_signin_btn_icon_dark)
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        onPalette(Palette.from(resource).generate());
//                        ViewGroup parent = (ViewGroup) mImageView.getParent().getParent();
//                        parent.setBackgroundColor(Color.GRAY);
                        mImageView.setImageBitmap(resource);
                        return false;
                    }

                    public void onPalette(Palette palette){
                        if(null != palette) {
                            ViewGroup parent = (ViewGroup) mImageView.getParent().getParent();
                            parent.setBackgroundColor(Color.GRAY);
                        }
                    }

                })
//                .listener(new RequestListener<String, Bitmap>() {
//                    @Override
//                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
////                        onPalette(Palette.from(resource));
//                        ViewGroup parent = (ViewGroup) mImageView.getParent().getParent();
//                        parent.setBackgroundColor(Color.GRAY);
//                        mImageView.setImageDrawable(resource);
//                        return false;
//                    }
//
//                    public void onPalette(Palette palette){
//                        if(null != palette) {
//                            ViewGroup parent = (ViewGroup) mImageView.getParent().getParent();
//                            parent.setBackgroundColor(Color.GRAY);
//                        }
//                    }
//                })
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mImageView);
        mDelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete the Image
                placeImageViewModel.deleteImage(photo);
            }
        });
    }
}
