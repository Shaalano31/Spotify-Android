package com.example.spotify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class fragmentHomeDisplay  extends Fragment {

    //////////////////variables  fragement filling up its variables needs



    RecyclerView    recyclerView;
    RecyclerAdapter recyclerAdapter;

    String titleOfPlaylist ;


    // constructor for the fragment

    public fragmentHomeDisplay(RecyclerView recyclerView, RecyclerAdapter recyclerAdapter, String title) {
        this.recyclerView = recyclerView;
        this.recyclerAdapter = recyclerAdapter;
        this.titleOfPlaylist= title;

    }

//setters & getters




    /// creation of vieww

    @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState)

        {
            View view  = inflater.inflate(R.layout.home_playlists_display, container, false);

            LinearLayoutManager LayoutManager =new LinearLayoutManager(getActivity());
            recyclerView.setAdapter(recyclerAdapter);
            recyclerView.setLayoutManager(LayoutManager);

            return view;

            }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_playlists_display);

      ///////// nothing



    }

    private void setContentView(int activity_home_fragment)
    {

    }



    public RecyclerView getRecyclerView()

    {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView)

    {
        this.recyclerView = recyclerView;
    }

    public RecyclerAdapter getRecyclerAdapter()

    {
        return recyclerAdapter;
    }

    public void setRecyclerAdapter(RecyclerAdapter recyclerAdapter) {
        this.recyclerAdapter = recyclerAdapter;
    }

    public String getTitleOfPlaylist() {
        return titleOfPlaylist;
    }

    public void setTitleOfPlaylist(String titleOfPlaylist)
    {
        this.titleOfPlaylist = titleOfPlaylist;
    }



}
