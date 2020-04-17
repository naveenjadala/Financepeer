package com.example.financepeer.Fragment;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.financepeer.R;
import com.example.financepeer.adapter.MovieListAdapter;
import com.example.financepeer.databinding.ServiceFragmentBinding;
import com.example.financepeer.model.Movie;
import com.example.financepeer.model.MoviesModel;
import com.example.financepeer.network.RetrofitInstance;
import com.example.financepeer.server.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceFragment extends Fragment {

    private ServiceFragmentBinding binding;
    private ProgressDialog progressDoalog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.service_fragment, container, false);
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        //API calling
        DataService dataService = RetrofitInstance.getRetrofitInstance().create(DataService.class);
        dataService.getMovieList().enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                progressDoalog.dismiss();
                assert response.body() != null;
                setData(response.body().getResult());
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    //calling adapter to set data
    private void setData(List<Movie> moviesList) {
        MovieListAdapter movieListAdapter = new MovieListAdapter(moviesList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(movieListAdapter);
    }
}
