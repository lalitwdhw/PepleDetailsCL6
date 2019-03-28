package com.cl.peopledetailscl6.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserPost implements Parcelable
{

    @SerializedName("userId")
    @Expose
    private long userId;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    public final static Parcelable.Creator<UserPost> CREATOR = new Creator<UserPost>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserPost createFromParcel(Parcel in) {
            return new UserPost(in);
        }

        public UserPost[] newArray(int size) {
            return (new UserPost[size]);
        }

    }
            ;

    protected UserPost(Parcel in) {
        this.userId = ((long) in.readValue((long.class.getClassLoader())));
        this.id = ((long) in.readValue((long.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.body = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public UserPost() {
    }

    /**
     *
     * @param id
     * @param body
     * @param title
     * @param userId
     */
    public UserPost(long userId, long id, String title, String body) {
        super();
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userId);
        dest.writeValue(id);
        dest.writeValue(title);
        dest.writeValue(body);
    }

    public int describeContents() {
        return 0;
    }

}