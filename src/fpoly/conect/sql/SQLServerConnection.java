package fpoly.conect.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnection {
	// Thay đổi thông tin kết nối cho phù hợp với cấu hình của bạn
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=VideoSharingDBB";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Kết nối tới cơ sở dữ liệu
            connection = SQLServerConnection.getConnection();
            System.out.println("Kết nối thành công!");

            // Thực hiện các thao tác với cơ sở dữ liệu ở đây

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối sau khi sử dụng
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Đã đóng kết nối!");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
