package porodiliste.bebe;

import java.util.GregorianCalendar;

public class Beba {
    private String imePrezime = null;
    private GregorianCalendar vremeRodjenja = null;
    private int tezina = 0;
    private int duzina = 0;
    
    /*
    Kreirana je klasa Beba sa privatnim atributima za ime, prezime i datum
    rodjenja koji su inicijalizovani sa "null" vrednostima s tim sto je datum
    rodjenja incijalizovan u Gregorijanskom kalendaru.
    privatni atributi za duzinu i tezinu bebe su inicijalizovani sa vrednoscu 0.
    */

    public Beba(String imePrezime, GregorianCalendar vremeRodjenja,
            int tezina, int duzina) throws IllegalArgumentException{
        if (imePrezime == null || imePrezime.length() < 4) {
            throw new IllegalArgumentException("Ime i prezime moraju biti duzi od 5 karaktera.");
        }
        
        GregorianCalendar vremeSad = new GregorianCalendar();
        if (vremeRodjenja == null || vremeRodjenja.after(vremeSad)) {
            throw new IllegalArgumentException("Vreme rodjenja mora biti biti pre trenutnog vremena.");
        }
        
        if (tezina < 500 || tezina > 8000) {
            throw new IllegalArgumentException("Tezina mora biti izmedju 500 i 8000 grama.");
        }
        
        if (duzina < 25) {
            throw new IllegalArgumentException("Duzina mora biti veca od 25 centimetara.");
        }
        
        this.imePrezime = imePrezime;
        this.vremeRodjenja = vremeRodjenja;
        this.tezina = tezina;
        this.duzina = duzina;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public GregorianCalendar getVremeRodjenja() {
        return vremeRodjenja;
    }

    public int getTezina() {
        return tezina;
    }

    public int getDuzina() {
        return duzina;
    }

    /*
        Seter za atriut imePrezime je napravljen tako da vrednost ne moze da bude
        null ili duzine manje od 5 karaktera. Ukoliko se takva vrednost pojavi
        doci ce do izuzetka.
    */
    
    public void setImePrezime(String imePrezime)throws IllegalArgumentException {
        if (imePrezime == null || imePrezime.length() < 4) {
            throw new IllegalArgumentException("Ime i prezime moraju biti duzi od 5 karaktera.");
        }
        this.imePrezime = imePrezime;
    }
    
    /*
        Seter za atriut vremeRodjenja je napravljen tako da vrednost ne moze da bude
        null ili vreme u buducnosti. Ukoliko se takva vrednost pojavi doci ce do izuzetka.
    */

    public void setVremeRodjenja(GregorianCalendar vremeRodjenja) throws IllegalArgumentException {
        GregorianCalendar vremeSad = new GregorianCalendar();
        if (vremeRodjenja == null || vremeRodjenja.after(vremeSad)) {
            throw new IllegalArgumentException("Vreme rodjenja mora biti biti pre trenutnog vremena.");
        }
        this.vremeRodjenja = vremeRodjenja;
    }
    
    /*
        Seter za atriut tezina je napravljen tako da vrednost ne moze biti manja
        od 500 grama ili veca od 8000 grama. Ukoliko se unese takva vrednost
        doci ce do izuzetka.
    */

    public void setTezina(int tezina) throws IllegalArgumentException {
        if (tezina < 500 || tezina > 8000) {
            throw new IllegalArgumentException("Tezina mora biti izmedju 500 i 8000 grama.");
        }
        this.tezina = tezina;
    }
    
    /*
        Seter za atriut duzina je napravljen tako da vrednost ne moze biti manja
        od 25 centimetara. Ukoliko se unese takva vrednost doci ce do izuzetka.
    */

    public void setDuzina(int duzina) throws IllegalArgumentException {
        if (duzina < 25) {
            throw new IllegalArgumentException("Duzina mora biti veca od 25 centimetara.");
        }
        this.duzina = duzina;
    }
    
    /*
        Redefinisali smo metodu equals klase Object tako da vraca vrednost 'true'
        ukoliko su atributi imePrezime i vremeRodjenja unetog objekta jednaki
        imenu i prezimenu i vremenu rodjenja bebe. U suprotnom vrednost je 'false'.
    */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Beba other = (Beba) obj;
        return imePrezime.equals(other.imePrezime) && vremeRodjenja.equals(other.vremeRodjenja);
    }
    
    /*
        Redefinisali smo metodu toString tako da ispisuje String sa svim
        podacima o novorodjencetu.
    */

    @Override
    public String toString() {
        StringBuilder novorodjence = new StringBuilder();
        novorodjence.append("Ime i prezime: ").append(imePrezime).append("\n");
        novorodjence.append("Vreme rodjenja: ").append(vremeRodjenja.getTime()).append("\n");
        novorodjence.append("Tezina: ").append(tezina).append("g\n");
        novorodjence.append("Duzina: ").append(duzina).append("cm");
        return novorodjence.toString();
    }
    
}
