package ru.leonov.cleanarch.model.network;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import ru.leonov.cleanarch.R;

public class LoadPhotoHelper {
    public static void getPhoto(String url, ImageView imageView) {
        Picasso
                .get()
                .load(url)
                .error(R.drawable.no_image)
                .placeholder(R.drawable.image)
                .resizeDimen(R.dimen.image_size, R.dimen.image_size)
                .centerCrop()
                .into(imageView);
    }

    /**
     * Полученияе URL для фото по параметрам
     * https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg
     */
    public static String getPhotoPath(int farmId, String serverId, String id, String secret, String size) {
        return String.format(Locale.getDefault(), "https:////farm%d.staticflickr.com//%s//%s_%s_%s.jpg",
                farmId, serverId, id, secret, size);
    }
}
