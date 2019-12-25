package ru.leonov.cleararch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import ru.leonov.cleararch.data.Storage;
import ru.leonov.cleararch.data.StorageRepository;
import ru.leonov.cleararch.interactor.main.IRatingLogic;
import ru.leonov.cleararch.interactor.counter.IRunCounter;
import ru.leonov.cleararch.interactor.main.RatingLogic;
import ru.leonov.cleararch.interactor.counter.RunCounter;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initArch();
    }

    private void initView() {
        textView = findViewById(R.id.textView);
    }

    private void initArch() {
        StorageRepository storageRepository = new StorageRepository(this, new Storage());
        final IRunCounter runCounter = new RunCounter(storageRepository);
        runCounter.IncrementRun();

        final IRatingLogic ratingLogic = new RatingLogic(runCounter);
        boolean res = ratingLogic.isShouldShowRatingRequest();
        if (res) showSomething();
    }

    private void showSomething() {
        View.OnClickListener yesOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something
            }
        };

        Snackbar
                .make(textView, "Would you like to rate the app?", Snackbar.LENGTH_LONG)
                .setAction("Show", yesOnClickListener)
                .setActionTextColor(Color.MAGENTA) // цвет текста у кнопки действия
                .show();
    }
}
