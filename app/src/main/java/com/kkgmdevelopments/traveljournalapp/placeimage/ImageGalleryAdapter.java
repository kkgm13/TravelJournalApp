package com.kkgmdevelopments.traveljournalapp.placeimage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
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
public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.ImageViewHolder> {
    private Context mContext;               // Context
    private List<PlaceImage> photoList;     // List of PlaceImage Relationship objects
    public static final String EXTRA_PHOTO = "Image";       // Intent Passer

    /**
     * Constructor
     *
     * @param context Context
     * @param photos  List of PlaceImage Objects
     */
    public ImageGalleryAdapter(Context context, List<PlaceImage> photos) {
        mContext = context;
        photoList = photos;
    }

    /**
     * Create the View Holder
     *
     * @param parent   View Group
     * @param viewType Type of View
     * @return Image View Holder Object Instance
     */
    @Override
    public ImageGalleryAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the layout
        View photoView = inflater.inflate(R.layout.image_item, parent, false);
        ImageGalleryAdapter.ImageViewHolder viewHolder = new ImageGalleryAdapter.ImageViewHolder(photoView);
        return viewHolder;
    }

    /**
     * Bind information to the URL
     *
     * @param holder   ImageViewHolder Class
     * @param position Gallery Position
     */
    @Override
    public void onBindViewHolder(ImageGalleryAdapter.ImageViewHolder holder, int position) {
        Photo photo = photoList.get(position).getImage();
        ImageView imageView = holder.mPhotoImageView;
        Glide.with(mContext)
                .load(photo.getURL())
                .placeholder(R.drawable.quantum_ic_clear_grey600_24)
                .into(imageView);
    }

    /**
     * Get Size of the Gallery List
     *
     * @return Gallery Size
     */
    @Override
    public int getItemCount() {
        return (photoList.size());
    }

    /**
     * Set the Photo
     *
     * @param photos Photo List
     */
    public void setPhotos(List<PlaceImage> photos) {
        photoList = photos;
        notifyDataSetChanged();
    }

    /**
     * Image Gallery View Holder
     * This utilizes the RecyclerView and the View Object based on the OnClickListener
     */
    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mPhotoImageView;   // UI Image View Object

        /**
         * Constructor
         *
         * @param itemView View Object
         */
        public ImageViewHolder(View itemView) {
            super(itemView);
            mPhotoImageView = itemView.findViewById(R.id.gallery_img);
            itemView.setOnClickListener(this);
        }

        /**
         * View Holder Selected based on position
         *
         * @param view View Object
         * @TODO: Understand the issue with the Intent not picking up PlaceImage Object
         */
        @Override
        public void onClick(View view) {
//            int position = getAdapterPosition();
//            if (position != RecyclerView.NO_POSITION) {
//                PlaceImage placeImage = photoList.get(position);
//                Intent intent = new Intent(mContext, PhotoDetailedActivity.class);
//                intent.putExtra(EXTRA_PHOTO, placeImage); // Unknown reason why intent can't put extra as an intent
//                mContext.startActivity(intent);
//            }
            Toast.makeText(mContext, "In Development", Toast.LENGTH_SHORT).show();
        }
    }
}