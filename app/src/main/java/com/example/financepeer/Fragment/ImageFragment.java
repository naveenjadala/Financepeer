package com.example.financepeer.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.financepeer.R;
import com.example.financepeer.adapter.ListViewAdapter;
import com.example.financepeer.databinding.ImageFragmentBinding;
import com.example.financepeer.model.UserPost;
import com.example.financepeer.server.AddDataBase;

import java.util.List;

public class ImageFragment extends Fragment {

    private ImageFragmentBinding binding;
    private List<UserPost> userPost;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.image_fragment, container, false);
        getlist(getContext());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(true);

        binding.getList.setOnClickListener(v -> {
            ListViewAdapter listAdapter = new ListViewAdapter(userPost);
            binding.recyclerView.setAdapter(listAdapter);
            binding.getList.setVisibility(View.GONE);
        });

        return binding.getRoot();
    }

    @SuppressLint("StaticFieldLeak")
    private void getlist(final Context context) {
        new AsyncTask<Void,Void,Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                userPost = AddDataBase.getAddDataBase(context).storeImageDao().getAll();
                return null;
            }
        }.execute();
    }
}
