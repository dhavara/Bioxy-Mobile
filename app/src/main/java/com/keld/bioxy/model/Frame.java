package com.keld.bioxy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Frame implements Parcelable{


    private String message;
    private List<Frames> frames;

    protected Frame(Parcel in) {
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

    public static final Creator<Frame> CREATOR = new Creator<Frame>() {
        @Override
        public Frame createFromParcel(Parcel in) {
            return new Frame(in);
        }

        @Override
        public Frame[] newArray(int size) {
            return new Frame[size];
        }
    };

    public static Frame objectFromData(String str) {

        return new Gson().fromJson(str, Frame.class);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Frames> getFrames() {
        return frames;
    }

    public void setFrames(List<Frames> frames) {
        this.frames = frames;
    }

    public static class Frames {
        private int id;
        private String name;
        private String image_path;
        private int price;

        public static Frames objectFromData(String str) {

            return new Gson().fromJson(str, Frames.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage_path() {
            return image_path;
        }

        public void setImage_path(String image_path) {
            this.image_path = image_path;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
