package com.appxemphim.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appxemphim.Api.ApiClient;
import com.appxemphim.R;
import com.appxemphim.adapters.PhimAdapter;
import com.appxemphim.dao.PhimDAO;
import com.appxemphim.data.Phim;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListPhimFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//    private PhimDAO phimDao;

    private PhimDAO phimDao;
    public ListPhimFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DanhSachPhim.
     */
    // TODO: Rename and change types and number of parameters
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