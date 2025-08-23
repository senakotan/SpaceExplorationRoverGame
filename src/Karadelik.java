
import java.util.Random;

public class Karadelik extends Cisim {

    private int xKonumu;
    private int yKonumu;
    UzayKesifAraci uzayAraci;

    public Karadelik(String ad, UzayKesifAraci uzayKesifAraci, int xKonumu, int yKonumu) {
        super(ad);  // Üst sınıfın (Cisim) yapıcı methodu çağrılır.
        uzayAraci = uzayKesifAraci;
        this.xKonumu = xKonumu;
        this.yKonumu = yKonumu;
    }

    public UzayKesifAraci getUzayAraci() {
        return uzayAraci;
    }

    public void setUzayAraci(UzayKesifAraci uzayAraci) {
        this.uzayAraci = uzayAraci;
    }

    public int getxKonumu() {
        return xKonumu;
    }

    public void setxKonumu(int xKonumu) {
        this.xKonumu = xKonumu;
    }

    public int getyKonumu() {
        return yKonumu;
    }

    public void setyKonumu(int yKonumu) {
        this.yKonumu = yKonumu;
    }

    public void karadelikEtkisi() {
        Random random = new Random();
        boolean etkisi = random.nextBoolean(); // İki ihtimal bulunur: true veya false

        // Random oluşan boolean değer True ise
        if (etkisi) {
            System.out.println("Karadelik sizi hiç yakıt harcamadan rastgele bir gezegene gönderiyor!");
        } //Random oluşan boolean değer False ise 
        else {
            System.out.println("Karadelik yakıtınızın yarısını ve kaynaklarınızın yarısını alarak sizi rastgele bir gezegene gönderiyor!");
            uzayAraci.setYakitSeviyesi(uzayAraci.getYakitSeviyesi() / 2);  // Yakıtın yarısı alınır.
            System.out.println("Mevcut Yakıt: " + uzayAraci.getYakitSeviyesi());
            // Her kaynağın yarısı alınır ve aracın kapasitesi güncellenir.
            for (Kaynak kaynak : uzayAraci.getKaynaklar()) {
                int harcadigi = kaynak.getMiktar() / 2;
                kaynak.setMiktar(kaynak.getMiktar() / 2);
                uzayAraci.setKapasite(uzayAraci.getKapasite() + harcadigi); //Kapasite, harcanan kaynak miktarı kadar arttılır.
                uzayAraci.setMevcutKaynakSayisi(uzayAraci.getMevcutKaynakSayisi() - harcadigi); //Mevcut kaynak sayısı, harcanan kaynak miktarı kadar azaltılır.

            }
            // Kalan kaynaklar listelenir.
            uzayAraci.kaynaklariListele();
        }
        randomGönder();  // Her iki durumda da uzay aracı rastgele bir gezegene gönderilir.
    }

    // Rastgele bir gezegene gönderme işlemini gerçekleştirecek metot
    public void randomGönder() {
        Random random = new Random();
        int index;
        /* Eğer seçilen indeks null ise veya Gezegen sınıfının objesi değilse tekrardan indeks üretilir.
           Uygun bir gezegen bulunana kadar döngü devam eder*/
        do {
            index = random.nextInt(uzayAraci.getGalaksi().getCisimler().length);  // Cisimler arrayinden rastgele bir indeks seçilir.
        } while (uzayAraci.getGalaksi().getCisimler()[index] == null || !(uzayAraci.getGalaksi().getCisimler()[index] instanceof Gezegen));

        Gezegen gezegen = (Gezegen) uzayAraci.getGalaksi().getCisimler()[index]; // Cast işlemi yapılır.

        // Haritadan gezegenin sembolü kaldırılır.
        String[][] galaksiHaritasi = uzayAraci.getGalaksi().getGalaksiHaritasi();
        galaksiHaritasi[gezegen.getxKonumu()][gezegen.getyKonumu()] = " "; // Sembol silinir.
        uzayAraci.getGalaksi().getCisimler()[index] = null;  //Cisimler array indeki referansı null olarak güncellenir.

        System.out.println("\n" + gezegen.getAd() + " gezegenine gidiyorsunuz...");
        System.out.println("\nGezegen " + gezegen.getAd() + "'a iniş yaptınız!");
        gezegen.bilgileriGoster(); //Gidilen gezegenin bilgilerini gösterecek method çağrılır.
        uzayAraci.secenekler(gezegen); //Gezegen içerisinde yapılabilecek işlemler kullanıcıya gösterilir.

    }
}

