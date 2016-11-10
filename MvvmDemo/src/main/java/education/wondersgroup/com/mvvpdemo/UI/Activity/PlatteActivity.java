package education.wondersgroup.com.mvvpdemo.UI.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import education.wondersgroup.com.mvvpdemo.R;

/**
 * Created by zhangwentao on 16/10/26.
 * Description :图片取色
 * Version :
 */
public class PlatteActivity extends AppCompatActivity {
    private ImageView imageView;
    private ImageView ic_img;
    private SimpleDraweeView frescoImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_plate);

        imageView = (ImageView) findViewById(R.id.platte_image);
        frescoImg = (SimpleDraweeView) findViewById(R.id.fresco_image);


        Uri uri = Uri.parse("http://h.hiphotos.baidu.com/zhidao/pic/item/6d81800a19d8bc3ed69473cb848ba61ea8d34516.jpg");
        frescoImg.setImageURI(uri);
        test(uri, this);
    }

    /**
     * 使用Palette组合Fresco 进行网络图片的取色
     *
     * @param uri
     * @param context
     */
    public void test(Uri uri, Context context) {
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();

        ImagePipeline imagePipeline = Fresco.getImagePipeline();

        DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, context);
        BaseBitmapDataSubscriber dataSubscriber = new BaseBitmapDataSubscriber() {
            @Override
            protected void onNewResultImpl(Bitmap bitmap) {
                Palette.Builder builder = new Palette.Builder(bitmap);
                builder.generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();
                        if (lightMutedSwatch != null) {
                            int color = lightMutedSwatch.getRgb();
                            imageView.setBackgroundColor(color);
                        }
                    }
                });
            }

            @Override
            protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {

            }
        };
        dataSource.subscribe(dataSubscriber, CallerThreadExecutor.getInstance());
    }
}
