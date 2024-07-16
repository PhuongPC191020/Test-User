package fpoly.stile.com;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestUpdateVideo {

    @Test
    public void testUpdateVideo() {
        // Video ID cần được cập nhật
        String videoID = "1";
        String newTitle = "New Title";
        String newDescription = "New Description";

        // Gọi phương thức cập nhật video và kiểm tra kết quả
        boolean updated = UpdateVideo.updateVideo(videoID, newTitle, newDescription);
        
        // Kiểm tra xem video đã được cập nhật thành công hay không
        Assert.assertTrue(updated, "Không thể cập nhật video");

        // Giả sử giờ bạn muốn kiểm tra khi không có ID video được cung cấp
        // Để kiểm tra việc cập nhật thất bại khi không có ID
        String invalidVideoID = null; // Đây là trường hợp không có ID video được cung cấp

        // Gọi phương thức cập nhật video mà không cung cấp ID và kiểm tra kết quả
        boolean updatedWithoutID = UpdateVideo.updateVideo(invalidVideoID, newTitle, newDescription);

        // Kiểm tra xem việc cập nhật có thất bại khi không có ID video được cung cấp hay không
        Assert.assertFalse(updatedWithoutID, "Cập nhật video không thất bại khi không có ID được cung cấp");
    }
}
