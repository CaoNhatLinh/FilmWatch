package com.appxemphim.Utils;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.appxemphim.R;

public class ItemClickSupport {
    private final RecyclerView recyclerView;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    private final RecyclerView.OnChildAttachStateChangeListener attachListener
            = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (onItemClickListener != null) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(v);
                        onItemClickListener.onItemClicked(recyclerView, holder.getAdapterPosition(), v);
                    }
                });
            }
            if (onItemLongClickListener != null) {
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(v);
                        return onItemLongClickListener.onItemLongClicked(recyclerView, holder.getAdapterPosition(), v);
                    }
                });
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {
        }
    };

    private ItemClickSupport(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.recyclerView.setTag(R.id.viewDetailPhim, this);
        this.recyclerView.addOnChildAttachStateChangeListener(attachListener);
    }

    public static ItemClickSupport addTo(RecyclerView view) {
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.viewDetailPhim);
        if (support == null) {
            support = new ItemClickSupport(view);
        }
        return support;
    }

    public static ItemClickSupport removeFrom(RecyclerView view) {
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.viewDetailPhim);
        if (support != null) {
            support.detach(view);
        }
        return support;
    }

    public ItemClickSupport setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
        return this;
    }

    public ItemClickSupport setOnItemLongClickListener(OnItemLongClickListener listener) {
        onItemLongClickListener = listener;
        return this;
    }

    private void detach(RecyclerView view) {
        view.removeOnChildAttachStateChangeListener(attachListener);
        view.setTag(R.id.viewDetailPhim, null);
    }

    public interface OnItemClickListener {
        void onItemClicked(RecyclerView recyclerView, int position, View v);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClicked(RecyclerView recyclerView, int position, View v);
    }
}
