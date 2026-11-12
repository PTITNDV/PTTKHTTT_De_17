package vn.edu.ptit.cineman.model;

import java.time.LocalDate;

public class User5 {
    private int id;
    private String hoten, sodienthoai, email;
    private LocalDate ngaysinh;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getHoten() { return hoten; }
    public void setHoten(String hoten) { this.hoten = hoten; }
    public String getSodienthoai() { return sodienthoai; }
    public void setSodienthoai(String sodienthoai) { this.sodienthoai = sodienthoai; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getNgaysinh() { return ngaysinh; }
    public void setNgaysinh(LocalDate ngaysinh) { this.ngaysinh = ngaysinh; }
}
