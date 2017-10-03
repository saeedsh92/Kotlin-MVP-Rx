package ss.com.Kotlin_MVP_Rx.main.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author S.Shahini
 * @since 8/12/17
 */

public class NewsViewModel implements Parcelable {
    @SerializedName("author")
    private String author;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("url")
    private String url;
    @SerializedName("urlToImage")
    private String imageUrl;
    @SerializedName("publishedAt")
    private String date;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.author);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.url);
        dest.writeString(this.imageUrl);
        dest.writeString(this.date);
    }

    public NewsViewModel() {
    }

    protected NewsViewModel(Parcel in) {
        this.author = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.url = in.readString();
        this.imageUrl = in.readString();
        this.date = in.readString();
    }

    public static final Parcelable.Creator<NewsViewModel> CREATOR = new Parcelable.Creator<NewsViewModel>() {
        @Override
        public NewsViewModel createFromParcel(Parcel source) {
            return new NewsViewModel(source);
        }

        @Override
        public NewsViewModel[] newArray(int size) {
            return new NewsViewModel[size];
        }
    };
}
