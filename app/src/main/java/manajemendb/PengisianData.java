package manajemendb;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class PengisianData {
    public static void isiData(SQLiteDatabase sqldb) {
        ContentValues val = new ContentValues();

        // TABEL MUSIK
        // Menu Istilah Musik
        val = new ContentValues();
        val.put("kata", "Agile");
        val.put("deskripsi", "Cekatan atau tangkas");
        val.put("nama_menu", "istilah_musik");
        val.put("favorit", "false");
        sqldb.insert(ManajemenDB.TABEL_MUSIK, null, val);

        val = new ContentValues();
        val.put("kata", "Acoperti");
        val.put("deskripsi", "Buka");
        val.put("nama_menu", "istilah_musik");
        val.put("favorit", "false");
        sqldb.insert(ManajemenDB.TABEL_MUSIK, null, val);

        // TABEL MUSIK
        // Menu Nama Musik
        val = new ContentValues();
        val.put("kata", "Abattuta");
        val.put("deskripsi", "Pada hitungan yang tepat");
        val.put("nama_menu", "nama_musik");
        val.put("favorit", "false");
        sqldb.insert(ManajemenDB.TABEL_MUSIK, null, val);

        val = new ContentValues();
        val.put("kata", "Aeva");
        val.put("deskripsi", "Lih.");
        val.put("nama_menu", "nama_musik");
        val.put("favorit", "false");
        sqldb.insert(ManajemenDB.TABEL_MUSIK, null, val);

        // TABEL MUSIK
        // Menu Kata Musik
        val = new ContentValues();
        val.put("kata", "Affannato");
        val.put("deskripsi", "Penuh kebebasan");
        val.put("nama_menu", "kata_musik");
        val.put("favorit", "false");
        sqldb.insert(ManajemenDB.TABEL_MUSIK, null, val);

        val = new ContentValues();
        val.put("kata", "Afflizione");
        val.put("deskripsi", "Cara main dengan rasa murung");
        val.put("nama_menu", "kata_musik");
        val.put("favorit", "false");
        sqldb.insert(ManajemenDB.TABEL_MUSIK, null, val);

        // TABEL SARAN BACAAN
        val = new ContentValues();
        val.put("judul_buku", "A General History of Music");
        val.put("penulis", "C.H. Barney");
        sqldb.insert(ManajemenDB.TABEL_SARAN_BACAAN, null, val);

        val = new ContentValues();
        val.put("judul_buku", "A Guide to Musical Style");
        val.put("penulis", "D. Moore");
        sqldb.insert(ManajemenDB.TABEL_SARAN_BACAAN, null, val);
    }
}
