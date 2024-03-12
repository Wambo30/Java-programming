package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import controller.Command;
import controller.Controller;

import model.TaylorModel;
import view.TaylorGraphicPanel;

/**
 * Class TaylorFrame.
 * View-Modell
 * 
 * Hier wird die Benutzeroberfläche erstellt.
 * Das Programm enthält ein Menü mit den Untermenüs Datei, Zoom und Hilfe. 
 * Zudem existieren einige Benutzer-Elemente oberhalb der Grafik-Oberfläche,
 * wie der Entwicklungspunkt a, Grad n und die Funktion f(x).
 * Auf der Grafikoberfläche wird die Funktion und die Taylorentwicklung gleichzeitig in einem
 * Koordinatensystem dargestellt.
 * Bei den Benutzerinteraktionen wird der Controller informiert. 
 */
@SuppressWarnings("serial")
public class TaylorFrame extends JFrame {

	/** Titel der Fenster */
	private String _title = "Taylor-Polynom";
	
	/** Grafikoberfläche */
	private TaylorGraphicPanel _taylorGraphicPanel;
	
	/** Controller */
	private Controller _controller = null;

	/** ComboBox Funktionname */
	private JComboBox _cbFuncName;
	
	/** TextField Entwicklungspunkt a*/
	private JTextField _txtA;
	
	/** ComboBox Grad N deer Taylor-Polynom */
	private JComboBox _cbNName;
	
    /**
     * Instantiates a new taylor frame.
     *
     * @param controller the controller
     */
    public TaylorFrame(Controller controller) {
    	_controller = controller;
    	_controller.setView(this);
    	
    	TaylorModel model = _controller.getModel();
    	
        Container contentPane = getContentPane(); 
        contentPane.setLayout(new BorderLayout());
        
        _taylorGraphicPanel = new TaylorGraphicPanel(model);
        getContentPane().add(_taylorGraphicPanel,BorderLayout.CENTER);
        
        
        JPanel panelEast = new JPanel();
        // panelEast.setLayout(new BoxLayout(panelEast, BoxLayout.X_AXIS));
        contentPane.add(panelEast,BorderLayout.NORTH);
        
        JLabel _lblFunc;
        _lblFunc = new JLabel("f(x)=");
        _lblFunc.setFont(new Font("SansSerif",Font.PLAIN,12));
        _lblFunc.setToolTipText("Funktion");
        _lblFunc.setOpaque(true);
        panelEast.add(_lblFunc);
        _cbFuncName = new JComboBox(model.funcNames);
        _cbFuncName.setPreferredSize(new Dimension(100, 20));
        _cbFuncName.setToolTipText("Funktion");
        _cbFuncName.setActionCommand(Command.CMD_FUNC);
        _cbFuncName.addActionListener(_controller);
        panelEast.add(_cbFuncName);
        
        JLabel _lblA;
        _lblA = new JLabel(" a=");
        _lblA.setFont(new Font("SansSerif",Font.PLAIN,12));
        _lblA.setToolTipText("Der Entwicklungspunkt a");
        _lblA.setOpaque(true);
        panelEast.add(_lblA);
        _txtA = new JTextField("0");
        _txtA.setPreferredSize(new Dimension(50, 20));
        _txtA.setToolTipText("Der Entwicklungspunkt a");
        _txtA.setActionCommand(Command.CMD_A);
        _txtA.addActionListener(_controller);
        panelEast.add(_txtA);
        
        JLabel _lblN;
        _lblN = new JLabel(" n=");
        _lblN.setFont(new Font("SansSerif",Font.PLAIN,12));
        _lblN.setToolTipText("Das Grad des Taylorpaloynoms");
        _lblN.setOpaque(true);
        panelEast.add(_lblN);
        _cbNName = new JComboBox(model.nNames);
        _cbNName.setPreferredSize(new Dimension(50, 20));
        _cbNName.setActionCommand(Command.CMD_N);
        _cbNName.setToolTipText("Das Grad des Taylorpaloynoms");
        _cbNName.addActionListener(_controller);
        panelEast.add(_cbNName);
 

        JButton _btnReset = new JButton();
        _btnReset.setText("Zurücksetzen");
        _btnReset.setPreferredSize(new Dimension(100, 20));
        _btnReset.setToolTipText("Die Variablen zurücksetzen");
        _btnReset.setActionCommand(Command.CMD_RESET);
        _btnReset.addActionListener(_controller);
        panelEast.add(_btnReset);
        
        initMenu();
        
        setTitle(_title);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Menu erstellen
     */
    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        
        
        // Menu Datei
        JMenu menuFile = new JMenu("Datei");
        menuFile.setMnemonic(KeyEvent.VK_D);
        // neue Menu Item
        JMenuItem itemExit = new JMenuItem("Beenden");
        // Command zuweisen
        itemExit.setActionCommand(Command.CMD_EXIT);
        // Tastenkürzel
        itemExit.setMnemonic(KeyEvent.VK_B);
        // Wenn ein Aktion ausgelöst wird,
        // wird Controller aufgerufen
        itemExit.addActionListener(_controller);
        // Item zu Menu hinzufügen
        menuFile.add(itemExit);
        // Menu zu Menubar hinzufügen
        menuBar.add(menuFile);
        
        // Menu Zoom
        JMenu menuZoom = new JMenu("Zoom");
        menuZoom.setMnemonic(KeyEvent.VK_Z);
        JMenuItem itemVergroessern = new JMenuItem("Vergrößen");
        itemVergroessern.setActionCommand(Command.CMD_ZOOM_UP);
        itemVergroessern.setMnemonic(KeyEvent.VK_G);
        itemVergroessern.addActionListener(_controller);
        menuZoom.add(itemVergroessern); 
        
        JMenuItem itemVerkleinern = new JMenuItem("Verkleinern");
        itemVerkleinern.setActionCommand(Command.CMD_ZOOM_DOWN);
        itemVerkleinern.setMnemonic(KeyEvent.VK_K);
        itemVerkleinern.addActionListener(_controller);
        menuZoom.add(itemVerkleinern);
        
        menuBar.add(menuZoom);      
        
        // Menu Hilfe
        JMenu menuHelp = new JMenu("Hilfe");
        menuHelp.setMnemonic(KeyEvent.VK_H);
        
        JMenuItem itemAbout = new JMenuItem("Info");
        itemAbout.setActionCommand(Command.CMD_ABOUT);
        itemAbout.setMnemonic(KeyEvent.VK_I);
        itemAbout.addActionListener(_controller);
        menuHelp.add(itemAbout);
        menuBar.add(menuHelp);
        

        setJMenuBar(menuBar);
    }
    
    /**
     * Show Info Dialog
     */
    public void showAboutMsg() {
        JOptionPane.showMessageDialog(this,
                "Zur Berechnung des Taylorpolynom müssen folgende Eingaben gemacht werden:"
                + "\r\n f(x) : Die Funktion, für die das Taylorpolynom entwickelt wird auswählen."
                + "\r\n a : Punkt, um den das Taylorpolynom entwickelt wird. Rationale Zahl eingeben. "
                + "\r\n n : Grad des Taylorpolynoms auswählen."
                + "\r\n "
                + "\r\n Zurücksetzen: Der Button bringt das Programm wieder in den Ausgangszustand zurück.",
                
                _title,
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Gibt taylorGraphicPanel zurück
     *
     * @return the taylor graphic panel
     */
    public TaylorGraphicPanel getTaylorGraphicPanel() {
    	return _taylorGraphicPanel;
    }
    
    /**
     * Gibt JComboBox Funktionname zurück
     *
     * @return the JComboBox func name
     */
    public JComboBox getCbFuncName() {
    	return _cbFuncName;
    }
    
    /**
     * Gibt JTextField für Entwicklungspunkt a zurück
     *
     * @return the JTextField txt A
     */
    public JTextField getTxtA() {
    	return _txtA;
    }
    
    /**
     * Gibt the JComboBox für Grad N zurück
     *
     * @return the JComboBox cb N
     */
    public JComboBox getCbN() {
    	return _cbNName;
    }

}
