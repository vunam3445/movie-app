package com.example.moviesapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviesapp.Activities.DetalfilmActivity;
import com.example.moviesapp.Adapters.IndividualFileAdapter;
import com.example.moviesapp.Adapters.MoreFilmAdapter;
import com.example.moviesapp.Adapters.MoviesNewUpdateAdapter;
import com.example.moviesapp.Adapters.SearchAdapter;
import com.example.moviesapp.R;
import com.example.moviesapp.ViewModel.HomeViewModel;
import com.example.moviesapp.ViewModel.SearchViewModel;
import com.example.moviesapp.databinding.FragmentIndividualFilmBinding;
import com.example.moviesapp.databinding.FragmentMoreFilmBinding;
import com.example.moviesapp.model.Item;
import com.example.moviesapp.model.ItemsMoviesNewUpdate;
import com.example.moviesapp.onClickItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoreFilmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreFilmFragment extends Fragment {

    private FragmentMoreFilmBinding binding;
    private HomeViewModel homeViewModel;
    private SearchViewModel searchViewModel;
    List<ItemsMoviesNewUpdate> list = new ArrayList<>();
    List<Item> listItem = new ArrayList<>();
    private  IndividualFileAdapter individualFileAdapter;

    public int page =1;
    public int n =20;
    public int t =0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MoreFilmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoreFilmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoreFilmFragment newInstance(String param1, String param2) {
        MoreFilmFragment fragment = new MoreFilmFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        binding = FragmentMoreFilmBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        Bundle args = getArguments();
        if(args.getString("keyfm").equals("moviesnewupdate")){
            homeViewModel.getMovies().observe(getActivity(), new Observer<List<ItemsMoviesNewUpdate>>() {
                @Override
                public void onChanged(List<ItemsMoviesNewUpdate> itemsMoviesNewUpdates) {
                    list.addAll(itemsMoviesNewUpdates);
                    MoreFilmAdapter moreFilmAdapter = new MoreFilmAdapter(getContext(), new onClickItem<ItemsMoviesNewUpdate>() {
                        @Override
                        public void onClick(ItemsMoviesNewUpdate itemsMoviesNewUpdate) {
                            Intent intent = new Intent(getActivity(), DetalfilmActivity.class);
                            intent.putExtra("name",itemsMoviesNewUpdate.getSlug());
                            startActivity(intent);
                        }

                        @Override
                        public void delete(ItemsMoviesNewUpdate itemsMoviesNewUpdate) {

                        }
                    },list);

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                    binding.recyclerListMovies.setLayoutManager(gridLayoutManager);
                    binding.progressMore.setVisibility(View.GONE);
                    binding.recyclerListMovies.setAdapter(moreFilmAdapter);
                }
            });
            homeViewModel.fetchMoviesNewUpdate(1);
            binding.buttonMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadMoreMoviesNewupdate();
                }
            });
        }

        if(args.getString("keyfm").equals("moviesIndividual")){
            homeViewModel.getIndividual().observe(getActivity(), new Observer<List<Item>>() {
                @Override
                public void onChanged(List<Item> items) {
                    listItem.addAll(items);
                    IndividualFileAdapter individualFileAdapter = new IndividualFileAdapter(getContext(), listItem, new onClickItem<Item>() {
                        @Override
                        public void onClick(Item item) {
                            Intent intent = new Intent(getActivity(), DetalfilmActivity.class);
                            intent.putExtra("name",item.getSlug());
                            startActivity(intent);
                        }

                        @Override
                        public void delete(Item item) {

                        }
                    });
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                    binding.recyclerListMovies.setLayoutManager(gridLayoutManager);
                    binding.progressMore.setVisibility(View.GONE);

                    binding.recyclerListMovies.setAdapter(individualFileAdapter);
                }
            });
            homeViewModel.fetchIndividual(1);
            binding.buttonMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadMoreMoviesIndividual();
                }
            });
        }

        if(args.getString("keyfm").equals("moviesAnimes")){
            homeViewModel.getAnimes().observe(getActivity(), new Observer<List<Item>>() {
                @Override
                public void onChanged(List<Item> items) {
                    listItem.addAll(items);
                    IndividualFileAdapter individualFileAdapter = new IndividualFileAdapter(getContext(), listItem, new onClickItem<Item>() {
                        @Override
                        public void onClick(Item item) {
                            Intent intent = new Intent(getActivity(), DetalfilmActivity.class);
                            intent.putExtra("name",item.getSlug());
                            startActivity(intent);
                        }

                        @Override
                        public void delete(Item item) {

                        }
                    });
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                    binding.recyclerListMovies.setLayoutManager(gridLayoutManager);
                    binding.progressMore.setVisibility(View.GONE);

                    binding.recyclerListMovies.setAdapter(individualFileAdapter);
                }
            });
            homeViewModel.fetchAnimes(1);
            binding.buttonMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadMoreMoviesAnimes();
                }
            });
        }


        if(args.getString("keyfm").equals("moreTVShows")){
            homeViewModel.getTVShows().observe(getActivity(), new Observer<List<Item>>() {
                @Override
                public void onChanged(List<Item> items) {
                    listItem.addAll(items);
                    IndividualFileAdapter individualFileAdapter = new IndividualFileAdapter(getContext(), listItem, new onClickItem<Item>() {
                        @Override
                        public void onClick(Item item) {
                            Intent intent = new Intent(getActivity(), DetalfilmActivity.class);
                            intent.putExtra("name",item.getSlug());
                            startActivity(intent);
                        }

                        @Override
                        public void delete(Item item) {

                        }
                    });
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                    binding.recyclerListMovies.setLayoutManager(gridLayoutManager);
                    binding.progressMore.setVisibility(View.GONE);

                    binding.recyclerListMovies.setAdapter(individualFileAdapter);
                }
            });
            homeViewModel.fetchTVShows(1);
            binding.buttonMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadMoreMoviesTVShows();
                }
            });
        }

        if(args.getString("keyfm").equals("moreSeries")){
            homeViewModel.getSeries().observe(getActivity(), new Observer<List<Item>>() {
                @Override
                public void onChanged(List<Item> items) {
                    listItem.addAll(items);
                    IndividualFileAdapter individualFileAdapter = new IndividualFileAdapter(getContext(), listItem, new onClickItem<Item>() {
                        @Override
                        public void onClick(Item item) {
                            Intent intent = new Intent(getActivity(), DetalfilmActivity.class);
                            intent.putExtra("name",item.getSlug());
                            startActivity(intent);
                        }

                        @Override
                        public void delete(Item item) {

                        }
                    });
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                    binding.recyclerListMovies.setLayoutManager(gridLayoutManager);
                    binding.progressMore.setVisibility(View.GONE);

                    binding.recyclerListMovies.setAdapter(individualFileAdapter);
                }
            });
            homeViewModel.fetchSeries(1);
            binding.buttonMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadMoreMoviesSeries();
                }
            });
        }


        if(args.getString("keyfm").equals("search")){
            String query = args.getString("query");
            searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
            searchViewModel.getSearch().observe(getActivity(), new Observer<List<Item>>() {
                @Override
                public void onChanged(List<Item> items) {
                    listItem.addAll(items);
                    individualFileAdapter = new IndividualFileAdapter(getContext(), listItem, new onClickItem<Item>() {
                        @Override
                        public void onClick(Item item) {
                            Intent intent = new Intent(getActivity(), DetalfilmActivity.class);
                            intent.putExtra("name",item.getSlug());
                            startActivity(intent);
                        }

                        @Override
                        public void delete(Item item) {

                        }
                    });
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                    binding.recyclerListMovies.setLayoutManager(gridLayoutManager);
                    binding.progressMore.setVisibility(View.GONE);

                    binding.recyclerListMovies.setAdapter(individualFileAdapter);
                }
            });
            searchViewModel.fetchSearch(query,20,0);
            binding.buttonMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //individualFileAdapter.clear();
                    loadMoreMoviesSearch(query);
                }
            });
        }



        return rootView;
    }
    void loadMoreMoviesNewupdate(){
        page++;
        homeViewModel.fetchMoviesNewUpdate(page);

    }
    void loadMoreMoviesIndividual(){
        page++;
        homeViewModel.fetchIndividual(page);

    }
    void loadMoreMoviesAnimes(){
        page++;
        homeViewModel.fetchAnimes(page);

    }
    void loadMoreMoviesSeries(){
        page++;
        homeViewModel.fetchSeries(page);

    }
    void loadMoreMoviesTVShows(){
        page++;
        homeViewModel.fetchTVShows(page);

    }
    void loadMoreMoviesSearch(String query){
        t=n;
        n+=20;

        searchViewModel.fetchSearch(query,n,t);

    }
}