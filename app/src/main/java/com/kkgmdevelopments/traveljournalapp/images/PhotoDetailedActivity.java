package com.kkgmdevelopments.traveljournalapp.images;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.palette.graphics.Palette;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.kkgmdevelopments.traveljournalapp.TabGalleryFragment;
import com.kkgmdevelopments.traveljournalapp.placeimage.ImageGalleryAdapter;
import com.kkgmdevelopments.traveljournalapp.placeimage.PlaceImage;
import com.kkgmdevelopments.traveljournalapp.placeimage.PlaceImageViewModel;

/**
 * Photo Detailed Activity
 *  This just presents the image in full width as an activity
 */
public class PhotoDetailedActivity extends AppCompatActivity {
    private ImageView mImageView;           // ImageView Object
    private Button mDelBtn;                 // Delete Button
    private PlaceImageViewModel placeImageViewModel;    // PlaceImageViewModel Object

    /**
     * Create the Activity View
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);

        //Set View Model
        placeImageViewModel = ViewModelProviders.of(this).get(PlaceImageViewModel.class);

        // Set UI elements
        mImageView = findViewById(R.id.image);
        mDelBtn = findViewById(R.id.btnDelete);

        // PlaceImage
        final PlaceImage photo = (PlaceImage) getIntent().getSerializableExtra(ImageGalleryAdapter.EXTRA_PHOTO);
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
                        mImageView.setImageBitmap(resource);
                        return false;
                    }

                    /**
                     * Set the background Palette
                     * @param palette
                     */
                    public void onPalette(Palette palette){
                        if(null != palette) {
                            ViewGroup parent = (ViewGroup) mImageView.getParent().getParent();
                            parent.setBackgroundColor(Color.GRAY);
                        }
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(mImageView);

        // Set the Button
        mDelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete the Image
                placeImageViewModel.deleteImage(photo);
            }
        });
    }
}
