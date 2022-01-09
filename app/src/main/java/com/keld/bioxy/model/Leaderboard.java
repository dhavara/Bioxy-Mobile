package com.keld.bioxy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Leaderboard implements Parcelable {


    private String message;
    private List<Leaderboards> leaderboards;

    protected Leaderboard(Parcel in) {
        message = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Leaderboard> CREATOR = new Creator<Leaderboard>() {
        @Override
        public Leaderboard createFromParcel(Parcel in) {
            return new Leaderboard(in);
        }

        @Override
        public Leaderboard[] newArray(int size) {
            return new Leaderboard[size];
        }
    };

    public static Leaderboard objectFromData(String str) {

        return new Gson().fromJson(str, Leaderboard.class);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Leaderboards> getLeaderboards() {
        return leaderboards;
    }

    public void setLeaderboards(List<Leaderboards> leaderboards) {
        this.leaderboards = leaderboards;
    }

    public static class Leaderboards {
        private int id;
        private String username;
        private int point;
        private int accuracy;
        private int total_correct;
        private int total_question;
        private String difficulty;
        private String created_at;

        public static Leaderboards objectFromData(String str) {

            return new Gson().fromJson(str, Leaderboards.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public int getAccuracy() {
            return accuracy;
        }

        public void setAccuracy(int accuracy) {
            this.accuracy = accuracy;
        }

        public int getTotal_correct() {
            return total_correct;
        }

        public void setTotal_correct(int total_correct) {
            this.total_correct = total_correct;
        }

        public int getTotal_question() {
            return total_question;
        }

        public void setTotal_question(int total_question) {
            this.total_question = total_question;
        }

        public String getDifficulty() {
            return difficulty;
        }

        public void setDifficulty(String difficulty) {
            this.difficulty = difficulty;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
