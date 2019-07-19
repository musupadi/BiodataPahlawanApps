package com.destinyapp.skripsiapps.Model;

import java.util.ArrayList;

public class KuisModel {
    public static String[][] data = new String[][]{
            {"1",
                    "Siapakah pencipta lagu satu nusa satu bangsa...",
                    "B",
                    "A. Ibu Soed",
                    "B. Liberty Manik",
                    "C. Ismail Marzuki",
                    "D. Husein Mutahar",
            },
            {"2",
                    "Dari jawaban berikut mana yang tidak termasuk ciptaan lagu dari Ibu Soed...",
                    "C",
                    "A. Bendera merah putih",
                    "B. Berkibarlah benderaku",
                    "C. Indonesia pusaka",
                    "D. Desaku",
            },
            {"3",
                    "Apa judul lagu yang benar dari potongan lirik berikut! \"Masa yang akan datang kewajibanmu lah, Menjadi tanggunganmu terhadap nusa\"",
                    "A",
                    "A. Bangun pemudi-pemuda",
                    "B. Syukur",
                    "C. Mengheningkan cipta",
                    "D. Indonesia subur",
            },
            {"4",
                    "Lagu berjudul \"Ayam Den Lapeh\" berasal dari daerah mana...",
                    "C",
                    "A. Jawa Tengah",
                    "B. Sumatra selatan",
                    "C. Sumatra Barat",
                    "D. Kalimantan tengah",
            },
            {"5",
                    "\"Cah angon cah angon penekna blimbing kuwi\n" +
                            "Lunyu lunyu penekna kanggo mbasuh dodotira\" dari potongan lirik tersebut, lagu tersebut berasal dari...",
                    "A",
                    "A. Jawa Tengah",
                    "B. Jawa Barat",
                    "C. Sulawesi Selatan",
                    "D. Jawa Timur",
            },
            {"6",
                    "\"Adammu samanna silappae... alla silappae Ruttungem manengngi\"\n" +
                            "manakah yang termasuk judul lagu dari lirik lagu di atas...",
                    "B",
                    "A. Bagimu Negeri",
                    "B. Ambo Logo",
                    "C. Hela Rotan",
                    "D. Ketabo",
            },
            {"7",
                    "Dari pilihan berikut ini, manakah judul lagu daerah yang keduanya berasal dari Nusa Tenggara Timur...",
                    "A",
                    "A. Putar-Putar Kopi dan Manalolo Banda",
                    "B. Anak Kukang dan Anak Kambing Saya",
                    "C. Ayo Mama dan Bajing Luncat",
                    "D. Bebilin dan Putar-Putar Kopi",
            },
            {"8",
                    "Pencipta lagu \"Gugur Bunga\" adalah...",
                    "D",
                    "A. Sartono",
                    "B. Husein Mutahar",
                    "C. Ibu Soed",
                    "D. Ismail Marzuki",
            },
            {"9",
                    "Lagu berjudul Goro-Goro Ne berasal dari provinsi...",
                    "A",
                    "A. Maluku",
                    "B. Nusa Tenggara Barat ",
                    "C. Kalimantan Tengah",
                    "D. Sumatera Barat",
            },
            {"10",
                    "Pencipta lagu Jembatan Merah adalah...",
                    "D",
                    "A. Ibu Soed",
                    "B. Cornel Simanjuntak",
                    "C. Wage Rudolf Soepratman",
                    "D. Gesang",
            },
    };
    public static ArrayList<DataModel> getListData(){
        DataModel models = null;
        ArrayList<DataModel> list = new ArrayList<>();
        for (String[] aData : data) {
            models = new DataModel();
            models.setNo(aData[0]);
            models.setSoal(aData[1]);
            models.setJawaban(aData[2]);
            models.setJawabana(aData[3]);
            models.setJawabanb(aData[4]);
            models.setJawabanc(aData[5]);
            models.setJawaband(aData[6]);
            list.add(models);
        }
        return list;
    }
}
