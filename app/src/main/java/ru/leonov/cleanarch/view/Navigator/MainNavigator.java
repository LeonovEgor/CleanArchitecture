package ru.leonov.cleanarch.view.Navigator;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import ru.leonov.cleanarch.R;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.view.ui.PhotoDetails.DetailFragment;
import ru.leonov.cleanarch.view.ui.PhotoList.PhotoListFragment;

public class MainNavigator implements INavigator {

    @Nullable
    private FragmentActivity activity;

    @Override
    public void gotoPhotosListScreen() {
        replaceFragment(new PhotoListFragment(), false);
    }

    @Override
    public void gotoPhotosDetailsScreen(PhotoContainer photoContainer) {
        replaceFragment(DetailFragment.newInstance(photoContainer), true);
    }

    private void replaceFragment(Fragment fragment, boolean addToBackStack) {
        if (activity != null) {
            FragmentTransaction transaction = activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment);
            if (addToBackStack) {
                transaction.addToBackStack("");
            }

            transaction.commit();
        }
    }

    public void attachActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    public void detachActivity() {
        this.activity = null;
    }
}
