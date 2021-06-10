package com.makarov.readdleinternship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data {
    private static final List<Profile> profiles = new ArrayList<>();

    public static void initData() {

        // add 5 profiles with gravatar on the email
        for(int i = 0; i < 5; i++) {
            profiles.add(new Profile("Ivan Makarov",
                    "makarov18042003@gmail.com", randomBoolean()));
        }

        // add 20 profiles without custom gravatar
        for(int i = 0; i < 20; i++) {
            profiles.add(new Profile("Name #" + i,
                    "test"+ i +"@ukr.net",
                    randomBoolean()));
        }
    }

    public static void applyRandomChanges() {
        Collections.shuffle(profiles);

        for(Profile profile : profiles) {
            profile.setOnline(!profile.isOnline());
        }

        // remove a random element
        int randomElement = (int) (Math.random() * profiles.size());
        profiles.remove(randomElement);

        profiles.add(new Profile("Random", "random@readdle.com", randomBoolean()));
    }

    // method used for email validation can be add in future
    // tested with unit tests
    public static boolean validateEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = java.util.regex.Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static List<Profile> getProfiles() {
        return profiles;
    }

    public static Profile getProfile(int index) {
        return profiles.get(index);
    }

    private static boolean randomBoolean() {
        return Math.random() < 0.5;
    }
}
