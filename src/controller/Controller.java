package controller;

import java.io.*;

//import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.TaylorModel;
import view.TaylorFrame;
import controller.Command;

/**
 * Class Controller
 * Steuerung-Module
 * 
 * Die Steuerung verwaltet View und Modell. 
 * Die Benutzerinteraktionen werden hier bearbeitet.
 * Das Modell wird angepasst und View wird aktualisiert. 
 */
public class Controller implements ActionListener {
	
	/** The model. */
	private TaylorModel _model;
	
	/** The view. */
	private TaylorFrame _view;
	
	/**
	 * Instantiates a new controller.
	 *
	 * @param model the model
	 */
	public Controller(TaylorModel model) {
		_model = model;
	}
	
	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public TaylorModel getModel() {
        return _model;
    }
	
	/**
	 * Sets the view.
	 *
	 * @param view the new view
	 */
	public void setView(TaylorFrame view) {
		_view = view;
	}
	
	/**
	 * Actionen werden ausgeführt
	 *
	 * @param e the e
	 */
	// Befehle ausführen
	public void actionPerformed(ActionEvent e) {
		// Command Name
        String cmdName = e.getActionCommand();
        // Befehle ausführen
        if ( cmdName.equals(Command.CMD_FUNC) ) {
        	// Combobox Funtionsname
        	JComboBox cbFunc = _view.getCbFuncName();
        	int selectedIndex = cbFunc.getSelectedIndex();
        	_model.setFunctionIndex(selectedIndex);
        	_view.repaint();
        } else if ( cmdName.equals(Command.CMD_N) ) {
        	// Combobox Grad N
        	JComboBox cbN = _view.getCbN();
        	int selectedIndex = cbN.getSelectedIndex();
        	_model.setN(selectedIndex);
        	_view.repaint();
        } else if ( cmdName.equals(Command.CMD_A) ) {
        	// Entwicklungspunkt a (Textfeld)
        	JTextField txtA = _view.getTxtA();
        	String text = txtA.getText();
        	double a = 0.0;
        	// Zahl a aus Textfeld lesen
        	// Wenn eingabe kein Zahl ist,
        	// bleibt a = 0
        	try {
        		a = Double.parseDouble(text);
        	} catch (NumberFormatException nfe) {
        		// Eingabe ist kein Zahl
        		// Fehler in Console ausgeben
        		System.out.println("NumberFormatException: " + nfe.getMessage());
        	}
        	_model.setA( a );
        	_view.repaint();
        } else if ( cmdName.equals(Command.CMD_ABOUT) ) {
        	// Info (Menu)
        	_view.showAboutMsg();
        } else if ( cmdName.equals(Command.CMD_EXIT) ) {
        	// Schliessen (Menu)
        	System.exit(0); // Program beenden
        } else if ( cmdName.equals(Command.CMD_RESET) ) {
        	// Button Zurücksetzen (Toolbar)
        	// Werte im Panel zurücksetzen
        	JComboBox cbFunc = _view.getCbFuncName();
        	cbFunc.setSelectedIndex(0);
        	JTextField txtA = _view.getTxtA();
        	txtA.setText("0");
        	JComboBox cbN = _view.getCbN();
        	cbN.setSelectedIndex(0);
        	// Werte im Model zurücksetzen
        	_model.setN(0);
        	_model.setFunctionIndex(0);
        	_model.setA( 0.0 );
        	// Panel neu zeichnen
        	_view.repaint();
        } else if ( cmdName.equals(Command.CMD_ZOOM_UP) ) {
        	// vergrößern
        	_view.getTaylorGraphicPanel().zoomUp();
        	// Panel neu zeichnen
        	_view.repaint();
        }  else if ( cmdName.equals(Command.CMD_ZOOM_DOWN) ) {
	    	// verkleinern
	    	_view.getTaylorGraphicPanel().zoomDown();
	    	// Panel neu zeichnen
        	_view.repaint();
	    } 
	}
	

}
