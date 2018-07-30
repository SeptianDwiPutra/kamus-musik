package manajemenlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

import manajemendb.ManajemenDB;
import septiandp.kamusistilahmusik.R;

public class AdapterSaran extends RecyclerView.Adapter<ViewHolderSaran>
        implements Filterable {

    // data yang akan dimasukkan ke RecyclerView
    private ArrayList<SaranBacaan> data;
    private Context context;
    private ManajemenDB db;

    // custom filter
    private FilterSaran filter;

    public AdapterSaran(Context context, ArrayList<SaranBacaan> data) {
        this.data = data;
        this.context = context;
    }

    public ArrayList<SaranBacaan> getData() {
        return data;
    }

    public void setData(ArrayList<SaranBacaan> data) {
        this.data = data;
    }

    @Override
    public ViewHolderSaran onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rowcard_saran,
                parent, false);

        db = ManajemenDB.dapatkanObjek(context);

        ViewHolderSaran holder = new ViewHolderSaran(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderSaran holder, int position) {
        holder.txJudul.setText(data.get(position).getJudul());
        holder.txPenulis.setText(data.get(position).getPenulis());
    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    @Override
    public Filter getFilter() {
        if(filter == null) {
            filter = new FilterSaran(data, this);
        }

        return filter;
    }
}
