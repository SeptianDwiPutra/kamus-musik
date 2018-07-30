package manajemenlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import septiandp.kamusistilahmusik.R;

public class ViewHolderMusik extends RecyclerView.ViewHolder
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
    public void onClick(View view) {
        if(view.getId() == R.id.btnFav) {
            bintangNyala = !bintangNyala;

            int id = (bintangNyala) ? android.R.drawable.star_big_on :
                    android.R.drawable.star_big_off;
            btnFav.setImageResource(id);

            onFavClickListener.onFavClick(bintangNyala, txNama.getText().toString(), view);
        }
    }
}