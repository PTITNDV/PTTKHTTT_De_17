package vn.edu.ptit.cineman.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CustomerRevenueStatistic5 {
    private Customer5 khachhang;
    private LocalDate ngaybatdau, ngayketthuc;
    private int solangiaodich;
    private BigDecimal tongdoanhthu;
    public Customer5 getKhachhang() { return khachhang; }
    public void setKhachhang(Customer5 khachhang) { this.khachhang = khachhang; }
    public LocalDate getNgaybatdau() { return ngaybatdau; }
    public void setNgaybatdau(LocalDate ngaybatdau) { this.ngaybatdau = ngaybatdau; }
    public LocalDate getNgayketthuc() { return ngayketthuc; }
    public void setNgayketthuc(LocalDate ngayketthuc) { this.ngayketthuc = ngayketthuc; }
    public int getSolangiaodich() { return solangiaodich; }
    public void setSolangiaodich(int solangiaodich) { this.solangiaodich = solangiaodich; }
    public BigDecimal getTongdoanhthu() { return tongdoanhthu; }
    public void setTongdoanhthu(BigDecimal tongdoanhthu) { this.tongdoanhthu = tongdoanhthu; }
}
