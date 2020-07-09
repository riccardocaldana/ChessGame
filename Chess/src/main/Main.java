package main;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.ChessView;

/**
 * @author Riccardo Caldana VR377851 - Valentina Ceoletta VR381758
 */

public class Main {

	public static void main(String[] args) {
		try {	//aspetto grafico sui vari SO
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				JFrame frame=new ChessView();
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		
	}

}
