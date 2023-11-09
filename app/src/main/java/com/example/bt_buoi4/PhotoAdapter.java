package com.example.bt_buoi4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private List<Photo> photoList;
    private Context context;

    public PhotoAdapter(List<Photo> photoList, Context context) {
        this.photoList = photoList;
        this.context = context;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        Photo photo = photoList.get(position);

        holder.titleTextView.setText(photo.getTitle());

        // Load ảnh từ URL bằng Picasso
        Picasso.get().load(photo.getThumbnailUrl()).into(holder.thumbnailImageView);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView thumbnailImageView;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            thumbnailImageView = itemView.findViewById(R.id.thumbnailImageView);
        }
    }
}
