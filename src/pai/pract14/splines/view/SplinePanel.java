/**
 * File containing the SplinePanel entity definition. 
 */
package pai.pract14.splines.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

/**
 * Class which represents the spline panel of the Splines program GUI. It was
 * created for the fourteenth practice of PAI (Programación de Aplicaciones
 * Interactivas) course of ULL (Universidad de la Laguna).
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 11 may. 2018
 */
public class SplinePanel extends JPanel {
	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;
	/** Spline points of the spline panel. */
	private ArrayList<SplinePoint> splinePoints;
	/** Curve points. */
	private ArrayList<ArrayList<Point2D.Double>> curvePoints;
	/** Selected spline point. */
	private int selectedSplinePoint = -1;
	/** Establishes is the cubic splines must be shown. */
	private boolean showCubicSplines;
	/** Establishes is the linear splines must be shown. */
	private boolean showLinearSplines;

	/**
	 * Default constructor.
	 * 
	 * @param width
	 *            Width of the panel.
	 * @param height
	 *            Height of the panel.
	 * @param mouseListener
	 *            Mouse listener for the mouse clicks.
	 * @param mouseMotionListener
	 *            Mouse motion listener for the mouse movements.
	 */
	public SplinePanel(int width, int height, MouseListener mouseListener, MouseMotionListener mouseMotionListener,
			KeyListener keyboardListener) {
		setName("Spline panel");
		setPreferredSize(new Dimension(width, height));
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseMotionListener);
		splinePoints = new ArrayList<>();
		curvePoints = new ArrayList<>();
		setBackground(Color.WHITE);
		addKeyListener(keyboardListener);
		showCubicSplines = true;
		showLinearSplines = false;
	}

	/**
	 * Paints the panel.
	 * 
	 * @param g
	 *            Graphic object where the panel will be painted.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		requestFocus();
		requestFocusInWindow();
		super.paintComponent(g);
		final int CURVE_POINTS_RADIUS = 3;
		Graphics2D g2D = (Graphics2D) g;
		if (splinePoints.size() < 1) {
			return;
		}
		if (showLinearSplines) {
			for (int i = 0; i < splinePoints.size(); ++i) {
				if (i == splinePoints.size() - 1) {
					continue;
				}
				g2D.setColor(Color.BLACK);
				final int WIDTH = 3;
				g2D.setStroke(new BasicStroke(WIDTH));
				g2D.draw(new Line2D.Double(splinePoints.get(i).getCenterX(), splinePoints.get(i).getCenterY(),
						splinePoints.get(i + 1).getCenterX(), splinePoints.get(i + 1).getCenterY()));
			}
		}
		
		for (int i = 0; i < splinePoints.size(); ++i) {
			splinePoints.get(i).paintComponent(g);
			if (i == selectedSplinePoint) {
				int XOFFSET = SplinePoint.GRAPHIC_POINT_DIAMETER;
				int YOFFSET = SplinePoint.GRAPHIC_POINT_DIAMETER;
				final int MIDDLE = 2;
				if (splinePoints.get(i).getCenterX() > (this.getWidth() / MIDDLE)) {
					XOFFSET *= -4.0;
				}
				if (splinePoints.get(i).getCenterY() > (this.getHeight() / MIDDLE)) {
					YOFFSET *= -1.0;
				}
				int xPos = (int) splinePoints.get(i).getCenterX();
				int yPos = (int) splinePoints.get(i).getCenterY();
				g.drawString("( " + xPos + ", " + yPos + ")", xPos + XOFFSET, yPos + YOFFSET);
			}

			if (i == splinePoints.size() - 1) {
				break;
			}
			if (showCubicSplines) {
				ArrayList<Point2D.Double> curve = curvePoints.get(i);
				if (showCubicSplines) {
					for (Point2D.Double point : curve) {
						g2D.setColor(splinePoints.get(i).getGraphicPointColor());
						g2D.fillOval((int) point.getX(), (int) point.getY(), CURVE_POINTS_RADIUS, CURVE_POINTS_RADIUS);
					}
				}
			}
		}
	}

	public void setShowCubicSplines(boolean showCubicSplines) {
		this.showCubicSplines = showCubicSplines;
		repaint();
	}

	public void setShowLinearSplines(boolean showLinearSplines) {
		this.showLinearSplines = showLinearSplines;
		repaint();
	}

	/**
	 * Adds a point to the spline.
	 * 
	 * @param newPoint
	 *            Point to add.
	 */
	public boolean addSplinePoint(SplinePoint newPoint) {
		for (SplinePoint point : splinePoints) {
			if (SplinePoint.testIntersection(point, newPoint)) {
				return false;
			}
		}
		for (int i = 0; i < splinePoints.size(); ++i) {
			if (newPoint.getCenterX() < splinePoints.get(i).getCenterX()) {
				splinePoints.add(i, newPoint);
				break;
			}
		}
		if (!splinePoints.contains(newPoint)) {
			splinePoints.add(newPoint);
		}
		repaint();
		return true;
	}

	/**
	 * Removes all the spline points.
	 */
	public void clearSplinePoints() {
		splinePoints.clear();
		repaint();
	}

	/**
	 * Getter method for splinePoints attribute.
	 * 
	 * @return splinePoints
	 */
	public ArrayList<SplinePoint> getSplinePoints() {
		return splinePoints;
	}

	/**
	 * Returns the center points of the graphic spline points.
	 * 
	 * @return Center points.
	 */
	public ArrayList<Point2D.Double> getSplinePointsCenters() {
		ArrayList<Point2D.Double> centerPoints = new ArrayList<>();
		for (SplinePoint splinePoint : splinePoints) {
			centerPoints.add(new Point2D.Double(splinePoint.getCenterX(), splinePoint.getCenterY()));
		}
		return centerPoints;
	}

	/**
	 * Sorts the points of the spline.
	 */
	public void sortSplinePoints() {
		Collections.sort(splinePoints, new Comparator<SplinePoint>() {
			public int compare(SplinePoint point1, SplinePoint point2) {
				return Double.compare(point1.getCenterX(), point2.getCenterX());
			}
		});
	}

	/**
	 * Getter method for curvePoints attribute.
	 * 
	 * @return curvePoints
	 */
	public ArrayList<ArrayList<Point2D.Double>> getCurvePoints() {
		return curvePoints;
	}

	/**
	 * Setter method for curvePoints attribute.
	 * 
	 * @param curvePoints
	 */
	public void setCurvePoints(ArrayList<ArrayList<Point2D.Double>> curvePoints) {
		this.curvePoints = curvePoints;
		repaint();
	}

	/**
	 * Updates the selected point.
	 */
	public void nextPoint() {
		if (selectedSplinePoint > -1) {
			splinePoints.get(selectedSplinePoint).setSelected(false);
		}
		selectedSplinePoint = ((selectedSplinePoint + 1) % splinePoints.size());
		splinePoints.get(selectedSplinePoint).setSelected(true);
		repaint();
	}

	/**
	 * Returns the selected point coordinates.
	 * 
	 * @return
	 */
	public String selectedPointCoords() {

		if (selectedSplinePoint <= -1) {
			return "X coord = " + "  Y coord = ";
		}

		return "X coord = " + splinePoints.get(selectedSplinePoint).getCenterX() + "  Y coord = "
				+ splinePoints.get(selectedSplinePoint).getCenterY();
	}

	/**
	 * Moves the selected point.
	 */
	public void moveSelectedPoint(double xOffset, double yOffset) {
		if (selectedSplinePoint <= -1) {
			return;
		}
		splinePoints.get(selectedSplinePoint).movePoint(xOffset, yOffset, getWidth(), getHeight());
		repaint();
	}

}
