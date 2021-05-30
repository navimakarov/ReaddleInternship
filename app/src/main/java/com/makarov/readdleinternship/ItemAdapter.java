package com.makarov.readdleinternship;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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

        Picasso.get().load(profile.getAvatarUrl()).error(R.drawable.default_icon).into((ImageView) holder.userAvatar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.userAvatar.setTransitionName("profilePicture" + position);
        }
        holder.itemView.setTag(position);
    }


    @Override
    public int getItemCount() {
        return profiles.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        View avatarView;
        CircleImageView  userAvatar;
        ImageView userOnline;
        TextView username;

        ItemViewHolder(View itemView, int viewType) {
            super(itemView);

            avatarView = itemView.findViewById(R.id.avatarLayout);
            userAvatar = avatarView.findViewById(R.id.userAvatar);
            userOnline = avatarView.findViewById(R.id.userOnline);
            if(viewType == VIEW_TYPE_BIG) {
                username = itemView.findViewById(R.id.userName);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CircleImageView profilePicture = view.findViewById(R.id.userAvatar);
                    Intent detailsIntent = new Intent(itemView.getContext(), DetailsActivity.class);
                    detailsIntent.putExtra("index", itemView.getTag().toString());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        detailsIntent.putExtra("transition_name", profilePicture.getTransitionName());
                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation((Activity) itemView.getContext(), (View)profilePicture, profilePicture.getTransitionName());
                        itemView.getContext().startActivity(detailsIntent, options.toBundle());
                    } else {
                        itemView.getContext().startActivity(detailsIntent);
                    }
                }
            });
        }
    }
}
