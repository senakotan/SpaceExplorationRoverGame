/* 
 SENA KOTAN
 C2221241001 

 */
import java.util.Random;

public class Galaksi {

    private String[][] galaksiHaritasi; //Gezegen ve karadeliklerin sembollerinin gösterileceği galaksi haritası.
    private Cisim[] cisimler; //Cisim sınıfından kalıtım alan Gezegen ve Karadelik referanslarının tutulduğu ObjectArray.

    public Galaksi() {
        galaksiHaritasi = new String[5][5];   //Galaksi sınıfından bir obje oluşturulduğu anda galaksi haritası oluşturulur.
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                galaksiHaritasi[i][j] = " "; // Ilk başta hepsine boşluk atanır.
            }
        }
    }

    public Cisim[] getCisimler() {
        return cisimler;
    }

    public String[][] getGalaksiHaritasi() {
        return galaksiHaritasi;
    }

    public void galaksiyiOlustur() {
        Random random = new Random();

        // Gezegen isimleri (her isimden sadece bir tane kullanılacak)
        String[] gezegenIsimleri = {"Kuş", "Gümüş", "Aydın", "Ersöz", "Bakır", "Kotan", "Inan", "Yılmaz"};
        String[] atmosferTipleri = {"Yaşanabilir", "Zehirli", "Yoğun", "Yanıcı"};

        // Min 3, Max 8 gezegen oluşturulur.
        int gezegenSayisi = random.nextInt(6) + 3; // 3 ile 8 arasında rastgele sayı

        // Min 1, Max 3 karadelik oluşturulur.
        int karadelikSayisi = random.nextInt(3) + 1; // 1 ile 3 arasında rastgele sayı

        // Toplam cisim sayısı belirlenir.
        int toplamCisimSayisi = gezegenSayisi + karadelikSayisi;

        // Toplam cisim sayısı boyutunda cisimler array'i oluşturulur.
        cisimler = new Cisim[toplamCisimSayisi];

        // Gezegenler oluşturulur ve array'e eklenir.
        boolean[] kullanilanIsimler = new boolean[gezegenIsimleri.length]; //Her isimden tek bir gezegen oluşturulması için boolean bir array oluşturulur.
        int eklenenGezegenIndex = 0;

        while (eklenenGezegenIndex < gezegenSayisi) { //Random oluşturulan gezegenSayisi kadar gezegen oluşturulur.
            int isimIndex = random.nextInt(gezegenIsimleri.length);
            if (!kullanilanIsimler[isimIndex]) { // Gezegen isminin daha önce kullanılıp kullanılmadığı kontrol edilir.
                kullanilanIsimler[isimIndex] = true; // Kullanılan isimler boolean arrayde true olarak güncellenir.

                // Gezegen özellikleri rastgele belirlenir.
                int yerCekimi = random.nextInt(20) + 1;
                String atmosfer = atmosferTipleri[random.nextInt(atmosferTipleri.length)];

                // Kaynakları rastgele oluşturulur.
                Kaynak[] kaynaklar = {
                    new Altin(random.nextInt(15) + 1),
                    new Gumus(random.nextInt(15) + 1),
                    new Su(random.nextInt(10) + 1),
                    new Bor(random.nextInt(10) + 1)
                };
                //Gezegen sembolünün galaksi haritasındaki konumu belirlenir.
                int xKonumu, yKonumu;
                do {
                    xKonumu = random.nextInt(galaksiHaritasi.length);
                    yKonumu = random.nextInt(galaksiHaritasi[0].length);
                } while (!galaksiHaritasi[xKonumu][yKonumu].equals(" ")); // Boş bir hücre bulana kadar döngü devam eder.

                // Atmosfer tipine göre uygun gezegen türü oluşturulur.
                Gezegen yeniGezegen = null;  //Başlangıçta referansa null atanır.
                switch (atmosfer) {
                    case "Yaşanabilir":
                        yeniGezegen = new YasanabilirGezegen(gezegenIsimleri[isimIndex], xKonumu, yKonumu, kaynaklar, yerCekimi);
                        break;
                    case "Zehirli":
                        yeniGezegen = new ZehirGezegen(gezegenIsimleri[isimIndex], xKonumu, yKonumu, kaynaklar, yerCekimi);
                        break;
                    case "Yoğun":
                        yeniGezegen = new YogunGezegen(gezegenIsimleri[isimIndex], xKonumu, yKonumu, kaynaklar, yerCekimi);
                        break;
                    case "Yanıcı":
                        yeniGezegen = new YaniciGezegen(gezegenIsimleri[isimIndex], xKonumu, yKonumu, kaynaklar, yerCekimi);
                        break;
                }

                // Oluşturulan gezegenler, Cisimler array'ine eklenir.
                cisimler[eklenenGezegenIndex] = yeniGezegen;

                // Haritaya eklenir
                galaksiHaritasi[xKonumu][yKonumu] = mySubstring(yeniGezegen.getAd(), 0, 1); // Gezegen isminin ilk harfi sembol olarak kullanılır.
                eklenenGezegenIndex++; //Her gezegen eklendiğinde eklenenGezegenIndex değeri bir arttırılır.
            }
        }
        
        // Karadelikleri oluşturulur ve array'e eklenir.
        for (int i = 0; i < karadelikSayisi; i++) {
            //Karadelik sembolünün galaksi haritasındaki konumu belirlenir.
            int xKonumu, yKonumu;
            do {
                xKonumu = random.nextInt(galaksiHaritasi.length);
                yKonumu = random.nextInt(galaksiHaritasi[0].length);
            } while (!galaksiHaritasi[xKonumu][yKonumu].equals(" ")); // Boş bir hücre bulana kadar döngü devam eder.

            Karadelik yeniKaradelik = new Karadelik("Karadelik", null, xKonumu, yKonumu); // Karadeliklerin uzayAraci özelliklerine başlangıçta null atanır.
            cisimler[gezegenSayisi + i] = yeniKaradelik; // Gezegenlerden sonra karadelikler de cisimler arrayine eklenir.

            // Oluşan karadelik haritaya eklenir. Gezegenlerden farklı olarak Karadelik'ler X ile gösterilir.
            galaksiHaritasi[xKonumu][yKonumu] = "X";
        }
    }

    /**
     * gezegen ve karadelikleri galaksi haritasinda gorebilmemizi sağlayan
     * method.
     */
    public void galaksiyiGoster() {
        System.out.println("\nGalaksi Haritası:");
        for (int i = 0; i < 5; i++) {  //Kullanıcıya galaksi haritası gösterilir.
            for (int j = 0; j < 5; j++) {
                System.out.print("[" + galaksiHaritasi[i][j] + "] ");
            }
            System.out.println();
        }

        //Kullanıcıya galaksiharitasında gördüğü sembollerin ne anlama geldiği söylenir.
        System.out.println("\nGezegenler ve semboller:");
        for (Cisim cisim : cisimler) {  //cisimler arrayi dolanılır.
            if (cisim != null && cisim instanceof Gezegen) { //cisim bir Gezegen objesi mi kontrolü yapılır.
                //Her bir gezegen için ilk harf sembol olarak kullanılır.
                System.out.println("- " + cisim.getAd() + " (" + mySubstring(cisim.getAd(), 0, 1) + ")");
            } else if (cisim instanceof Karadelik) {  //Karadelikler, gezegenlerden farklı olarak X ile gösterilir.
                System.out.println("- Karadelik (X)");
            }
        }
    }

    //Gidilebilecek gezegenin kalıp kalmadığını kontrol eden method.
    public boolean galaksiBosMu() {
        for (Cisim c : getCisimler()) { //cisimler arrayi forEach döngüsü ile dolaşılır.
            if (c instanceof Gezegen) {
                return false; //En az bir gezegen varsa bile method false döndürür.
            }
        }
        return true; //Hiç bir gezegen objesi bulunamadıysa method true döndürür.
    }

    public String mySubstring(String input, int beginIndex, int endIndex) {
        // Geçersiz giriş kontrolü yapılır
        if (input == null || beginIndex < 0 || endIndex > input.length() || beginIndex > endIndex) {
            throw new IllegalArgumentException("Geçersiz başlangıç veya bitiş indeksleri!");
        }

        // Yeni ve boş bir string oluşturulur
        String result = "";

        //Parçalamak istenilen indeksler arasında döngüyle gezilir.
        for (int i = beginIndex; i < endIndex; i++) {
            result += input.charAt(i); //  Karakterler boş Stringe eklenir.
        }

        return result; //yeni oluşan String döndürülür. 
    }
}
