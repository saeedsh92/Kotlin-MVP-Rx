package ss.com.Kotlin_MVP_Rx.component.imageloading;

import android.support.annotation.DrawableRes;
import android.widget.ImageView;

/**
 * @author S.Shahini
 * @since 8/13/17
 */

public interface ImageLoadingService {

    void loadImage(String url, ImageView imageView);

    void loadImage(@DrawableRes int resource, ImageView imageView);

    void loadImage(String url, @DrawableRes int placeHolder, @DrawableRes int errorDrawable, ImageView imageView);

}
