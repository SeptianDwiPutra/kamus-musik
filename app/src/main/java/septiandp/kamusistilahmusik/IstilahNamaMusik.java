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
import android.widget.SearchView;

import java.util.ArrayList;

import manajemendb.ManajemenDB;
import manajemenlist.AdapterMusik;
import manajemenlist.Musik;
import manajemenlist.OnFavClickListener;

public class IstilahNamaMusik extends AppCompatActivity
        implements SearchView.OnQueryTextListener, Filter.FilterListener, OnFavClickListener {

    private RecyclerView listNamaMusik;
    private SearchView srcCariMusik;

    private AdapterMusik adapter;
    private ManajemenDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.istilah_nama_musik);

        db = ManajemenDB.dapatkanObjek(this);

        listNamaMusik = (RecyclerView) findViewById(R.id.listNamaMusik);
        listNamaMusik.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AdapterMusik(this, isiData());
        adapter.setOnFavClickListener(this);
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

    public ArrayList<Musik> isiData() {
        ArrayList<ManajemenDB.StrukturTabel> data =
                db.dapatkanSemuaData(ManajemenDB.TABEL_MUSIK);

        ArrayList<Musik> dataMusik = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            boolean favorit = data.get(i).dapatkanData(3).equals("true");
            Musik musik = new Musik(data.get(i).dapatkanData(1),
                    data.get(i).dapatkanData(2), favorit);

            dataMusik.add(musik);
        }

        return dataMusik;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        adapter.getFilter().filter(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.getFilter().filter(s, this);
        return false;
    }

    @Override
    public void onFavClick(boolean bintangNyala, String kata, View view) {
        ManajemenDB.StrukturTabel data =
                db.dapatkanData(ManajemenDB.TABEL_MUSIK, "kata", kata);
        String kataDariDB = data.dapatkanData(1);
        String deskripsiDariDB = data.dapatkanData(2);

        if(bintangNyala) {
            db.update(ManajemenDB.TABEL_MUSIK, "kata", kata,
                    kataDariDB, deskripsiDariDB, "true");
            data = db.dapatkanData(ManajemenDB.TABEL_MUSIK, "kata", kata);

            Snackbar.make(view, kata + " masuk favorit!", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();

            System.out.println("\n");
            System.out.println(kata + " MASUK KE FAVORIT");
            System.out.println("KATA " + kataDariDB);
            System.out.println("DESKRIPSI " + deskripsiDariDB);
            System.out.println("FAVORIT " + data.dapatkanData(3));
        }
        else {
            db.update(ManajemenDB.TABEL_MUSIK, "kata", kata,
                    kataDariDB, deskripsiDariDB, "false");
            data = db.dapatkanData(ManajemenDB.TABEL_MUSIK, "kata", kata);

            Snackbar.make(view, kata + " keluar dari favorit!", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();

            System.out.println("\n");
            System.out.println(kata + " KELUAR DARI FAVORIT");
            System.out.println("KATA " + kataDariDB);
            System.out.println("DESKRIPSI " + deskripsiDariDB);
            System.out.println("FAVORIT " + data.dapatkanData(3));
        }

        adapter.setData(isiData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFilterComplete(int i) {
        ArrayList<Musik> dataMusik = new ArrayList<>();

        for(int ind = 0; ind < i; ind++) {
            ManajemenDB.StrukturTabel dataDariDB =
                    db.dapatkanData(ManajemenDB.TABEL_MUSIK,
                            "kata", adapter.getData().get(ind).getKata());

            boolean fav = dataDariDB.dapatkanData(3).equals("true");
            dataMusik.add(new Musik(dataDariDB.dapatkanData(1),
                    dataDariDB.dapatkanData(2),
                    fav));
        }

        adapter.setData(dataMusik);
        adapter.notifyDataSetChanged();
    }
}
