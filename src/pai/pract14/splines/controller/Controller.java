/**
 * File containing the Controller entity definition. 
 */
package pai.pract14.splines.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import pai.pract14.splines.model.Spline;
import pai.pract14.splines.view.MainWindow;
import pai.pract14.splines.view.SplinePoint;

/**
 * Class which represents the controller of the Splines program. It was created
 * for the fourteenth practice of PAI (Programación de Aplicaciones
 * Interactivas) course of ULL (Universidad de la Laguna).
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 11 may. 2018
 */
public class Controller
		implements MouseListener, MouseMotionListener, ActionListener, KeyListener {
	/** View of the program. */
	private MainWindow	view;
	/** Model of the program. */
	private Spline			model;
	/** Represents the spline point being moved at each moment. */
	private SplinePoint	movingSplinePoint;
	/** Colors for the spline. */
	static public Color[]			colors;
	/** Index to choose the color. */
	private int					colorsIndex;

	/**
	 * Default constructor.
	 */
	public Controller() {
		final int GUI_WIDTH = 700;
		final int GUI_HEIGHT = 700;
		view = new MainWindow(GUI_WIDTH, GUI_HEIGHT, this, this, this, this);
		model = new Spline();
		movingSplinePoint = null;
		colorsIndex = 0;
		colors = new Color[] { Color.RED, Color.BLUE, Color.GREEN,
				Color.YELLOW, Color.CYAN };
	}

	/**
	 * Sets the GUI visible.
	 */
	public void showGUI() {
		view.showGUI();
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		if (movingSplinePoint == null) {
			return;
		}
		movingSplinePoint.setCenterX(event.getX(), (int)view.getPreferredSize().getWidth());
		movingSplinePoint.setCenterY(event.getY(), view.SPLINES_PANEL_HEIGHT);
		view.getSplinesPanel().sortSplinePoints();
		model.setSplinePoints(view.getSplinesPanel().getSplinePointsCenters());
		view.getSplinesPanel().setCurvePoints(model.getCurvePoints());
		view.getSplinesPanel().repaint();
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		;
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		;
	}

	@Override
	public void mouseExited(MouseEvent event) {
		;
	}

	@Override
	public void mousePressed(MouseEvent event) {
		SplinePoint newSplinePoint = new SplinePoint(event.getX(), event.getY(),
				colors[colorsIndex % colors.length]);
		for (SplinePoint splinePoint : view.getSplinesPanel().getSplinePoints()) {
			if (splinePoint
					.contains(new Point2D.Double(event.getX(), event.getY()))) {
				movingSplinePoint = splinePoint;
				return;
			} else if (SplinePoint.testIntersection(newSplinePoint, splinePoint)) {
				return;
			}
		}
		view.getSplinesPanel().addSplinePoint(newSplinePoint);
		movingSplinePoint = newSplinePoint;
		model.setSplinePoints(view.getSplinesPanel().getSplinePointsCenters());
		view.getSplinesPanel().setCurvePoints(model.getCurvePoints());
		view.getSplinesPanel().repaint();
		colorsIndex++;
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		movingSplinePoint = null;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("Generate")) {
			try {
				int amountOfPoints = Integer
						.parseInt(view.getControlPanel().getGenerateTextField().getText());
				if (amountOfPoints < 0) {
					throw new NumberFormatException();
				} else {
					Random rng = new Random();
					for (int i = 0; i < amountOfPoints; ++i) {
						int xPos = rng
								.nextInt(view.getSplinesPanel().getWidth()
										- SplinePoint.GRAPHIC_POINT_DIAMETER)
								+ SplinePoint.GRAPHIC_POINT_RADIUS;
						int yPos = rng
								.nextInt(view.getSplinesPanel().getHeight()
										- SplinePoint.GRAPHIC_POINT_DIAMETER)
								+ SplinePoint.GRAPHIC_POINT_RADIUS;
						view.getSplinesPanel().addSplinePoint(
								new SplinePoint(xPos, yPos, colors[colorsIndex % colors.length]));
						colorsIndex++;
					}
					model
							.setSplinePoints(view.getSplinesPanel().getSplinePointsCenters());
					view.getSplinesPanel().setCurvePoints(model.getCurvePoints());
					view.getSplinesPanel().repaint();
				}
			} catch (NumberFormatException exception) {
				System.err.println("Bad amount of points!");
			}
		} else if (event.getActionCommand().equals("Reset")) {
			view.getSplinesPanel().clearSplinePoints();
			model.getSplinePoints().clear();
		} else if (event.getActionCommand().equals("Info")) {
			final int INFO_WIDTH = 368;
			final int INFO_HEIGHT = 75;
			JFrame infoFrame = new JFrame("Information");
			infoFrame.setResizable(false);
			JTextArea textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setText(
					" Fourteenth practice of PAI (Programación de Aplicaciones \n"
							+ " Interactivas) course of ULL (Universidad de la Laguna) \n"
							+ " Author: Daute Rodríguez (alu0100973914@ull.edu.es) ");
			infoFrame.add(textArea);
			infoFrame.setSize(new Dimension(INFO_WIDTH, INFO_HEIGHT));
			infoFrame.setLocationRelativeTo(null);
			infoFrame.setVisible(true);
		} else if (event.getActionCommand().equals("Cubic splines")) {
			view.getSplinesPanel().setShowCubicSplines(view.getControlPanel().getCubicCheckBox().isSelected());
		} else if (event.getActionCommand().equals("Linear splines")) {
			view.getSplinesPanel().setShowLinearSplines(view.getControlPanel().getLinearCheckBox().isSelected());
		}

	}

	/**
	 * Getter method for view attribute.
	 * 
	 * @return view
	 */
	public MainWindow getView() {
		return view;
	}

	/**
	 * Setter method for view attribute.
	 * 
	 * @param view
	 */
	public void setView(MainWindow view) {
		this.view = view;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		final int OFFSET = 5;
		if (e.getKeyChar() == 'n') {
			view.getSplinesPanel().nextPoint();
		} else if (e.getKeyChar() == 'w') {
			view.getSplinesPanel().moveSelectedPoint(0, -OFFSET);
		} else if (e.getKeyChar() == 'a') {
			view.getSplinesPanel().moveSelectedPoint(-OFFSET, 0);
		} else if (e.getKeyChar() == 's') {
			view.getSplinesPanel().moveSelectedPoint(0, OFFSET);
		} else if (e.getKeyChar() == 'd') {
			view.getSplinesPanel().moveSelectedPoint(OFFSET, 0);
		}
		view.getSplinesPanel().sortSplinePoints();
		model.setSplinePoints(view.getSplinesPanel().getSplinePointsCenters());
		view.getSplinesPanel().setCurvePoints(model.getCurvePoints());
		view.getSplinesPanel().repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
