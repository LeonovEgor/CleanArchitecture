package ru.leonov.cleanarch.view.ui.PhotoList;

import androidx.recyclerview.widget.DiffUtil;

import ru.leonov.cleanarch.model.entities.PhotoContainer;

public class DiffUtilCallback extends DiffUtil.ItemCallback<PhotoContainer> {

    @Override
    public boolean areItemsTheSame(PhotoContainer oldItem, PhotoContainer newItem) {
            return oldItem.getPhotoUrl().equals(newItem.getPhotoUrl());
    }

    @Override
    public boolean areContentsTheSame(PhotoContainer oldItem, PhotoContainer newItem) {
            return oldItem.getPhotoUrl().equals(newItem.getPhotoUrl());
    }
}