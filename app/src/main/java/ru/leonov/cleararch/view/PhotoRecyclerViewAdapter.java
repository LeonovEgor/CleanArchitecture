package ru.leonov.cleararch.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.leonov.cleararch.R;
import ru.leonov.cleararch.model.entities.PhotoContainer;
import ru.leonov.cleararch.model.network.LoadPhotoHelper;

public class PhotoRecyclerViewAdapter  extends RecyclerView.Adapter<PhotoRecyclerViewAdapter.ViewHolder> {

    private List<PhotoContainer> list;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    PhotoRecyclerViewAdapter(Context context, List<PhotoContainer> list) {
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.photo_recycler_view_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhotoContainer container = list.get(position);
        if (container == null) return;

        holder.tvName.setText(container.getName());
        holder.tvDescription.setText(container.getDescription());
        LoadPhotoHelper.getPhoto(container.getPhotoUrl(), holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvDescription;
        ImageView ivPhoto;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDescription = itemView.findViewById(R.id.tv_description);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    PhotoContainer getItem(int id) {
        return list.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
