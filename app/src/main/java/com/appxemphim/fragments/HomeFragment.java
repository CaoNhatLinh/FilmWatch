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
import android.widget.Button;

import com.appxemphim.Api.ApiType;
import com.appxemphim.R;
import com.appxemphim.Utils.ItemClickSupport;
import com.appxemphim.Utils.Utils;
import com.appxemphim.activities.ChiTietPhimActivity;
import com.appxemphim.activities.SharedListPhimActivity;
import com.appxemphim.adapters.BannerAdapter;
import com.appxemphim.adapters.ListPhimAdapter;
import com.appxemphim.dao.PhimDAO;
import com.appxemphim.data.Phim;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView rvBanner,rvPhimMoi,rvPhimTrungQuocHot,rvPhimHanHot,rvLove,rvAnime;
    private PhimDAO phimDAO;
    private BannerAdapter posterAdapter;
    private ListPhimAdapter phimMoiAdapter,phimTrungQuocHotAdapter,phimHanHotAdapter,loveAdapter,animeAdapter;
    private Button btnChina,btnKorea,btnLove,btnAnime;
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
        btnChina = view.findViewById(R.id.btnMoreTrungQuoc);
        btnKorea = view.findViewById(R.id.btnMoreKorea);
        btnAnime = view.findViewById(R.id.btnMoreAnime);
        btnLove = view.findViewById(R.id.btnMoreLove);
    }
    private ListPhimAdapter createPhimAdapter() {
        return new ListPhimAdapter(getContext(), new ArrayList<>());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        findview(view);
        HorizontalLayoutManager();
        phimDAO = new PhimDAO();

        posterAdapter = new BannerAdapter(getContext(),new ArrayList<>());
        rvBanner.setAdapter(posterAdapter);


        phimMoiAdapter = createPhimAdapter();
        phimTrungQuocHotAdapter = createPhimAdapter();
        phimHanHotAdapter = createPhimAdapter();
        loveAdapter = createPhimAdapter();
        animeAdapter = createPhimAdapter();
        //set adapter
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
        click();
        btnMore();
    }
    private void openSharedActivity(String title, ArrayList<Phim> data) {
        Intent intent = new Intent(getActivity(), SharedListPhimActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("data", data);
        startActivity(intent);
    }
    public void loadList()
    {
        loadBanner();
        loadPhim(ApiType.PHIM_MOI, phimMoiAdapter);
        loadPhim(ApiType.TOP_PHIM_TRUNG_QUOC, phimTrungQuocHotAdapter);
        loadPhim(ApiType.TOP_PHIM_HAN, phimHanHotAdapter);
        loadPhim(ApiType.TOP_ANIME, animeAdapter);
        loadPhim(ApiType.TOP_LOVE, loveAdapter);
    }
    public void btnMore()
    {
        buttonshare(ApiType.TOP_PHIM_TRUNG_QUOC,btnChina,"Danh Sách Phim Trung Quốc");
        buttonshare(ApiType.TOP_PHIM_HAN,btnKorea,"Danh Sách Phim Hàn Quốc");
        buttonshare(ApiType.LOVE,btnLove,"Danh Sách Tình cảm - Lãng Mãn");
        buttonshare(ApiType.ANIME,btnAnime,"Danh Sách Phim Anime");
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
    private void loadPhim(ApiType apiType, final ListPhimAdapter adapter) {
        phimDAO.fetchPhimList(apiType, new PhimDAO.PhimCallback() {
            @Override
            public void onSuccess(List<Phim> phimList) {
                adapter.updatePhimList(phimList);
            }

            @Override
            public void onFailure(String message) {
                Log.e("MainActivity", "Failed to fetch data: " + message);
            }
        });
    }
    public void click()
    {
        Utils.setupRecyclerViewClickListener(getActivity(), rvPhimHanHot, phimHanHotAdapter, ChiTietPhimActivity.class);
        Utils.setupRecyclerViewClickListener(getActivity(), rvPhimTrungQuocHot, phimTrungQuocHotAdapter, ChiTietPhimActivity.class);
        Utils.setupRecyclerViewClickListener(getActivity(), rvAnime, animeAdapter, ChiTietPhimActivity.class);
        Utils.setupRecyclerViewClickListener(getActivity(), rvLove, loveAdapter, ChiTietPhimActivity.class);
        Utils.setupRecyclerViewClickListener(getActivity(), rvPhimMoi, phimMoiAdapter, ChiTietPhimActivity.class);
    }

    public void buttonshare(ApiType apiType,Button btn,String title)
    {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phimDAO.fetchPhimList(apiType, new PhimDAO.PhimCallback() {
                    @Override
                    public void onSuccess(List<Phim> phimList) {
                        ArrayList<Phim> listPhim = new ArrayList<>(phimList);
                        openSharedActivity(title, listPhim);
                    }
                    @Override
                    public void onFailure(String message) {
                        Log.e("PhimFragment", "Failed to fetch data: " + message);
                    }
                });
            }
        });
    }
}
