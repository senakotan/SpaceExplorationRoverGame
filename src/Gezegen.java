
public abstract class Gezegen extends Cisim {

    private int xKonumu;
    private int yKonumu;
    private Kaynak[] kaynaklar; // Kaynak sınıfından türeyen kaynak objelerini tutan objectArray tanımlanır.
    private String atmosferDurumu;
    private int yerCekimi;

    public Gezegen(String ad, int xKonumu, int yKonumu, Kaynak[] kaynaklar, String atmosferDurumu, int yerCekimi) {
        super(ad); //Üst sınıfın(Cisim) constructor'ı çağrılır.
        this.xKonumu = xKonumu;
        this.yKonumu = yKonumu;
        this.kaynaklar = kaynaklar;
        this.atmosferDurumu = atmosferDurumu;
        this.yerCekimi = yerCekimi;
    }

    public int getxKonumu() {
        return xKonumu;
    }

    public int getyKonumu() {
        return yKonumu;
    }

    public Kaynak[] getKaynaklar() {
        return kaynaklar;
    }

    public String getAtmosferDurumu() {
        return atmosferDurumu;
    }

    public int getYerCekimi() {
        return yerCekimi;
    }

    //Gezegen sınıfıdan kalıtım alan her sınıf, kendi inisMaliyetiHesapla() methodunu yazar.(override eder)
    public abstract int inisMaliyetiHesapla();

    //Gezegenin bilgilerini gösteren method
    public void bilgileriGoster() {
        System.out.println("Gezegenin Özellikleri: ");
        System.out.println("Gezegen Adı: " + getAd());
        System.out.println("- Yerçekimi: " + getYerCekimi());
        System.out.println("- Atmosfer: " + getAtmosferDurumu());
        kaynaklariGoster();
    }

    //Tek tek kaynak listesini gezip miktarları ile birlikte gösteren method
    public void kaynaklariGoster() {
        System.out.println(getAd() + " gezegenindeki kaynaklar:");
        for (Kaynak kaynak : kaynaklar) {
            System.out.println("- " + kaynak.getKaynakTipi() + ": " + kaynak.getMiktar());
        }
    }
}

//GEZEGEN SINIFINDAN KALITIM ALAN SINIFLAR...
class ZehirGezegen extends Gezegen {

    public ZehirGezegen(String ad, int xKonumu, int yKonumu, Kaynak[] kaynaklar, int yerCekimi) {
        super(ad, xKonumu, yKonumu, kaynaklar, "Zehirli", yerCekimi);
    }

    @Override
    public int inisMaliyetiHesapla() { //Abstract method override edildi.
        int maliyet = 15; // Zehirli gezegenler için başlangıç maliyeti
        maliyet += getYerCekimi() > 10 ? 15 : 0; // Yerçekimi 10 dan büyükse ekstra maliyet eklenir.
        return maliyet;
    }
}

class YogunGezegen extends Gezegen {

    public YogunGezegen(String ad, int xKonumu, int yKonumu, Kaynak[] kaynaklar, int yerCekimi) {
        super(ad, xKonumu, yKonumu, kaynaklar, "Yoğun", yerCekimi);
    }

    @Override
    public int inisMaliyetiHesapla() {
        int maliyet = 25; // Yoğun gezegenler için başlangıç maliyeti
        maliyet += getYerCekimi() > 10 ? 20 : 0; // Yüksek yer çekimi eklenir.
        return maliyet;
    }

    @Override
    public void bilgileriGoster() { //Superclass taki method subclassta override edilir.
        super.bilgileriGoster(); //Üst sınıftaki override edilen method çağrılır.
        System.out.println("!DİKKAT: Yoğun atmosfer nedeniyle iniş daha maliyetlidir!");
    }
}

class YasanabilirGezegen extends Gezegen {

    public YasanabilirGezegen(String ad, int xKonumu, int yKonumu, Kaynak[] kaynaklar, int yerCekimi) {
        super(ad, xKonumu, yKonumu, kaynaklar, "Yaşanabilir", yerCekimi);
    }

    @Override
    public int inisMaliyetiHesapla() {
        int maliyet = 10; // Yaşanabilir gezegenler için başlangıç maliyeti
        maliyet += getYerCekimi() > 10 ? 10 : 0; // Yüksek yer çekimi eklenir
        return maliyet;
    }
}

class YaniciGezegen extends Gezegen {

    public YaniciGezegen(String ad, int xKonumu, int yKonumu, Kaynak[] kaynaklar, int yerCekimi) {
        super(ad, xKonumu, yKonumu, kaynaklar, "Yanıcı", yerCekimi);
    }

    @Override
    public int inisMaliyetiHesapla() {
        // Yanıcı atmosfer nedeniyle ekstra iniş maliyeti eklenir.
        int maliyet = 25; // Değere baz maliyet atanır.
        maliyet += (getYerCekimi() > 15) ? 15 : 0; // Yerçekimi yüksekse ek maliyet eklenir.
        maliyet += 20; // Yanıcı atmosfer nedeniyle ek maliyet eklenir
        return maliyet;
    }

    @Override
    public void bilgileriGoster() { //Superclass taki method subclassta override edilir.
        super.bilgileriGoster(); //Üst sınıftaki override edilen method çağrılır.
        System.out.println("!DİKKAT: Yanıcı atmosfer nedeniyle iniş daha maliyetlidir!");
    }
}

