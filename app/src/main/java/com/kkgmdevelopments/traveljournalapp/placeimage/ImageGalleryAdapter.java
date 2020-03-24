package com.kkgmdevelopments.traveljournalapp.placeimage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kkgmdevelopments.traveljournalapp.R;
import com.kkgmdevelopments.traveljournalapp.images.Photo;
import com.kkgmdevelopments.traveljournalapp.images.PhotoDetailedActivity;

import java.util.List;

/**
 * Image Gallery Adapter (Not in Use)
 *  The Adapter that manages the Image Gallery system
 */
public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.ImageViewHolder>  {
    private Photo[] mPhotos;
    private Context mContext;
    private List<Bitmap> photoList;

    public ImageGalleryAdapter(Context context, Photo[] photos) {
        mContext = context;
        mPhotos = photos;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the layout
        View photoView = inflater.inflate(R.layout.image_item, parent, false);
        ImageViewHolder viewHolder = new ImageViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Photo photo = mPhotos[position];
        ImageView imageView = holder.mPhotoImageView;
        Glide.with(mContext)
                .load(photo.getURL())
                .placeholder(R.drawable.quantum_ic_clear_grey600_24)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return (mPhotos.length);
    }

    /**
     * Image Gallery View Holder
     */
    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mPhotoImageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            mPhotoImageView = itemView.findViewById(R.id.gallery_img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                Photo photo = mPhotos[position];
                Intent intent = new Intent(mContext, PhotoDetailedActivity.class);
                intent.putExtra(PhotoDetailedActivity.EXTRA_SPACE_PHOTO, photo);
//                    intent.putExtra("titleName", )
                mContext.startActivity(intent);
            }
        }
    }
}
