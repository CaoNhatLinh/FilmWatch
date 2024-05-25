package com.appxemphim.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.appxemphim.R;
import com.appxemphim.adapters.PhimAdapter;
import com.appxemphim.dao.PhimDAO;
import com.appxemphim.data.Phim;
import java.util.List;


public class ListPhimFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private PhimDAO phimDao;
    public ListPhimFragment() {
        // Required empty public constructor
    }


    public static ListPhimFragment newInstance(String param1, String param2) {
        ListPhimFragment fragment = new ListPhimFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private RecyclerView recyclerView;
    private PhimDAO phimDAO;
    private PhimAdapter phimAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_phim, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3)); // 3 columns
        phimDAO = new PhimDAO();
        loadPhimList();
        return view;
    }
    private void loadPhimList() {
        phimDAO.getPhimList(new PhimDAO.PhimCallback() {
            @Override
            public void onSuccess(List<Phim> phimList) {
                phimAdapter = new PhimAdapter(phimList, getContext());
                recyclerView.setAdapter(phimAdapter);
            }

            @Override
            public void onFailure(String message) {
                Log.e("PhimFragment", "Failed to fetch data: " + message);
            }
        });
    }
}