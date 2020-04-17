package com.example.financepeer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financepeer.R;
import com.example.financepeer.model.Movie;

import java.util.List;
/*
*
*      adapter to the show the list of movies
* */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private List<Movie> moviesList;

    public MovieListAdapter(List<Movie> moviesList) {
            this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movies_list_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        if(movie != null) {
            holder.title.setText(movie.getTitle());
            holder.date.setText(movie.getRelease_date());
            holder.description.setText(movie.getOverview());
            holder.rating.setText(movie.getVote_average());
        }

    }

    @Override
    public int getItemCount() {
        return moviesList != null ? moviesList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, date, description, rating;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            description = itemView.findViewById(R.id.description);
            rating = itemView.findViewById(R.id.rating);
        }
    }
}
