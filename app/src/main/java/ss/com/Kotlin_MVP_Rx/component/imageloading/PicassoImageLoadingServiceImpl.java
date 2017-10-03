package ss.com.Kotlin_MVP_Rx.component.imageloading;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * @author S.Shahini
 * @since 8/13/17
 */

public class PicassoImageLoadingServiceImpl implements ImageLoadingService {
    private Context context;

    public PicassoImageLoadingServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        Picasso.with(context).load(url).into(imageView);
    }

    @Override
    public void loadImage(@DrawableRes int resource, ImageView imageView) {
        Picasso.with(context).load(resource).into(imageView);
    }

    @Override
    public void loadImage(String url, @DrawableRes int placeHolder, @DrawableRes int errorDrawable, ImageView imageView) {

    }
}
