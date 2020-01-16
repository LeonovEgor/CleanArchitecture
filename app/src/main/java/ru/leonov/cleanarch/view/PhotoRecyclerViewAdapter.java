package ru.leonov.cleanarch.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.leonov.cleanarch.databinding.PhotoRecyclerViewLayoutBinding;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.model.network.LoadPhotoHelper;

public class PhotoRecyclerViewAdapter  extends RecyclerView.Adapter<PhotoRecyclerViewAdapter.ViewHolder> {

    private List<PhotoContainer> list = new ArrayList<>();

    public void setData(List<PhotoContainer> newData) {
        list.clear();
        list.addAll(newData);
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PhotoRecyclerViewLayoutBinding binding =
                PhotoRecyclerViewLayoutBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
        PhotoContainer container = getItem(position);
        holder.binding.setPhotoContainer(container);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private PhotoContainer getItem(int id) {
        return list.get(id);
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        LoadPhotoHelper.getPhoto(url, imageView);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        PhotoRecyclerViewLayoutBinding binding;

        ViewHolder(PhotoRecyclerViewLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(PhotoContainer container) {
            binding.setPhotoContainer(container);
            binding.executePendingBindings();
        }
    }
}