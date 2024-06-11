package com.appxemphim.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import com.appxemphim.R;
import com.appxemphim.Utils.CapitalizeWords;
import com.appxemphim.activities.XemPhimActivity;
import com.appxemphim.dao.PhimDAO;
import com.appxemphim.dao.TapPhimDAO;
import com.appxemphim.data.Phim;

import com.appxemphim.data.Phim_NguoiDung;
import com.appxemphim.data.TapPhim;
import com.squareup.picasso.Picasso;

import java.util.List;

public class historyAdapter extends RecyclerView.Adapter<historyAdapter.PhimItemViewHolder> {
    private List<Phim_NguoiDung> Phim_nguoidungs;
    private Context context;
    private TapPhimDAO tapPhimDAO;
    private PhimDAO phimDAO;

    public historyAdapter( Context c,List<Phim_NguoiDung> Phim_nguoidungs) {
        this.Phim_nguoidungs = Phim_nguoidungs;
        this.context = c;
    }
    @Override
    public int getItemCount() {
        return Phim_nguoidungs.size();
    }
    public void updatePhimList(List<Phim_NguoiDung> newPhimList) {
        Phim_nguoidungs.clear();
        Phim_nguoidungs.addAll(newPhimList);
        notifyDataSetChanged();
    }
    @Override
    public PhimItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_phimhistory, parent, false);

        return new PhimItemViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(PhimItemViewHolder holder, int position) {
        phimDAO = new PhimDAO();
        tapPhimDAO = new TapPhimDAO();
        Phim_NguoiDung u = Phim_nguoidungs.get(position);
        phimDAO.getPhimById(u.getMaPhim(), new PhimDAO.PhimByIdCallback() {
            @Override
            public void onSuccess(Phim phim) {
                String tenphim = phim.getTieuDe().toString();
                holder.TitlePhim.setText(CapitalizeWords.capitalizeWords(shortenToTenWords(tenphim)));
                Picasso.with(context)
                        .load(phim.getAnhBia())
                        .into(holder.ivPhim);
            }
            @Override
            public void onFailure(String error) {
            }
        });
        tapPhimDAO.getTapPhimById(u.getMaTapPhim(), new TapPhimDAO.TapPhimCallback() {
            @Override
            public void onSuccess(TapPhim tapPhim) {
                String tentapphim = tapPhim.getTenTap().toString();
                holder.TitleTapPhim.setText("Đang xem: "+CapitalizeWords.capitalizeWords(tentapphim));
            }
            @Override
            public void onFailure(String errorMessage) {
                Log.e("ProfileFragment", "Failed to fetch data: " + errorMessage);
            }
        });
    }
    public Phim_NguoiDung getPhimAtPosition(int position) {
        return Phim_nguoidungs.get(position);
    }
    public static class PhimItemViewHolder extends RecyclerView.ViewHolder {
        public TextView TitlePhim,TitleTapPhim;
        public ImageView ivPhim;
        public PhimItemViewHolder(View itemView) {
            super(itemView);
            TitlePhim = (TextView) itemView.findViewById(R.id.textviewTitleHisory);
            ivPhim = (ImageView) itemView.findViewById(R.id.imageViewHisory);
            TitleTapPhim = (TextView) itemView.findViewById(R.id.textViewTapPhimHistory);
        }
    }
    public static String shortenToTenWords(String input) {
        String[] words = input.split("\\s+");
        if (words.length <= 8) {
            return input;
        }
        StringBuilder shortenedString = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            shortenedString.append(words[i]);
            if (i < 7) {
                shortenedString.append(" ");
            }
        }
        shortenedString.append("...");
        return shortenedString.toString();
    }

}