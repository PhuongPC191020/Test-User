package fpoly.model.com;

import java.util.Date;

public class KhoaHoc {
    private int maKH;
    private int maCD;
    private double hocPhi;
    private String thoiLuong;
    private Date ngay;
    private String ghiChu;
    private int maUser;
    
    
	public KhoaHoc(int maKH, int maCD, double hocPhi, String thoiLuong, Date ngay, String ghiChu, int maUser) {
		super();
		this.maKH = maKH;
		this.maCD = maCD;
		this.hocPhi = hocPhi;
		this.thoiLuong = thoiLuong;
		this.ngay = ngay;
		this.ghiChu = ghiChu;
		this.maUser = maUser;
	}
	public int getMaKH() {
		return maKH;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
	public int getMaCD() {
		return maCD;
	}
	public void setMaCD(int maCD) {
		this.maCD = maCD;
	}
	public double getHocPhi() {
		return hocPhi;
	}
	public void setHocPhi(double hocPhi) {
		this.hocPhi = hocPhi;
	}
	public String getThoiLuong() {
		return thoiLuong;
	}
	public void setThoiLuong(String thoiLuong) {
		this.thoiLuong = thoiLuong;
	}
	public Date getNgay() {
		return ngay;
	}
	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public int getMaUser() {
		return maUser;
	}
	public void setMaUser(int maUser) {
		this.maUser = maUser;
	}

   

}
