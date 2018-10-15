package com.udacity.sandwichclub;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;

import java.util.List;

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.ViewHolder> {

    private List<Sandwich> sandwichList;

    public SandwichAdapter(List<Sandwich> sandwichList) {
        this.sandwichList = sandwichList;
    }

    @Override
    public int getItemCount() {
        return sandwichList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sandwich_list_row, parent, false);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sandwich sandwich = sandwichList.get(position);
        holder.sandwichTitle.setText(sandwich.getMainName());
        holder.sandwichDescription.setText(sandwich.getDescription());

        String imageUrl = sandwich.getImage();
        Picasso.get()
                .load(imageUrl)
                .resize(80, 80)
                .centerCrop()
                .placeholder(R.drawable.sandwich_placeholder)
                .error(R.drawable.sandwich_placeholder)
                .into(holder.sandwichImage);

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView sandwichTitle, sandwichDescription;
        private ImageView sandwichImage;

        private ViewHolder(View view) {
            super(view);
            sandwichTitle = view.findViewById(R.id.tv_list_title);
            sandwichDescription = view.findViewById(R.id.tv_list_description);
            sandwichImage = view.findViewById(R.id.iv_image_list);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
            view.getContext().startActivity(intent);
        }
    }
}
