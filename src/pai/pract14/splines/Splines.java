/**
 * File containing the Splines entity definition. 
 */
package pai.pract14.splines;

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
public class Splines {
	
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
