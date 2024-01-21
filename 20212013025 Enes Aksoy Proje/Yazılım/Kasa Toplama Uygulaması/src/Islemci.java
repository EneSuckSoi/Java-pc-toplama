
public class Islemci extends BilgisayarParcasi{
    private String model;
    private String gigaHertz;
    private int cekirdekSayisi;

    public Islemci(String marka, String model, String gigaHertz, int cekirdekSayisi, int fiyat) {
        super(marka, fiyat);
        this.model = model;
        this.gigaHertz = gigaHertz;
        this.cekirdekSayisi = cekirdekSayisi;
    }
    public String getMarka() {
        return super.getMarka();
    }
    public void setMarka(String marka) {
        super.setMarka(marka);
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getGigaHertz() {
        return gigaHertz;
    }
    public void setGigaHertz(String gigaHertz) {
        this.gigaHertz = gigaHertz;
    }
    public int getCekirdekSayisi() {
        return cekirdekSayisi;
    }
    public void setCekirdekSayisi(int cekirdekSayisi) {
        this.cekirdekSayisi = cekirdekSayisi;
    }
    public int getFiyat() {
        return super.getFiyat();
    }
    public void setFiyat(int fiyat) {
        super.setFiyat(fiyat);
    }
    
}
