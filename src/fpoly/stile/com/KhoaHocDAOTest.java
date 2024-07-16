package fpoly.stile.com;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import fpoly.dao.com.KhoaHocDAO;
import fpoly.model.com.KhoaHoc;

public class KhoaHocDAOTest {
    private Connection connection;
    private KhoaHocDAO khoaHocDAO;

    @BeforeClass
    public void setUp() {
        try {
            // Kết nối tới cơ sở dữ liệu test (thay đổi thông tin kết nối phù hợp)
            String url = "jdbc:sqlserver://localhost:1433;databaseName=VideoSharingDBB";
            String username = "sa";
            String password = "123";
            connection = DriverManager.getConnection(url, username, password);
            khoaHocDAO = new KhoaHocDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAllKhoaHoc() throws SQLException {
        List<KhoaHoc> khoaHocList = khoaHocDAO.getAllKhoaHoc();
        assertNotNull(khoaHocList);
        // Kiểm tra số lượng khóa học lấy được
        assertTrue(khoaHocList.size() > 0);
    }

    @Test
    public void testAddKhoaHoc() throws SQLException {
        // Tạo một khóa học mới để thêm vào cơ sở dữ liệu
        KhoaHoc khoaHoc = new KhoaHoc(1, 1, 1000.0, "3 tháng", new Date(), "Ghi chú", 1);
        assertTrue(khoaHocDAO.addKhoaHoc(khoaHoc));

        // Kiểm tra xem khóa học đã được thêm thành công chưa
        KhoaHoc insertedKhoaHoc = khoaHocDAO.getKhoaHocById(khoaHoc.getMaKH());
        assertNotNull(insertedKhoaHoc);
        assertEquals(insertedKhoaHoc.getMaKH(), khoaHoc.getMaKH());
    }

    @Test(dependsOnMethods = { "testAddKhoaHoc" })
    public void testUpdateKhoaHoc() throws SQLException {
        // Lấy một khóa học từ cơ sở dữ liệu để cập nhật thông tin
        KhoaHoc khoaHocToUpdate = khoaHocDAO.getKhoaHocById(1);
        assertNotNull(khoaHocToUpdate);
        
        // Cập nhật thông tin khóa học
        khoaHocToUpdate.setGhiChu("Ghi chú mới");
        assertTrue(khoaHocDAO.updateKhoaHoc(khoaHocToUpdate));
        
        // Kiểm tra xem thông tin đã được cập nhật thành công chưa
        KhoaHoc updatedKhoaHoc = khoaHocDAO.getKhoaHocById(1);
        assertNotNull(updatedKhoaHoc);
        assertEquals(updatedKhoaHoc.getGhiChu(), "Ghi chú mới");
    }

    @Test(dependsOnMethods = { "testUpdateKhoaHoc" })
    public void testDeleteKhoaHoc() throws SQLException {
        // Xóa một khóa học từ cơ sở dữ liệu
        assertTrue(khoaHocDAO.deleteKhoaHoc(1));

        // Kiểm tra xem khóa học đã bị xóa thành công chưa
        KhoaHoc deletedKhoaHoc = khoaHocDAO.getKhoaHocById(1);
        assertNull(deletedKhoaHoc);
    }
}
