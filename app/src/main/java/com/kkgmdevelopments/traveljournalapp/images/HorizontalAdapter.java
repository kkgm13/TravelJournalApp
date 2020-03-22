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

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {
    private List<Bitmap> horizontalList;
    private Context context;

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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        holder.imageView.setImageBitmap(horizontalList.get(position));
        holder.imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String list = "Clicked Image "+ position;
                Toast.makeText(context,list,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView txtView;

        public ViewHolder(View v){
            super(v);
            imageView = v.findViewById(R.id.gallery_img);
        }
    }
}
