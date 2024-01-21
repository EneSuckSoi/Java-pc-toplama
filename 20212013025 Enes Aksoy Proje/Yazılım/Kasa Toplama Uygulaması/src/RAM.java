
public class RAM extends BilgisayarParcasi{
    private String bellekMiktari;
    private String bellekTipi;
    private String megaHertz;

    public RAM(String marka,String bellekMiktari, String bellekTipi, String megaHertz,  int fiyat) {
        super(marka, fiyat);
        this.bellekMiktari = bellekMiktari;
        this.bellekTipi = bellekTipi;
        this.megaHertz = megaHertz;
    }

    

    public String getMarka() {
        return super.getMarka();
    }
    public void setMarka(String marka) {
        super.setMarka(marka);
    }
    public String getBellekMiktari() {
        return bellekMiktari;
    }
    public void setBellekMiktari(String bellekBoyutu) {
        this.bellekMiktari = bellekBoyutu;
    }
    public String getBellekTipi() {
        return bellekTipi;
    }
    public void setBellekTipi(String bellekTipi) {
        this.bellekTipi = bellekTipi;
    }
    public String getMegaHertz() {
        return megaHertz;
    }
    public void setMegaHertz(String mHz) {
        this.megaHertz = megaHertz;
    }
    public int getFiyat() {
        return super.getFiyat();
    }
    public void setFiyat(int fiyat) {
        super.setFiyat(fiyat);
    }
    
}
