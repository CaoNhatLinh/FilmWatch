package com.appxemphim.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.appxemphim.R;
import com.appxemphim.activities.FavoriteListActivity;
import com.appxemphim.activities.HistoryMovieActivity;
import com.appxemphim.activities.MainActivity;
import com.appxemphim.activities.ProfileDetailActivity;
import com.appxemphim.dao.NguoiDungDAO;
import com.appxemphim.data.NguoiDung;


public class ProfileFragment extends Fragment {

    private Button btnHistoryMoives;
    private Button btnProfileDetails,btnLogout;
    private Button btnYeuThich;
    private NguoiDungDAO nguoiDungDAO;
    private static final int EDIT_PROFILE_REQUEST_CODE = 1;
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
        btnLogout = view.findViewById(R.id.btnLogout);
        btnHistoryMoives = view.findViewById(R.id.btnHistoryMovies);
        nguoiDungDAO = new NguoiDungDAO();
        btnProfileDetails = view.findViewById(R.id.btnProfileDetails);
        btnYeuThich = view.findViewById(R.id.btnYeuThich);  // Thêm dòng này
        int id;
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("MaNguoiDung");
            load(id);

        }

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_PROFILE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String newUserName = data.getStringExtra("hoten");

                TextView userNameTextView = getView().findViewById(R.id.tvName);
                userNameTextView.setText(newUserName);
            }
        }
    }
    public void load(int id)
    {
        btnYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FavoriteListActivity.class);
                intent.putExtra("MaNguoiDung", id);
                startActivity(intent);
            }
        });
        btnProfileDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileDetailActivity.class);
                intent.putExtra("MaNguoiDung", id);
                startActivityForResult(intent, EDIT_PROFILE_REQUEST_CODE);
            }
        });
        btnHistoryMoives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HistoryMovieActivity.class);
                intent.putExtra("MaNguoiDung", id);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                getActivity().finish();
            }
        });
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
}