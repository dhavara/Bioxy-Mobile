package com.keld.bioxy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class Soal implements Parcelable {


    private String message;
    private List<Soals> soals;

    protected Soal(Parcel in) {
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

    public static final Creator<Soal> CREATOR = new Creator<Soal>() {
        @Override
        public Soal createFromParcel(Parcel in) {
            return new Soal(in);
        }

        @Override
        public Soal[] newArray(int size) {
            return new Soal[size];
        }
    };

    public static Soal objectFromData(String str) {

        return new Gson().fromJson(str, Soal.class);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Soals> getSoals() {
        return soals;
    }

    public void setSoals(List<Soals> soals) {
        this.soals = soals;
    }

    public static class Soals {
        private int id;
        private String question;
        private String soal_image;
        private String answer_correct;
        private String answer_1;
        private String answer_2;
        private String answer_3;
        private String answer_4;
        private List<String> difficulties;

        public static Soals objectFromData(String str) {

            return new Gson().fromJson(str, Soals.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getSoal_image() {
            return soal_image;
        }

        public void setSoal_image(String soal_image) {
            this.soal_image = soal_image;
        }

        public String getAnswer_correct() {
            return answer_correct;
        }

        public void setAnswer_correct(String answer_correct) {
            this.answer_correct = answer_correct;
        }

        public String getAnswer_1() {
            return answer_1;
        }

        public void setAnswer_1(String answer_1) {
            this.answer_1 = answer_1;
        }

        public String getAnswer_2() {
            return answer_2;
        }

        public void setAnswer_2(String answer_2) {
            this.answer_2 = answer_2;
        }

        public String getAnswer_3() {
            return answer_3;
        }

        public void setAnswer_3(String answer_3) {
            this.answer_3 = answer_3;
        }

        public String getAnswer_4() {
            return answer_4;
        }

        public void setAnswer_4(String answer_4) {
            this.answer_4 = answer_4;
        }

        public List<String> getDifficulties() {
            return difficulties;
        }

        public void setDifficulties(List<String> difficulties) {
            this.difficulties = difficulties;
        }
    }
}
