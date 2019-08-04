package com.destinyapp.skripsiapps.Model;

import java.util.ArrayList;

public class KuisModel {
    public static String[][] data = new String[][]{
            {"1",
                    "Tanggal Berapakah Kelahiran Siswondo Parman?",
                    "B",
                    "A. 9 Juni 1925",
                    "B. 4 Agustus 1918",
                    "C. 3 Desember 1918",
                    "D. 5 Maret 1909",
            },
            {"2",
                    "Tanggal Berapakah Agus Salim",
                    "C",
                    "A. 16 Desember 1817",
                    "B. 1 Oktober 1965",
                    "C. 4 November 1954",
                    "D. 9 April 1966",
            },
            {"3",
                    "Siapakah nama asli Pattimura",
                    "A",
                    "A. Thomas Matulessy",
                    "B. Agus Salim",
                    "C. Soetomo",
                    "D. Donald Isaac Panjaitan",
            },
            {"4",
                    "Dimanakah Supriyadi Lahir",
                    "C",
                    "A. Jawa Tengah",
                    "B. Jawa Barat",
                    "C. Jawa Timur",
                    "D. Bali",
            },
            {"5",
                    "Dimanakan Soetomo Lahir",
                    "A",
                    "A. Jawa Timur",
                    "B. Jawa Barat",
                    "C. Sulawesi Selatan",
                    "D. Jawa Tengah",
            },
            {"6",
                    "Dimanakah Ernest Douwes Dekker Lahir",
                    "B",
                    "A. Kalimantan",
                    "B. Jawa Timur",
                    "C. Jawa Barat",
                    "D. Bali",
            },
            {"7",
                    "Siapakah nama Panggilan Umar Said Cokrominoto",
                    "A",
                    "A. Tjokroaminoto",
                    "B. Ernest Douwes Dekker",
                    "C. Supriyadi",
                    "D. Agus Salim",
            },
            {"8",
                    "Siapakah Pendiri Serekat Dagang Islam",
                    "D",
                    "A. Abdoel Moeis",
                    "B. Mohammad Husni Thamrin",
                    "C. Ki Hadjar Dewantara",
                    "D. KH Samanhudi",
            },
            {"9",
                    "Tanggal berapakh Ki Hadjar Dewantara Dilahirkan",
                    "A",
                    "A. 2 Mei 1889",
                    "B. 16 Desember 1817",
                    "C. 9 Juni 1925",
                    "D. 5 Maret 1909",
            },
            {"10",
                    "Presiden Pertama Di Indonesia",
                    "D",
                    "A. Mohammad Hatta",
                    "B. Supriyadi",
                    "C. Ki Hadjar Dewantara",
                    "D. Soekarno",
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
