package com.makarov.readdleinternship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        CircleImageView profilePicture = findViewById(R.id.profilePicture);
        TextView profileName = findViewById(R.id.profileName);
        TextView profileStatus = findViewById(R.id.profileStatus);
        TextView profileEmail = findViewById(R.id.profileEmail);

        Intent detailsIntent = getIntent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // if animation is possible - assign profilePicture with transition name in extra
            String imageTransitionName = detailsIntent.getStringExtra("transition_name");
            postponeEnterTransition();
            profilePicture.setTransitionName(imageTransitionName);
        }

        int index = Integer.parseInt(detailsIntent.getStringExtra("index"));
        Profile profile = Data.getProfile(index);


        profileName.setText(profile.getUsername());
        if(profile.isOnline()) {
            profileStatus.setTextColor(getResources().getColor(R.color.green));
            profileStatus.setText(getResources().getString(R.string.online));
        }
        else {
            profileStatus.setTextColor(getResources().getColor(R.color.gray));
            profileStatus.setText(getResources().getString(R.string.offline));
        }
        profileEmail.setText(profile.getEmail());
        Picasso.get().load(profile.getAvatarUrl()).error(R.drawable.default_icon).into((ImageView) profilePicture, new Callback() {
            @Override
            public void onSuccess() {
                supportStartPostponedEnterTransition();
            }

            @Override
            public void onError(Exception e) {
                supportStartPostponedEnterTransition();
            }
        });

    }

    /**
     * Override back button on actionbar onClick
     * finish activity to get back to MainActivity with chosen layout
     * layout on MainActivity(list or grid) doesn't reset after getting back
     * @param item backButton on actionbar
     * @return true as only one MenuItem present
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}