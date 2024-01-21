
public class BilgisayarParcasi {
    private String marka;
    private String ozellikler;
    private int fiyat;

    public BilgisayarParcasi(String marka, int fiyat) {
        this.marka = marka;
        this.fiyat = fiyat;
    }

    public BilgisayarParcasi(String marka, String ozellikler, int fiyat) {
        this.marka = marka;
        this.ozellikler = ozellikler;
        this.fiyat = fiyat;
    }

    BilgisayarParcasi() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    public String getMarka() {
        return marka;
    }
    public void setMarka(String marka) {
        this.marka = marka;
    }
    public String getOzellikler() {
        return ozellikler;
    }
    public void setOzellikler(String ozellikler) {
        this.ozellikler = ozellikler;
    }
    public int getFiyat() {
        return fiyat;
    }
    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }
    
}
