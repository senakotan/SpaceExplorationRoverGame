
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        oyunaBasla();  //UzayKeşifOyunu oyunaBasla methodu ile başlatılır.
    }

    public static void oyunaBasla() {
        Scanner scanner = new Scanner(System.in);
        Galaksi galaksi = new Galaksi();  //Galaksi objesi oluşturulur.
        galaksi.galaksiyiOlustur();  //Galaksi random şekilde doldurulur.
        UzayKesifAraci arac = new UzayKesifAraci(galaksi);  //UzayKesifAracı objesi oluşturulur.

        //Kullanıcıya başlangıç değerleri gösterilir.
        System.out.println("~~~~~UZAY KESİF OYUNU~~~~~\n"
                + "Başlangıç Özellikleriniz: \n"
                + "-Yakıt: " + arac.getYakitSeviyesi() + "\n"
                + "-Kapasite: " + arac.getKapasite());

        arac.gezegeneGit();  //Oyun kullanıcıdan bir gezegene gitmesi istenerek başlar.

        boolean oyunDevam = true;
        while (oyunDevam) {  //Oyunun devamı için gerekli koşullar kontrol edilir.
            if (galaksi.galaksiBosMu()) { // Galaksideki tüüm gezegenler gezildiyse oyun bitirilir.
                System.out.println("Tüm gezegenler keşfedildi! Oyun sona erdi.");
                oyunDevam = false;
                break;
            }
            if (!arac.gidebilecegiGezegenVarMi()) {  // Yakıt seviyesinden dolayı hiçbir gezegene gidilemiyorsa oyun bitirilir.
                System.out.println("Yakıtınız mevcut gezegenlere iniş yapmaya yetmiyor! Oyun sona erdi.");
                oyunDevam = false;
                break;
            }

            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\nSECENEKLER:");  //Kullanıcıya seçenekler gösterilir.
            System.out.println("1. Gezegenlere Seyahat\n"
                    + "2. Uzay Aracını Geliştir\n"
                    + "3. Yakıt Yenile\n"
                    + "4. Çıkış");

            // Kullanıcıdan String olarak seçim alınır
            System.out.print("\nSeçiminiz: ");
            String secim = scanner.nextLine();

            // Kullanıcı doğru bir seçim yapana kadar döngü devam eder.Yanlış seçimde tekrar seçim yapması istenir.
            while (!(secim.equals("1") || secim.equals("2") || secim.equals("3") || secim.equals("4"))) {
                System.out.println("Geçersiz seçim! Lütfen 1, 2, 3 veya 4'ü girin.");
                System.out.print("\nSeçiminiz: ");
                secim = scanner.nextLine();  // Kullanıcıdan yeniden seçim alınır
            }
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            // Kullanıcıdan String olarak alınan secime göre işlemler yapılır
            switch (secim) {
                case "1":
                    arac.gezegeneGit();
                    break;
                case "2":
                    arac.araciGelistir();
                    break;
                case "3":
                    arac.yakitiYenile();
                    break;
                case "4": //Kullanıcı giriş olarak 4'ü seçerse oyundan çıkış yapılır.
                    oyunDevam = false;
                    System.out.println("Çıkış Yapılıyor..");
                    break;
            }

        }
    }
}
