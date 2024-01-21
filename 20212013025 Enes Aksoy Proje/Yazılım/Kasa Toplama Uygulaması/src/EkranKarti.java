
public class EkranKarti extends BilgisayarParcasi{
    private String model;
    private String bellekBoyutu;

    public EkranKarti(String marka, String model, String bellekBoyutu, int fiyat) {
        super(marka, fiyat);
        this.model = model;
        this.bellekBoyutu = bellekBoyutu;
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
    public String getBellekBoyutu() {
        return bellekBoyutu;
    }
    public void setBellekBoyutu(String bellekBoyutu) {
        this.bellekBoyutu = bellekBoyutu;
    }
    public int getFiyat() {
        return super.getFiyat();
    }
    public void setFiyat(int fiyat) {
        super.setFiyat(fiyat);
    }

}
