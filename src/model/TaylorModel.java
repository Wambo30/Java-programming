/*
 * Das Modell
 * 
 * Das Modell beinhaltet, die Daten
 * zum Darstellung der Taylorentwicklung nötig sind.
 */
package model;

import java.util.function.Function;

import model.TaylorFunc;


/**
 * Class TaylorModel
 * Das Modell
 * 
 * Das Modell beinhaltet, die Daten
 * zur Darstellung die für die Taylorentwicklung nötig sind.
 */
public class TaylorModel {
	
	/** Konstanten für Funktionsnamen. */
	public static final String funcNames[]={"exp(x)","sin(x)","cos(x)"};
	
	/** Konstanten für den Grad des Taylor-Polynoms */
	public static final String nNames[]={"0","1","2","3","4","5"};
	
	/** The taylor func. */
	private TaylorFunc _taylorFunc = null;

	/**
	 * Instantiates a new taylor model.
	 */
	public TaylorModel() {
		TaylorFunc taylorFunc = new TaylorFunc();
		_taylorFunc = taylorFunc;
	}

	/**
	 * Gibt den Wert für ein Punkt x
	 * des Taylor-Polynoms zurück
	 *
	 * @param x the x
	 * @return P(x)
	 */
	public double taylorValue(double x) {
		return _taylorFunc.taylorValue(x);
	}
	
	/**
	 * Gibt den Wert für ein Punkt x
	 * der Funktion zurück
	 *
	 * @param x Punkt x
	 * @return Wert f(x)
	 */
	public double funcValue(double x) {
		return _taylorFunc.funcValue(x);
	};
	
	/**
	 * Sets the function index.
	 *
	 * @param index the new function index
	 */
	public void setFunctionIndex(int index) {
		switch(index) {
		case 1:
			_taylorFunc.setFunction(TaylorFuncEnum.SIN);
			break;
		case 2:
			_taylorFunc.setFunction(TaylorFuncEnum.COS);
			break;
		default:
			_taylorFunc.setFunction(TaylorFuncEnum.EXP);
			break;
		}
	}
	
	/**
	 * Setze Grad n
	 *
	 * @param n the new n
	 */
	public void setN(int n) {
		_taylorFunc.setN(n);
	}
	
	/**
	 * Setze Entwicklungspunkt a
	 *
	 * @param a the new a
	 */
	public void setA(double a) {
		_taylorFunc.setA(a);
	}
	
	/**
	 * Gibt Entwicklungspunkt a zurück
	 *
	 * @return Entwicklungspunkt a
	 */
	public double getPointA() {
		return _taylorFunc.getA();
	}
	
}
