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
    private RecyclerView rvPhimHanHot;
    private RecyclerView rvLove;
    private RecyclerView rvAnime;
    private PhimDAO phimDAO;
    private BannerAdapter posterAdapter;
    private ListPhimAdapter phimMoiAdapter;
    private ListPhimAdapter phimTrungQuocHotAdapter;
    private ListPhimAdapter phimHanHotAdapter;
    private ListPhimAdapter loveAdapter;
    private ListPhimAdapter animeAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private void setupHorizontalLayoutManager(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }
    public void HorizontalLayoutManager()
    {
        setupHorizontalLayoutManager(rvBanner);
        setupHorizontalLayoutManager(rvPhimMoi);
        setupHorizontalLayoutManager(rvPhimTrungQuocHot);
        setupHorizontalLayoutManager(rvPhimHanHot);
        setupHorizontalLayoutManager(rvLove);
        setupHorizontalLayoutManager(rvAnime);
    }
    public void findview(View view)
    {
        rvBanner = view.findViewById(R.id.rvBanner);
        rvPhimMoi = view.findViewById(R.id.rvPhimMoi);
        rvPhimTrungQuocHot = view.findViewById(R.id.rvTrungQuocHot);
        rvPhimHanHot = view.findViewById(R.id.rvHanHot);
        rvLove = view.findViewById(R.id.rvLove);
        rvAnime = view.findViewById(R.id.rvAnime);
    }
    private ListPhimAdapter createPhimAdapter() {
        return new ListPhimAdapter(getContext(), new ArrayList<>());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        findview(view);
        HorizontalLayoutManager();
        posterAdapter = new BannerAdapter(getContext(),new ArrayList<>());
        rvBanner.setAdapter(posterAdapter);
        phimMoiAdapter = createPhimAdapter();
        phimTrungQuocHotAdapter = createPhimAdapter();
        phimHanHotAdapter = createPhimAdapter();
        loveAdapter = createPhimAdapter();
        animeAdapter = createPhimAdapter();
        phimDAO = new PhimDAO();

        rvPhimMoi.setAdapter(phimMoiAdapter);
        rvPhimTrungQuocHot.setAdapter(phimTrungQuocHotAdapter);
        rvPhimHanHot.setAdapter(phimHanHotAdapter);
        rvAnime.setAdapter(animeAdapter);
        rvLove.setAdapter(loveAdapter);

        loadList();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadList();
    }
    public void loadList()
    {
        loadBanner();
        loadPhimMoi();
        loadPhimHanHot();
        loadPhimTrungQuocHot();
        loadTopAnime();
        loadTopPhimLove();
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

        phimDAO.getListPhimMoi(new PhimDAO.PhimCallback() {
            @Override
            public void onSuccess(List<Phim> phimList) {
                phimMoiAdapter.updatePhimList(phimList);
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
    private void loadPhimHanHot() {

        phimDAO.getListPhimHanHot(new PhimDAO.PhimCallback() {
            @Override
            public void onSuccess(List<Phim> phimList) {
                phimHanHotAdapter.updatePhimList(phimList);
            }

            @Override
            public void onFailure(String message) {
                Log.e("PhimFragment", "Failed to fetch data: " + message);
            }
        });
        ItemClickSupport.addTo(rvPhimHanHot).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
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

        phimDAO.getListPhimTrungQuocHot(new PhimDAO.PhimCallback() {
            @Override
            public void onSuccess(List<Phim> phimList) {
                phimTrungQuocHotAdapter.updatePhimList(phimList);
            }

            @Override
            public void onFailure(String message) {
                Log.e("PhimFragment", "Failed to fetch data: " + message);
            }
        });
        ItemClickSupport.addTo(rvPhimTrungQuocHot).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                // Handle item click here
                Phim phim = phimTrungQuocHotAdapter.getPhimAtPosition(position);
                Intent intent = new Intent(getActivity(), ChiTietPhimActivity.class);
                intent.putExtra("MaPhim", phim.getMaPhim());
                startActivity(intent);
            }
        });
    }
    private void loadTopPhimLove() {

        phimDAO.getTopLove(new PhimDAO.PhimCallback() {
            @Override
            public void onSuccess(List<Phim> phimList) {
                loveAdapter.updatePhimList(phimList);
            }

            @Override
            public void onFailure(String message) {
                Log.e("PhimFragment", "Failed to fetch data: " + message);
            }
        });
        ItemClickSupport.addTo(rvLove).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                // Handle item click here
                Phim phim = loveAdapter.getPhimAtPosition(position);
                Intent intent = new Intent(getActivity(), ChiTietPhimActivity.class);
                intent.putExtra("MaPhim", phim.getMaPhim());
                startActivity(intent);
            }
        });
    }
    private void loadTopAnime() {

        phimDAO.getTopAnime(new PhimDAO.PhimCallback() {
            @Override
            public void onSuccess(List<Phim> phimList) {
                animeAdapter.updatePhimList(phimList);
            }

            @Override
            public void onFailure(String message) {
                Log.e("PhimFragment", "Failed to fetch data: " + message);
            }
        });
        ItemClickSupport.addTo(rvAnime).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                // Handle item click here
                Phim phim = animeAdapter.getPhimAtPosition(position);
                Intent intent = new Intent(getActivity(), ChiTietPhimActivity.class);
                intent.putExtra("MaPhim", phim.getMaPhim());
                startActivity(intent);
            }
        });
    }
}
