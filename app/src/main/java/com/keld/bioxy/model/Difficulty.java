package com.keld.bioxy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Difficulty implements Parcelable {

    private String message;
    private List<Difficulties> difficulties;

    protected Difficulty(Parcel in) {
        message = in.readString();
    }

    public static final Creator<Difficulty> CREATOR = new Creator<Difficulty>() {
        @Override
        public Difficulty createFromParcel(Parcel in) {
            return new Difficulty(in);
        }

        @Override
        public Difficulty[] newArray(int size) {
            return new Difficulty[size];
        }
    };

    public static Difficulty objectFromData(String str) {

        return new Gson().fromJson(str, Difficulty.class);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Difficulties> getDifficulties() {
        return difficulties;
    }

    public void setDifficulties(List<Difficulties> difficulties) {
        this.difficulties = difficulties;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
    }

    public static class Difficulties {
        private int id;
        private String difficulty;
        private int health;

        public static Difficulties objectFromData(String str) {

            return new Gson().fromJson(str, Difficulties.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDifficulty() {
            return difficulty;
        }

        public void setDifficulty(String difficulty) {
            this.difficulty = difficulty;
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }
    }
}
