
// Tüm kaynak türleri için ortak özellikleri tanımlayan üst sınıf tanımlanır.
public class Kaynak {

    private String kaynakTipi;
    private int miktar;

    public Kaynak(String kaynakTipi, int miktar) {
        this.kaynakTipi = kaynakTipi;
        this.miktar = miktar;
    }

    public String getKaynakTipi() {
        return kaynakTipi;
    }

    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }
}

//KAYNAK SINIFINDAN KALITIM ALAN ALT SINIFLAR:
class Altin extends Kaynak {

    public Altin(int miktar) {
        super("Altın", miktar); // Üst sınıfın (Kaynak) yapıcı methodu çağrılır.
    }
}

class Gumus extends Kaynak {

    public Gumus(int miktar) {
        super("Gümüş", miktar); // Üst sınıfın (Kaynak) yapıcı methodu çağrılır.
    }
}

class Bor extends Kaynak {

    public Bor(int miktar) {
        super("Bor", miktar); // Üst sınıfın (Kaynak) yapıcı methodu çağrılır.
    }
}

class Su extends Kaynak {

    public Su(int miktar) {
        super("Su", miktar);  // Üst sınıfın (Kaynak) yapıcı methodu çağrılır.
    }
}
