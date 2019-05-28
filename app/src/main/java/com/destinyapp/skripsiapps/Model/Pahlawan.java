package com.destinyapp.skripsiapps.Model;

public class Pahlawan {
    String nama,remarks,photo,detail,lahir,wafat,langitude,longitude;

    public Pahlawan() {

    }
    public Pahlawan(String nama,String remarks,String photo,String detail,String lahir,String wafat,String langitude,String longitude){
        this.nama=nama;
        this.remarks=remarks;
        this.photo=photo;
        this.detail=detail;
        this.lahir=lahir;
        this.wafat=wafat;
        this.langitude=langitude;
        this.longitude=longitude;
    }



    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLahir() {
        return lahir;
    }

    public void setLahir(String lahir) {
        this.lahir = lahir;
    }

    public String getWafat() {
        return wafat;
    }

    public void setWafat(String wafat) {
        this.wafat = wafat;
    }

    public String getLangitude() {
        return langitude;
    }

    public void setLangitude(String langitude) {
        this.langitude = langitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
