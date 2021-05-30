package com.makarov.readdleinternship;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    public static final int SPAN_LIST_COUNT = 1;
    public static final int SPAN_GRID_COUNT = 5;

    private static final int VIEW_TYPE_SMALL = 1;
    private static final int VIEW_TYPE_BIG = 2;

    private List<Profile> profiles;
    private GridLayoutManager layoutManager;

    public ItemAdapter(List profiles, GridLayoutManager layoutManager) {
        this.profiles = profiles;
        this.layoutManager = layoutManager;
    }

    @Override
    public int getItemViewType(int position) {
        int spanCount = layoutManager.getSpanCount();
        if(spanCount == SPAN_LIST_COUNT) {
            return VIEW_TYPE_BIG;
        }
        else {
            return VIEW_TYPE_SMALL;
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(viewType == VIEW_TYPE_BIG) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_big, parent, false);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_small, parent, false);
        }
        return new ItemViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Profile profile = profiles.get(position);
        if(profile.isOnline()) {
            holder.userOnline.setVisibility(View.VISIBLE);
        }
        else {
            holder.userOnline.setVisibility(View.INVISIBLE);
        }

        if(getItemViewType(position) == VIEW_TYPE_BIG) {
            holder.username.setText(profile.getUsername());
        }

        Picasso.get().load(profile.getAvatarUrl()).error(R.drawable.default_icon)
                .into(holder.userAvatar);

    }


    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        View avatarView;
        ImageView userAvatar, userOnline;
        TextView username;

        ItemViewHolder(View itemView, int viewType) {
            super(itemView);

            avatarView = itemView.findViewById(R.id.avatarLayout);
            userAvatar = avatarView.findViewById(R.id.userAvatar);
            userOnline = avatarView.findViewById(R.id.userOnline);
            if(viewType == VIEW_TYPE_BIG) {
                username = itemView.findViewById(R.id.userName);
            }
        }
    }
}
