package fpoly.model.com;

import java.util.Date;

public class Share {
	 private int shareId;
	    private int userId;
	    private int videoId;
	    private Date shareDate;

	    public Share(int shareId, int userId, int videoId, Date shareDate) {
	        this.shareId = shareId;
	        this.userId = userId;
	        this.videoId = videoId;
	        this.shareDate = shareDate;
	    }

	    // Getter và Setter cho các thuộc tính

	    public int getShareId() {
	        return shareId;
	    }

	    public void setShareId(int shareId) {
	        this.shareId = shareId;
	    }

	    public int getUserId() {
	        return userId;
	    }

	    public void setUserId(int userId) {
	        this.userId = userId;
	    }

	    public int getVideoId() {
	        return videoId;
	    }

	    public void setVideoId(int videoId) {
	        this.videoId = videoId;
	    }

	    public Date getShareDate() {
	        return shareDate;
	    }

	    public void setShareDate(Date shareDate) {
	        this.shareDate = shareDate;
	    }
}
