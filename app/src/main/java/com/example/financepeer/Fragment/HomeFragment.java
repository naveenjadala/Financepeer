package com.example.financepeer.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.financepeer.R;
import com.example.financepeer.adapter.SlideImageAdapter;
import com.example.financepeer.databinding.BannerSlideImageBinding;
import com.example.financepeer.model.SlideItem;
import com.example.financepeer.server.AddDataBase;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private BannerSlideImageBinding binding;
    private Handler slideHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.banner_slide_image, container, false);
        getlist(getContext());

        List<SlideItem> slideItemList = new ArrayList<>();
        slideItemList.add(new SlideItem(R.drawable.imge1));
        slideItemList.add(new SlideItem(R.drawable.img2));
        slideItemList.add(new SlideItem(R.drawable.img3));
        slideItemList.add(new SlideItem(R.drawable.img4));

        binding.viewSlideImg.setAdapter(new SlideImageAdapter(slideItemList, binding.viewSlideImg));
        binding.viewSlideImg.setClipToPadding(false);
        binding.viewSlideImg.setClipChildren(false);
        binding.viewSlideImg.setOffscreenPageLimit(2);
        binding.viewSlideImg.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float f = 1- Math.abs(position);
                page.setScaleY(0.85f + f *  0.15f);
            }
        });


        binding.viewSlideImg.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(runnable);
                slideHandler.postDelayed(runnable, 2000);
            }
        });

        return binding.getRoot();
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            binding.viewSlideImg.setCurrentItem(binding.viewSlideImg.getCurrentItem() + 1);
        }
    };

    @SuppressLint("StaticFieldLeak")
    private void getlist(final Context context) {
        new AsyncTask<Void,Void,Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                AddDataBase.getAddDataBase(context).storeImageDao().getAll();
                return null;
            }
        }.execute();
    }
}
