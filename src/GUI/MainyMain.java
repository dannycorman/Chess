package GUI;


//Normal game with no robot tester implimented

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * The main class and essentially the surface of the application's architecture.
 * This class implements a GUI object, GUIGoo, which has a chessGame object inside it,
 *  which
 * interact with all the other objects in the application.
 * 
 * 
 * @author Danny Corman
 *
 */
public class MainyMain {
	
	/**
	 * Creates a JFrame specifies, the desirable info, 
	 * and adds the Gui Object, GuiGoo to it.
	 * 
	 * 
	 *
	 */
	public static void main(String[] args) {
		JFrame fram = new JFrame();
		fram.setPreferredSize(new Dimension(600, 600));
		fram.add(new GuiGoo());
		fram.setTitle("Standard Game");
		fram.setVisible(true);
		fram.pack();
		fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
