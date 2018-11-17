/**
 * Klasse Artikel
 *
 * @author JKrier, JVogt
 * @version Ueb 02
 */
public class Artikel
{
    private         int     artikelNummer;
    private         String  artikelBezeichnung;
    private         int     artikelBestand;
    
    private final   String  msg1 = "Die eingegebene ArtikelNummer ist nicht vierstellig!";
    private final   String  msg2 = "Hier bitte einen positiven Wert eingeben!";
    private final   String  msg3 = "Kleinerer Wert eingeben! Mit dem eingegebenen Wert würde es einen negativen Bestand geben.";
    private final   String  msg4 = "Bitte Artikelbezeichnung eingeben.";
 
    /**
     * Konstruktor mit drei Argumenten
     * 
     * @param nummer Muss vierstellig sein.
     * @param bezeichnung Muss vorhanden sein. (!= null)
     * @param bestand Muss positiv sein.
     */
    public Artikel(int nummer, String bezeichnung, int bestand)
    {
        String s = Integer.toString(nummer);
        check (((nummer >= 0000) && (nummer <= 9999)&&(s.length()==4)),msg1);
        check (((!bezeichnung.trim().equals("") && bezeichnung != null)), msg4);
        check ((bestand >=0), msg2);
        this.artikelNummer = nummer;
        this.artikelBezeichnung = new String(bezeichnung);
        this.artikelBestand = bestand;
    }
    /**
     * Konstruktor mit zwei Argumenten: Wenn ein Artikel neu im Sortiment 
     * angelegt werden soll, aber noch kein Artikel nicht vorhanden ist.
     * 
     * @param nummer Muss vierstellig sein.
     * @param bezeichnung Muss vorhanden sein. (!= null)
     */
    public Artikel(int nummer, String bezeichnung)
    {
        this(nummer,bezeichnung,0);
    }
 
    /**
     * Methode um einen Bestandszugang zu buchen.
     *
     * @param  artikelZugang Wert muss positiv sein. Sonst waere es ein Abgang.
     * @return artikelBestand.
     */
    public int zugang(int artikelZugang)
    {
        check((artikelZugang>0),msg2);
        artikelBestand += artikelZugang;                
         
        return artikelBestand;
    }
     
    /**
     * Methode um einen Bestandsabgang zu buchen.
     *
     * @param  artikelAbgang Wert muss positiv sein. Sonst waere es ein Zugang. Zudem darf der Bestand durch einen Abgang nicht negativ werden.
     * @return artikelBestand.
     */
    public int abgang (int artikelAbgang)
    {
        check ((artikelAbgang>0), msg2);
        check ((artikelBestand-artikelAbgang>0),msg3);
        artikelBestand -= artikelAbgang;
         
        return artikelBestand;
    }
     
    /**
     * Getter Methoden der Attribute Nummer, Bestand und Bezeichnung
     *
     * @return die jeweiligen Attributwerte.
     */
    public int getNummer()
    {
        return artikelNummer;
    }
    public int getBestand()
    {
        return artikelBestand;
    }
    public String getBezeichnung()
    {
        return artikelBezeichnung;
    }
     
    /**
     * Setter Methodefuer das Attribut Bezeichnung
     * @param artikelBezeichnung uebernimmt den im Methodenaufruf mitgegebenen "neueBezeichnung" Stringwert.
     * (Der Setter fuer die artikelNummer wird verzichtet, da Artikel IDs nicht veraendert werden sollten.)
     */
    public void setBezeichnung(String neueBezeichnung)
    {
        check (((!neueBezeichnung.trim().equals("") && neueBezeichnung != null)), msg1);
        artikelBezeichnung = neueBezeichnung;
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
     * toString Methode, bei der ein String generiert wird, der bei einer KonsolenAusgabe ausgegeben wird.
     */
    public String toString ()
    {
        return "Artikel: "+artikelNummer+" Bezeichnung: "+artikelBezeichnung+" Bestand: "+artikelBestand;
    } 
}
