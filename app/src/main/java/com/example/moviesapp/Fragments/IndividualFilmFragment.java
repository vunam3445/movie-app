package com.example.moviesapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moviesapp.Activities.DetalfilmActivity;
import com.example.moviesapp.Activities.ListMoviesActivity;
import com.example.moviesapp.Adapters.IndividualFileAdapter;
import com.example.moviesapp.ViewModel.HomeViewModel;

import com.example.moviesapp.databinding.FragmentIndividualFilmBinding;
import com.example.moviesapp.model.Item;
import com.example.moviesapp.onClickItem;
import com.google.android.exoplayer2.util.Log;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IndividualFilmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IndividualFilmFragment extends Fragment {
    private FragmentIndividualFilmBinding binding;
    private Context context;
    private HomeViewModel homeViewModel;
    private RecyclerView recycler;
private TextView more;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public IndividualFilmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IndividualFilmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IndividualFilmFragment newInstance(String param1, String param2) {
        IndividualFilmFragment fragment = new IndividualFilmFragment();
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
        binding = FragmentIndividualFilmBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        recycler = binding.recyclerIndividual;
        more = binding.textView20;
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIndividual = new Intent(getActivity(), ListMoviesActivity.class);
                intentIndividual.putExtra("keywordIndividual","moviesIndividual");

                getActivity().startActivity(intentIndividual);
            }
        });

        Log.e("namnamnam","https://img.phimapi.com/");
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getIndividual().observe(getViewLifecycleOwner(), new Observer<List<Item>>() {

            @Override
            public void onChanged(List<Item> items) {
                IndividualFileAdapter individualFileAdapter = new IndividualFileAdapter(requireContext(), items, new onClickItem<Item>() {
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
                recycler.setLayoutManager(gridLayoutManager);
                binding.progressIndividual.setVisibility(View.GONE);
                recycler.setAdapter(individualFileAdapter);

            }
        });
        homeViewModel.fetchIndividual(1);
        return rootView;
    }

}