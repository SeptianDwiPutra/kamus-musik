package manajemendb;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class PengisianData {
    public static void isiData(SQLiteDatabase sqldb) {
        ContentValues val = new ContentValues();

        // TABEL MUSIK
        val = new ContentValues();
        val.put("kata", "Abattuta");
        val.put("deskripsi", "Pada hitungan yang tepat");
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
