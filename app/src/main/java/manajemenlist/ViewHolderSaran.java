package manajemenlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import septiandp.kamusistilahmusik.R;

public class ViewHolderSaran extends RecyclerView.ViewHolder {

    public TextView txJudul;
    public TextView txPenulis;

    public ViewHolderSaran(View itemView) {
        super(itemView);

        txJudul = (TextView) itemView.findViewById(R.id.txJudul);
        txPenulis = (TextView) itemView.findViewById(R.id.txPenulis);
    }
}