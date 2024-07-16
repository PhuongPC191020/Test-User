package MavenReport.MavenReport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=VideoSharingDBB";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "123";

    @Test
    @Parameters({"username", "password"})
    public void testLogin(String username, String password) throws SQLException {
        // Thiết lập kết nối đến cơ sở dữ liệu
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Thực hiện truy vấn để kiểm tra thông tin đăng nhập
            String sql = "SELECT COUNT(*) FROM [User] WHERE Username = ? AND Password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Kiểm tra kết quả của truy vấn
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        // Nếu có một bản ghi khớp với username và password, đăng nhập thành công
                        boolean loggedIn = count > 0;
                        // Kiểm tra kết quả đăng nhập
                        Assert.assertTrue(loggedIn, "Đăng nhập không thành công cho user đầu tiên!");
                    }
                }
            }
        }
    }
}
