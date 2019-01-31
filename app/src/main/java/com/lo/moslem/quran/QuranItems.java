package com.lo.moslem.quran;

import org.json.JSONObject;

public class QuranItems {
    private int id;
    private int chapterNumber;
    private int urutanWahyu;
    private int ayatDihitung;
    private int halaman;
    private String tempatWhayu;
    private String namaSurah;
    private String tulisanArab;
    private String artiSurah;
    private Boolean bismillahPre;

    public QuranItems (JSONObject object) {
        try {
            int id = object.getInt("id");
            int chapterNumber = object.getInt("chapter_number");
            int urutanWahyu = object.getInt("revelation_order");
            int ayatDihitung = object.getInt("verses_count");
            String tempatWhayu = object.getString("revelation_place");
            String namaSurah = object.getString("name_complex");
            String tulisanArab = object.getString("name_arabic");
            String artiSurah = object.getJSONObject("translated_name").getString("name");
            Boolean bismillahPre = object.getBoolean("bismillah_pre");
            this.id = id;
            this.chapterNumber = chapterNumber;
            this.urutanWahyu = urutanWahyu;
            this.ayatDihitung = ayatDihitung;
            this.halaman = halaman;
            this.tempatWhayu = tempatWhayu;
            this.namaSurah = namaSurah;
            this.tulisanArab =tulisanArab;
            this.artiSurah = artiSurah;
            this.bismillahPre = bismillahPre;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public int getUrutanWahyu() {
        return urutanWahyu;
    }

    public void setUrutanWahyu(int urutanWahyu) {
        this.urutanWahyu = urutanWahyu;
    }

    public int getAyatDihitung() {
        return ayatDihitung;
    }

    public void setAyatDihitung(int ayatDihitung) {
        this.ayatDihitung = ayatDihitung;
    }

    public int getHalaman() {
        return halaman;
    }

    public void setHalaman(int halaman) {
        this.halaman = halaman;
    }

    public String getTempatWhayu() {
        return tempatWhayu;
    }

    public void setTempatWhayu(String tempatWhayu) {
        this.tempatWhayu = tempatWhayu;
    }

    public String getNamaSurah() {
        return namaSurah;
    }

    public void setNamaSurah(String namaSurah) {
        this.namaSurah = namaSurah;
    }

    public String getTulisanArab() {
        return tulisanArab;
    }

    public void setTulisanArab(String tulisanArab) {
        this.tulisanArab = tulisanArab;
    }

    public String getArtiSurah() {
        return artiSurah;
    }

    public void setArtiSurah(String artiSurah) {
        this.artiSurah = artiSurah;
    }

    public Boolean getBismillahPre() {
        return bismillahPre;
    }

    public void setBismillahPre(Boolean bismillahPre) {
        this.bismillahPre = bismillahPre;
    }
}
