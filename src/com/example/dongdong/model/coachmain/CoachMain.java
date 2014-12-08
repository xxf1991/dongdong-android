package com.example.dongdong.model.coachmain;

import com.example.dongdong.model.BaseEntry;
import com.google.gson.annotations.SerializedName;

/**
 * @author deofly
 * @since 1.0 2014/11/24
 */
public class CoachMain extends BaseEntry {

    public interface Gender {
        int MALE = 1;
        int FEMALE = 2;
    }

    public interface JobType {
        int FULL_TIME = 1;
        int HALF_TIME = 2;
    }

    @SerializedName("id")
    private final int id;

    @SerializedName("avatar_url")
    private final String avatarUrl;

    @SerializedName("name")
    private final String name;

    @SerializedName("gender")
    private final int gender;

    @SerializedName("job_type")
    private final int jobType;

    @SerializedName("rating")
    private final float rating;

    @SerializedName("comment_count")
    private final int commentCount;

    @SerializedName("location")
    private final String location;

    @SerializedName("distance")
    private final int distance;

    public CoachMain(int id, String avatarUrl, String name, int gender, int jobType,
                     int rating, int commentCount, String location, int distance) {
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.gender = gender;
        this.jobType = jobType;
        this.rating = rating;
        this.commentCount = commentCount;
        this.location = location;
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public int getJobType() {
        return jobType;
    }

    public float getRating() {
        return rating;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public String getLocation() {
        return location;
    }

    public int getDistance() {
        return distance;
    }
}
