package interview.com.doordashlite.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Restaurant implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("status_type")
    private String statusType;
    @SerializedName("status")
    private String status;
    @SerializedName("cover_img_url")
    private String coverImageUrl;
    @SerializedName("is_only_catering")
    private boolean isOnlyCatering;

    public Restaurant(String id, String name, String description, String statusType, String status, String coverImageUrl, boolean isOnlyCatering) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.statusType = statusType;
        this.status = status;
        this.coverImageUrl = coverImageUrl;
        this.isOnlyCatering = isOnlyCatering;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public boolean isOnlyCatering() {
        return isOnlyCatering;
    }

    public void setOnlyCatering(boolean onlyCatering) {
        isOnlyCatering = onlyCatering;
    }
}
