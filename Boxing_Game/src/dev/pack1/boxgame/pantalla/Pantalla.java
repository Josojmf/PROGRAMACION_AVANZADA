package dev.pack1.boxgame.pantalla;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
public class Pantalla {
	private JFrame frame;
	private Canvas lienzo;
	
	private String title;
	private int width, height;
	
	public Pantalla(String title,int width,int height) {
		this.title=title;
		this.width=width;
		this.height=height;
		
		formarPantalla();
		
	
		
	}
	
	private void formarPantalla(){
		frame= new JFrame(title);
		frame.setSize(width,height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		lienzo= new Canvas();
		lienzo.setPreferredSize(new Dimension(width,height));
		lienzo.setMaximumSize(new Dimension(width,height));
		lienzo.setMinimumSize(new Dimension(width,height));
		lienzo.setFocusable(false);
		
		frame.add(lienzo);
		frame.pack();
		
		
	}
	
	public Canvas getCanvas() {
		return lienzo;
		}
	public JFrame getFrame() {
		return frame;
		
	}
}



