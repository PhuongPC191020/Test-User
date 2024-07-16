package fpoly.dao.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fpoly.model.com.KhoaHoc;

public class KhoaHocDAO {
    private Connection connection;

   

	public KhoaHocDAO(Connection connection2) {
		// TODO Auto-generated constructor stub
	}

	// Phương thức để lấy danh sách tất cả các khóa học từ cơ sở dữ liệu
    public List<KhoaHoc> getAllKhoaHoc() throws SQLException {
        List<KhoaHoc> khoaHocList = new ArrayList<>();
        String sql = "SELECT * FROM KhoaHoc";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int maKH = resultSet.getInt("MaKH");
                int maCD = resultSet.getInt("MaCD");
                double hocPhi = resultSet.getDouble("HocPhi");
                String thoiLuong = resultSet.getString("ThoiLuong");
                Date ngay = resultSet.getDate("Ngay");
                String ghiChu = resultSet.getString("GhiChu");
                int maUser = resultSet.getInt("MaUser");
                KhoaHoc khoaHoc = new KhoaHoc(maKH, maCD, hocPhi, thoiLuong, ngay, ghiChu, maUser);
                khoaHocList.add(khoaHoc);
            }
        }
        return khoaHocList;
    }

    // Phương thức để lấy một khóa học dựa trên mã khóa học
    public KhoaHoc getKhoaHocById(int maKH) throws SQLException {
        String sql = "SELECT * FROM KhoaHoc WHERE MaKH = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, maKH);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int maCD = resultSet.getInt("MaCD");
                    double hocPhi = resultSet.getDouble("HocPhi");
                    String thoiLuong = resultSet.getString("ThoiLuong");
                    Date ngay = resultSet.getDate("Ngay");
                    String ghiChu = resultSet.getString("GhiChu");
                    int maUser = resultSet.getInt("MaUser");
                    return new KhoaHoc(maKH, maCD, hocPhi, thoiLuong, ngay, ghiChu, maUser);
                }
            }
        }
        return null;
    }

    // Phương thức để thêm một khóa học vào cơ sở dữ liệu
    public boolean addKhoaHoc(KhoaHoc khoaHoc) throws SQLException {
        String sql = "INSERT INTO KhoaHoc (MaCD, HocPhi, ThoiLuong, Ngay, GhiChu, MaUser) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, khoaHoc.getMaCD());
            statement.setDouble(2, khoaHoc.getHocPhi());
            statement.setString(3, khoaHoc.getThoiLuong());
            statement.setDate(4, new java.sql.Date(khoaHoc.getNgay().getTime()));
            statement.setString(5, khoaHoc.getGhiChu());
            statement.setInt(6, khoaHoc.getMaUser());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
    }

    // Phương thức để cập nhật thông tin của một khóa học trong cơ sở dữ liệu
    public boolean updateKhoaHoc(KhoaHoc khoaHoc) throws SQLException {
        String sql = "UPDATE KhoaHoc SET MaCD = ?, HocPhi = ?, ThoiLuong = ?, Ngay = ?, GhiChu = ?, MaUser = ? WHERE MaKH = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, khoaHoc.getMaCD());
            statement.setDouble(2, khoaHoc.getHocPhi());
            statement.setString(3, khoaHoc.getThoiLuong());
            statement.setDate(4, new java.sql.Date(khoaHoc.getNgay().getTime()));
            statement.setString(5, khoaHoc.getGhiChu());
            statement.setInt(6, khoaHoc.getMaUser());
            statement.setInt(7, khoaHoc.getMaKH());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    // Phương thức để xóa một khóa học từ cơ sở dữ liệu
    public boolean deleteKhoaHoc(int maKH) throws SQLException {
        String sql = "DELETE FROM KhoaHoc WHERE MaKH = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, maKH);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
    }

	
}
