package manajemenlist;

import android.widget.Filter;

import java.util.ArrayList;

public class FilterSaran extends Filter {
    AdapterSaran adapter;
    ArrayList<SaranBacaan> data;

    public FilterSaran(ArrayList<SaranBacaan> data, AdapterSaran adapter) {
        this.adapter = adapter;
        this.data = data;
    }

    // lakukan proses filtering
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        // cek validitas teks yang akan dicari (constraint)
        if (constraint != null && constraint.length() > 0) {
            // ubah teks ke uppercase
            constraint = constraint.toString().toUpperCase();
            // sediakan array untuk menyimpan data hasil filter
            ArrayList<SaranBacaan> dataTerfilter = new ArrayList<>();

            for (int i = 0; i < data.size(); i++) {
                // cek masing-masing data
                if (data.get(i).getJudul().toUpperCase().contains(constraint)) {
                    // masukkan data ke array
                    dataTerfilter.add(data.get(i));
                }
            }

            // simpan ukuran data dan salin datanya ke results
            results.count = dataTerfilter.size();
            results.values = (ArrayList<SaranBacaan>) dataTerfilter;
        }
        else {
            // jika data original kosong, maka tetap simpan ukuran dan data ke results
            results.count = data.size();
            results.values = (ArrayList<SaranBacaan>) data;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        // set data dari adapter dengan hasil pencarian
        adapter.setData((ArrayList<SaranBacaan>)results.values);

        // beri tau adapter bahwa data telah diubah
        adapter.notifyDataSetChanged();
    }
}
