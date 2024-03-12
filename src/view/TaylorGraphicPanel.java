package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import model.TaylorModel;

/**
 * The Class TaylorGraphicPanel.
 * Grafikfläche
 * 
 * Auf der Grafikoberfläche wird die Funktion und die Taylorentwicklung gleichzeitig in einem
 * Koordinatensystem dargestellt.
 */
@SuppressWarnings("serial")
public class TaylorGraphicPanel extends JPanel {
	
	/** Das Modell */
	private TaylorModel _model = null;
	
	/** Das Zoomfaktor */
	private double _zoom = 1.0;
	
	
	/**
	 * Zeichnet x-y-Achsen
	 *
	 * @param g the g
	 * @param xCenter the x center
	 * @param yCenter the y center
	 * @param factor the factor
	 */
	private void drawAxis(Graphics g, int xCenter, int yCenter, int factor) {
		int lineHeight = 2;
		int width   = getWidth();
        int height  = getHeight();
        
        // Antialiasing zum Scharfzeichnen der linien
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.setColor(Color.BLACK);
        // x-Achse
        // x-Achsen Linie
        g.drawLine(0, yCenter, width, yCenter);
        for(int i=1; i<(width - xCenter) / factor + 1; i++) {
        	// positive Achse
        	int x = xCenter + i * factor;
        	// Einheit-Linie zeichnen
        	g.drawLine(x, yCenter - lineHeight, x, yCenter + lineHeight);
        	// Einheit zeichnen
        	g.drawString(String.valueOf( i ), x - 2, yCenter + lineHeight + 15);
        	// negative
        	x = xCenter - i * factor;
        	// Einheit-Linie zeichnen
        	g.drawLine(x, yCenter - lineHeight, x, yCenter + lineHeight);
        	// Einheit zeichnen
        	g.drawString(String.valueOf( -1 * i ), x - 5, yCenter + lineHeight + 15);
        }
        // x-Achse Pfeil
        g.drawLine(width - 3, yCenter - 3, width, yCenter);
        g.drawLine(width - 3, yCenter + 3, width, yCenter);
        // y-Achsen Linie
        g.drawLine(xCenter, 0, xCenter, height);
        for(int i=1; i<(height - yCenter) / factor + 1; i++) {
        	// negative Achse
        	int y = yCenter + i * factor;
        	// Einheit-Linie zeichnen
        	g.drawLine(xCenter - lineHeight, y, xCenter + lineHeight, y);
        	// Einheit zeichnen
        	g.drawString(String.valueOf( -1 * i ), xCenter + lineHeight + 15, y + 2);
        	// negative
        	y = yCenter - i * factor;
        	// Einheit-Linie zeichnen
        	g.drawLine(xCenter - lineHeight, y, xCenter + lineHeight, y);
        	// Einheit zeichnen
        	g.drawString(String.valueOf( i ), xCenter + lineHeight + 15, y + 2);
        }
        // y-Achse Pfeil
        g.drawLine(xCenter - 3, 3, xCenter, 0);
        g.drawLine(xCenter + 3, 3, xCenter, 0);
	}
	
	/**
	 * Zeichnet die Funktion und das Taylorpolynom
	 *
	 * @param g the g
	 * @param xCenter the x center
	 * @param yCenter the y center
	 * @param factor the factor
	 */
	private void drawFunctions(Graphics g, int xCenter, int yCenter, int factor) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
		int lineHeight = 2;
		int width   = getWidth();
        int height  = getHeight();

        double xValues[] = new double[width];
        xValues[0] = (double) (xCenter) / factor * -1.0;
        for ( int i = 1; i < xValues.length; i++ ) {
        	xValues[i] = xValues[i-1] + 1.0/factor;
        }
        xValues[xCenter] = 0.0;
        
        // Entwicklungspunkt a zeichnen
        int r = 6;
        double a = _model.getPointA();
        double aValue = _model.funcValue(a);
        double x_a = xCenter + a * factor - r;
        double y_a = yCenter - aValue * factor - r;
        g.setColor(Color.GREEN);
        g.fillOval((int)x_a, (int)y_a, 2*r, 2*r);

        
        int xPoints[] = new int[width];
        int yPoints[] = new int[width];
        for ( int i = 0; i < yPoints.length; i++ ) {
        	xPoints[i] = i;
        	double yValue = _model.funcValue(xValues[i]);
        	yPoints[i] = (int) (yCenter - yValue * factor);
        }
        
        
        // Echte Function Zeichnen
        g.setColor(Color.RED);

        for ( int i = 1; i < xValues.length; i++ ) {
        	g.drawLine(xPoints[i-1], yPoints[i-1], xPoints[i], yPoints[i]);
        }
        
        // Taylor Polynom Zeichnen
        g.setColor(Color.BLUE);
        
        int y0 = 0;
        for ( int i = 0; i < xValues.length; i++ ) {
        	double yValue = _model.taylorValue(xValues[i]);
        	int y1 = (int) (yCenter - yValue * factor);
        	if (i>0)
        		g.drawLine(xPoints[i-1], y0, xPoints[i], y1);
        	y0 = y1;
        }
        
	}
    
    /**
     * Zeichnet die Grafikfläche
     *
     * @param g the g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int width   = getWidth();
        int height  = getHeight();

        // Die gesamte Zeichenflaeche mit einer Hintegrundfrabe (z.B. weiss) fuellen
        g.setColor(Color.WHITE);
        g.fillRect(0,0,width,height);

        // Die Grafik soll nur dann gezeichnet werden, 
        // wenn das Panel eine bestimmte Mindestgroesse hat 
        if ( width >= 200 && height >= 100 ) {

            // Nun muss die Gleitkommazahl "factor", die der Anzahl der Pixel pro Einheit entspricht, 
            // ermittelt werden, so dass die Grafik nicht verzerrt dargestellt wird:
        	// X-Achsen Länge bestimmen
        	// Standard Range -10 bis 10
        	// Die länge entpricht 2*11, wegen Sicherheitsabstand
        	double xAchsenLange = 11.0 * 2.0;
        	// Faktor: Pixelanzahl pro Einheit
            double factorW = (double)width / (xAchsenLange); 
            // Mit Zoomfaktor multiplizieren
            double factor = factorW * _zoom;

            
            // Koordinatenursprung:
            // Mittelpunkt der Zeichnung
            int xCenter = (int)(width* 0.5 + 0.5);
            int yCenter = (int)(height* 0.5 + 0.5);
            yCenter = height - yCenter;
            
            // Zeichne Achsen
            drawAxis(g, xCenter, yCenter, (int) factor);
            // Zeichne Funktionen
            drawFunctions(g, xCenter, yCenter, (int) factor);
            System.out.println("xCenter="+xCenter+" yCenter="+yCenter+" factorW="+factorW+" factorH=");            

        }            
    }
    
    /**
     * Instantiates a new taylor graphic panel.
     *
     * @param model the model
     */
    public TaylorGraphicPanel(TaylorModel model) {
    	_model = model;
    }
    
    /**
     * Vergrößern
     */
    public void zoomUp() {
    	_zoom *= 1.5;
    }
    
    /**
     * Verkleinern
     */
    public void zoomDown() {
    	_zoom /= 1.5;
    }
}