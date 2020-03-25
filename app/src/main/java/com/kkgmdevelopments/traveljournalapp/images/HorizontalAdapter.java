package com.kkgmdevelopments.traveljournalapp.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.kkgmdevelopments.traveljournalapp.R;
import java.util.List;

/**
 * Horizonal Adapter
 *  Horizontal List used for the Visited Place Form Image List
 */
public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {
    private List<Bitmap> horizontalList;        // List of Bitmap Image Objects
    private Context context;                    // Context

    /**
     * Context
     * @param horizontalList
     * @param context
     */
    public HorizontalAdapter(List<Bitmap> horizontalList, Context context){
        this.horizontalList = horizontalList;
        this.context = context;
    }

    /**
     * Add a Bitmap image to List
     * @param bitmap
     */
    public void addBitmap(Bitmap bitmap){
        horizontalList.add(bitmap);
    }

    /**
     * View Holder Creater
     *
     * @param parent
     * @param viewType
     * @return New ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     * Bind the Information to the UI
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        holder.imageView.setImageBitmap(horizontalList.get(position));
//        holder.imageView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                String list = "Clicked Image "+ position;
//                Toast.makeText(context,list,Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    /**
     * Get List Size
     * @return Size List
     */
    @Override
    public int getItemCount() {
        return horizontalList.size();
    }

    /**
     * View Holder
     *
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;    // Image View Object

        /**
         * Constructor
         * @param v View Object
         */
        public ViewHolder(View v){
            super(v);
            imageView = v.findViewById(R.id.gallery_img);
        }
    }
}
