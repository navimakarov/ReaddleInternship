package com.makarov.readdleinternship;

import fr.tkeunebr.gravatar.Gravatar;

public class Profile {
    private boolean isOnline;
    private String username, email, avatarUrl;

    public Profile(String username, String email, boolean isOnline) {
        this.isOnline = isOnline;
        this.username = username;
        this.email = email;
        
        this.avatarUrl = Gravatar.init().with(this.email).defaultImage(Gravatar.DefaultImage.MYSTERY_MAN).size(100).build();
        // get avatar with size 100 (100 - DetailsActivity, 48 - MainActivity)
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
