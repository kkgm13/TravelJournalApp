package com.kkgmdevelopments.traveljournalapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kkgmdevelopments.traveljournalapp.images.SpacePhoto;
import com.kkgmdevelopments.traveljournalapp.images.SpacePhotoActivity;

/**
 * Holiday Gallery Fragment
 *  Fragment for relevant images for the associated holiday and visited place of the holidays
 */
public class TabGalleryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_tab_gallery, container, false);

        // Create a Recycler View that uses a Grid Layout manager that will propose the amount of images
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        RecyclerView recyclerView = v.findViewById(R.id.gallery_imgs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        // Utilize the Image Gallery Adapter
        ImageGalleryAdapter adapter = new ImageGalleryAdapter(getActivity(), SpacePhoto.getSpacePhotos());
        recyclerView.setAdapter(adapter);

        return v;
    }

    /**
     * Image Gallery Adapter
     *  The Adapter that manages the Image Gallery system
     */
    private class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.ImageViewHolder>  {

        private SpacePhoto[] mSpacePhotos;
        private Context mContext;

        public ImageGalleryAdapter(Context context, SpacePhoto[] spacePhotos) {
            mContext = context;
            mSpacePhotos = spacePhotos;
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
            SpacePhoto spacePhoto = mSpacePhotos[position];
            ImageView imageView = holder.mPhotoImageView;

            Glide.with(mContext)
                    .load(spacePhoto.getURL())
                    .placeholder(R.drawable.quantum_ic_clear_grey600_24)
                    .into(imageView);
        }

        @Override
        public int getItemCount() {
            return (mSpacePhotos.length);
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
                    SpacePhoto spacePhoto = mSpacePhotos[position];

                    Intent intent = new Intent(mContext, SpacePhotoActivity.class);
                    intent.putExtra(SpacePhotoActivity.EXTRA_SPACE_PHOTO, spacePhoto);
                    startActivity(intent);
                }
            }
        }
    }
}
