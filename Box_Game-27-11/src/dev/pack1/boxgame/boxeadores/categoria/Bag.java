package dev.pack1.boxgame.boxeadores.categoria;

import java.awt.Graphics;

import dev.pack1.boxgame.gfx.Assets;

public class Bag {
	 private static Bag instance;
	
	private Bag() {
		
		
		
	}
	 public static Bag getInstance() {
		 if (instance == null) {
	            instance = new Bag();
	        }
	        return instance;
		 
		 
		 
	 }
	public void render(Graphics g) {
		g.drawImage(Assets.bag, 600, 530, 200,200,null);

		}
	public int getX() {
		
		return 600;
	}

}
