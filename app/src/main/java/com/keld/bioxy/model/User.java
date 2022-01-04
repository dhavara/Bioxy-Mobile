package com.keld.bioxy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.List;

public class User implements Parcelable {


    private String message;
    private List<Users> users;

    protected User(Parcel in) {
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

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public static User objectFromData(String str) {

        return new Gson().fromJson(str, User.class);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public static class Users {
        private int id;
        private String username;
        private String name;
        private String email;
        private String school;
        private String city;
        private String birthdate;
        private Details details;

        public static Users objectFromData(String str) {

            return new Gson().fromJson(str, Users.class);
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(String birthdate) {
            this.birthdate = birthdate;
        }

        public Details getDetails() {
            return details;
        }

        public void setDetails(Details details) {
            this.details = details;
        }

        public static class Details {
            private Object user_image;
            private String point;
            private Object user_color;
            private Object user_frame;
            private int user_title;

            public static Details objectFromData(String str) {

                return new Gson().fromJson(str, Details.class);
            }

            public Object getUser_image() {
                return user_image;
            }

            public void setUser_image(Object user_image) {
                this.user_image = user_image;
            }

            public String getPoint() {
                return point;
            }

            public void setPoint(String point) {
                this.point = point;
            }

            public Object getUser_color() {
                return user_color;
            }

            public void setUser_color(Object user_color) {
                this.user_color = user_color;
            }

            public Object getUser_frame() {
                return user_frame;
            }

            public void setUser_frame(Object user_frame) {
                this.user_frame = user_frame;
            }

            public int getUser_title() {
                return user_title;
            }

            public void setUser_title(int user_title) {
                this.user_title = user_title;
            }
        }
    }
}
