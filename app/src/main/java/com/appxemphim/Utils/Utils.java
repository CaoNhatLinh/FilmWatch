package com.appxemphim.Utils;

// Utils.java hoặc trong MainActivity.java
import android.content.Context;
import android.content.Intent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

import com.appxemphim.adapters.ListPhimAdapter;
import com.appxemphim.data.Phim;

public class Utils {

    public static <T> void setupRecyclerViewClickListener(Context context, RecyclerView recyclerView, ListPhimAdapter adapter, Class<T> targetActivity) {
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Phim phim = adapter.getPhimAtPosition(position);
                Intent intent = new Intent(context, targetActivity);
                intent.putExtra("MaPhim", phim.getMaPhim());
                context.startActivity(intent);
            }
        });
    }


}
