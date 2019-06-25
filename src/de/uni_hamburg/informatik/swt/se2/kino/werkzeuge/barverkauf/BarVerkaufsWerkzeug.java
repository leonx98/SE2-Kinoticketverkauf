package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barverkauf;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BarVerkaufsWerkzeug {

    private BarVerkaufsWerkzeugUI _ui;
    private boolean _istBezahlungErfolgreich;
    private double _betragInEuro;

    private String gesamtBetragText = "Gesamtbetrag: {BETRAG} €";
    private String rueckGeldBetragText = "Rückgeld: {BETRAG} €";

    /**
     * Initialisiert das BarVerkaufsWerkzeug.
     */
    public BarVerkaufsWerkzeug()
    {
        _ui = new BarVerkaufsWerkzeugUI();
        registriereUIAktionen();

    }

    /**
     * Fügt der UI die Funktionalität hinzu mit entsprechenden Listenern.
     */
    private void registriereUIAktionen()
    {
        _ui.getTextField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                textFieldAktualisiert();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                textFieldAktualisiert();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        _ui.getAbbrechenButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abbrechenButtonGedrueckt();
            }
        });

        _ui.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okButtonGedrueckt();
            }
        });
    }

    private void okButtonGedrueckt() {
        _istBezahlungErfolgreich = true;
        _ui.getMainDialog().setVisible(false);
    }


    private void abbrechenButtonGedrueckt() {
        _istBezahlungErfolgreich = false;
        _ui.getMainDialog().setVisible(false);
    }

    private void textFieldAktualisiert() {
        String input = _ui.getTextField().getText();

        Pattern p = Pattern.compile("^[0-9]{0,4}(\\,[0-9]{1,2})$");
        Matcher m = p.matcher(input);

        if (m.find()) {
            //System.out.println("Matched: " + m.group(0));
            aktualisiereRueckgeld(Double.parseDouble(input.replace(",", ".")));
        } else {
            //System.out.println("No match.");
            aktualisiereRueckgeld(0);
        }
    }

    private void aktualisiereRueckgeld(double eingegebenerBetrag) {
        _istBezahlungErfolgreich = false;
        if (eingegebenerBetrag < _betragInEuro) {
            _ui.getRueckGeldLabel().setText("Rückgeld:");
            _ui.getOkButton().setEnabled(false);
        } else {
            double rueckgeld = Math.round((_betragInEuro - eingegebenerBetrag)*100)/100.00;//Math.round(_betragInEuro - eingegebenerBetrag);
            _ui.getRueckGeldLabel().setText(rueckGeldBetragText.replace("{BETRAG}", Double.toString(rueckgeld).replace(".", ",")));
            _ui.getOkButton().setEnabled(true);
        }
    }

    public boolean starteBarBezahlung(int geldbetrag) {

        _ui.getOkButton().setEnabled(false);
        _ui.getRueckGeldLabel().setText("Rückgeld:");
        _ui.getTextField().setText("");

        _betragInEuro = ((double)geldbetrag)/100;
        _ui.getGesamtBetragLabel().setText(gesamtBetragText.replace("{BETRAG}", Double.toString(_betragInEuro).replace(".", ",")));

        JDialog dialog = _ui.getMainDialog();
        dialog.setLocationRelativeTo(null);
        //dialog.setSize(400, 200);
        dialog.pack();
        dialog.setVisible(true);

        return _istBezahlungErfolgreich;
    }
}
