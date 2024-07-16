package fpoly.model.com;

public class ChuyenDe {
    private int MaCD;
    private String TenCD;
    private String HocPhi;
    private String ThoiLuong;
    private String Hinh;
    private String Mota;
    
    public ChuyenDe(int maCD, String tenCD, String hocPhi, String thoiLuong, String hinh, String mota) {
        this.MaCD = maCD;
        this.TenCD = tenCD;
        this.HocPhi = hocPhi;
        this.ThoiLuong = thoiLuong;
        this.Hinh = hinh;
        this.Mota = mota;
    }

	public int getMaCD() {
		return MaCD;
	}

	public void setMaCD(int maCD) {
		MaCD = maCD;
	}

	public String getTenCD() {
		return TenCD;
	}

	public void setTenCD(String tenCD) {
		TenCD = tenCD;
	}

	public String getHocPhi() {
		return HocPhi;
	}

	public void setHocPhi(String hocPhi) {
		HocPhi = hocPhi;
	}

	public String getThoiLuong() {
		return ThoiLuong;
	}

	public void setThoiLuong(String thoiLuong) {
		ThoiLuong = thoiLuong;
	}

	public String getHinh() {
		return Hinh;
	}

	public void setHinh(String hinh) {
		Hinh = hinh;
	}

	public String getMota() {
		return Mota;
	}

	public void setMota(String mota) {
		Mota = mota;
	}

    // Getter và Setter cho các thuộc tính
    
}
