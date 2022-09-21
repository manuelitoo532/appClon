package com.thecode.ortez.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thecode.ortez.R;
import com.thecode.ortez.activities.MainActivity;
import com.thecode.ortez.adapters.MatchListAdapter;
import com.thecode.ortez.entities.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class
FeedFragment extends Fragment {

    View rootLayout;
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Match> matchList;
    private MatchListAdapter mAdapter;
    private String[] matchDates = {"11 Jan. 2020", "26 Dec. 2019", "12 Dec. 2019", "17 Nov. 2019", "06 Oct. 2019", "01 Enero 2021", "04 Abril 2021", "06 Julio 2021", "05 Agosto 2021"};
    private int[] matchPictures = {R.drawable.foto_mujer, R.drawable.foto_mujer_2, R.drawable.foto_mujer_3, R.drawable.foto_mujer_4 , R.drawable.foto_mujer_5, R.drawable.foto_mujer_6, R.drawable.foto_mujer_7, R.drawable.foto_mujer_8, R.drawable.foto_mujer_9};
    private String[] matchNames = {"Martha Salgado", "Rita Centeno", "Sandra Palucho", "Nidia Diaz", "Karina Machado"};
    private String[] matchLocations = {"San Miguel", "Concepcion Batres", "Santa Maria", "Santa Maria", "Ereguayquin", "Santa Elena", "Usulutan", "Ereguayquin", "San Miguel"};


    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootLayout = inflater.inflate(R.layout.fragment_feed, container, false);


        RecyclerView recyclerView = rootLayout.findViewById(R.id.recycler_view_matchs);
        matchList = new ArrayList<>();
        mAdapter = new MatchListAdapter(getContext(), matchList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        prepareMatchList();
        
        return rootLayout;
    }


    private void prepareMatchList(){

        Random rand = new Random();
        int id = rand.nextInt(100);
        int i;
        for(i=0; i<5; i++) {
            Match match = new Match(id, matchNames[i], matchPictures[i], matchLocations[i], matchDates[i]);
            matchList.add(match);
        }
    }


}
