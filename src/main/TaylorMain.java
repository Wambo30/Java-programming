/*
 * 
 */
package main;

import javax.swing.UIManager;

import model.TaylorModel;
import view.TaylorFrame;
import controller.Controller;


/**
 * The Class TaylorMain.
 */
public class TaylorMain {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e);
        }
		
        TaylorModel model = new TaylorModel();
        Controller controller = new Controller(model);
        new TaylorFrame(controller);
	}

}
