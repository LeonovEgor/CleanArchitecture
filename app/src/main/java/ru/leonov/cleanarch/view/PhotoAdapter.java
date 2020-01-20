package ru.leonov.cleanarch.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import ru.leonov.cleanarch.R;
import ru.leonov.cleanarch.databinding.PhotoRecyclerViewLayoutBinding;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.model.network.LoadPhotoHelper;

public class PhotoAdapter extends PagedListAdapter<PhotoContainer, PhotoAdapter.PhotoViewHolder> {

    private Context context;

    PhotoAdapter(Context context) {
        super(new DiffUtilCallback());

        this.context = context;
    }

    @NonNull
    @Override
    public PhotoAdapter.PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PhotoRecyclerViewLayoutBinding binding =
                PhotoRecyclerViewLayoutBinding.inflate(inflater, parent, false);
        return new PhotoAdapter.PhotoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.PhotoViewHolder holder, int
            position) {

            PhotoContainer container = getItem(position);

            if (container == null) {
                String loading = context.getString(R.string.loading);
                container = new PhotoContainer(loading, "", loading);
                holder.bind(container);
                holder.binding.setPhotoContainer(container);

            } else {
                holder.bind(getItem(position));
                holder.binding.setPhotoContainer(container);
            }
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        LoadPhotoHelper.getPhoto(url, imageView);
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {
        PhotoRecyclerViewLayoutBinding binding;

        PhotoViewHolder(PhotoRecyclerViewLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(PhotoContainer container) {
            binding.setPhotoContainer(container);
            binding.executePendingBindings();
        }
    }
}