package ru.leonov.cleanarch.view.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import ru.leonov.cleanarch.CleanArch;
import ru.leonov.cleanarch.R;
import ru.leonov.cleanarch.view.navigator.MainNavigator;


public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CleanArch.getInstance().getNavigator().attachActivity(this);

        if (savedInstanceState == null) {
            CleanArch.getInstance().getNavigator().gotoPhotosListScreen();
        }

    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        CleanArch.getInstance().getNavigator().attachActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        CleanArch.getInstance().getNavigator().detachActivity();
    }


    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
//        viewModel.onSaveInstanceState(outState);
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
}