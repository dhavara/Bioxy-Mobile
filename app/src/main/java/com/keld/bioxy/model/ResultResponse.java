package com.keld.bioxy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ResultResponse implements Parcelable {
    private String difficulty;
    private int point;
    private int total_correct;
    private int total_number;

    protected ResultResponse(Parcel in) {
        difficulty = in.readString();
        point = in.readInt();
        total_correct = in.readInt();
        total_number = in.readInt();
    }

    public static final Creator<ResultResponse> CREATOR = new Creator<ResultResponse>() {
        @Override
        public ResultResponse createFromParcel(Parcel in) {
            return new ResultResponse(in);
        }

        @Override
        public ResultResponse[] newArray(int size) {
            return new ResultResponse[size];
        }
    };

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getTotal_correct() {
        return total_correct;
    }

    public void setTotal_correct(int total_correct) {
        this.total_correct = total_correct;
    }

    public int getTotal_number() {
        return total_number;
    }

    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(difficulty);
        dest.writeInt(point);
        dest.writeInt(total_correct);
        dest.writeInt(total_number);
    }
}
