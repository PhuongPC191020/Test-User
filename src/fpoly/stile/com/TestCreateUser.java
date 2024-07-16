package fpoly.stile.com;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCreateUser {

    @Test
    public void testCreateUser_Success() {
        String username = "haha11";
        String password = "111";
        String email = "haha@example.com";

        // Gọi phương thức để tạo người dùng mới và kiểm tra kết quả
        boolean created = CreateUser.createUser(username, password, email);

        // Kiểm tra xem người dùng đã được tạo thành công hay không
        Assert.assertTrue(created, "Không thể tạo người dùng mới");
    }

    @Test
    public void testCreateUser_Fail_UsernameExists() {
        String existingUsername = "admin";
        String password = "123";
        String email = "admin@example.com";

        // Gọi phương thức để tạo người dùng mới với username đã tồn tại và kiểm tra kết quả
        boolean created = CreateUser.createUser(existingUsername, password, email);

        // Kiểm tra xem người dùng đã được tạo thành công hay không
        Assert.assertFalse(created, "Không thể tạo người dùng mới với username đã tồn tại");
    }
}
