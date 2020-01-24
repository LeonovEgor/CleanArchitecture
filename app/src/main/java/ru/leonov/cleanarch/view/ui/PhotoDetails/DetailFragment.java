package ru.leonov.cleanarch.view.ui.PhotoDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import ru.leonov.cleanarch.R;
import ru.leonov.cleanarch.databinding.FragmentDetailsBinding;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.view.ui.DetailViewModel;

public class DetailFragment extends Fragment {
    private static final String PHOTO_CONTAINER = "PHOTO_CONTAINER";

    private DetailViewModel viewModel;
    private FragmentDetailsBinding binding;

    public static Fragment newInstance(PhotoContainer photoContainer) {
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable(PHOTO_CONTAINER, photoContainer);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = ViewModelProviders
                .of(this)
                .get(DetailViewModel.class);

        viewModel.setResult(getBundleData());

        binding = binding(inflater, container);

        initDataObserver();

        return binding.getRoot();
    }

    private PhotoContainer getBundleData() {
        Bundle bundle=getArguments();
        return  (PhotoContainer) Objects.requireNonNull(bundle).getSerializable(PHOTO_CONTAINER);
    }

    private FragmentDetailsBinding binding(LayoutInflater inflater, ViewGroup container) {

        FragmentDetailsBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_details, container, false);

        binding.setPhotoContainer(null);
        binding.setLifecycleOwner(this);
        return binding;
    }

    private void initDataObserver() {
        viewModel.getResult().observe(this, new Observer<PhotoContainer>() {
            @Override
            public void onChanged(PhotoContainer photoContainer) {
                binding.setPhotoContainer(photoContainer);
            }
        });
    }
}