package MavenReport.MavenReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import fpoly.conect.sql.SQLServerConnection;

public class LoginAll implements ITest {
    private String username;
    private String password;

    public LoginAll(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getTestName() {
        return getClass().getSimpleName() + "-" + username;
    }

    @Factory
    public static Object[] create() {
        return new Object[]{
                new LoginAll("admin", "123"),
                new LoginAll("user1", "123"),
                new LoginAll("user2", "123"),
                new LoginAll("user3", "123"),
                new LoginAll("user4", "123")
        };
    }

    @Test
    public void testLogin() {
        // Thực hiện kiểm tra đăng nhập
        boolean loggedIn = checkLogin(username, password);

        // Kiểm tra kết quả đăng nhập
        Assert.assertTrue(loggedIn, "Đăng nhập không thành công cho user: " + username);
    }

    private boolean checkLogin(String username, String password) {
        // Thiết lập kết nối đến cơ sở dữ liệu
        try (Connection connection = SQLServerConnection.getConnection()) {
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
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            // Xử lý hoặc log ngoại lệ ở đây
            e.printStackTrace();
        }
        return false;
    }
}
