/* 
 SENA KOTAN
 C2221241001 

 */
import java.util.Scanner;

public class UzayKesifAraci {

    private int yakitSeviyesi;
    private int kapasite;
    private Kaynak[] kaynaklar;
    private int mevcutKaynakSayisi;
    private Galaksi galaksi;

    public UzayKesifAraci(Galaksi galaksi) {
        this.yakitSeviyesi = 100;  //Başlangıçta yakıt seviyesi 100 olarak ayarlanır.
        this.kapasite = 50;  //Başlangıçta aracın kapasitesi 50 olarak ayarlanır.
        this.kaynaklar = new Kaynak[]{ //Kaynak listesi, başlangıçta her bir kaynağın miktarı 0 olacak şekilde ayarlanır.
            new Altin(0),
            new Gumus(0),
            new Su(0),
            new Bor(0)
        };
        this.mevcutKaynakSayisi = 0;
        this.galaksi = galaksi;
    }

    public Galaksi getGalaksi() {
        return galaksi;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    public void setMevcutKaynakSayisi(int mevcutKaynakSayisi) {
        this.mevcutKaynakSayisi = mevcutKaynakSayisi;
    }

    public int getYakitSeviyesi() {
        return yakitSeviyesi;
    }

    public Kaynak[] getKaynaklar() {
        return kaynaklar;
    }

    public void setYakitSeviyesi(int yakitSeviyesi) {
        this.yakitSeviyesi = yakitSeviyesi;
    }

    public int getKapasite() {
        return kapasite;
    }

    public int getMevcutKaynakSayisi() {
        return mevcutKaynakSayisi;
    }

    //Uzay aracının içindeki kaynaklar, kaynak listesi dolanılarak listelenir.
    public void kaynaklariListele() {
        System.out.println("Uzay aracının içerisindeki kaynaklar:");
        for (Kaynak kaynak : kaynaklar) {
            System.out.println("- " + kaynak.getKaynakTipi() + ": " + kaynak.getMiktar());
        }
        System.out.println("Kapasite (kalan alan): " + kapasite);
    }

    public void kaynaklariTopla(Gezegen gezegen) {  //Kullanıcının gittiği gezegen methoda parametre olarak verilir.
        System.out.println("\n" + gezegen.getAd() + " gezegeninden kaynaklar toplanıyor...");

        for (Kaynak gezegenKaynak : gezegen.getKaynaklar()) {  //Gidilen gezegenin kaynak listesinde gezilir.
            if (gezegenKaynak.getMiktar() > 0) {  //Her bir kaynağın değeri 0 dan büyük mü kontrolü yapılır.(Gezegende o kaynaktan bulunuyor mu?)
                // Her gezegen kaynağı için aracın kaynakları kontrol edilir.
                for (Kaynak aracKaynak : kaynaklar) {
                    if (aracKaynak.getKaynakTipi().equals(gezegenKaynak.getKaynakTipi())) {
                        // Mevcut kapasiteye göre gezegenden alınabilecek miktar belirlenir.
                        int alinacakMiktar = myMin(gezegenKaynak.getMiktar(), kapasite);

                        if (alinacakMiktar > 0) { //myMin methodundan sıfır dönmediyse yani araç kapatsitesi 0'dan büyükse
                            aracKaynak.setMiktar(aracKaynak.getMiktar() + alinacakMiktar); // Aracın ilgili kaynağı güncellenir.
                            gezegenKaynak.setMiktar(gezegenKaynak.getMiktar() - alinacakMiktar); // Gezegenin ilgili kaynağı, araca yüklenen miktar kadar azalır.
                            mevcutKaynakSayisi += alinacakMiktar; // Mevcut kaynak sayısı arttrılır. 
                            kapasite -= alinacakMiktar; // Kapasite düşürülür.
                            System.out.println(alinacakMiktar + " birim " + aracKaynak.getKaynakTipi() + " araca eklendi.");
                        } else { //Araç kapasitesi dolduysa else e düşer.
                            System.out.println("Kapasite dolduğu için " + aracKaynak.getKaynakTipi() + " eklenemedi.");
                        }
                        break; // Diğer kaynaklara geçilir.
                    }
                }
            }
        }

        System.out.println("Mevcut kaynak durumu:");
        kaynaklariListele(); //Kaynakları topladıktan sonra mevcut kaynak listesi kullanıcıya gösterilir.
    }

    public void yakitiYenile() {
        Scanner scanner = new Scanner(System.in);

        // Kullanıcıya mevcut kaynakları gösterilir.
        System.out.println("\nYakıt yenilemek için kaynaklar harcanacak.");
        kaynaklariListele();

        System.out.println("Mevcut Yakıt: " + yakitSeviyesi);

        int harcanacakMiktar;

        // Kullanıcı geçerli bir giriş yapana kadar döngü devam eder.
        while (true) {
            System.out.print("Yakıt yenilemek için kaç kaynak harcamak istersiniz? (Mevcut: " + mevcutKaynakSayisi + "): ");
            try { //Hata çıkması beklenen kısım try bloğunun içine yazılır.
                harcanacakMiktar = scanner.nextInt();

                if (harcanacakMiktar >= 0 && harcanacakMiktar <= mevcutKaynakSayisi) {
                    break; // Geçerli bir değer girildiğinde döngüden çıkılır.
                } else if (harcanacakMiktar < 0) {
                    System.out.println("Negatif bir değer giremezsiniz! Lütfen pozitif bir değer girin.");
                } else {
                    System.out.println("Yeterli kaynağınız yok!");
                }
            } catch (Exception e) {
                System.out.println("Geçersiz giriş! Lütfen bir sayı girin.");
                scanner.nextLine(); // Hatalı girdiyi temizle
            }
        }

        if (harcanacakMiktar == 0) { //Kullanıcı giriş olarak 0'ı seçerse yakıt seviyesi değişmez ve methoddan çıkılır.
            System.out.println("0 kaynak harcadınız, yakıt seviyesi değişmedi.");
            return;
        }

        // Yakıt yenileme işlemi
        kaynakHarca(harcanacakMiktar);
        //Harcanan kaynak miktarının 2 katı kadar yakıt artışı gerçekleşir ve kullanıcıya kalan kaynakları listelenir.
        int yakitArtisi = harcanacakMiktar * 2;
        yakitSeviyesi += yakitArtisi;
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\nYakıt yenilendi! Eklenen Yakıt: " + yakitArtisi);
        System.out.println("Yeni yakıt seviyesi: " + yakitSeviyesi);
        System.out.println("Güncel kaynak durumu:");
        kaynaklariListele();
    }

    public void araciGelistir() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nAracınızı geliştirmek için kaynaklar harcanacak.");
        kaynaklariListele();

        int harcanacakMiktar; // Harcanacak kaynak miktarını tutan değişken.

        // Geçerli bir giriş yapılana kadar döngü devam eder. 
        while (true) {
            // Kullanıcıdan geliştirme için harcanacak kaynak miktarını girilmesi istenir.
            System.out.print("Aracınızı geliştirmek için kaç kaynak harcamak istersiniz? (Mevcut: " + mevcutKaynakSayisi + "): ");
            try {  //Hata çıkması beklenen kısım try bloğunun içine yazılır.
                harcanacakMiktar = scanner.nextInt();

                if (harcanacakMiktar >= 0 && harcanacakMiktar <= mevcutKaynakSayisi) {
                    break; // Geçerli bir değer girildiğinde döngüden çıkılır.
                } else if (harcanacakMiktar < 0) { //Kullanıcının negatif değer girme durumu kontrol edilr.
                    System.out.println("Negatif bir değer giremezsiniz! Lütfen pozitif bir değer girin.");
                } else {
                    System.out.println("Yeterli kaynağınız yok!");
                }
            } catch (Exception e) {
                // Geçersiz bir giriş yapılırsa kullanıcı bilgilendirilir ve girdi temizlenir
                System.out.println("Geçersiz giriş! Lütfen bir sayı girin.");
                scanner.nextLine(); // Hatalı girdi temizlenir.
            }
        }

        if (harcanacakMiktar == 0) {  //Kullanıcı giriş olarak 0'ı seçerse yakıt seviyesi değişmez ve methoddan çıkılır.
            System.out.println("0 kaynak harcadınız, araç kapasitesi değişmedi.");
            return;
        }

        // Araç geliştirme işlemi için kaynakHarca methodu çağrılır.
        kaynakHarca(harcanacakMiktar);

        //Kullanılan kaynak miktarının 5 katı kadar kapasite artışı hesaplanır.Harcanan kaynak da kapasiteden düşer.
        int kapasiteArtisi = harcanacakMiktar * 5;
        kapasite += kapasiteArtisi;
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        System.out.println("\nAraç başarıyla geliştirildi!");
        System.out.println("Aracın kapasitesi " + kapasiteArtisi + " birim artırıldı. Yeni kapasite: " + kapasite);

        System.out.println("Güncel kaynak durumu:");  //Harcama sonrası kalan kaynaklar kullanıcıya gösterilir.
        kaynaklariListele();
    }

    public void kaynakHarca(int harcanacakMiktar) {
        for (Kaynak kaynak : kaynaklar) { //Kaynak listesi gezilir.
            if (harcanacakMiktar <= 0) {
                break; // Artık harcama yapılacak kaynak kalmadıysa döngüden çıkılır.
            }

            int mevcutMiktar = kaynak.getMiktar();
            if (mevcutMiktar > 0) {
                // Harcanacak miktar ile mevcut miktar karşılaştırılır.
                int harcanan = myMin(harcanacakMiktar, mevcutMiktar);
                kaynak.setMiktar(mevcutMiktar - harcanan); // Kaynak harcanan miktar kadar düşürülür.
                harcanacakMiktar -= harcanan; // Harcanması gereken miktar, harcanan miktar kadar azaltılır.
                mevcutKaynakSayisi -= harcanan; //MevcutKaynakSayisi harcanan miktar kadar azaltılır.
                kapasite += harcanan; // Kaynak harcanması nedeniyle kapasite arttılır.
            }
        }

    }

    public void gezegeneGit() {
        //Method, galakside gidilebilecek gezegen varsa ve yakıtı yeterliyse çalıştırılır.
        if (!galaksi.galaksiBosMu() && gidebilecegiGezegenVarMi()) {
            galaksi.galaksiyiGoster(); //Kullanıcıya ilk olarak gidebileceği gezegenler gösterilir.
            boolean yakitYeterliMi = true;
            Scanner scanner = new Scanner(System.in); //Kullanıcıdan seçimi alınır.
            String secim;

            // Geçerli bir gezegen harfi girene kadar döngü devam eder.
            while (true) {
                // Kullanıcıya mevcut yakıtı gösterilir ve bir sembol girişi alınır.
                System.out.println("\nMevcut Yakıt: " + yakitSeviyesi);
                System.out.print("Bir gezegen seçin (İlk harfi girin, örneğin Kotan için K): ");
                secim = scanner.nextLine();
                secim = myUpperCase(secim); // Büyük harf dönüşümü yapılır.
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                // Eğer kullanıcı "X" girdiyse yani bir karadeliğe gitmek istiyorsa ayrıca kontrol yapılır.(Karadelik için matrise K değil X kondu.)
                if (secim.equals("X")) {

                    for (Cisim cisim : galaksi.getCisimler()) { // Galaksideki cisimler arrayi taranır.
                        if (cisim instanceof Karadelik) { // Eğer cisim bir Karadelik ise
                            Karadelik karadelik = (Karadelik) cisim;  //Kullanılmak üzere karade1ik sınıfına cast edilir.

                            // Kara deliğin uzay aracı özelliği güncellenir ve kullanılan aracın referansı atanır. 
                            karadelik.setUzayAraci(this);

                            // Karadelik sembolü galaksi haritasından silinir.
                            String[][] galaksiHaritasi = galaksi.getGalaksiHaritasi();
                            galaksiHaritasi[karadelik.getxKonumu()][karadelik.getyKonumu()] = " ";

                            // Karadelik, galaksinin Cisimler arrayinden silinir.
                            for (int i = 0; i < galaksi.getCisimler().length; i++) {
                                if (galaksi.getCisimler()[i] instanceof Karadelik) {
                                    galaksi.getCisimler()[i] = null;  // Karadeliğin referansı null yapılır.
                                    break; // Sadece tek bir referansın null edilmesi sağlanır.
                                }
                            }
                            karadelik.karadelikEtkisi();
                            return; // Yönlendirme sonrası methoddan çıkılır.
                        }
                    }
                }

                // Kullanıcı X girmediyse gezegenlerden birini seçmiştir.
                boolean gezegenBulundu = false; //Gezegenin galakside bulunup bulunmadığını belirlemek için boolan değişkene ilk başta false atanır.
                for (int i = 0; i < galaksi.getCisimler().length; i++) {  // Cisimler listesindeki gezegenler kontrol edilir.
                    Cisim cisim = galaksi.getCisimler()[i];
                    //Gerekli şartlar kontrol edilir, uygunsa ilgili referans kullanılmak üzere Gezegen sınıfına cast edilir.
                    if (cisim != null && cisim instanceof Gezegen && mySubstring(cisim.getAd(), 0, 1).equals(secim)) {
                        Gezegen gezegen = (Gezegen) cisim;
                        // Gezegenin iniş maliyeti hesaplanır.
                        int inisMaliyeti = gezegen.inisMaliyetiHesapla();
                        if (yakitSeviyesi >= inisMaliyeti) { //Yakıt seviyesinin yeterliliği kontrol edilir. 
                            System.out.println("\n" + gezegen.getAd() + " gezegenine gidiyorsunuz...");
                            System.out.println("Yakıt tüketimi: " + inisMaliyeti + " birim");
                            setYakitSeviyesi(getYakitSeviyesi() - inisMaliyeti); // Gezegenin iniş maliyeti kadar yakıt seviyesi azaltılır.
                            System.out.println("Kalan yakıt: " + getYakitSeviyesi());

                            // Gidilen gezegenin bilgileri gösterilir.
                            System.out.println("\nGezegen " + gezegen.getAd() + "'a iniş yaptınız!");
                            gezegen.bilgileriGoster();

                            // Galaksi haritasından sembol kaldırılır.
                            String[][] galaksiHaritasi = galaksi.getGalaksiHaritasi();
                            galaksiHaritasi[gezegen.getxKonumu()][gezegen.getyKonumu()] = " "; // Sembol silinir.
                            galaksi.getCisimler()[i] = null;  //Gidilen gezegenin referansına null atanır.

                            // Kullanıcıya gezegen içerisindeki seçenekler gösterilir.
                            secenekler(gezegen);
                            gezegenBulundu = true;
                            break; // Gezegen bulundu, döngüden çık
                        } else {
                            System.out.println("Yakıt seviyeniz " + gezegen.getAd() + " gezegenine gitmek için yetersiz!" + "\nGerekli yakıt:" + gezegen.inisMaliyetiHesapla());  //DEĞİŞTİM
                            yakitYeterliMi = false; // Yakıt yeterli değil, değişkene false atanır.
                        }

                    }
                }

                if (gezegenBulundu) {
                    break; // Gezegen bulundu, döngü sonlandırılır.
                } else if (yakitYeterliMi) {
                    // Yalnızca yakıt yeterli ve geçersiz bir harf girilmişse mesaj yazdırılır.
                    System.out.println("Geçersiz giriş! Lütfen başka bir seçim yapın.");
                }
            }
        }

    }

    public void secenekler(Gezegen gezegen) {
        Scanner scanner = new Scanner(System.in);
        String secim;

        do {
            //Kullanıcıya seçenekler gösterilir ve girdi alınır.
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\nNe yapmak istersiniz?");
            System.out.println("1. Kaynak topla");
            System.out.println("2. Yakıt yenile");
            System.out.println("3. Uzay aracı geliştirme");
            System.out.println("4. Başka bir gezegene git");
            System.out.print("\nSeçiminiz: ");
            secim = scanner.nextLine();
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            switch (secim) { //Kullanıcıdan alının seçime göre farklı methodlar çağrılır.
                case "1":
                    kaynaklariTopla(gezegen);
                    return; // Geçerli herhangi bir seçenek yapıldıktan sonra methoddan çıkmak için return kullanılır
                case "2":
                    yakitiYenile();
                    return;
                case "3":
                    araciGelistir();
                    return;
                case "4":
                    gezegeneGit();
                    return;
                default:
                    System.out.println("Geçersiz seçenek! Lütfen tekrar deneyin.");
            }
        } while (true); // Kullanıcı geçerli bir seçenek girene kadar döngü devam eder.
    }

    public boolean gidebilecegiGezegenVarMi() {
        for (Cisim cisim : galaksi.getCisimler()) {
            if (cisim instanceof Gezegen) {
                Gezegen gezegen = (Gezegen) cisim;
                /*Eğer gezegenin iniş maliyeti mevcut yakıt seviyesinden küçük veya eşitse,
                 bu gezegene iniş yapılabileceği anlamına gelir ve true döndürülür.*/
                if (gezegen.inisMaliyetiHesapla() <= yakitSeviyesi) {
                    return true;  // En az bir gezegene iniş yapabiliyorsa method true döndürür.
                }
            }
        }
        return false;  // Hiçbir gezegene iniş yapamıyorsa method false döndürür.
    }

    public int myMin(int a, int b) {
        return (a < b) ? a : b; // Eğer a, b'den küçükse a döndürülür; diğer durumda b döndürülür.
    }

    public String myUpperCase(String input) {
        String result = ""; // Yeni boş bir String oluişturulur.
        for (int i = 0; i < input.length(); i++) { //Mevcut string gezilir.
            char c = input.charAt(i); // Mevcut karakter alınır.
            // Eğer küçük harf ise (Unicode değerleri: 'a' = 97, 'z' = 122)
            if (c >= 'a' && c <= 'z') {
                c = (char) (c - 32); // Küçük harf, büyük harfe dönüştürülür.
            }
            result += c; // Karakterler sonuca eklenir.
        }
        return result; // Büyük harfe dönüştürülmüş string döndürülür.
    }

    public String mySubstring(String input, int beginIndex, int endIndex) {
        // Geçersiz giriş kontrolü yapılır.
        if (input == null || beginIndex < 0 || endIndex > input.length() || beginIndex > endIndex) {
            //Eğer parametreler uygun değilse bir IllegalArgumentException fırlatılır ve kullanıcıya mesaj verdirilir.
            throw new IllegalArgumentException("Geçersiz başlangıç veya bitiş indeksleri!");
        }
        // Yeni,boş bir string oluşturulur.
        String result = "";

        // Döngü ile girilen indeksler arasındakikarakterler alınır.
        for (int i = beginIndex; i < endIndex; i++) {
            result += input.charAt(i); // Karakterleri tek tek ekle
        }

        return result; //Yeni oluşan string döndürülür.
    }
}
