package ru.leonov.cleanarch.view.ui.PhotoDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ru.leonov.cleanarch.R;
import ru.leonov.cleanarch.databinding.FragmentDetailsBinding;
import ru.leonov.cleanarch.databinding.FragmentPhotoListBinding;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.view.ui.PhotoDetailsViewModel;

public class PhotoDetailsFragment extends Fragment {

    //private PhotosPresenter presenter;
    private PhotoDetailsViewModel viewModel;
    private FragmentDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        DetailsViewModelFactory factory = new DetailsViewModelFactory();
        viewModel = ViewModelProviders
                .of(this, factory)
                .get(PhotoDetailsViewModel.class);
        viewModel.onStart();
        binding = binding(inflater, container);
        //initView(binding.getRoot());
        initDataObserver();

        return binding.getRoot();
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
                binding.setPhotoContainer(null);
            }
        });
    }

}
