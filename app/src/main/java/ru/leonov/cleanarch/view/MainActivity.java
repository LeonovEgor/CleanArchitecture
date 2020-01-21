package ru.leonov.cleanarch.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
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
import ru.leonov.cleanarch.databinding.ActivityMainBinding;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.model.utils.executor.MainThreadExecutor;
import ru.leonov.cleanarch.model.utils.logger.ILogger;
import ru.leonov.cleanarch.model.utils.logger.MyLogger;
import ru.leonov.cleanarch.viewmodel.PhotoViewModel;


public class MainActivity extends AppCompatActivity  {
    private final int COLUMN_NUMBERS = 2;

    RecyclerView recyclerView;
    MaterialButton btnSearch;
    TextInputEditText etSearch;

    //private MainPresenter presenter;

    //@Inject
    PhotoViewModel viewModel;

    private ILogger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PhotoViewModelFactory photoViewModelFactory = new PhotoViewModelFactory();
        viewModel = ViewModelProviders.of(this, photoViewModelFactory).get(PhotoViewModel.class);

        super.onCreate(savedInstanceState);

        initLogger();
        binding();
        initView();
        PhotoAdapter adapter = initPagingRecycler();
        runPagedListThreadExecutor(adapter);
        setupSearchButtonClick();
    }

    private void binding() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void initLogger() {
        logger = new MyLogger();
    }

    private void initView() {

        recyclerView = findViewById(R.id.rv_photos);
        recyclerView.setLayoutManager(new GridLayoutManager(this, COLUMN_NUMBERS));

        etSearch = findViewById(R.id.tiSearch);
        btnSearch = findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                viewModel.setSearchString(Objects.requireNonNull(etSearch.getText()).toString());
            }
        });
    }

    private PhotoAdapter initPagingRecycler() {
        GridLayoutManager layoutManager = new GridLayoutManager(getBaseContext(), COLUMN_NUMBERS);
        PhotoAdapter adapter = new PhotoAdapter(getApplicationContext());
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
                runOnUiThread(new Runnable() {
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

    public void updateSearch() {
        PhotoAdapter adapter = initPagingRecycler();
        adapter.notifyDataSetChanged();
        runPagedListThreadExecutor(adapter);
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        viewModel.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

//    @Override
//    public void showRate() {
//        View.OnClickListener yesOnClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Do something
//            }
//        };
//
//        Snackbar.make(tvInfo, getString(R.string.rate_request_caption), Snackbar.LENGTH_LONG)
//                .setAction(getString(R.string.rate_request_show_button), yesOnClickListener)
//                .setActionTextColor(Color.MAGENTA) // цвет текста у кнопки действия
//                .show();
//    }

//    private void initInjector() {
//        presenter = new MainPresenter(this, app.getRatingLogic(), app.getRunCounter(), logger);
//        IPhotoComponent component = ((AppComponentProvider) getApplicationContext())
//                .getAppComponent()
//                .getUserComponent()
//                .setModule(new PhotoModule())
//                .build();
//        component.inject(this);
//    }
}