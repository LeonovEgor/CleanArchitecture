package ru.leonov.cleanarch.model.utils.ui;

import android.content.Context;
import android.util.DisplayMetrics;

public class UiHelper {
    public static int calcColumnsCount(Context context, float columnWidthDp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (screenWidthDp / columnWidthDp + 0.5);
    }
}