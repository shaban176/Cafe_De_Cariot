package com.uzziqam.cafedecariot.helper;

public class Products {
    public String img;
    public String ptitle;
    public String pdetail;

    public boolean pstatus;
    public int ptime;
    public Products() {
    }

    public Products(String img, String ptitle, String pdetail,boolean pstatus,int ptime) {
        this.img = img;
        this.ptitle = ptitle;
        this.pdetail = pdetail;
        this.pstatus=pstatus;
        this.ptime=ptime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getPdetail() {
        return pdetail;
    }

    public void setPdetail(String pdetail) {
        this.pdetail = pdetail;
    }

    public boolean isPstatus() {
        return pstatus;
    }

    public void setPstatus(boolean pstatus) {
        this.pstatus = pstatus;
    }

    public int getPtime() {
        return ptime;
    }

    public void setPtime(int ptime) {
        this.ptime = ptime;
    }
}
