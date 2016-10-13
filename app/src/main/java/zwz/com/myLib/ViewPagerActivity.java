package zwz.com.myLib;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import zwz.com.myLib.convenientbanner.ConvenientBanner;
import zwz.com.myLib.convenientbanner.holder.CBViewHolderCreator;
import zwz.com.myLib.convenientbanner.holder.Holder;
import zwz.com.myLib.convenientbanner.listener.OnItemClickListener;

public class ViewPagerActivity extends AppCompatActivity {
    private ConvenientBanner mDiyBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        mDiyBanner = (ConvenientBanner) findViewById(R.id.diy_fragment_banner);
        initBanner();
    }


    private void initBanner() {
        //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
        final List<String> mBannerList=new ArrayList();
        mBannerList.add("http://img05.tooopen.com/images/20140604/sy_62331342149.jpg");
        mBannerList.add("http://pic5.nipic.com/20100121/1396946_104643942888_2.jpg");
        mDiyBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, mBannerList)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.shape_page_indicator_nomal, R.drawable.shape_page_indicator_select})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .startTurning(3000);
        ;
        //设置翻页的效果，不需要翻页效果可用不设
        //.setPageTransformer(Transformer.DefaultTransformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
        //        convenientBanner.setManualPageable(false);//设置不能手动影响

        mDiyBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ShowPhotoActivity.launch(ViewPagerActivity.this,mDiyBanner,mBannerList.get(position));
            }
        });
    }

    class  LocalImageHolderView implements Holder<String>{
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Picasso.with(context).load(data).into(imageView);
        }
    }

}
