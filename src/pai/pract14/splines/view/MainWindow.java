/**
 * File containing the MainWindow entity definition. 
 */
package pai.pract14.splines.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Class which represents the main window of the Splines program GUI It was
 * created for the fourteenth practice of PAI (Programación de Aplicaciones
 * Interactivas) course of ULL (Universidad de la Laguna).
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 11 may. 2018
 */
public class MainWindow extends JFrame {

	/** Default serial version ID. */
	private static final long	serialVersionUID	= 1L;
	/** Splines panel of the GUI. */
	private SplinePanel			splinesPanel;
	/** Control panel of the GUI. */
	private ControlPanel			controlPanel;
	
	public final int SPLINES_PANEL_HEIGHT;

	/**
	 * Default constructor.
	 * 
	 * @param width
	 *          Width of the GUI.
	 * @param height
	 *          Height of the GUI.
	 * @param buttonsActionListener
	 *          Action listener for the control panel buttons.
	 * @param mouseListener
	 *          Mouse listener for the splines panel clicks.
	 * @param mouseMotionListener
	 *          Mouse listener for the splines panel movements.
	 */
	public MainWindow(int width, int height, ActionListener buttonsActionListener,
			MouseListener mouseListener, MouseMotionListener mouseMotionListener, KeyListener keyboardListener) {
		setName("Main window");
		setTitle("Splines");
		setPreferredSize(new Dimension(width, height));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setName("Main panel");
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		SPLINES_PANEL_HEIGHT = (int) (height * 0.9);
		splinesPanel = new SplinePanel(width, SPLINES_PANEL_HEIGHT, mouseListener,
				mouseMotionListener, keyboardListener);
		controlPanel = new ControlPanel(width, height - SPLINES_PANEL_HEIGHT,
				buttonsActionListener);
		mainPanel.add(splinesPanel);
		mainPanel.add(controlPanel);
		this.add(mainPanel);
		this.pack();

	}

	/**
	 * Method which sets the GUI visible.
	 */
	public void showGUI() {
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Getter method for splinesPanel attribute.
	 * 
	 * @return splinesPanel
	 */
	public SplinePanel getSplinesPanel() {
		return splinesPanel;
	}

	/**
	 * Setter method for splinesPanel attribute.
	 * 
	 * @param splinesPanel
	 */
	public void setSplinesPanel(SplinePanel splinesPanel) {
		this.splinesPanel = splinesPanel;
	}

	/**
	 * Getter method for controlPanel attribute.
	 * 
	 * @return controlPanel
	 */
	public ControlPanel getControlPanel() {
		return controlPanel;
	}

	/**
	 * Setter method for controlPanel attribute.
	 * 
	 * @param controlPanel
	 */
	public void setControlPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}
}
