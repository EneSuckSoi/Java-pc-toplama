
public class PowerSupply extends BilgisayarParcasi{
    private String watt;

    public PowerSupply(String marka, String watt, int fiyat) {
        super(marka, fiyat);
        this.watt = watt;
    }

    public String getWatt() {
        return watt;
    }
    public void setWatt(String watt) {
        this.watt = watt;
    }

    @Override
    public void setFiyat(int fiyat) {
        super.setFiyat(fiyat); 
    }
    
    @Override
    public int getFiyat() {
        return super.getFiyat(); 
    }
    
    @Override
    public void setMarka(String marka) {
        super.setMarka(marka); 
    }
    
    @Override
    public String getMarka() {
        return super.getMarka(); 
    }
    
    
    
}
