/**
 * File containing the MovingShapesTest entity definition. 
 */

package pai.pract14.splines.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Color;

import org.assertj.swing.core.MouseButton;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pai.pract14.splines.view.MainWindow;

/**
 * Class which tests the behavior of the Splines program GUI. It was
 * created for the fourteenth practice of PAI (Programación de Aplicaciones
 * Interactivas) course of ULL (Universidad de la Laguna).
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 12 abr. 2018
 */
public class GUITests {

	/**
	 * Supports functional testing of Frames. AssertJ Swing fixtures simulate a
	 * user interacting with the GUI in order to verify that such GUI behave as we
	 * expect.
	 */
	private FrameFixture	window;
	/** Controller of the program. */
	private Controller		controller;
	/** GUI of the program. */
	private MainWindow 		gui;

	/**
	 * Forces a test to fail if access to GUI components is not performed on the
	 * EDT.
	 */
	@BeforeClass
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}

	/**
	 * Prepares everything each time a test will be executed.
	 */
	@Before
	public void setUp() {
		controller = GuiActionRunner.execute(new GuiQuery<Controller>() {
			@Override
			protected Controller executeInEDT() throws Throwable {
				return new Controller();
			}
		});
		gui = controller.getView();
		window = new FrameFixture(gui);
		window.show();
	}

	/**
	 * Tests the GUI panels
	 */
	@Test
	public void testGUIPanels() {
		final int RGB_GRAY = 238;
		Color defaultColor = new Color(RGB_GRAY, RGB_GRAY, RGB_GRAY);
		
		assertThat(window.requireEnabled());
		assertThat(window.requireVisible());
		assertThat(window.requireTitle("Splines"));

		assertThat(window.panel("Main panel").requireVisible());
		assertThat(window.panel("Main panel").requireEnabled());
		assertThat(window.panel("Main panel").background()
				.requireEqualTo(defaultColor));
		assertThat(window.panel("Main panel").panel("Spline panel").requireEnabled());
		assertThat(window.panel("Main panel").panel("Spline panel").requireVisible());
		assertThat(window.panel("Main panel").panel("Spline panel").background()
				.requireEqualTo(Color.WHITE));
		
		assertThat(window.panel("Main panel").panel("Control panel").requireEnabled());
		assertThat(window.panel("Main panel").panel("Control panel").requireVisible());
		assertThat(window.panel("Main panel").panel("Control panel").background()
				.requireEqualTo(defaultColor));
		
		assertThat(window.panel("Main panel").panel("Control panel").textBox("Generate textfield").requireEditable());
		assertThat(window.panel("Main panel").panel("Control panel").textBox("Generate textfield").requireEnabled());
		assertThat(window.panel("Main panel").panel("Control panel").textBox("Generate textfield").requireVisible());
		assertThat(window.panel("Main panel").panel("Control panel").textBox("Generate textfield").requireText("\\d+"));
		assertThat(window.panel("Main panel").panel("Control panel").textBox("Generate textfield").requireFocused());
		
		assertThat(window.panel("Main panel").panel("Control panel").button("Generate button").requireEnabled());
		assertThat(window.panel("Main panel").panel("Control panel").button("Generate button").requireVisible());
		assertThat(window.panel("Main panel").panel("Control panel").button("Generate button").requireText("Generate"));
		
		assertThat(window.panel("Main panel").panel("Control panel").button("Reset button").requireEnabled());
		assertThat(window.panel("Main panel").panel("Control panel").button("Reset button").requireVisible());
		assertThat(window.panel("Main panel").panel("Control panel").button("Reset button").requireText("Reset"));
		
		assertThat(window.panel("Main panel").panel("Control panel").button("Info button").requireEnabled());
		assertThat(window.panel("Main panel").panel("Control panel").button("Info button").requireVisible());
		assertThat(window.panel("Main panel").panel("Control panel").button("Info button").requireText("Info"));
		
	}

	/**
	 * Tests the GUI program actions.
	 */
	@Test
	public void testProgramActions() {
		assertThat(gui.getControlPanel().getGenerateTextField().getText().equals("6"));
		assertThat(gui.getSplinesPanel().getSplinePoints().size() == 0);
		window.panel("Main panel").panel("Control panel").button("Reset button").click();
		assertThat(gui.getSplinesPanel().getSplinePoints().size() == 0);
		final int CLICKS = 4;
		int INITIAL_POINTS = 6;
		for (int i = 0; i < CLICKS; ++i) {
			window.panel("Main panel").panel("Control panel").button("Generate button").click();
			window.panel("Main panel").panel("Control panel").button("Reset button").click();
			assertThat(gui.getSplinesPanel().getSplinePoints().size() == 0);
		}
		for (int i = 0; i < CLICKS; ++i) {
			window.panel("Main panel").panel("Control panel").button("Generate button").click();
		}
		assertThat(gui.getSplinesPanel().getSplinePoints().size() == CLICKS * INITIAL_POINTS);
		window.panel("Main panel").panel("Control panel").button("Reset button").click();
		assertThat(gui.getSplinesPanel().getSplinePoints().size() == 0);
		INITIAL_POINTS = 3;
		window.panel("Main panel").panel("Control panel").textBox().setText("3");
		window.panel("Main panel").panel("Control panel").button("Generate button").click();
		assertThat(gui.getSplinesPanel().getSplinePoints().size() == INITIAL_POINTS);
		window.panel("Main panel").panel("Control panel").button("Reset button").click();
		assertThat(gui.getSplinesPanel().getSplinePoints().size() == 0);
		window.panel("Main panel").panel("Spline panel").click(MouseButton.LEFT_BUTTON);
		assertThat(gui.getSplinesPanel().getSplinePoints().size() == 1);
		window.panel("Main panel").panel("Control panel").button("Reset button").click();
		assertThat(gui.getSplinesPanel().getSplinePoints().size() == 0);
	}

	/**
	 * Closes the needed resources each time a test is executed.
	 */
	@After
	public void tearDown() {
		window.cleanUp();
	}

}

