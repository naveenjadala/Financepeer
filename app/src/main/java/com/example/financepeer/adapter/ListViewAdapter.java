package com.example.financepeer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financepeer.R;
import com.example.financepeer.model.UserPost;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    List<UserPost> userPostList = new ArrayList<>();
    public ListViewAdapter(List<UserPost> userPostList) {
        this.userPostList = userPostList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserPost userPost = userPostList.get(position);
        holder.title.setText(userPost.getTitle());
        holder.body.setText(userPost.getBody());
    }

    public void setPost(List<UserPost> userPostList) {
        this.userPostList = userPostList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return userPostList != null ? userPostList.size() : 0 ;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView body;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }
}
