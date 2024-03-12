package model;

import java.util.function.Function;
import model.TaylorFuncEnum;

// TODO: Auto-generated Javadoc
/**
 * Class TaylorFunc
 * Berechnet Funktionswerte und Taylorentwicklung
 */
public class TaylorFunc {
	
	/** Funktionsname */
	private String _name = "";
	
	/** Grad  n. */
	// private double[] _koef = null;
	private int _N = 0;
	
	/** Entwicklungspunkt a. */
	private double _a = 0.0;
	
	/** Funktion */
	private TaylorFuncEnum _func = TaylorFuncEnum.UNDEFINED;
	
	/** The Constant factorials. */
	private static final double[] factorials = {1.0, 1.0, 2.0, 6.0, 24.0, 120.0, 720.0, 5040.0, 40320.0, 362888.0};
	
	/**
	 * Instantiates a new taylor func.
	 */
	public TaylorFunc() {
		setFunction(TaylorFuncEnum.EXP);
	}
	
	/**
	 * Getter: Grad n.
	 *
	 * @return the n
	 */
	// Getter-Methoden
	public int getN() {
		return _N;
	}
	
	/**
	 * Getter: Entwicklungspunkt a
	 *
	 * @return the a
	 */
	public double getA() {
		return _a;
	}
	
	/**
	 * Gets the function name.
	 *
	 * @return the name
	 */
	public String getName() {
		return _name;
	}
	
	
	/**
	 * Sets the n.
	 *
	 * @param n the new n
	 */
	// Setter-Methoden
	public void setN(int n) {
		_N = n;
	}
	
	/**
	 * Sets the function.
	 *
	 * @param func the new function
	 */
	public void setFunction(TaylorFuncEnum func) {
		_func = func;
	}

	/**
	 * Wert der Taylor-Polynome berechnen
	 *
	 * @param x the x
	 * @return the double
	 */
	public double taylorValue(double x) {
		double sum = 0.0;
		double a = _a;
		double xa = x - a;

		switch ( _func ){
		case EXP:
			for (int i = 0; i<_N+1; i++) {
				sum += Math.exp(a) / factorials[i] * Math.pow( xa, (double) i) ;
			}
			break;
		case COS:
			sum += Math.cos(a);
			if (_N>0)
				sum += -1 * Math.sin(a) / factorials[1] * Math.pow( xa, 1.0);
			if (_N>1)
				sum += -1 * Math.cos(a) / factorials[2] * Math.pow( xa, 2.0);
			if (_N>2)
				sum += Math.sin(a) / factorials[3] * Math.pow( xa, 3.0);
			if (_N>3)
				sum += Math.cos(a) / factorials[4] * Math.pow( xa, 4.0);
			if (_N>4)
				sum += -1 * Math.sin(a) / factorials[5] * Math.pow( xa, 5.0);
			if (_N>5)
				sum += -1 * Math.cos(a) / factorials[6] * Math.pow( xa, 6.0);
			if (_N>6)
				sum += Math.sin(a) / factorials[7] * Math.pow( xa, 7.0);
			if (_N>7)
				sum += Math.cos(a) / factorials[8] * Math.pow( xa, 8.0);
			break;
		case SIN:
			sum += Math.sin(a);
			if (_N>0)
				sum += Math.cos(a) / factorials[1] * Math.pow( xa, 1.0);
			if (_N>1)
				sum += -1 * Math.sin(a) / factorials[2] * Math.pow( xa, 2.0);
			if (_N>2)
				sum += -1 * Math.cos(a) / factorials[3] * Math.pow( xa, 3.0);
			if (_N>3)
				sum += Math.sin(a) / factorials[4] * Math.pow( xa, 4.0);
			if (_N>4)
				sum += Math.cos(a) / factorials[5] * Math.pow( xa, 5.0);
			if (_N>5)
				sum += -1 * Math.sin(a) / factorials[6] * Math.pow( xa, 6.0);
			if (_N>6)
				sum += -1 * Math.cos(a) / factorials[7] * Math.pow( xa, 7.0);
			if (_N>7)
				sum += Math.sin(a) / factorials[8] * Math.pow( xa, 8.0);
			break;

		case UNDEFINED:
		default:
			return 0.0;
		}
		return sum;
	}
	
	
	
	/**
	 * Setze Funktion
	 *
	 * @param x the x
	 * @return the double
	 */
	public double funcValue(double x) {
		switch ( _func ){
		case EXP:
			return Math.exp(x);
		case COS:
			return Math.cos(x);
		case SIN:
			return Math.sin(x);
		case UNDEFINED:
		default:
			return 0.0;
		}
	}

	/**
	 * Sets the a.
	 *
	 * @param a the new a
	 */
	public void setA(double a) {
		_a = a;
	};
}
