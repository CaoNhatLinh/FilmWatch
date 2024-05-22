package com.appxemphim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.appxemphim.R;
import com.appxemphim.data.Phim;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PhimAdapter extends RecyclerView.Adapter<PhimAdapter.PhimItemViewHolder> {
    private List<Phim> Phims;
    private Context context;

    public PhimAdapter(List<Phim> Phims, Context c) {
        this.Phims = Phims;
        this.context = c;
    }

    @Override
    public int getItemCount() {
        return Phims.size();
    }

    @Override
    public PhimItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new PhimItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PhimItemViewHolder holder, int position) {
        Phim u = Phims.get(position);
        Picasso.with(context)
                .load(u.getAnhBia())
                .into(holder.ivAvatar);
        holder.tvLoginName.setText(u.getTieuDe());
    }

    public static class PhimItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvLoginName;
        public ImageView ivAvatar;

        public PhimItemViewHolder(View itemView) {
            super(itemView);
            tvLoginName = (TextView) itemView.findViewById(R.id.textViewTitle);
            ivAvatar = (ImageView) itemView.findViewById(R.id.imageViewPoster);
        }
    }
}
