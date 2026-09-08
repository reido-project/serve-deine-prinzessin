package sdp.content.prinzessins.kyoko.items;

import sdp.content.gameplay.inventory.items.Item;
import sdp.content.prinzessins.kyoko.items.behaviors.*;

import static sdp.content.gameplay.inventory.items.ItemID.*;
import static sdp.content.gameplay.inventory.items.Preference.*;

public final class KyokoItem{
    private static final Item[] items = {
        new Item(
            ITEM_01,
            "Adapter 500 Batang Rokok",
            "Adapter plastik yang dapat menampung hingga 500 batang rokok sekaligus. Bisa membuat asap sebanyak letusan Gunung Krakatau.",
            DISLIKED,
            true
        ),

        new Item(
            ITEM_02,
            "Ahegao Hoodie",
            "Hoodie beraroma bawang. Diclaim bisa nambah aura +99999.",
            DISLIKED,
            false
        ),

        new Item(
            ITEM_03,
            "Bit Particle Gun",
            "Mainan pistol plastik yang bisa mengganti channel TV dan menembakkan laser pointer merah ketika digunakan.",
            NEUTRAL,
            false
        ),

        new Item(
            ITEM_04,
            "Boneka Takeda Poro",
            "Boneka humanoid berbentuk kucing dengan tubuh kurus, kepala besar, dan ekepresi sedih. Katanya cocok buat hadiah ulang tahun.",
            NEUTRAL,
            false
        ),

        new Item(
            ITEM_05,
            "Buku \"Cara Melakukan 101\"",
            "Panduan praktis untuk melakukan berbagai hal. Bab 1: Cara Bernapas. Bab 2: Cara Berdiri. Bab 3: Cara Membuka Buku...",
            NEUTRAL,
            false
        ),

        new Item(
            ITEM_06,
            "Buku \"Cara Memahami Perempuan\"",
            "Buku setebal 2000 halaman yang ditulis dengan bahasa Kawi kuno.",
            LIKED,
            false
        ),

        new Item(
            ITEM_07,
            "Cyalume Saber",
            "Pedang lightsaber tiruan pake glowstick. Di gagangnya tertulis \"Future Gadget Lab\".",
            NEUTRAL,
            false
        ),

        new Item(
            ITEM_08,
            "DISTRUST Audio Player",
            "Sebuah Walkman yang hanya bisa memutar satu audio berjudul \"DISTRUST\".",
            LOVED,
            true
        ),

        new Item(
            ITEM_09,
            "Difteri Abed Khas Depok",
            "Kuliner bioterorisme dalam kotak kaca hasil fermentasi catering 2 bulan di balkon sekolah lantai 3.",
            HATED,
            false
        ),

        new Item(
            ITEM_10,
            "Jari Sukuna",
            "Benda kutukan tingkat khusus. Rasanya seperti dendeng kedaluwarsa.",
            new JariSukunaBehavior(ITEM_10)
        ),

        new Item(
            ITEM_11,
            "Kamus Bahasa Jawa",
            "Kamus bahasa Jawa 7 jilid. Di covernya tertulis \"Kyou\".",
            LIKED,
            false
        ),

        new Item(
            ITEM_12,
            "Kaset Album \"Kota Jahat\"",
            "Kaset album Terbalaskan Tujuh Lipat. Track 1: Binatang dan Pelacur. Track 2: Dibutakan Rantai. Track 3: Negara Kelelawar...",
            new KotaJahatBehavior(ITEM_12)
        ),

        new Item(
            ITEM_13,
            "Kaset VHS Anime Keputusasaan",
            "Anime karya Ryota Mitarai, bisa bikin orang jadi berputus asa.",
            HATED,
            true
        ),

        new Item(
            ITEM_14,
            "Kaset VHS Tutorial Debus Banten",
            "Video tutorial ilmu debus keluaran 2001 dengan kualitas 3gp.",
            NEUTRAL,
            false
        ),

        new Item(
            ITEM_15,
            "Kitab Cheat Playstation 2",
            "Kumpulan kode cheat legendaris untuk berbagai game Playstation 2. Beberapa halaman sudah hilang.",
            DISLIKED,
            false
        ),

        new Item(
            ITEM_16,
            "Kitab Primbon Betaljutur",
            "Kitab kuno yang memuat berbagai ramalan, petunjuk kehidupan, dan cara mengetahui apakah seseorang sedang kerasukan atau hanya sedang malas.",
            DISLIKED,
            false
        ),

        new Item(
            ITEM_17,
            "Life Note",
            "Buku terkutuk yang bisa menghidupkan orang jika namanya ditulis. Hanya efektif terhadap orang yang masih hidup.",
            LOVED,
            true
        ),

        new Item(
            ITEM_18,
            "Lukisan Gunung Kelir",
            "Lukisan legendaris karya Vincent van Fufufafa.",
            NEUTRAL,
            false
        ),

        new Item(
            ITEM_19,
            "Microphone Pelunas Hutang",
            "Sebuah microphone berwarna pink yang bisa membantu melunasi hutang pinjol. Diclaim bisa menghasilkan uang dengan nominal acak setiap 1 jam pemakaian.",
            new MicrophonePelunasBehavior(ITEM_19)
        ),

        new Item(
            ITEM_20,
            "NvidiMD Ryzen 4090TI",
            "GPU dengan performa luar biasa, mengalahkan semua GPU lain dalam uji benchmark. Hanya kompatibel dengan komputer IBN 5100.",
            NEUTRAL,
            false
        ),

        new Item(
            ITEM_21,
            "Patung Ayam Hias Asli Ngawi",
            "Patung berbentuk ayam ras Jomok, ayam hias yang hanya bisa ditemukan di kedalaman hutan di barat Ngawi.",
            NEUTRAL,
            false
        ),

        new Item(
            ITEM_22,
            "Phone Microwave (Name Subject to Change)",
            "Microwave yang telah dimodifikasi sehingga bisa mengubah pisang menjadi jelly hijau, lalu mengirimnya ke masa lalu.",
            LIKED,
            true
        ),

        new Item(
            ITEM_23,
            "Piring Bertanda \"Milik Sekolah\"",
            "Piring keramik dengan tulisan 'MILIK SEKOLAH' di bagian bawahnya.",
            DISLIKED,
            false
        ),

        new Item(
            ITEM_24,
            "Pistol Korek Api",
            "Korek api berbentuk pistol yang terbuat dari logam. Tidak mematikan, kecuali mungkin bagi orang yang memiliki ketakutan terhadap korek api berbentuk pistol.",
            LIKED,
            false
        ),

        new Item(
            ITEM_25,
            "Ramuan Posesi Iblis Khas Situbondo",
            "Ramuan racikan Dr. Yomemibas S.Jmk M.Bsing PhD untuk mengubah manusia pasif menjadi sangat agresif dengan kandungan ekstrak biji raja iblis Alas Baluran.",
            new RamuanPosesiBehavior(ITEM_25)
        ),

        new Item(
            ITEM_26,
            "Rumput Tetangga",
            "Sepotong tanah berumput dalam kotak kaca. Rumput ini akan selalu lebih hijau jika dibandingkan dengan rumput lain.",
            NEUTRAL,
            false
        ),

        new Item(
            ITEM_27,
            "Sendal Swallow Sebelah Kanan",
            "Sebuah sendal Swallow usang bertuliskan \"Fu'ad 49\". Keberadaan pasangannya tidak diketahui.",
            HATED,
            false
        ),

        new Item(
            ITEM_28,
            "Topi Sherlock Holmes Asli",
            "Sebuah topi deerstalker. Tag di topinya tertulis \"Pasar Merakmati\".",
            LOVED,
            true
        ),

        new Item(
            ITEM_29,
            "Ulet Bulu Inggris",
            "Ulet bulu Inggris super langka dari Yorkshire, bisa menangani masalah-masalah medis.",
            LIKED,
            false
        ),

        new Item(
            ITEM_30,
            "Wireless Gaming Headphone Ambaudio",
            "Headphone bersertifikasi Hi-Res Audio dan fitur \"Kerasukan\". Bassnya diclaim bisa membuatmu mendesah \"Omaygot\" secara spontan.",
            NEUTRAL,
            false
        )
    };

    public static Item[] getItems() {
        return items.clone();
    }
}
