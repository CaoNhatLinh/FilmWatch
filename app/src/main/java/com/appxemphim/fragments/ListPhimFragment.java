package com.appxemphim.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.appxemphim.R;
import com.appxemphim.Utils.ItemClickSupport;
import com.appxemphim.activities.ChiTietPhimActivity;
import com.appxemphim.adapters.ListPhimAdapter;
import com.appxemphim.dao.PhimDAO;
import com.appxemphim.data.Phim;

import java.util.ArrayList;
import java.util.List;


public class ListPhimFragment extends Fragment {

    private RecyclerView recyclerView;
    private PhimDAO phimDAO;
    private ListPhimAdapter phimAdapter;
    public ListPhimFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_phim, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewListPhim);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3)); // 3 columns
        phimDAO = new PhimDAO();
        phimAdapter = new ListPhimAdapter(getContext(),new ArrayList<>());
        recyclerView.setAdapter(phimAdapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                // Handle item click here
                Phim phim = phimAdapter.getPhimAtPosition(position);
                Intent intent = new Intent(getActivity(), ChiTietPhimActivity.class);
                intent.putExtra("MaPhim", phim.getMaPhim());
                startActivity(intent);
            }
        });
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        loadPhimList();
    }
    private void loadPhimList() {

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
    }
}