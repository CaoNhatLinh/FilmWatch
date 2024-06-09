package com.appxemphim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.recyclerview.widget.RecyclerView;

import com.appxemphim.R;
import com.appxemphim.Utils.CapitalizeWords;
import com.appxemphim.data.TapPhim;

import java.util.List;

public class TapPhimAdapter extends RecyclerView.Adapter<TapPhimAdapter.TapPhimItemViewHolder> {
    private List<TapPhim> TapPhims;
    private Context context;


    public TapPhimAdapter( Context c,List<TapPhim> TapPhims) {
        this.TapPhims = TapPhims;
        this.context = c;
    }

    @Override
    public int getItemCount() {
        return TapPhims.size();
    }
    public void updateTapPhimList(List<TapPhim> newTapPhimList) {
        TapPhims.clear();
        TapPhims.addAll(newTapPhimList);
        notifyDataSetChanged();
    }
    @Override
    public TapPhimItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.buttontapphim, parent, false);

        return new TapPhimItemViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(TapPhimItemViewHolder holder, int position) {
        TapPhim u = TapPhims.get(position);
        holder.TitleTapPhim.setText(CapitalizeWords.capitalizeWords(u.getTenTap()));
    }
    public TapPhim getTapPhimAtPosition(int position) {
        return TapPhims.get(position);
    }
    public static class TapPhimItemViewHolder extends RecyclerView.ViewHolder {
        public Button TitleTapPhim;

        public TapPhimItemViewHolder(View itemView) {
            super(itemView);
            TitleTapPhim = (Button) itemView.findViewById(R.id.buttonTapPhim);

        }
    }

}
