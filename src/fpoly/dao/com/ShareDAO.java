package fpoly.dao.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ShareDAO {
    private Connection connection;

    public ShareDAO(Connection connection) {
        this.connection = connection;
    }

    public void shareVideo(int userId, int videoId) throws SQLException {
        String sql = "INSERT INTO Share (UserID, VideoID) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, videoId);
            statement.executeUpdate();
        }
    }
}