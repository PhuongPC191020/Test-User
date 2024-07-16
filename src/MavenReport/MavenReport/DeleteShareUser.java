package MavenReport.MavenReport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteShareUser {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=VideoSharingDBB";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "123";

    @Test
    public void testDeleteShareUser() throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String usernameToDelete = "user1";

            // Xóa tất cả các mục chia sẻ liên quan đến người dùng
            deleteSharesOfUser(connection, usernameToDelete);

            // Kiểm tra xem các mục chia sẻ đã được xóa thành công hay không
            boolean sharesDeleted = areSharesDeleted(connection, usernameToDelete);
            Assert.assertTrue(sharesDeleted, "Không thể xóa các mục chia sẻ của người dùng");
        }
    }

    // Phương thức để xóa tất cả các mục chia sẻ liên quan đến một người dùng
    private void deleteSharesOfUser(Connection connection, String username) throws SQLException {
        String deleteSharesSQL = "DELETE FROM Share WHERE UserID = (SELECT UserID FROM [User] WHERE Username = ?)";
        try (PreparedStatement deleteSharesStatement = connection.prepareStatement(deleteSharesSQL)) {
            deleteSharesStatement.setString(1, username);
            deleteSharesStatement.executeUpdate();
        }
    }

    // Phương thức để kiểm tra xem tất cả các mục chia sẻ của người dùng đã được xóa thành công hay không
    private boolean areSharesDeleted(Connection connection, String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Share WHERE UserID = (SELECT UserID FROM [User] WHERE Username = ?)";
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
