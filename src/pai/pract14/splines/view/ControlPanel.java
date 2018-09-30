/**
 * File containing the ControlPanel entity definition. 
 */
package pai.pract14.splines.view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class which represents the control panel of the Splines program GUI. It was
 * created for the fourteenth practice of PAI (Programación de Aplicaciones
 * Interactivas) course of ULL (Universidad de la Laguna).
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 11 may. 2018
 */
public class ControlPanel extends JPanel {

	/** Default serial version ID. */
	private static final long	serialVersionUID	= 1L;
	/** Generate text field of the control panel. */
	private JTextField				generateTextField;
	/** Generate button of the control panel. */
	private JButton						generateButton;
	/** Reset button of the control panel. */
	private JButton						resetButton;
	
	private JCheckBox cubicCheckBox;
	 
	private JCheckBox linearCheckBox; 
	
	/**
	 * Default constructor.
	 * 
	 * @param width
	 *          Width of the panel.
	 * @param height
	 *          Height of the panel.
	 * @param buttonsActionListener
	 *          Action listener for the control panel buttons.
	 */
	public ControlPanel(int width, int height,
			ActionListener buttonsActionListener) {
		setName("Control panel");
		setPreferredSize(new Dimension(width, height));

		final int COLUMNS = 5;
		generateTextField = new JTextField("6");
		generateTextField.setColumns(COLUMNS);
		generateTextField.setName("Generate textfield");

		generateButton = new JButton("Generate");
		generateButton.addActionListener(buttonsActionListener);
		generateButton.setName("Generate button");

		resetButton = new JButton("Reset");
		resetButton.addActionListener(buttonsActionListener);
		resetButton.setName("Reset button");
		
		cubicCheckBox = new JCheckBox("Cubic splines");
		cubicCheckBox.setSelected(true);
		cubicCheckBox.addActionListener(buttonsActionListener);
		linearCheckBox = new JCheckBox("Linear splines");
		linearCheckBox.setSelected(false);
		linearCheckBox.addActionListener(buttonsActionListener);

		add(generateTextField);
		add(generateButton);
		add(resetButton);
		add(cubicCheckBox);
		add(linearCheckBox);
	}

	/**
	 * Getter method for generateTextField attribute.
	 * 
	 * @return generateTextField
	 */
	public JTextField getGenerateTextField() {
		return generateTextField;
	}

	public JCheckBox getCubicCheckBox() {
		return cubicCheckBox;
	}

	public void setCubicCheckBox(JCheckBox cubicCheckBox) {
		this.cubicCheckBox = cubicCheckBox;
	}

	public JCheckBox getLinearCheckBox() {
		return linearCheckBox;
	}

	public void setLinearCheckBox(JCheckBox linearCheckBox) {
		this.linearCheckBox = linearCheckBox;
	}
}
