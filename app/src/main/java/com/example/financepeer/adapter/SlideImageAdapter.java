package com.example.financepeer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.financepeer.R;
import com.example.financepeer.model.SlideItem;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class SlideImageAdapter extends RecyclerView.Adapter<SlideImageAdapter.SlideViewHolder> {

    private List<SlideItem> slideItems;
    private ViewPager2 viewPager2;

    public SlideImageAdapter(List<SlideItem> slideItems, ViewPager2 viewPager2) {
        this.slideItems = slideItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlideViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {
        holder.setImage(slideItems.get(position));
        if(position == slideItems.size()-2) {
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return slideItems.size();
    }

    class SlideViewHolder extends RecyclerView.ViewHolder {

       private RoundedImageView roundedImageView;

       public SlideViewHolder(@NonNull View itemView) {
           super(itemView);
           roundedImageView = itemView.findViewById(R.id.imageSlide);
       }

       public void setImage(SlideItem slideItem) {
            roundedImageView.setImageResource(slideItem.getImage());

       }
   }
   private Runnable runnable = new Runnable() {
       @Override
       public void run() {
           slideItems.addAll(slideItems);
           notifyDataSetChanged();
       }
   };
}
