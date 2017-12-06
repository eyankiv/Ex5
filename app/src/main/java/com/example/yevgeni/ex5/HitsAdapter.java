package com.example.yevgeni.ex5;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yevgeni on 29/11/2017.
 */

public class HitsAdapter extends RecyclerView.Adapter<HitsAdapter.HitsViewHolder> {
    ArrayList<Hit> hits;

    public HitsAdapter(ArrayList<Hit> hitsList){
        this.hits = hitsList;
    }

    @Override
    public HitsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.li_hit,parent,false);
        ImageView hitImage = view.findViewById(R.id.li_hit_imageView);
        HitsViewHolder hitsViewHolder = new HitsViewHolder(view,hitImage );
        return hitsViewHolder;
    }

    @Override
    public void onBindViewHolder(HitsViewHolder holder, int position) {
        Hit hit = hits.get(position);
        Picasso.with(holder.hitLayout.getContext()).load(hit.getWebformatURL()).placeholder(R.drawable.loading).into(holder.hitImageView);

    }

    @Override
    public int getItemCount() {
        return hits.size();
    }

    class HitsViewHolder extends RecyclerView.ViewHolder{
        View hitLayout;
        ImageView hitImageView;

        public HitsViewHolder(View itemView, ImageView imageView) {
            super(itemView);
            hitLayout = itemView;
            hitImageView = imageView;
        }
    }

}
