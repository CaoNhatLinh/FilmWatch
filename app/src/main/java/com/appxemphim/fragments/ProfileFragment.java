package com.appxemphim.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.appxemphim.R;
import com.appxemphim.activities.HistoryMovieActivity;
import com.appxemphim.activities.ProfileDetailActivity;


public class ProfileFragment extends Fragment {

    private Button btnHistoryMoives;
    private Button btnProfileDetails;

    public ProfileFragment() {
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        gotoHistoryMoivesActivity(view);
        gotoProfileDetailsActivity(view);
        return view;
    }

    public void gotoHistoryMoivesActivity(View view)
    {
        btnHistoryMoives = view.findViewById(R.id.btnHistoryMovies);
        btnHistoryMoives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HistoryMovieActivity.class);
                startActivity(intent);
            }
        });
    }
    public void gotoProfileDetailsActivity(View view)
    {
        btnProfileDetails = view.findViewById(R.id.btnProfileDetails);
        btnProfileDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}