package fpoly.stile.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateVideo {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=VideoSharingDBB";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "123";

    public static boolean updateVideo(String videoID, String newTitle, String newDescription) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE Video SET Title = ?, Description = ? WHERE VideoID = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, newTitle);
                statement.setString(2, newDescription);
                statement.setString(3, videoID);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
