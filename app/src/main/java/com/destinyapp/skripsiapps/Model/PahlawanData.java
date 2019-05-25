package com.destinyapp.skripsiapps.Model;

import java.util.ArrayList;

public class PahlawanData {
    public static String[][] data = new String[][]{
            {"Ki Hadjar Dewantara",
                    "Pahlawan Nasional",
                    "https://upload.wikimedia.org/wikipedia/commons/3/3a/Ki_Hadjar_Dewantara_Mimbar_Umum_18_October_1949_p2.jpg",
                    "Raden Mas Soewardi Soerjaningrat (EYD: Suwardi Suryaningrat, Sejak 1972 menjadi Ki Hadjar Dewantara, EYD : Ki Hajar Dewantara, beberapa menuliskan bunyi bahasa jawanya dengan Ki Hadjar Dewantoro.\nIa dikukuhkan sebagai pahlawan nasional yang ke-2 oleh Presiden RI, Soekarno, pada 28 November 1959(Surat Keputusan Presiden Republik Indonesia No.305 Tahun 1959,Tanggal 28 November 1959)",
                    "2 Mei 1889",
                    "26 April 1959",
                    "-7.806546",
                    "110.382414"
            },
            {"Abdoel Moeis",
                    "Pahlawan Kemerdekaan Nasional",
                    "https://upload.wikimedia.org/wikipedia/commons/d/de/Abdul_Muis%2C_Pekan_Buku_Indonesia_1954%2C_p211.jpg",
                    "Abdoel Moeis adalah seorang Minangkabau,putra datuk Tumanggung Sutan Sulaiman. Ayahnya merupakan seorang demang yang keras menentang kebijakan belanda di dataran tinggi Agam. Selesai dari ELS,Abdul Muis melanjutkan pendidikannya ke stovia (sekolah kedokteran, sekarang Fakultas kedokteran Universitas Indonesia), Jakarta.Namun karena sakit,ia tidak menyelesaikan pendidikannya",
                    "3 Juli 1883",
                    "17 Juni 1959",
                    "-7.806546",
                    "110.382414"
            },
            {"Mohammad Husni Thamrin",
                    "Pahlawan Kemerdekaan Nasional",
                    "https://cdns.klimg.com/merdeka.com/i/w/tokoh/2012/03/15/4548/200x300/mohammad-hoesni-thamrin.jpg",
                    "Thamrin Lahir di Waltevreden,Batavia(Sekarang Jakarta), Hindia Belanda, pada 16 Februari 1894. Ayahnya adalah seorang Belanda dengan ibu orang Betawi. Sejak kecil ia dirawat oleh oamannya dari pihak ibu karena ayahnya meninggal,sehingga ia tidak menyandang nama belanda. Sementara itu kakeknya,Ort, seorang Inggris, merupakan pemilik hotel di bilangan Petojo, menikah dengan seorang Betawi yang bernama Noeraini",
                    "16 February 1894",
                    "11 Januari 1941",
                    "-7.806546",
                    "110.382414"
            }
    };
    public static ArrayList<ModelPahlawan> getListData(){
        ModelPahlawan modelPahlawan = null;
        ArrayList<ModelPahlawan> list = new ArrayList<>();
        for (String[] aData : data) {
            modelPahlawan = new ModelPahlawan();
            modelPahlawan.setNama(aData[0]);
            modelPahlawan.setRemarks(aData[1]);
            modelPahlawan.setPhoto(aData[2]);
            modelPahlawan.setDetail(aData[3]);
            modelPahlawan.setLahir(aData[4]);
            modelPahlawan.setWafat(aData[5]);
            modelPahlawan.setLangitude(aData[6]);
            modelPahlawan.setLongitude(aData[7]);
            list.add(modelPahlawan);
        }

        return list;
    }
}
