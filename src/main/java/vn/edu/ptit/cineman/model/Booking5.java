package vn.edu.ptit.cineman.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Booking5 {
    private int id, customerId;
    private LocalDateTime ngaydat, hanthanhtoan;
    private String trangthai, ghichu;
    private BigDecimal tongtien;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(LocalDateTime ngaydat) {
        this.ngaydat = ngaydat;
    }

    public LocalDateTime getHanthanhtoan() {
        return hanthanhtoan;
    }

    public void setHanthanhtoan(LocalDateTime hanthanhtoan) {
        this.hanthanhtoan = hanthanhtoan;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public BigDecimal getTongtien() {
        return tongtien;
    }

    public void setTongtien(BigDecimal tongtien) {
        this.tongtien = tongtien;
    }
}
