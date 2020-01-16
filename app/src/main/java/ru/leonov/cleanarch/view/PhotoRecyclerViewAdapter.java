package ru.leonov.cleanarch.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.leonov.cleanarch.databinding.PhotoRecyclerViewLayoutBinding;
import ru.leonov.cleanarch.model.entities.PhotoContainer;

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


    //    class ViewHolder extends RecyclerView.ViewHolder {
//        PhotoRecyclerViewLayoutBinding binding;
//
//        ViewHolder(View itemView) {
//            super(itemView);
//            binding = DataBindingUtil.bind(itemView);
//        }
//    }
    class ViewHolder extends RecyclerView.ViewHolder {
        PhotoRecyclerViewLayoutBinding binding;

        ViewHolder(PhotoRecyclerViewLayoutBinding binding) {
            super(binding.getRoot());
            binding.executePendingBindings();
        }

        public void bind(PhotoContainer container) {
            binding.setPhotoContainer(container);
            binding.executePendingBindings();
        }
    }
}