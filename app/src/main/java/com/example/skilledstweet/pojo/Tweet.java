package com.example.skilledstweet.pojo;

public class Tweet {
    private User user;
    private long id;
    private String creationDate;
    private String text;
    private Long retweetCount;
    private Long favoriteCount;
    private String imageUrl;

    public Tweet(User user, long id, String creationDate, String text, Long retweetCount, Long favoriteCount, String imageUrl) {
        this.user = user;
        this.id = id;
        this.creationDate = creationDate;
        this.text = text;
        this.retweetCount = retweetCount;
        this.favoriteCount = favoriteCount;
        this.imageUrl = imageUrl;
    }

    public User getUser() {
        return user;
    }

    public long getId() {
        return id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getText() {
        return text;
    }

    public Long getRetweetCount() {
        return retweetCount;
    }

    public Long getFavoriteCount() {
        return favoriteCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

        if (id != tweet.id) return false;
        if (!user.equals(tweet.user)) return false;
        if (!creationDate.equals(tweet.creationDate)) return false;
        if (!text.equals(tweet.text)) return false;
        if (!retweetCount.equals(tweet.retweetCount)) return false;
        if (!favoriteCount.equals(tweet.favoriteCount)) return false;
        return imageUrl != null ? imageUrl.equals(tweet.imageUrl) : tweet.imageUrl == null;
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + retweetCount.hashCode();
        result = 31 * result + favoriteCount.hashCode();
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        return result;
    }
}
