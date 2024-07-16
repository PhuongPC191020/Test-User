package fpoly.dao.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fpoly.model.com.ChuyenDe;

public class ChuyenDeDAO {
    private Connection connection;

    public ChuyenDeDAO(Connection connection) {
        this.connection = connection;
    }

    public List<ChuyenDe> getAllChuyenDe() throws SQLException {
        List<ChuyenDe> chuyenDeList = new ArrayList<>();
        String sql = "SELECT * FROM ChuyenDe";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int maCD = resultSet.getInt("MaCD");
                String tenCD = resultSet.getString("TenCD");
                String hocPhi = resultSet.getString("HocPhi");
                String thoiLuong = resultSet.getString("ThoiLuong");
                String hinh = resultSet.getString("Hinh");
                String moTa = resultSet.getString("Mota");
                ChuyenDe chuyenDe = new ChuyenDe(maCD, tenCD, hocPhi, thoiLuong, hinh, moTa);
                chuyenDeList.add(chuyenDe);
            }
        }
        return chuyenDeList;
    }

    public ChuyenDe getChuyenDeById(int maCD) throws SQLException {
        String sql = "SELECT * FROM ChuyenDe WHERE MaCD = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, maCD);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String tenCD = resultSet.getString("TenCD");
                    String hocPhi = resultSet.getString("HocPhi");
                    String thoiLuong = resultSet.getString("ThoiLuong");
                    String hinh = resultSet.getString("Hinh");
                    String moTa = resultSet.getString("Mota");
                    return new ChuyenDe(maCD, tenCD, hocPhi, thoiLuong, hinh, moTa);
                }
            }
        }
        return null;
    }

    public boolean addChuyenDe(ChuyenDe chuyenDe) throws SQLException {
        String sql = "INSERT INTO ChuyenDe (TenCD, HocPhi, ThoiLuong, Hinh, Mota) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, chuyenDe.getTenCD());
            statement.setString(2, chuyenDe.getHocPhi());
            statement.setString(3, chuyenDe.getThoiLuong());
            statement.setString(4, chuyenDe.getHinh());
            statement.setString(5, chuyenDe.getMota());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
    }

    public boolean updateChuyenDe(ChuyenDe chuyenDe) throws SQLException {
        String sql = "UPDATE ChuyenDe SET TenCD = ?, HocPhi = ?, ThoiLuong = ?, Hinh = ?, Mota = ? WHERE MaCD = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, chuyenDe.getTenCD());
            statement.setString(2, chuyenDe.getHocPhi());
            statement.setString(3, chuyenDe.getThoiLuong());
            statement.setString(4, chuyenDe.getHinh());
            statement.setString(5, chuyenDe.getMota());
            statement.setInt(6, chuyenDe.getMaCD());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public boolean deleteChuyenDe(int maCD) throws SQLException {
        String sql = "DELETE FROM ChuyenDe WHERE MaCD = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, maCD);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
    }
}
