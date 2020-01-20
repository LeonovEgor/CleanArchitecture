package ru.leonov.cleanarch.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import ru.leonov.cleanarch.R;
import ru.leonov.cleanarch.databinding.PhotoRecyclerViewLayoutBinding;
import ru.leonov.cleanarch.model.entities.PhotoContainer;

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