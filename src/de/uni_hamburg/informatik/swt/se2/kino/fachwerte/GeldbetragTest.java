package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class GeldbetragTest
{

    @Test
	 public void testeKonvertiereVonString()
	 {
		 Geldbetrag g1 = Geldbetrag.konvertiere("50,00");
		 
		 assertEquals(Geldbetrag.konvertiere(5000), g1);
	 }
	
    @Test
    public void testeAddiere()
    {
    	Geldbetrag g1 = Geldbetrag.konvertiere(1000);
        Geldbetrag g2 = Geldbetrag.konvertiere(1000);
        
        Geldbetrag ergebnis = g1.addiere(g2);
        
        assertEquals(Geldbetrag.konvertiere(2000), ergebnis);
    }
    
    @Test
    public void testeSubtrahiere()
    {
    	Geldbetrag g1 = Geldbetrag.konvertiere(1000);
        Geldbetrag g2 = Geldbetrag.konvertiere(500);
        
        Geldbetrag ergebnis = g1.subtrahiere(g2);
        
        assertEquals(Geldbetrag.konvertiere(500), ergebnis);
    }
    
    @Test
    public void testeMultipliziere()
    {
    	Geldbetrag g1 = Geldbetrag.konvertiere(1000);
        
        Geldbetrag ergebnis = g1.multipliziere(5);
        
        assertEquals(Geldbetrag.konvertiere(5000), ergebnis);
    }
    
    @Test
    public void testeToStringRepresentation()
    {
    	Geldbetrag g1 = Geldbetrag.konvertiere(5000);
        
    	assertEquals("50,00 €", g1.toStringRepresentation());
    }

    @Test
    public void testeEquals()
    {
    	Geldbetrag g1 = Geldbetrag.konvertiere(1000);
        Geldbetrag g2 = Geldbetrag.konvertiere(1000);
        Geldbetrag g3 = Geldbetrag.konvertiere(541);
        Geldbetrag g4 = Geldbetrag.konvertiere(550);
        Geldbetrag g5 = Geldbetrag.konvertiere(641);
       

        assertEquals(g1, g2);
        assertFalse("Geldbetrag(541) ungleich Geldbetrag(550)", g3.equals(g4));
        assertFalse("Geldbetrag(541) ungleich Geldbetrag(641)", g3.equals(g5));
    }

    @Test
    public void testeHashCode()
    {
    	Geldbetrag g1 = Geldbetrag.konvertiere(1000);
        Geldbetrag g2 = Geldbetrag.konvertiere(1000);

        assertEquals("HashCode bleibt bei zwei Aufrufen gleich", g1.hashCode(),
                g1.hashCode());
        assertEquals("HashCodes mit gleichem Geldbetrag sind gleich",
                g1.hashCode(), g2.hashCode());
    }
}
