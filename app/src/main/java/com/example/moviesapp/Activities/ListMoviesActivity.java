package com.example.moviesapp.Activities;

import static android.util.Log.e;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

//import com.example.moviesapp.Adapters.MoreFilmAdapter;
import com.example.moviesapp.Adapters.IndividualFileAdapter;
import com.example.moviesapp.Adapters.MoviesNewUpdateAdapter;
import com.example.moviesapp.Adapters.SearchAdapter;
import com.example.moviesapp.Fragments.AnimesFragment;
import com.example.moviesapp.Fragments.IndividualFilmFragment;
import com.example.moviesapp.Fragments.MoreFilmFragment;
import com.example.moviesapp.R;
import com.example.moviesapp.ViewModel.HomeViewModel;
import com.example.moviesapp.ViewModel.SearchViewModel;
import com.example.moviesapp.databinding.ActivityListMoviesBinding;

import com.example.moviesapp.model.Item;
import com.example.moviesapp.model.ItemsMoviesNewUpdate;
import com.example.moviesapp.onClickItem;
import com.google.android.exoplayer2.util.Log;

import java.util.List;

public class ListMoviesActivity extends AppCompatActivity {
    private ActivityListMoviesBinding binding;
    private SearchViewModel searchViewModel;
    private HomeViewModel homeViewModel;
    private MoviesNewUpdateAdapter moviesNewUpdateAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListMoviesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.editTextTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Xử lý khi người dùng ấn Enter
                    String query = binding.editTextTextSearch.getText().toString().trim();
                    if (!query.isEmpty()) {
                        Fragment fm = new MoreFilmFragment();
                        Bundle args = new Bundle();
                        args.putString("keyfm", "search");
                        args.putString("query",query);
                        fm.setArguments(args);
                        loadFragment(fm);
                        return true;
                    }
                }
                return false;

            }
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle !=null && bundle.containsKey("keywordsearch")){
            String keyword = bundle.getString("keywordsearch");
            if("search".equals(keyword)){
                String query = bundle.getString("query");
                Fragment fm = new MoreFilmFragment();
                Bundle args = new Bundle();
                args.putString("keyfm", "search");
                args.putString("query",query);
                fm.setArguments(args);
                loadFragment(fm);
            }
        }
        if (bundle != null && bundle.containsKey("keyword")) {
            String keyword = bundle.getString("keyword");
            if ("moviesnewupdate".equals(keyword)) {
                Fragment fm = new MoreFilmFragment();
                Bundle args = new Bundle();
                args.putString("keyfm", "moviesnewupdate");
                fm.setArguments(args);
                loadFragment(fm);
            }
        } else {
            Bundle fragmentArgs = getIntent().getExtras(); // Lấy bundle từ Intent của fragment
            if (fragmentArgs != null && fragmentArgs.containsKey("keywordIndividual")) {
                String fragmentData = fragmentArgs.getString("keywordIndividual");
                // Xử lý dữ liệu từ fragment ở đây
                if ("moviesIndividual".equals(fragmentData)) {
                    Fragment fm = new MoreFilmFragment();
                    Bundle args = new Bundle();
                    args.putString("keyfm", "moviesIndividual");
                    fm.setArguments(args);
                    loadFragment(fm);
                }
            } else {
                // Không có dữ liệu từ cả activity và 1
                if(fragmentArgs != null && fragmentArgs.containsKey("keywordAnime")){
                    String fragmentData=  fragmentArgs.getString("keywordAnime");
                    if("moreAnimes".equals(fragmentData)){
                        Fragment fm = new MoreFilmFragment();
                        Bundle args = new Bundle();
                        args.putString("keyfm", "moviesAnimes");
                        fm.setArguments(args);
                        loadFragment(fm);
                    }
                }else {
                    if(fragmentArgs != null && fragmentArgs.containsKey("keywordSeries")){
                        String fragmentData=  fragmentArgs.getString("keywordSeries");
                        if("moreSeries".equals(fragmentData)){
                            Fragment fm = new MoreFilmFragment();
                            Bundle args = new Bundle();
                            args.putString("keyfm", "moreSeries");
                            fm.setArguments(args);
                            loadFragment(fm);
                        }
                    }  else {
                        if(fragmentArgs != null && fragmentArgs.containsKey("keywordTVShows")){
                            String fragmentData=  fragmentArgs.getString("keywordTVShows");
                            if("moreTVShows".equals(fragmentData)){
                                Fragment fm = new MoreFilmFragment();
                                Bundle args = new Bundle();
                                args.putString("keyfm", "moreTVShows");
                                fm.setArguments(args);
                                loadFragment(fm);
                            }
                        }
                    }

                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove observer to avoid memory leaks
        if (searchViewModel != null && searchViewModel.getSearch().hasObservers()) {
            searchViewModel.getSearch().removeObservers(this);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish(); // Đóng activity
    }
    public void event(){
        binding.imageViewhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMoviesActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    void loadFragment(Fragment fmnew ){
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.frame_list,fmnew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }
}
