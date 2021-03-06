package com.example.financepeer.model;

import java.util.ArrayList;

public class MoviesModel {
    private int page;
    private int total_results;
    private int total_pages;
    private ArrayList<Movie> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<Movie> getResult() {
        return results;
    }

    public void setResult(ArrayList<Movie> results) {
        this.results = results;
    }
}
