package com.makarov.readdleinternship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsActivity extends AppCompatActivity {
    private CircleImageView profilePicture;
    private TextView profileName, profileStatus, profileEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        profilePicture = findViewById(R.id.profilePicture);
        profileName = findViewById(R.id.profileName);
        profileStatus = findViewById(R.id.profileStatus);
        profileEmail = findViewById(R.id.profileEmail);

        Intent detailsIntent = getIntent();
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
        Picasso.get().load(profile.getAvatarUrl()).error(R.drawable.default_icon)
                .resize(100, 100).into((ImageView) profilePicture);
    }
}