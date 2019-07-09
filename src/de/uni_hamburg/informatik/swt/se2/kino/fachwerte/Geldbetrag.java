package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

public class Geldbetrag implements Comparable<Geldbetrag>
{

	private final int _euroCent;

	/**
	 * Konstruktor
	 *
	 * @param eingabeGeld Initialbetrag
	 */
	private Geldbetrag(int eingabeGeld)
	{
		_euroCent = eingabeGeld;
	}

	/**
	 * Initialisert einen Geldbetrag aus einem int-Wert
	 *
	 * @param eingabeGeld Initialbetrag
	 *
	 * @require eingabeGeld >= 0
	 *
	 * @return Geldbetrag
	 */
	public static Geldbetrag konvertiere(int eingabeGeld)
	{
		assert eingabeGeld >= 0 : "Vorbedingung verletzt: eingabeGeld >= 0";

		return new Geldbetrag(eingabeGeld);
	}

	/**
	 * Initialisert einen Geldbetrag aus einem String mit Format "_Euro_,_Cent_" / "_Euro_". Sonst null.
	 *
	 * @param eingabeGeld Initialbetrag
	 *
	 * @return Geldbetrag
	 */
	public static Geldbetrag konvertiere(String eingabeGeld)
	{
		if (eingabeGeld.matches("^[0-9]{1,7}(,[0-9]{0,2})?$")) {

			int parsedBetrag = Integer.parseInt(eingabeGeld.replace(",", ""));

			return new Geldbetrag(parsedBetrag);
		} else {
			return null;
		}

	}

	/**
	 * Gibt den Geldbetrag in EuroCent zurück
	 *
	 * @return int
	 */
	public int getEuroCent() {
		return _euroCent;
	}

	/**
	 * Repräsentiert den Geldbetrag als formatierten €-Norm String
	 *
	 * @return String
	 */
	public String toStringRepresentation()
	{
		long euro = _euroCent / 100;
		long cent = _euroCent % 100;
		
		return euro + "," + String.format("%02d", cent ) + " €";
	}

	/**
	 * Addiert den angegebenen Geldbetrag und gibt einen neuen Geldbetrag zurück
	 *
	 * @param g2 Der aufzuaddierende Geldbetrag
	 *
	 * @require istAdditionMoeglich(g2)
	 * @ensure return != null
	 *
	 * @return Geldbetrag
	 */
	public Geldbetrag addiere(Geldbetrag g2)
	{
        assert istAdditionMoeglich(g2) : "Vorbedingung verletzt: Summe wäre größer als Integer.MAX_VALUE";
        
        return new Geldbetrag(this._euroCent + g2._euroCent);
	}

	/**
	 * Subtrahiert den angegebenen Geldbetrag und gibt einen neuen Geldbetrag zurück
	 *
	 * @param g2 Der abziehende Geldbetrag
	 *
	 * @require istSubtraktionMoeglich(g2)
	 * @ensure return != null
	 *
	 * @return Geldbetrag
	 */
	public Geldbetrag subtrahiere(Geldbetrag g2)
    {
        assert istSubtraktionMoeglich(g2) : "Vorbedingung verletzt: Differenz wäre kleiner als Integer.MIN_VALUE";
        
        return new Geldbetrag(this._euroCent - g2._euroCent);
    }

	/**
	 * Multipliziert den aktuellen Geldbetrag mit einem Faktor und gibt einen neuen Geldbetrag zurück
	 *
	 * @param faktor Der Faktor
	 *
	 * @require istMultiplikationMoeglich(faktor)
	 * @ensure return != null
	 *
	 * @return Geldbetrag
	 */
    public Geldbetrag multipliziere(int faktor)
    {
    	assert istMultiplikationMoeglich(faktor) : "Vorbedingung verletzt: Produkt wäre außerhalb des Integer-Bereichs";
    	
        return new Geldbetrag((this._euroCent * faktor));
    }

	/**
	 * Prüft, ob eine Addition möglich ist
	 *
	 * @param g2 Der Summand
	 *
	 * @return true, wenn möglich. Sonst false
	 */
	public boolean istAdditionMoeglich(Geldbetrag g2)
	{
		return ((long)this._euroCent + (long)g2._euroCent) < Integer.MAX_VALUE;
	}

	/**
	 * Prüft, ob eine Subtraktion möglich ist
	 *
	 * @param g2 Der Subtrahend
	 *
	 * @return true, wenn möglich. Sonst false
	 */
	public boolean istSubtraktionMoeglich(Geldbetrag g2)
	{
		return ((long)this._euroCent - (long)g2._euroCent) > Integer.MIN_VALUE;
	}

	/**
	 * Prüft, ob eine Multiplikation möglich ist
	 *
	 * @param faktor Der Faktor
	 *
	 * @return true, wenn möglich. Sonst false
	 */
	public boolean istMultiplikationMoeglich(int faktor)
	{
		return ((long)this._euroCent * faktor) < Integer.MAX_VALUE;
	}
	
	 @Override
	 public int compareTo(Geldbetrag g2)
	 {
	    return _euroCent - g2._euroCent;
	 }
	
	@Override
	public boolean equals(Object g2)
	{
	   return (g2 instanceof Geldbetrag) && equals((Geldbetrag)g2);
	}

	private boolean equals(Geldbetrag g2)
	{
		return (_euroCent == g2._euroCent) && (_euroCent == g2._euroCent) && (_euroCent == g2._euroCent);
	}

	@Override
	public int hashCode()
	{
		return _euroCent;
	}
}
