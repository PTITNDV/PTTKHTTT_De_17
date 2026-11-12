package vn.edu.ptit.cineman.model;

import java.time.LocalDate;

public class Movie5 {
    private int id;
    private String tenphim, anhposter, daodien, videotrailer, dienvien, mota;
    private int thoiluong, gioihantuoi;
    private LocalDate ngaykhoichieu;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTenphim() { return tenphim; }
    public void setTenphim(String tenphim) { this.tenphim = tenphim; }
    public String getAnhposter() { return anhposter; }
    public void setAnhposter(String anhposter) { this.anhposter = anhposter; }
    public String getDaodien() { return daodien; }
    public void setDaodien(String daodien) { this.daodien = daodien; }
    public String getVideotrailer() { return videotrailer; }
    public void setVideotrailer(String videotrailer) { this.videotrailer = videotrailer; }
    public String getDienvien() { return dienvien; }
    public void setDienvien(String dienvien) { this.dienvien = dienvien; }
    public String getMota() { return mota; }
    public void setMota(String mota) { this.mota = mota; }
    public int getThoiluong() { return thoiluong; }
    public void setThoiluong(int thoiluong) { this.thoiluong = thoiluong; }
    public int getGioihantuoi() { return gioihantuoi; }
    public void setGioihantuoi(int gioihantuoi) { this.gioihantuoi = gioihantuoi; }
    public LocalDate getNgaykhoichieu() { return ngaykhoichieu; }
    public void setNgaykhoichieu(LocalDate ngaykhoichieu) { this.ngaykhoichieu = ngaykhoichieu; }
}
