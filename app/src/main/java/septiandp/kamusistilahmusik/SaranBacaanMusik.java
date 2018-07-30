package septiandp.kamusistilahmusik;

import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import manajemendb.ManajemenDB;
import manajemenlist.AdapterMusik;
import manajemenlist.AdapterSaran;
import manajemenlist.Musik;
import manajemenlist.OnFavClickListener;
import manajemenlist.SaranBacaan;

public class SaranBacaanMusik extends AppCompatActivity
        implements SearchView.OnQueryTextListener {

    private RecyclerView listNamaMusik;
    private SearchView srcCariMusik;

    private AdapterSaran adapter;
    private ManajemenDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saran_bacaan_musik);

        db = ManajemenDB.dapatkanObjek(this);

        listNamaMusik = (RecyclerView) findViewById(R.id.listSaranBacaan);
        listNamaMusik.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AdapterSaran(this, isiData());
        listNamaMusik.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_namamusik, menu);

        MenuItem menuCari = menu.findItem(R.id.menuCari);
        srcCariMusik = (SearchView) MenuItemCompat.getActionView(menuCari);
        srcCariMusik.setOnQueryTextListener(this);

        return true;
    }

    public ArrayList<SaranBacaan> isiData() {
        ArrayList<ManajemenDB.StrukturTabel> data =
                db.dapatkanSemuaData(ManajemenDB.TABEL_SARAN_BACAAN);

        ArrayList<SaranBacaan> dataSaran = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            SaranBacaan saran = new SaranBacaan(data.get(i).dapatkanData(1),
                                                data.get(i).dapatkanData(2));
            dataSaran.add(saran);
        }

        return dataSaran;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        adapter.getFilter().filter(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.getFilter().filter(s);
        return false;
    }
}
