package porodiliste;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import porodiliste.bebe.Beba;
import porodiliste.statistike.Statistike;

public class Porodiliste implements Statistike {
    private Beba[] bebe;
    
    /*
        Konstruktor je definisan tako da prima kapacitet porodilista kao argument
        i inicijalizuje niz bebe na tu vrednost. Ako je uneti kapacitet
        manji ili jednak nuli, dolazi do izuzetka.
    */

    public Porodiliste(int kapacitet) throws IllegalArgumentException {
        if (kapacitet <= 0) {
            throw new IllegalArgumentException("Kapacitet porodilista mora biti veci od 0!");
        }
        bebe = new Beba[kapacitet];
    }
    
    /*
        Metodom dodajBebu() dodajemo bebu u porodiliste ukoliko postoji slobodnih
        mesta u porodilistu.
    */
    
    public void dodajBebu(Beba beba) throws IllegalArgumentException {
        for (int i = 0; i < bebe.length; i++) {
            if (bebe[i] == null) {
                bebe[i] = beba;
                return;
            }
        }
        throw new IllegalArgumentException("Nema slobodnih mesta u porodilistu.");
    }
    
    /*
        Metodom pronadjiNajtezuBebu() prolazimo kroz bebe i svakom iteracijom
        upisujemo podatke o najtezoj bebi (ukoliko je teza od prethodne).
        Ova metoda nam je bitna jer cemo je koristiti prilikom "overrajdovanja"
        metode upisiEkstreme iz interfejsa 'Statistike'.
    */
    
    private Beba pronadjiNajtezuBebu() {
        Beba najtezaBeba = null;
        int najvecaTezina = 0;
        
        for (Beba beba : bebe) {
            if (beba != null && beba.getTezina() > najvecaTezina) {
                najtezaBeba = beba;
                najvecaTezina = beba.getTezina();
            }
        }
        
        return najtezaBeba;
    }
    
    /*
        Metodom otpustiIzPorodilista() pronalazimo sve bebe sa datumom i vremenom
        rodjenja koji je pre unetog datuma i da ih iskopiramo u listu.
    */
    
    public List<Beba> otpustiIzPorodilista(GregorianCalendar datum) {
        List<Beba> otpusteneBebe = new ArrayList<>();
        for (int i = 0; i < bebe.length; i++) {
            Beba beba = bebe[i];
            if (beba != null && beba.getVremeRodjenja().before(datum)) {
                otpusteneBebe.add(beba);
                bebe[i] = null;
            }
        }
        return otpusteneBebe;
    }
    
    /*
        "Overrajdovana" metoda upisiEkstreme() upisuje podatke u nojtezoj bebi
        u fajl.
    */
    
    @Override
    public void upisiEkstreme(String teskaBeba) {
        Beba najtezaBeba = pronadjiNajtezuBebu();
        if (najtezaBeba != null) {
            try (FileWriter writer = new FileWriter(teskaBeba)) {
                String podaci = 
                        najtezaBeba.getImePrezime() + "\t" +
                        najtezaBeba.getTezina() + "\t" +
                        najtezaBeba.getDuzina();
                writer.write(podaci);
            } catch (IOException e) {
                System.out.println("Greska prilikom upisa u fajl");
            }
        }
    }
    
    /*
        Metoda vratiFrekvencijskuTabelu je "overrajdovana"  vraca niz koji bi
        trebalo da sadrzi broj beba u porodilistu koji su se rodili u satu.
    */

    @Override
    public int[] vratiFrekvencijskuTabelu() {
        int[] frekvencijskaTabela = new int[24];
        for (Beba beba : bebe) {
            if (beba != null) {
                int sat = beba.getVremeRodjenja().get(GregorianCalendar.HOUR_OF_DAY);
                frekvencijskaTabela[sat]++;
            }
        }
        
        return frekvencijskaTabela;
    }
    
    
}
