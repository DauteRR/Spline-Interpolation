/**
 * File containing the Splines entity definition. 
 */
package pai.pract14.splines;

import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JPanel;

import pai.pract14.splines.controller.Controller;


/**
 * Class which contains the main method of the Splines program. This
 * program can be executed as an applet. It was created for the fourteenth
 * practice of PAI (Programación de Aplicaciones Interactivas) course of ULL
 * (Universidad de la Laguna).
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 11 may. 2018
 */
@SuppressWarnings("deprecation")
public class Splines extends JApplet {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Init method for the Applet mode execution.
	 */
	public void init() {
		Controller controller = new Controller();
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(controller.getView().getSplinesPanel());
		mainPanel.add(controller.getView().getControlPanel());
		this.add(mainPanel);
	}
	
	/**
	 * Main method.
	 * 
	 * @param args Arguments given to the program.
	 */
	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.showGUI();
	}
}
