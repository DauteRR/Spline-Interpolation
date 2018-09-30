/**
 * File containing the CubicPolynomial entity definition. 
 */
package pai.pract14.splines.model;

/**
 * Class which represents a cubic polynomial of the Splines program. It was
 * created for the fourteenth practice of PAI (Programación de Aplicaciones
 * Interactivas) course of ULL (Universidad de la Laguna).
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 12 may. 2018
 */
public class CubicPolynomial {

	/** First coefficient of the polynomial: ax³ + bx² + cx + d = f(x) */
	private double	aCoefficient;
	/** Second coefficient of the polynomial: ax³ + bx² + cx + d = f(x) */
	private double	bCoefficient;
	/** Third coefficient of the polynomial: ax³ + bx² + cx + d = f(x) */
	private double	cCoefficient;
	/** Fourth coefficient of the polynomial: ax³ + bx² + cx + d = f(x) */
	private double	dCoefficient;

	/**
	 * Constructor.
	 * 
	 * @param aCoefficient
	 * @param bCoefficient
	 * @param cCoefficient
	 * @param dCoefficient
	 */
	public CubicPolynomial(double aCoefficient, double bCoefficient,
			double cCoefficient, double dCoefficient) {
		this.aCoefficient = aCoefficient;
		this.bCoefficient = bCoefficient;
		this.cCoefficient = cCoefficient;
		this.dCoefficient = dCoefficient;
	}

	/**
	 * Returns the image of a given x point.
	 * 
	 * @param xPoint
	 *          Point.
	 * @return Image.
	 */
	public double getImage(double xPoint) {
		final int CUBE = 3;
		final int SQUARE = 2;
		return aCoefficient * Math.pow(xPoint, CUBE)
				+ bCoefficient * Math.pow(xPoint, SQUARE) + cCoefficient * xPoint
				+ dCoefficient;
	}

}
