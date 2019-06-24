package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barverkauf;

import javax.swing.*;
import javax.swing.plaf.multi.MultiLabelUI;
import java.awt.*;

public class BarVerkaufsWerkzeugUI {

    protected JDialog _mainDialog;
    protected JButton _okButton;
    protected JButton _abbrechenButton;
    protected JTextField _textfield;
    protected JLabel _gesamtBetragLabel;
    protected JLabel _rueckGeldLabel;


    public BarVerkaufsWerkzeugUI() {
        _mainDialog = new JDialog();
        _mainDialog.setModal(true);
        _mainDialog.setTitle("Verkauf");
        _mainDialog.setLayout(new BoxLayout(_mainDialog.getContentPane(), BoxLayout.Y_AXIS));

        _gesamtBetragLabel = new JLabel("", JLabel.CENTER);
        _gesamtBetragLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        _mainDialog.add(_gesamtBetragLabel);

        _textfield = new JTextField();
        _textfield.setHorizontalAlignment(SwingConstants.CENTER);
        _mainDialog.add(_textfield);

        _rueckGeldLabel = new JLabel("", JLabel.CENTER);
        _rueckGeldLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        _mainDialog.add(_rueckGeldLabel);

        _okButton = new JButton("OK");
        _abbrechenButton = new JButton("Abbrechen");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(_okButton);
        buttonPanel.add(_abbrechenButton);

        _mainDialog.add(buttonPanel);
    }
}
