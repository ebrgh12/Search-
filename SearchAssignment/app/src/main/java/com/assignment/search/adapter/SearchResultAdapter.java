package com.assignment.search.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.search.R;
import com.assignment.search.model.ItemListElement;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchViewHolder> {

    private List<ItemListElement> items;

    public SearchResultAdapter(List<ItemListElement> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SearchViewHolder(
                LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.layout_search_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {
        ItemListElement item = items.get(i);
        searchViewHolder.titleTV.setText(item.getResult().getName());
        searchViewHolder.subTitleTV.setText(item.getResult().getDescription());
        if(item.getResult().getImage()!=null && item.getResult().getImage().getContentUrl() != null) {
            Glide.with(searchViewHolder.itemView.getContext())
                    .load(item.getResult().getImage().getContentUrl())
                    .placeholder(R.color.gray)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .crossFade()
                    .into(searchViewHolder.resultIV);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTV;
        private TextView subTitleTV;
        private ImageView resultIV;


        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleTV);
            subTitleTV = itemView.findViewById(R.id.subtitleTV);
            resultIV = itemView.findViewById(R.id.resultIV);
        }
    }
}
