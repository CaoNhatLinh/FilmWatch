package com.appxemphim.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appxemphim.R;
import com.appxemphim.activities.ChiTietPhimActivity;
import com.appxemphim.data.BinhLuan;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private List<BinhLuan> binhLuanList;

    public CommentAdapter(ChiTietPhimActivity chiTietPhimActivity, List<BinhLuan> binhLuanList) {
        this.binhLuanList = binhLuanList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        BinhLuan binhLuan = binhLuanList.get(position);
        holder.bind(binhLuan);
    }

    @Override
    public int getItemCount() {
        return binhLuanList.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserName;
        private TextView tvComment;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvComment = itemView.findViewById(R.id.tvComment);
        }

        public void bind(BinhLuan binhLuan) {
            tvUserName.setText(String.valueOf(binhLuan.getMaNguoiDung()));
            tvComment.setText(binhLuan.getNoiDung());
        }

    }
}

