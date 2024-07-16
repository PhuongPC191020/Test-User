package fpoly.dao.com;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fpoly.model.com.Video;

public class VideoDAO {
    private Connection connection;

    public VideoDAO(Connection connection) {
        this.connection = connection;
    }

    public void addVideo(Video video) throws SQLException {
        String sql = "INSERT INTO Video (Title, Description, UserID) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, video.getTitle());
            statement.setString(2, video.getDescription());
            statement.setInt(3, video.getUserId());
            statement.executeUpdate();
        }
    }
}