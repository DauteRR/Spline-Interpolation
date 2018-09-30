/**
 * File containing the SplinePoint entity definition. 
 */
package pai.pract14.splines.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Class which represents a spline point of the Splines program GUI. It was
 * created for the fourteenth practice of PAI (Programación de Aplicaciones
 * Interactivas) course of ULL (Universidad de la Laguna).
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 13 may. 2018
 */
public class SplinePoint {
	
	/** Color of the graphic point. */
	private Color graphicPointColor;
	/** Diameter of the graphic point. */
	public static int GRAPHIC_POINT_DIAMETER = 20;
	/** Radius of the graphic point. */
	public static int GRAPHIC_POINT_RADIUS = GRAPHIC_POINT_DIAMETER / 2;
	/** Represents the exact real point. */
	private Point2D.Double realPoint;
	/** Represents the graphic point (ball). */
	private Ellipse2D.Double graphicPoint;
	/** Is a point selected?*/
	private boolean selected;
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * Default constructor.
	 * 
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 */
	public SplinePoint(double x, double y, Color color) {
		realPoint = new Point2D.Double(x, y);
		graphicPoint = new Ellipse2D.Double(x - GRAPHIC_POINT_RADIUS, y - GRAPHIC_POINT_RADIUS, GRAPHIC_POINT_DIAMETER, GRAPHIC_POINT_DIAMETER);
		graphicPointColor = color;
	}
	
	/**
	 * Paints the spline point.
	 * 
	 * @param g Graphics object where the spline point will be painted.
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if (selected) {
			g2d.setPaint(Color.BLACK);
		} else {
			g2d.setPaint(graphicPointColor);
		}
		g2d.fill(graphicPoint);
	}
	
	/**
	 * Checks if the graphic point contains a specific point.
	 * 
	 * @param point Point
	 * @return Result
	 */
	public boolean contains(Point2D.Double point) {
		return graphicPoint.contains(point);
	}

	/**
	 * Getter method for realPoint attribute.
	 * @return realPoint
	 */
	public Point2D.Double getRealPoint() {
		return realPoint;
	}

	/**
	 * Setter method for realPoint attribute.
	 * @param realPoint 
	 */
	public void setRealPoint(Point2D.Double realPoint) {
		this.realPoint = realPoint;
	}

	/**
	 * Getter method for graphicPoint attribute.
	 * @return graphicPoint
	 */
	public Ellipse2D.Double getGraphicPoint() {
		return graphicPoint;
	}

	/**
	 * Setter method for graphicPoint attribute.
	 * @param graphicPoint 
	 */
	public void setGraphicPoint(Ellipse2D.Double graphicPoint) {
		this.graphicPoint = graphicPoint;
	}
	
	/**
	 * Returns the x center coord of the graphic point.
	 * 
	 * @return X center coord.
	 */
	public double getCenterX() {
		return graphicPoint.getCenterX();
	}
	
	/**
	 * Returns the y center coord of the graphic point.
	 * 
	 * @return Y center coord.
	 */
	public double getCenterY() {
		return graphicPoint.getCenterY();
	}
	

	/**
	 * Sets the x center coord of the graphic point.
	 * 
	 */
	public void setCenterX(double xCenter, int width) {
		if (xCenter - GRAPHIC_POINT_RADIUS <= 0) {
			return;
		}
		if (xCenter + GRAPHIC_POINT_RADIUS >= width) {
			return;
		}
		realPoint.x = xCenter - GRAPHIC_POINT_RADIUS;
		graphicPoint.x = xCenter - GRAPHIC_POINT_RADIUS;
	}
	
	/**
	 * Sets the y center coord of the graphic point.
	 * 
	 */
	public void setCenterY(double yCenter, int height) {
		if (yCenter - GRAPHIC_POINT_RADIUS <= 0) {
			return;
		}
		if (yCenter + GRAPHIC_POINT_RADIUS >= height) {
			return;
		}
		realPoint.y = yCenter - GRAPHIC_POINT_RADIUS;
		graphicPoint.y = yCenter - GRAPHIC_POINT_RADIUS;
	}
	
	/**
	 * Tests if two shapes are intersected.
	 * 
	 * @param firstShape
	 *          First shape.
	 * @param secondShape
	 *          Second shape.
	 * @return Result.
	 */
	public static boolean testIntersection(SplinePoint firstShape,
			SplinePoint secondShape) {
		Area intersectionArea = new Area(firstShape.getGraphicPoint());
		intersectionArea.intersect(new Area(secondShape.getGraphicPoint()));
		return !intersectionArea.isEmpty();
	}

	/**
	 * Getter method for graphicPointColor attribute.
	 * @return graphicPointColor
	 */
	public Color getGraphicPointColor() {
		return graphicPointColor;
	}

	/**
	 * Setter method for graphicPointColor attribute.
	 * @param graphicPointColor 
	 */
	public void setGraphicPointColor(Color graphicPointColor) {
		this.graphicPointColor = graphicPointColor;
	}
	
	/**
	 * Moves the point.
	 * @param xOffset
	 * @param yOffset
	 */
	public void movePoint(double xOffset, double yOffset, int width, int height) {
		setCenterX(realPoint.x + xOffset + GRAPHIC_POINT_RADIUS, width);
		setCenterY(realPoint.y + yOffset + GRAPHIC_POINT_RADIUS, height);
	}
}
