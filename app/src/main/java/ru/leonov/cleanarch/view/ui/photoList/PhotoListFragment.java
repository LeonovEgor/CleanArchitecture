package ru.leonov.cleanarch.view.ui.photoList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ru.leonov.cleanarch.R;
import ru.leonov.cleanarch.databinding.FragmentPhotoListBinding;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.model.utils.executor.MainThreadExecutor;
import ru.leonov.cleanarch.model.utils.logger.ILogger;
import ru.leonov.cleanarch.model.utils.logger.MyLogger;
import ru.leonov.cleanarch.model.utils.ui.UiHelper;

public class PhotoListFragment extends Fragment {
    private final float IMAGE_SIZE = 110.0f + 16.0f;

    private PhotoListViewModel viewModel;

    private TextInputEditText etSearch;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = ViewModelProviders
                .of(this, new PhotoViewModelFactory())
                .get(PhotoListViewModel.class);

        FragmentPhotoListBinding binding = binding(inflater, container);
        initView(binding.getRoot());
        PhotoAdapter adapter = initPagingRecycler();
        runPagedListThreadExecutor(adapter);
        setupSearchButtonClick();

        return binding.getRoot();
    }

    private FragmentPhotoListBinding binding(LayoutInflater inflater, ViewGroup container) {

        FragmentPhotoListBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_photo_list, container, false);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding;
    }

    private void initView(View view) {

        recyclerView = view.findViewById(R.id.rv_photos);

        etSearch = view.findViewById(R.id.tiSearch);
        MaterialButton btnSearch = view.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                viewModel.setSearchString(Objects.requireNonNull(etSearch.getText()).toString());
            }
        });
    }

    private PhotoAdapter initPagingRecycler() {

        int columnsCount = UiHelper.calcColumnsCount(Objects.requireNonNull(getContext()),
                IMAGE_SIZE);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),  columnsCount);
        PhotoAdapter adapter = new PhotoAdapter(viewModel);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return adapter;
    }

    private void runPagedListThreadExecutor(final PhotoAdapter adapter) {
        final Executor fetchExecutor = Executors.newSingleThreadExecutor();
        final PagedList.Builder<Integer, PhotoContainer> pagedListBuilder = createPagedList(fetchExecutor);
        viewModel.setResult(getString(R.string.loading));

        fetchExecutor.execute(new Runnable() {
            @Override
            public void run() {
                final PagedList<PhotoContainer> pagedList = pagedListBuilder.build();

                Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.submitList(pagedList);
                        viewModel.setResult("");
                    }
                });
            }
        });
    }

    private PagedList.Builder<Integer, PhotoContainer> createPagedList(final Executor fetchExecutor) {
        return new PagedList.Builder<>(viewModel.getDataSource(), config())
                .setFetchExecutor(fetchExecutor)
                .setNotifyExecutor(new MainThreadExecutor());
    }

    private PagedList.Config config() {
        return new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(5)
                .setInitialLoadSizeHint(30)
                .setPrefetchDistance(10)
                .build();
    }

    private void setupSearchButtonClick() {
        viewModel.getSearchString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String searchString) {
                updateSearch();
            }
        });
    }

    private void updateSearch() {
        PhotoAdapter adapter = initPagingRecycler();
        adapter.notifyDataSetChanged();
        runPagedListThreadExecutor(adapter);
    }
}