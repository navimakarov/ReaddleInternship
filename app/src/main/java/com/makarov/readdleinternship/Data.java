package com.makarov.readdleinternship;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private static List<Profile> profiles = new ArrayList<>();

    public static void initData() {
        for(int i = 0; i < 5; i++) {
            profiles.add(new Profile("Ivan Makarov",
                    "makarov18042003@gmail.com", randomBoolean()));
        }

        for(int i = 0; i < 20; i++) {
            profiles.add(new Profile("Name #" + String.valueOf(i),
                    "test"+String.valueOf(i)+"@ukr.net",
                    randomBoolean()));
        }
    }

    public static void applyRandomChanges() {

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
