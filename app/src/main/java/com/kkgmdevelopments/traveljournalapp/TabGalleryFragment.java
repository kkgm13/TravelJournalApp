package com.kkgmdevelopments.traveljournalapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.kkgmdevelopments.traveljournalapp.holiday.Holiday;
import com.kkgmdevelopments.traveljournalapp.images.Photo;
import com.kkgmdevelopments.traveljournalapp.images.PhotoDetailedActivity;
import com.kkgmdevelopments.traveljournalapp.placeimage.PlaceImage;
import com.kkgmdevelopments.traveljournalapp.placeimage.PlaceImageViewModel;
import com.kkgmdevelopments.traveljournalapp.places.VisitedPlace;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  Gallery Fragment
 *  Fragment for relevant images for the associated holiday and visited place of the holidays
 */
public class TabGalleryFragment extends Fragment {

    private PlaceImageViewModel placeImageViewModel;
    private Holiday holiday;
    private ImageGalleryAdapter adapter;
    private VisitedPlace vp;
//    private Photo[] galleryImg;
    private List<PlaceImage> placeImageList;


    public TabGalleryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabGalleryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabGalleryFragment newInstance(String param1, String param2) {
        TabGalleryFragment fragment = new TabGalleryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

//    public static TabGalleryFragment newInstance(Holiday holiday) {
//        TabGalleryFragment fragment = new TabGalleryFragment();
//        Bundle args = new Bundle();
//        args.putSerializable("ARG_PARAM1", holiday );
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        placeImageList = new ArrayList<>();
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_tab_gallery, container, false);
        placeImageViewModel = ViewModelProviders.of(this).get(PlaceImageViewModel.class);
        // Create a Recycler View that uses a Grid Layout manager that will propose the amount of images
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        RecyclerView recyclerView = v.findViewById(R.id.gallery_imgs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        // In the onCreateView of your Gallery Fragment, comment out the code that displays
        // the fixed set of images.

        adapter = new ImageGalleryAdapter(getContext(), placeImageList);

//        adapter = new HorizontalAdapter(new ArrayList<Bitmap>(), getContext());

        // New Version??
//        final ImageGalleryAdapter adapter = new ImageGalleryAdapter(
//                getContext()
//                // ????
//        );

        // Add some code to initialise a placePictureViewModel and use it to observe
        // placePictureViewModel.getPlacePictures().
        placeImageViewModel.getAllImages().observe(getActivity(), new Observer<List<PlaceImage>>() {
            @Override
            public void onChanged(List<PlaceImage> placeImages) {
//                ArrayList<Photo> images =
                for(PlaceImage img : placeImages){
                    placeImageList.add(img);
                }
                adapter.notifyDataSetChanged();
            }
        });
        // Then call placePictureViewModel.getPicturesAllPlaces().
        placeImageViewModel.getPlaceAllImages();
        // Utilize the Image Gallery Adapter
        recyclerView.setAdapter(adapter);

        return v;
    }

    /**
     * Image Gallery Adapter
     *  The Adapter that manages the Image Gallery system
     */
    private class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.ImageViewHolder>  {
//        private Photo[] mPhotos;
        private Context mContext;
        private List<PlaceImage> photoList;

        public ImageGalleryAdapter(Context context, List<PlaceImage> photos) {
            mContext = context;
            photoList = photos;
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
            Photo photo = photoList.get(position).getImage();
            ImageView imageView = holder.mPhotoImageView;
            Glide.with(mContext)
                    .load(photo.getURL())
                    .placeholder(R.drawable.quantum_ic_clear_grey600_24)
                    .into(imageView);
        }

        @Override
        public int getItemCount() {
            return (photoList.size());
        }

        public void setPhotos(List<PlaceImage> photos){
            photoList = photos;
            notifyDataSetChanged();
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
                    PlaceImage photo = photoList.get(position);
                    Intent intent = new Intent(mContext, PhotoDetailedActivity.class);
                    intent.putExtra(PhotoDetailedActivity.EXTRA_SPACE_PHOTO, photo.getImage());
                    startActivity(intent);
                }
            }
        }
    }
}
