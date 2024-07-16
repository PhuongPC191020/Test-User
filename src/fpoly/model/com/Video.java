package fpoly.model.com;

public class Video {
	private int videoId;
    private String title;
    private String description;
    private int userId;

    public Video(int videoId, String title, String description, int userId) {
        this.videoId = videoId;
        this.title = title;
        this.description = description;
        this.userId = userId;
    }

    // Getter và Setter cho các thuộc tính

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
