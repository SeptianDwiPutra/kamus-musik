package septiandp.kamusistilahmusik;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TentangAplikasi extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tentang_aplikasi);

        webView = (WebView) findViewById(R.id.webView);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        String dataHtml = dapatkanHtml("index.html");
        webView.loadDataWithBaseURL("file:///android_asset/", dataHtml,
                "text/html", "UTF-8", null);
    }

    public String dapatkanHtml(String namaFile) {
        String hasil = "";

        try {
            InputStream inputStream = getAssets().open(namaFile);
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader buffReader = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();
            String baris = null;

            while((baris = buffReader.readLine()) != null) {
                sb.append(baris).append("\n");
            }

            hasil = sb.toString();

            buffReader.close();
            isr.close();
            inputStream.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        return hasil;
    }
}
