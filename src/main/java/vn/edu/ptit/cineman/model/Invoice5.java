package vn.edu.ptit.cineman.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Invoice5 {
    private int id, bookingId, diemthuong, soluongve;
    private LocalDateTime ngaylap;
    private BigDecimal tongtien;
    private String trangthai;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    public LocalDateTime getNgaylap() { return ngaylap; }
    public void setNgaylap(LocalDateTime ngaylap) { this.ngaylap = ngaylap; }
    public BigDecimal getTongtien() { return tongtien; }
    public void setTongtien(BigDecimal tongtien) { this.tongtien = tongtien; }
    public int getDiemthuong() { return diemthuong; }
    public void setDiemthuong(int diemthuong) { this.diemthuong = diemthuong; }
    public int getSoluongve() { return soluongve; }
    public void setSoluongve(int soluongve) { this.soluongve = soluongve; }
    public String getTrangthai() { return trangthai; }
    public void setTrangthai(String trangthai) { this.trangthai = trangthai; }
}
