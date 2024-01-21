
public class Anakart extends BilgisayarParcasi{
    private String model;
    private String bellekTipi;
    private String megaHertz;

    public Anakart(String marka, String model, String bellekTipi, String megaHertz, int fiyat) {
        super(marka, fiyat);
        this.model = model;
        this.bellekTipi = bellekTipi;
        this.megaHertz = megaHertz;
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
    public String getBellekTipi() {
        return bellekTipi;
    }
    public void setBellekTipi(String bellekTipi) {
        this.bellekTipi = bellekTipi;
    }
    public String getMegaHertz() {
        return megaHertz;
    }
    public void setMegaHertz(String megaHertz) {
        this.megaHertz = megaHertz;
    }
    public int getFiyat() {
        return super.getFiyat();
    }
    public void setFiyat(int fiyat) {
        super.setFiyat(fiyat);
    }
    
    
    
    
}
