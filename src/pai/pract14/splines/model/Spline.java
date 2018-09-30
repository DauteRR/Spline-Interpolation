/**
 * File containing the Spline entity definition. 
 */
package pai.pract14.splines.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class which represents a spline of the Splines program. It was created for
 * the fourteenth practice of PAI (Programación de Aplicaciones Interactivas)
 * course of ULL (Universidad de la Laguna).
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 11 may. 2018
 */
public class Spline {

	/** Points of the spline. */
	private ArrayList<Point2D.Double>		splinePoints;
	/** Cubic polynomials of the spline. */
	private ArrayList<CubicPolynomial>	cubicPolynomials;

	/**
	 * Default constructor.
	 */
	public Spline() {
		splinePoints = new ArrayList<>();
		cubicPolynomials = new ArrayList<>();
	}

	/**
	 * Calculates and returns all the spline curve points.
	 * 
	 * @return Points
	 */
	public ArrayList<ArrayList<Point2D.Double>> getCurvePoints() {
		ArrayList<ArrayList<Point2D.Double>> curvePoints = new ArrayList<>();
		for (int i = 0; i < splinePoints.size() - 1; ++i) {
			Point2D.Double first = splinePoints.get(i);
			Point2D.Double second = splinePoints.get(i + 1);
			if (first.getX() > second.getX()) {
				double dummy = first.getX();
				first.x = second.getX();
				second.x = dummy;
			}
			ArrayList<Point2D.Double> curve = new ArrayList<>();
			for (double j = first.getX(); j <= second.getX(); j += 0.1) {
				curve.add(new Point2D.Double(j, cubicPolynomials.get(i).getImage(j)));
			}
			curvePoints.add(curve);
		}
		return curvePoints;
	}

	/**
	 * Calculates the cubic polynomials of the spline.
	 */
	private void calculateCubicPolynomials() {
		if (splinePoints.size() < 2) {
			return;
		}
		cubicPolynomials.clear();
		final int K = splinePoints.size() - 1;
		final int THREE = 3;
		final int TWO = 2;
		// Step 1
		double[] a = new double[K + 1];
		for (int i = 0; i < a.length; ++i) {
			a[i] = splinePoints.get(i).getY();
		}
		// Step 2
		double[] b = new double[K];
		double[] d = new double[K];
		double[] micro = new double[K];
		// Step 3
		double[] h = new double[K];
		for (int i = 0; i < h.length; ++i) {
			h[i] = splinePoints.get(i + 1).getX() - splinePoints.get(i).getX();
		}
		// Step 4
		double[] alpha = new double[K];
		for (int i = 1; i < alpha.length; ++i) {
			alpha[i] = (THREE / h[i]) * (a[i + 1] - a[i]);
			alpha[i] -= (THREE / h[i - 1]) * (a[i] - a[i - 1]);
		}
		// Step 5
		double[] c = new double[K + 1];
		double[] l = new double[K + 1];
		double[] z = new double[K + 1];
		// Step 6
		l[0] = 1.0;
		micro[0] = 0.0;
		z[0] = 0.0;
		// Step 7
		for (int i = 1; i < K; ++i) {
			l[i] = TWO
					* (splinePoints.get(i + 1).getX() - splinePoints.get(i - 1).getX())
					- h[i - 1] * micro[i - 1];
			micro[i] = h[i] / l[i];
			z[i] = (alpha[i] - h[i - 1] * z[i - 1]) / l[i];
		}
		// Step 8
		l[K] = 1.0;
		z[K] = 0.0;
		c[K] = 0.0;
		// Step 9
		for (int j = K - 1; j > -1; --j) {
			c[j] = z[j] - micro[j] * c[j + 1];
			b[j] = ((a[j + 1] - a[j]) / h[j])
					- ((h[j] * (c[j + 1] + TWO * c[j])) / THREE);
			d[j] = (c[j + 1] - c[j]) / (THREE * h[j]); 
		}
		// Step 10 and 11
		for (int i = 0; i < K; ++i) {
			double x = splinePoints.get(i).getX();
			double aCoefficient = d[i];
			double bCoefficient = c[i] - THREE * d[i] * x;
			double cCoefficient = b[i] - TWO * c[i] * x + THREE * d[i] * Math.pow(x, TWO);
			double dCoefficient = a[i] - b[i] * x + c[i] * Math.pow(x, TWO) - d[i] * Math.pow(x, THREE);
			cubicPolynomials.add(new CubicPolynomial(aCoefficient, bCoefficient, cCoefficient, dCoefficient));
		}
	}

	/**
	 * Sorts the points of the spline.
	 */
	public void sortSplinePoints() {
		Collections.sort(splinePoints, new Comparator<Point2D.Double>() {
			public int compare(Point2D.Double point1, Point2D.Double point2) {
				return Double.compare(point1.getX(), point2.getX());
			}
		});
	}

	/**
	 * Getter method for splinePoints attribute.
	 * 
	 * @return splinePoints
	 */
	public ArrayList<Point2D.Double> getSplinePoints() {
		return splinePoints;
	}

	/**
	 * Setter method for splinePoints attribute.
	 * 
	 * @param splinePoints
	 */
	public void setSplinePoints(ArrayList<Point2D.Double> splinePoints) {
		this.splinePoints = splinePoints;
		calculateCubicPolynomials();
	}

}
