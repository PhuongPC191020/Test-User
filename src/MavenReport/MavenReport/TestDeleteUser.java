package MavenReport.MavenReport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDeleteUser	 {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=VideoSharingDBB";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "123";

    @Test
    public void testDeleteUser() throws SQLException {
        // Thiết lập kết nối đến cơ sở dữ liệu
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Xóa user và tất cả các video của user
            String usernameToDelete = "user1";
            deleteUserAndVideos(connection, usernameToDelete);

            // Kiểm tra xem user đã được xóa thành công hay không
            boolean userDeleted = isUserDeleted(connection, usernameToDelete);
            Assert.assertTrue(userDeleted, "Không thể xóa user thành công");
        }
    }

    // Phương thức để xóa user và tất cả các video của user từ cơ sở dữ liệu
    private void deleteUserAndVideos(Connection connection, String username) throws SQLException {
        // Xóa tất cả các video của user
        deleteVideosOfUser(connection, username);

        // Xóa user
        String deleteUserSQL = "DELETE FROM [User] WHERE Username = ?";
        try (PreparedStatement deleteUserStatement = connection.prepareStatement(deleteUserSQL)) {
            deleteUserStatement.setString(1, username);
            deleteUserStatement.executeUpdate();
        }
    }

    // Phương thức để xóa tất cả các video của một user
    private void deleteVideosOfUser(Connection connection, String username) throws SQLException {
        String deleteVideosSQL = "DELETE FROM Video WHERE UserID = (SELECT UserID FROM [User] WHERE Username = ?)";
        try (PreparedStatement deleteVideosStatement = connection.prepareStatement(deleteVideosSQL)) {
            deleteVideosStatement.setString(1, username);
            deleteVideosStatement.executeUpdate();
        }
    }

    // Phương thức để kiểm tra xem user đã được xóa thành công hay không
    private boolean isUserDeleted(Connection connection, String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM [User] WHERE Username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 0;
                }
            }
        }
        return false;
    }
    
    // Kiểm tra xem người dùng có tồn tại không
    private boolean doesUserExist(Connection connection, String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM [User] WHERE Username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    // Kiểm tra xem tất cả các video của người dùng đã được xóa thành công hay không
    private boolean areVideosDeleted(Connection connection, String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Video WHERE UserID = (SELECT UserID FROM [User] WHERE Username = ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 0;
                }
            }
        }
        return false;
    }
}
