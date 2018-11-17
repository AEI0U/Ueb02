import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *    Die Dialog Klasse fuer die Artikel-Klasse aus Ueb01
 *
 * @version  Ueb02
 * @author  JKrier, JVogt
 *
 */

public class ArtikelDialog
{

    private         Artikel artikel;
    private         Scanner input;
    
    private final   String  msg1 = "\nDer Artikel konnte nicht angelegt werden!";
    private final   String  msg2 = "\nDer Artikel existiert noch nicht!";
    private final   String  msg3 = "\nEs existiert bereits ein Artikel.";
    private final   String  msg4 = "\nBitte Artikelbezeichnung eingeben.";
    private final   String  msg5 = "\n Bitte j bzw. ja für eine Bestandseingabe oder n bzw nein für keine Bestandseingabe eingeben.";
    private final   String  msg6 = "\nHier bitte eine positive Zahl eingeben.";

    /**
     * Konstruktor fuer ArtikelDialog
     */
    public ArtikelDialog()
    {
        artikel = null;
        input = new Scanner( System.in );
    }

    /**
     * run() Interaktives Testprogramm
     * 
     */
    public void run()
    {
        int wahl=1;

        while ( wahl != 0 )
        {
            wahl = readlnInt("\n\n Bitte waehlen Sie aus: \n Neuen Artikel anlegen = 1\n Artikel entfernen = 2\n Bezeichnung eines Artikels aendern = 3"
                             +"\n Bestandszugang buchen = 4\n Bestandsabgang buchen = 5\n Ende = 0\n");
            try
            {

                System.out.println ("");
                switch (wahl)
                {
                    case 0:
                        System.out.println("Ende");
                        break;
                    case 1:
                        artikelNeu();
                        break;
                    case 2:
                        artikelLoeschen();
                        break;
                    case 3:
                        artikel.setBezeichnung(neueBezeichnung());
                        break;
                    case 4:
                        artikel.zugang( zugangWert() );
                        break;
                    case 5:
                        artikel.abgang( abgangWert() );
                        break;

                    default:
                        System.out.println("Ungueltige Eingabe!");
                        break;
                }
            }
            catch (AssertionError ae)
            {
                System.err.println(ae);
            } 
            catch ( NumberFormatException nfe)
            {  
                System.err.println(nfe);                           
            }       
            catch (RuntimeException rex)
            {  
                System.err.println(rex);
            } 
            if(artikel != null)
            {
                System.out.println("\n\n"+artikel+"\n");
            }  
        }
    }

    /**
     *  Anlegen eines neuen Artikels.
     *  Dabei wird abhaengig von der Bestandszahl der jeweilige Konstruktr verwendet.
     */
    private void artikelNeu()
    {
        check ((artikel == null),msg3);
        int artikelNummer;
        String artikelBezeichnung;
        String bestandAbfrage;
        int bestand;

        artikelNummer = readlnInt("ArtikelNummer eingeben: ");
        System.out.print("ArtikelBezeichnung eingeben: ");
        artikelBezeichnung = input.nextLine(); 
        check (((!artikelBezeichnung.trim().equals("") && artikelBezeichnung != null)), msg4);
        System.out.print("Soll ein Artikelbestand eingegeben werden? j/n");
        bestandAbfrage = input.nextLine();

        try
        {
            if (bestandAbfrage.equals("n")||bestandAbfrage.equals("nein"))
            {
                artikel = new Artikel(artikelNummer, artikelBezeichnung);
            }
            else if (bestandAbfrage.equals("j")||bestandAbfrage.equals("ja"))
            {
                bestand = readlnInt("Artikelbestand eingeben: ");  
                artikel = new Artikel(artikelNummer, artikelBezeichnung, bestand);
            }
            else
            {
                System.err.println (msg5);
            }
        }
        catch (AssertionError ae)
        {
            System.err.println(ae + msg1);                  
        }

        catch ( RuntimeException re )
        {  
            System.err.println(re+ msg1);                
        } 
    }  

    /**
     *  Bezeichnung eines Artikels aendern.
     *  
     *  @return Parameterwert der zu aendernden Bezeichnung eines Artikels
     */
    private String neueBezeichnung()
    {
        check ((artikel != null), msg2);

        String artikelBezeichnung;
        System.out.print("Neue Artikel-Bezeichnung eingeben: ");
        return artikelBezeichnung = input.nextLine();
    }  

    /**
     *  Existierenden Artikel aus dem Bestand loeschen.
     *  
     *  
     */
    private void artikelLoeschen()
    {
        check ((artikel != null), msg2);
        artikel = null;
        System.out.println("Artikel wurde entfernt!");
    }   

    /**
     * zugungBuchen() Wert einlesen, der der Methode zugang() uebergeben werden soll. 
     *  @return Parameterwert fuer methode zugang().
     */
    private int zugangWert()
    {
        check((artikel != null), msg2);      
        return readlnInt( "Zu buchender Zugang eingeben: " );
    }

    /**
     *  abgangBuchen() Wert einlesen, der der Methode abgang() uebergeben werden soll.
     *  
     *  @return  Parameterwert fuer methode abgang().
     */
    private int abgangWert()
    {
        check((artikel != null), msg2);
        return readlnInt("Zu buchender Abgang eingeben: ");
    }   

    /**
     * Einlesen einzugebener Integer Werte.
     *  
     *  @param  Eingabeaufforderung Der Eingabe-Aufforderungs-Text
     *  @return die Eingabe-Zahl
     */
    private int readlnInt(String eingabe)
    {
        int wert = 0;
        try {
            System.out.print(eingabe);
            wert= input.nextInt();
            input.nextLine();
            
        }
        
        catch (InputMismatchException e) 
        {
            System.err.println(e+ msg6);
        } 
        return wert;
    }

    /**
     * Check-Methode um Fehler zu erkennen und als 
     * IllegalArgumentException auszuwerfen.
     */
    private static void check(boolean bedingung, String msg)
    {
        if (!bedingung)
            throw new IllegalArgumentException(msg);
    } 

    /**
     *  main Methode Ausfuehren des interaktiven Tests.
     *  @param args Kommandozeilenparameter
     */  
    public static void main(String[] args)
    {
        ArtikelDialog dialog = new ArtikelDialog();
        dialog.run();
    }
}
