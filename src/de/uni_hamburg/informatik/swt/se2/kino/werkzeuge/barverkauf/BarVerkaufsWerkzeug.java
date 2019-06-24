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
        _ui._textfield.getDocument().addDocumentListener(new DocumentListener() {
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

        _ui._textfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("pressed enter");
            }
        });

        _ui._abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abbrechenButtonGedrueckt();
            }
        });

        _ui._okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okButtonGedrueckt();
            }
        });
    }

    private void okButtonGedrueckt() {
        _istBezahlungErfolgreich = true;
        _ui._mainDialog.setVisible(false);
    }


    private void abbrechenButtonGedrueckt() {
        _istBezahlungErfolgreich = false;
        _ui._mainDialog.setVisible(false);
    }

    private void textFieldAktualisiert() {
        String input = _ui._textfield.getText();

        Pattern p = Pattern.compile("^[0-9]{0,4}(\\,[0-9]{1,2})$");
        Matcher m = p.matcher(input);

        if (m.find()) {
            System.out.println("Matched: " + m.group(0));
            aktualisiereRueckgeld(Double.parseDouble(input.replace(",", ".")));
        } else {
            System.out.println("No match.");
            aktualisiereRueckgeld(0);
        }
    }

    private void aktualisiereRueckgeld(double eingegebenerBetrag) {
        _istBezahlungErfolgreich = false;
        if (eingegebenerBetrag < _betragInEuro) {
            _ui._rueckGeldLabel.setText("");
            _ui._okButton.setEnabled(false);
        } else {
            double rueckgeld = Math.round((_betragInEuro - eingegebenerBetrag)*100)/100.00;//Math.round(_betragInEuro - eingegebenerBetrag);
            _ui._rueckGeldLabel.setText(rueckGeldBetragText.replace("{BETRAG}", Double.toString(rueckgeld).replace(".", ",")));
            _ui._okButton.setEnabled(true);
        }
    }

    public boolean starteBarBezahlung(int geldbetrag) {

        _ui._okButton.setEnabled(false);
        _ui._rueckGeldLabel.setText("");
        _ui._textfield.setText("");

        _betragInEuro = ((double)geldbetrag)/100;
        _ui._gesamtBetragLabel.setText(gesamtBetragText.replace("{BETRAG}", Double.toString(_betragInEuro).replace(".", ",")));

        JDialog dialog = _ui._mainDialog;
        dialog.setLocationRelativeTo(null);
        dialog.setSize(400, 200);
        dialog.setVisible(true);

        return true;
    }
}
