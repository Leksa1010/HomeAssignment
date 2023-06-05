package domacizadatak2;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import porodiliste.bebe.Beba;
import porodiliste.Porodiliste;

public class TestPorodiliste {
    public static void main(String[] args) {
        
        try {
            Porodiliste porodiliste = new Porodiliste(10);
            
            // Kreiracemo nekoliko beba
            
            Beba b1 = new Beba(
                    "Ana Anic",
                    new GregorianCalendar(
                            2023, Calendar.JANUARY, 10, 13, 42
                    ),
                    2000, 
                    50
            );
            
            Beba b2 = new Beba("Milica Milic",
                    new GregorianCalendar(
                            2023, Calendar.FEBRUARY, 13, 14, 30
                    ),
                    2500, 
                    52
            );
            
            Beba b3 = new Beba("Pera Peric",
                    new GregorianCalendar(
                            2023, Calendar.MARCH, 16, 12, 15
                    ),
                    3000, 
                    54
            );
            
            Beba b4 = new Beba("Zika Zikic",
                    new GregorianCalendar(
                            2023, Calendar.APRIL, 18, 17, 00
                    ),
                    3500, 
                    56
            );
            
            // Sada dodajemo bebe u porodiliste
            porodiliste.dodajBebu(b1);
            porodiliste.dodajBebu(b2);
            porodiliste.dodajBebu(b3);
            porodiliste.dodajBebu(b4);
            
            // Zatim upisujemo podatke o najtezoj bebi
            porodiliste.upisiEkstreme("najtezaBeba.txt");
            
            // Otpusticemo bebe starije od odredjenog datuma
            GregorianCalendar datum = new GregorianCalendar(2023, Calendar.APRIL, 15);
            List<Beba> otpusteneBebe = porodiliste.otpustiIzPorodilista(datum);
            System.out.println("Otpustene bebe: ");
            for (Beba beba : otpusteneBebe) {
                System.out.println(beba);
            }
            
            // Vracamo frekvencijsku tabelu
            int[] frekvencijskaTabela = porodiliste.vratiFrekvencijskuTabelu();
            System.out.println("Frekvencijska tabela: ");
            for (int i = 0; i < frekvencijskaTabela.length; i++) {
                System.out.println(i + ": " + frekvencijskaTabela[i]);
                
            }
            
        } catch (IllegalArgumentException e) {
            System.out.println("Greska pri kreiranju porodilista: " + e.getMessage());
        }
        
    }
    
}
