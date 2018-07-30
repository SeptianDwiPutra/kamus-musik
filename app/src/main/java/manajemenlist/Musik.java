package manajemenlist;

public class Musik {
    private String kata;
    private String deskripsi;
    private boolean favorit;

    public Musik(String kata, String deskripsi, boolean favorit) {
        this.kata = kata;
        this.deskripsi = deskripsi;
        this.favorit = favorit;
    }

    public String getKata() {
        return kata;
    }

    public void setKata(String kata) {
        this.kata = kata;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public boolean isFavorit() {
        return favorit;
    }

    public void setFavorit(boolean favorit) {
        this.favorit = favorit;
    }
}
