package manajemenlist;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import manajemendb.ManajemenDB;
import septiandp.kamusistilahmusik.R;

public class AdapterMusik extends RecyclerView.Adapter<AdapterMusik.ViewHolderMusik>
implements Filterable {

    // data yang akan dimasukkan ke RecyclerView
    private ArrayList<Musik> data;
    private Context context;
    private OnFavClickListener onFavClickListener;
    private ManajemenDB db;

    // custom filter
    private FilterMusik filter;

    public AdapterMusik(Context context, ArrayList<Musik> data) {
        this.data = data;
        this.context = context;
    }

    public ArrayList<Musik> getData() {
        return data;
    }

    public void setData(ArrayList<Musik> data) {
        this.data = data;
    }

    public void setOnFavClickListener(OnFavClickListener listener) {
        this.onFavClickListener = listener;
    }

    @Override
    public ViewHolderMusik onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rowcard,
                parent, false);

        db = ManajemenDB.dapatkanObjek(context);

        ViewHolderMusik holder = new ViewHolderMusik(view);
        holder.onFavClickListener = onFavClickListener;

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderMusik holder, int position) {
        holder.txNama.setText(data.get(position).getKata());
        holder.txDeskripsi.setText(data.get(position).getDeskripsi());

        if(data.get(position).isFavorit()) {
            holder.bintangNyala = true;
            holder.btnFav.setImageResource(android.R.drawable.star_big_on);
        }
        else {
            holder.bintangNyala = false;
            holder.btnFav.setImageResource(android.R.drawable.star_big_off);
        }
    }

    @Override
    public int getItemCount() {
        return (data != null) ? data.size() : 0;
    }

    @Override
    public Filter getFilter() {
        if(filter == null) {
            filter = new FilterMusik(data, this);
        }

        return filter;
    }

    class ViewHolderMusik extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public TextView txNama;
        public TextView txDeskripsi;
        public ImageButton btnFav;

        public OnFavClickListener onFavClickListener;
        public boolean bintangNyala = false;

        public ViewHolderMusik(View itemView) {
            super(itemView);

            txNama = (TextView) itemView.findViewById(R.id.txNama);
            txDeskripsi = (TextView) itemView.findViewById(R.id.txDeskripsi);
            btnFav = (ImageButton) itemView.findViewById(R.id.btnFav);

            btnFav.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            if(view.getId() == R.id.btnFav) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setMessage("Apakah anda yakin ?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        bintangNyala = !bintangNyala;

                        int id = (bintangNyala) ? android.R.drawable.star_big_on :
                                android.R.drawable.star_big_off;
                        btnFav.setImageResource(id);

                        onFavClickListener.onFavClick(bintangNyala,
                                txNama.getText().toString(),
                                view);
                    }
                });

                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                dialog.show();
            }
        }
    }
}
