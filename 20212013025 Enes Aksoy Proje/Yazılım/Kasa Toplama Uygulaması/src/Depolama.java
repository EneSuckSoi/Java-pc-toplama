
public class Depolama extends BilgisayarParcasi{
    private String diskTuru;
    private String depolamaBoyutu;
    private String okumaYazmaHizi;

    public Depolama(String marka, String diskTuru, String depolamaBoyutu, String okumaYazmaHizi, int fiyat) {
        super(marka, fiyat);
        this.diskTuru = diskTuru;
        this.depolamaBoyutu = depolamaBoyutu;
        this.okumaYazmaHizi = okumaYazmaHizi;
    }

    public String getMarka() {
        return super.getMarka();
    }
    public void setMarka(String marka) {
        super.setMarka(marka);
    }
    public String getDiskTuru() {
        return diskTuru;
    }
    public void setDiskTuru(String diskTuru) {
        this.diskTuru = diskTuru;
    }
    public String getDepolamaBoyutu() {
        return depolamaBoyutu;
    }
    public void setDepolamaBoyutu(String depolamaBoyutu) {
        this.depolamaBoyutu = depolamaBoyutu;
    }
    public String getOkumaYazmaHizi() {
        return okumaYazmaHizi;
    }
    public void setOkumaYazmaHizi(String okumaYazmaHizi) {
        this.okumaYazmaHizi = okumaYazmaHizi;
    }
    public int getFiyat() {
        return super.getFiyat();
    }
    public void setFiyat(int fiyat) {
       super.setFiyat(fiyat);
    }
    
    
}
