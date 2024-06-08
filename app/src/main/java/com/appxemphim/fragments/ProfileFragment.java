package com.appxemphim.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.appxemphim.R;
import com.appxemphim.activities.HistoryMovieActivity;
import com.appxemphim.activities.ProfileDetailActivity;
import com.appxemphim.dao.NguoiDungDAO;
import com.appxemphim.data.NguoiDung;
import com.appxemphim.data.Phim;

import java.util.List;


public class ProfileFragment extends Fragment {

    private Button btnHistoryMoives;
    private Button btnProfileDetails;
    private NguoiDungDAO nguoiDungDAO;

    private TextView tvName;
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
        tvName = view.findViewById(R.id.tvName);

        int id= 2;
        //Bundle bundle = getArguments();
//        if (bundle != null) {
//            id = bundle.getInt("MaNguoiDung");
//            getProfileById(data);
//        }
        gotoHistoryMoivesActivity(view,id);
        gotoProfileDetailsActivity(view,id);
        nguoiDungDAO = new NguoiDungDAO();

        getProfileById(id);
        return view;
    }

    public void gotoHistoryMoivesActivity(View view,int id)
    {
        btnHistoryMoives = view.findViewById(R.id.btnHistoryMovies);
        btnHistoryMoives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HistoryMovieActivity.class);
                intent.putExtra("MaNguoiDung", id);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();

    }
    public void getProfileById(int id)
    {
        nguoiDungDAO.getProfileById(id, new NguoiDungDAO.NguoiDungCallback() {
            @Override
            public void onSuccess(NguoiDung nguoiDung) {
                tvName.setText(nguoiDung.getHoTen());
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e("ProfileFragment", "Failed to fetch data: " + errorMessage);
            }
        });
    }
    public void gotoProfileDetailsActivity(View view,int id)
    {
        btnProfileDetails = view.findViewById(R.id.btnProfileDetails);
        btnProfileDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileDetailActivity.class);
                intent.putExtra("MaNguoiDung", id);
                startActivity(intent);
            }
        });
    }
}