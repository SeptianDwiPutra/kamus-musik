package septiandp.kamusistilahmusik;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import manajemendb.ManajemenDB;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ManajemenDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Enjoyin bro", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        db = ManajemenDB.dapatkanObjek(this);
        ArrayList<ManajemenDB.StrukturTabel> data =
                db.dapatkanSemuaData(ManajemenDB.TABEL_MUSIK);

        for(int i = 0; i < data.size(); i++) {
            System.out.println("kata : " + data.get(i).dapatkanData(1));
            System.out.println("deskripsi : " + data.get(i).dapatkanData(2));
            System.out.println("favorit : " + data.get(i).dapatkanData(3));
            System.out.println("\n");
        }

//        System.out.println("MENGUBAH DATA");
//        db.update(ManajemenDB.TABEL_MUSIK, "kata", "A",
//                "A", "Huruf pertama di abjad", "true");
//        System.out.println("DATA TERUBAH");
//        ManajemenDB.StrukturTabel dataA = db.dapatkanData(ManajemenDB.TABEL_MUSIK,
//                "kata", "A");
//        System.out.println("DATA YANG DIUBAH : " + dataA.dapatkanData(3));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_istilah_musik) {
            startActivity(new Intent(getApplicationContext(), IstilahMusik.class));
        }
        else if (id == R.id.nav_istilah_nama_musik) {
            startActivity(new Intent(getApplicationContext(), IstilahNamaMusik.class));
        }
        else if (id == R.id.nav_istilah_kata_musik) {
            startActivity(new Intent(getApplicationContext(), KataMusik.class));
        }
        else if (id == R.id.nav_saran_bacaan_musik) {
            startActivity(new Intent(getApplicationContext(), SaranBacaanMusik.class));
        }
        else if (id == R.id.nav_favorit) {
            startActivity(new Intent(getApplicationContext(), Favorite.class));
        }
        else if (id == R.id.nav_about) {
            startActivity(new Intent(getApplicationContext(), TentangAplikasi.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        db.tutup();
        super.onDestroy();
    }
}
