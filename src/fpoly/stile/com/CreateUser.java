package fpoly.stile.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateUser {

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=VideoSharingDBB";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "123";

    public static boolean createUser(String username, String password, String email) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Kiểm tra xem username đã tồn tại trong cơ sở dữ liệu chưa
            if (isUsernameExists(connection, username)) {
                return false; // Trả về false nếu username đã tồn tại
            }

            // Tạo câu lệnh SQL để chèn người dùng mới vào cơ sở dữ liệu
            String sql = "INSERT INTO [User] (username, password, email) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, email); // Thêm giá trị cho cột 'Email'

                // Thực thi câu lệnh SQL để chèn người dùng mới vào cơ sở dữ liệu
                int rowsAffected = statement.executeUpdate();

                // Trả về true nếu có ít nhất một hàng đã được thêm vào cơ sở dữ liệu
                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Trả về false nếu có bất kỳ lỗi nào xảy ra trong quá trình tạo người dùng mới
            return false;
        }
    }

    private static boolean isUsernameExists(Connection connection, String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM [User] WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Trả về true nếu username đã tồn tại
                }
            }
        }
        return false; // Trả về false nếu có lỗi xảy ra hoặc không tìm thấy username trong cơ sở dữ liệu
    }
}
