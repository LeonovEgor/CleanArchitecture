package ru.leonov.cleararch.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import ru.leonov.cleararch.ClearArch;
import ru.leonov.cleararch.R;
import ru.leonov.cleararch.utils.logger.ILogger;
import ru.leonov.cleararch.utils.logger.MyLogger;

public class MainActivity extends AppCompatActivity implements IMainPresenter.PresenterView {
    private TextView textView;
    private MainPresenter presenter;
    private ILogger logger;
    private ClearArch app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app = (ClearArch)getApplication();

        initLogger();
        initPresenter();
        initView();
    }

    private void initLogger() {
        logger = new MyLogger();
    }

    private void initPresenter() {
        presenter = new MainPresenter(this, app.getRatingLogic(), app.getRunCounter(), logger);
    }

    private void initView() {
        textView = findViewById(R.id.textView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.onStart();
    }

    @Override
    protected void onStop() {
        presenter.onStop();

        super.onStop();
    }

    @Override
    public void showRate() {
        View.OnClickListener yesOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something
            }
        };

        Snackbar.make(textView, getString(R.string.rate_request_caption), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.rate_request_show_button), yesOnClickListener)
                .setActionTextColor(Color.MAGENTA) // цвет текста у кнопки действия
                .show();
    }

    @Override
    public void showRunCounter(int count) {
        textView.setText(String.valueOf(count));
    }
}