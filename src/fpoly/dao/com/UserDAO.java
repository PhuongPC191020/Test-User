package fpoly.dao.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fpoly.model.com.User;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM [User] WHERE Username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("UserID");
                    String email = resultSet.getString("Email");
                    String password = resultSet.getString("Password");
                    return new User(userId, username, email, password);
                }
            }
        }
        return null;
    }
}
