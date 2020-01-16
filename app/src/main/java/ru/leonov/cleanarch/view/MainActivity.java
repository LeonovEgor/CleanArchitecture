package ru.leonov.cleanarch.view;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ru.leonov.cleanarch.R;
import ru.leonov.cleanarch.databinding.ActivityMainBinding;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.model.utils.logger.ILogger;
import ru.leonov.cleanarch.model.utils.logger.MyLogger;
import ru.leonov.cleanarch.viewmodel.PhotoViewModel;


public class MainActivity extends AppCompatActivity  {
    private final int COLUMN_NUMBERS = 2;

    private TextView tvInfo;
    RecyclerView recyclerView;
    ProgressBar progressBar;
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

        viewModel.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        initLogger();
        //bindingViewModel();
        //binding();
        initView();
        //initInjector();
        initViewModelObserve();
    }

    private void bindingViewModel() {

    }

    private void binding() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(viewModel);
    }

    private void initLogger() {
        logger = new MyLogger();
    }

    private void initView() {
        tvInfo = findViewById(R.id.tv_info);

        recyclerView = findViewById(R.id.rv_photos);
        recyclerView.setLayoutManager(new GridLayoutManager(this, COLUMN_NUMBERS));

        progressBar = findViewById(R.id.progress_bar);
        etSearch = findViewById(R.id.tiSearch);
        btnSearch = findViewById(R.id.btn_search);
    }

    private void initInjector() {
        //presenter = new MainPresenter(this, app.getRatingLogic(), app.getRunCounter(), logger);

//        IPhotoComponent component = ((AppComponentProvider) getApplicationContext())
//                .getAppComponent()
//                .getUserComponent()
//                .setModule(new PhotoModule())
//                .build();
//        component.inject(this);
    }

    private void initViewModelObserve() {

        PhotoViewModel photoViewModel = ViewModelProviders.of(this).get(PhotoViewModel.class);
        //От модели получаем LiveData
        LiveData<List<PhotoContainer>> data = photoViewModel.getPhotos();
        //подписываемся на получение данных
        data.observe(this, new Observer<List<PhotoContainer>>() {
            @Override
            public void onChanged(List<PhotoContainer> photoContainerList) {
                GridLayoutManager layoutManager = new GridLayoutManager(getBaseContext(), COLUMN_NUMBERS);
                PhotoRecyclerViewAdapter recyclerViewAdapter = new PhotoRecyclerViewAdapter();
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.setData(photoContainerList);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        viewModel.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onStart() {
        super.onStart();

        viewModel.onStart();
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

//    private void renderError(Throwable error) {
//        if (error != null) {
//            tvInfo.setVisibility(View.VISIBLE);
//            String msg = "Error: " + error.getMessage();
//            tvInfo.setText(msg);
//        }
//    }

//    private void renderProgress(boolean loading) {
//        progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
//
//        if (loading) tvInfo.setVisibility(View.VISIBLE);
//        tvInfo.setText(getString(R.string.loading));
//    }

//    private void renderPhotos(boolean loading, List<PhotoContainer> list) {
//        if (loading) return;
//
//        if (list != null) {
//            PhotoRecyclerViewAdapter adapter = new PhotoRecyclerViewAdapter(this, list);
//            recyclerView.setAdapter(adapter);
//            tvInfo.setVisibility(View.GONE);
//
//        } else {
//            tvInfo.setVisibility(View.VISIBLE);
//            tvInfo.setText(getString(R.string.no_photo));
//        }
//    }
}