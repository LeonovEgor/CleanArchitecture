package ru.leonov.cleanarch.view.ui.PhotoList;

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

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ru.leonov.cleanarch.CleanArch;
import ru.leonov.cleanarch.R;
import ru.leonov.cleanarch.databinding.FragmentPhotoListBinding;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.model.utils.executor.MainThreadExecutor;
import ru.leonov.cleanarch.model.utils.logger.ILogger;
import ru.leonov.cleanarch.model.utils.logger.MyLogger;
import ru.leonov.cleanarch.presenter.PhotosPresenter;
import ru.leonov.cleanarch.view.ui.PhotoViewModel;

public class PhotoListFragment extends Fragment {
    private final int COLUMN_NUMBERS = 2;

    private PhotosPresenter presenter;
    private PhotoViewModel viewModel;
    private ILogger logger;

    private RecyclerView recyclerView;
    private MaterialButton btnSearch;
    private TextInputEditText etSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        PhotoViewModelFactory factory = new PhotoViewModelFactory();
        viewModel = ViewModelProviders
                .of(this, factory)
                .get(PhotoViewModel.class);

        initLogger();
        FragmentPhotoListBinding binding = binding(inflater, container);
        initView(binding.getRoot());
        PhotoAdapter adapter = initPagingRecycler();
        runPagedListThreadExecutor(adapter);
        setupSearchButtonClick();
        presenter = new PhotosPresenter(CleanArch.getInstance().getNavigator());

//        view.findViewById(R.id.bottom).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                presenter.onBottomButtonClick();
//            }
//        });

        return binding.getRoot();
    }

    private FragmentPhotoListBinding binding(LayoutInflater inflater, ViewGroup container) {

        FragmentPhotoListBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_photo_list, container, false);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding;
    }

    private void initLogger() {
        logger = new MyLogger();
    }

    private void initView(View view) {

        recyclerView = view.findViewById(R.id.rv_photos);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), COLUMN_NUMBERS));

        etSearch = view.findViewById(R.id.tiSearch);
        btnSearch = view.findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                viewModel.setSearchString(Objects.requireNonNull(etSearch.getText()).toString());
            }
        });
    }

    private PhotoAdapter initPagingRecycler() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), COLUMN_NUMBERS);
        PhotoAdapter adapter = new PhotoAdapter(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return adapter;
    }

    private void runPagedListThreadExecutor(final PhotoAdapter adapter) {
        final Executor fetchExecutor = Executors.newSingleThreadExecutor();
        final PagedList.Builder<Integer, PhotoContainer> pagedListBuilder = createPagedList(fetchExecutor);

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
