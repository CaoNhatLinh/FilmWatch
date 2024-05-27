package com.appxemphim.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appxemphim.R;
import com.appxemphim.Utils.ItemClickSupport;
import com.appxemphim.activities.ChiTietPhimActivity;
import com.appxemphim.adapters.BannerAdapter;
import com.appxemphim.adapters.ListPhimAdapter;
import com.appxemphim.dao.PhimDAO;
import com.appxemphim.data.Phim;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView rvBanner;
    private RecyclerView rvPhimMoi;
    private RecyclerView rvPhimTrungQuocHot;
    private RecyclerView rvPhimAuMyHot;
    private PhimDAO phimDAO;
    private BannerAdapter posterAdapter;
    private ListPhimAdapter phimAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvBanner = view.findViewById(R.id.rvBanner);
        rvPhimMoi = view.findViewById(R.id.rvPhimMoi);
        rvPhimTrungQuocHot = view.findViewById(R.id.rvTrungQuocHot);
        rvPhimAuMyHot = view.findViewById(R.id.rvAuMyHot);


        rvBanner.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvPhimMoi.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvPhimTrungQuocHot.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvPhimAuMyHot.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        phimAdapter = new ListPhimAdapter(getContext(),new ArrayList<>());
        posterAdapter = new BannerAdapter(getContext(),new ArrayList<>());

        rvBanner.setAdapter(posterAdapter);
        rvPhimMoi.setAdapter(phimAdapter);
        rvPhimTrungQuocHot.setAdapter(phimAdapter);
        rvPhimAuMyHot.setAdapter(phimAdapter);
        phimDAO = new PhimDAO();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        loadBanner();
        loadPhimMoi();
        loadPhimAuMyHot();
        loadPhimTrungQuocHot();
    }
    private void loadBanner() {

        phimDAO.getTop5Phim(new PhimDAO.PhimCallback() {
            @Override
            public void onSuccess(List<Phim> phimList) {
                posterAdapter.updatePhimList(phimList);
            }

            @Override
            public void onFailure(String message) {
                Log.e("PhimFragment", "Failed to fetch data: " + message);
            }
        });
        ItemClickSupport.addTo(rvBanner).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                // Handle item click here
                Phim phim = posterAdapter.getPhimAtPosition(position);
                Intent intent = new Intent(getActivity(), ChiTietPhimActivity.class);
                intent.putExtra("MaPhim", phim.getMaPhim());
                startActivity(intent);
            }
        });
    }

    private void loadPhimMoi() {

        phimDAO.getListPhimTrungQuocHot(new PhimDAO.PhimCallback() {
            @Override
            public void onSuccess(List<Phim> phimList) {
                phimAdapter.updatePhimList(phimList);
            }

            @Override
            public void onFailure(String message) {
                Log.e("PhimFragment", "Failed to fetch data: " + message);
            }
        });
        ItemClickSupport.addTo(rvPhimMoi).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                // Handle item click here
                Phim phim = posterAdapter.getPhimAtPosition(position);
                Intent intent = new Intent(getActivity(), ChiTietPhimActivity.class);
                intent.putExtra("MaPhim", phim.getMaPhim());
                startActivity(intent);
            }
        });
    }
    private void loadPhimAuMyHot() {

        phimDAO.getListPhimAuMyHot(new PhimDAO.PhimCallback() {
            @Override
            public void onSuccess(List<Phim> phimList) {
                phimAdapter.updatePhimList(phimList);
            }

            @Override
            public void onFailure(String message) {
                Log.e("PhimFragment", "Failed to fetch data: " + message);
            }
        });
        ItemClickSupport.addTo(rvPhimMoi).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                // Handle item click here
                Phim phim = posterAdapter.getPhimAtPosition(position);
                Intent intent = new Intent(getActivity(), ChiTietPhimActivity.class);
                intent.putExtra("MaPhim", phim.getMaPhim());
                startActivity(intent);
            }
        });
    }
    private void loadPhimTrungQuocHot() {

        phimDAO.getPhimList(new PhimDAO.PhimCallback() {
            @Override
            public void onSuccess(List<Phim> phimList) {
                phimAdapter.updatePhimList(phimList);
            }

            @Override
            public void onFailure(String message) {
                Log.e("PhimFragment", "Failed to fetch data: " + message);
            }
        });
        ItemClickSupport.addTo(rvPhimMoi).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                // Handle item click here
                Phim phim = posterAdapter.getPhimAtPosition(position);
                Intent intent = new Intent(getActivity(), ChiTietPhimActivity.class);
                intent.putExtra("MaPhim", phim.getMaPhim());
                startActivity(intent);
            }
        });
    }
}
