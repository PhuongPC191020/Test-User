package fpoly.stile.com;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import fpoly.dao.com.ChuyenDeDAO;
import fpoly.model.com.ChuyenDe;

public class ChuyenDeDAOTest {
    private Connection connection;
    private ChuyenDeDAO chuyenDeDAO;

    @BeforeClass
    public void setUp() {
    	String url = "jdbc:sqlserver://localhost:1433;databaseName=VideoSharingDBB";
        String username = "sa";
        String password = "123";
        try {
            connection = DriverManager.getConnection(url, username, password);
            chuyenDeDAO = new ChuyenDeDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetAllChuyenDe() throws SQLException {
        List<ChuyenDe> chuyenDeList = chuyenDeDAO.getAllChuyenDe();
        Assert.assertNotNull(chuyenDeList);
        Assert.assertTrue(chuyenDeList.size() > 0);
    }

    @Test
    public void testGetChuyenDeById() throws SQLException {
        int maCD = 1; // Thay đổi thành mã chuyên đề tồn tại trong cơ sở dữ liệu của bạn
        ChuyenDe chuyenDe = chuyenDeDAO.getChuyenDeById(maCD);
        Assert.assertNotNull(chuyenDe);
        Assert.assertEquals(chuyenDe.getMaCD(), maCD);
    }

    @Test
    public void testAddChuyenDe() throws SQLException {
        ChuyenDe chuyenDe = new ChuyenDe(1, "java", "2000000", "24", "hh.jpg", "Mo ta chuyen de");
        boolean result = chuyenDeDAO.addChuyenDe(chuyenDe);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdateChuyenDe() throws SQLException {
        ChuyenDe chuyenDe = new ChuyenDe(1, "java1", "3000000", "30", "hhhh.jpg", "Mo ta chuyen de cap nhat");
        boolean result = chuyenDeDAO.updateChuyenDe(chuyenDe);
        Assert.assertTrue(result);
    }

    @Test
    public void testDeleteChuyenDe() throws SQLException {
        int maCD = 1; // Thay đổi thành mã chuyên đề đã thêm trong bước testAddChuyenDe()
        boolean result = chuyenDeDAO.deleteChuyenDe(maCD);
        Assert.assertTrue(result);
    }
}