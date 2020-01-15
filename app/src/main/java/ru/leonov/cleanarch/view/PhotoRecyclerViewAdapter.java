package ru.leonov.cleanarch.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.leonov.cleanarch.R;
import ru.leonov.cleanarch.databinding.PhotoRecyclerViewLayoutBinding;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.model.network.LoadPhotoHelper;

public class PhotoRecyclerViewAdapter  extends RecyclerView.Adapter<PhotoRecyclerViewAdapter.ViewHolder> {

    private List<PhotoContainer> list;

    PhotoRecyclerViewAdapter(Context context, List<PhotoContainer> list) {
        this.list = list;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PhotoRecyclerViewLayoutBinding binding =
                PhotoRecyclerViewLayoutBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PhotoContainer container = getItem(position);
        holder.binding.setPhotoContainer(container);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        PhotoRecyclerViewLayoutBinding binding;

        ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    // convenience method for getting data at click position
    private PhotoContainer getItem(int id) {
        return list.get(id);
    }
}
