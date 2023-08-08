package com.uzziqam.cafedecariot.helper;

public class Deals {
    public String img;
    public String dtitle;
    public String ddetail;

    public Deals() {
    }

    public Deals(String img, String dtitle, String ddetail) {
        this.img = img;
        this.dtitle = dtitle;
        this.ddetail = ddetail;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDtitle() {
        return dtitle;
    }

    public void setDtitle(String dtitle) {
        this.dtitle = dtitle;
    }

    public String getDdetail() {
        return ddetail;
    }

    public void setDdetail(String ddetail) {
        this.ddetail = ddetail;
    }
}
