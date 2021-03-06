package ru.leonov.cleanarch.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import ru.leonov.cleanarch.CleanArch;
import ru.leonov.cleanarch.R;
import ru.leonov.cleanarch.model.PhotoViewState;
import ru.leonov.cleanarch.model.di.AppComponentProvider;
import ru.leonov.cleanarch.model.di.IPhotoComponent;
import ru.leonov.cleanarch.model.di.PhotoModule;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.model.utils.logger.ILogger;
import ru.leonov.cleanarch.model.utils.logger.MyLogger;
import ru.leonov.cleanarch.presenter.IPhotoPresenter;
import ru.leonov.cleanarch.presenter.IViewPhotos;


public class MainActivity extends AppCompatActivity implements IViewPhotos {
    private final int COLUMN_NUMBERS = 2;

    private TextView tvInfo;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    MaterialButton btnSearch;
    TextInputEditText etSearch;

    //private MainPresenter presenter;
    @Inject
    IPhotoPresenter photoPresenter;

    private ILogger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLogger();
        initView();
        initPresenter();
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

    private void initPresenter() {
        //presenter = new MainPresenter(this, app.getRatingLogic(), app.getRunCounter(), logger);

        IPhotoComponent component = ((AppComponentProvider) getApplicationContext())
                .getAppComponent()
                .getUserComponent()
                .setModule(new PhotoModule(this))
                .build();
        component.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        photoPresenter.onStart();
    }

    @Override
    protected void onStop() {
        photoPresenter.onStop();

        super.onStop();
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

    @Override
    public void render(PhotoViewState viewState) {
        renderProgress(viewState.isLoading());
        renderError(viewState.getError());
        renderPhotos(viewState.isLoading(), viewState.getPhotos());
    }

    @Override
    public Observable<String> userActionIntent() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                btnSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        emitter.onNext(Objects.requireNonNull(etSearch.getText()).toString());
                    }
                });
            }
        });
    }

    @Override
    public Observable<String> onStartIntent() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("");
            }
        });
    }

    private void renderError(Throwable error) {
        if (error != null) {
            tvInfo.setVisibility(View.VISIBLE);
            String msg = "Error: " + error.getMessage();
            tvInfo.setText(msg);
        }
    }

    private void renderProgress(boolean loading) {
        progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);

        if (loading) tvInfo.setVisibility(View.VISIBLE);
        tvInfo.setText(getString(R.string.loading));
    }

    private void renderPhotos(boolean loading, List<PhotoContainer> list) {
        if (loading) return;

        if (list != null) {
            PhotoRecyclerViewAdapter adapter = new PhotoRecyclerViewAdapter(this, list);
            //adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);
            tvInfo.setVisibility(View.GONE);

        } else {
            tvInfo.setVisibility(View.VISIBLE);
            tvInfo.setText(getString(R.string.no_photo));
        }
    }
}