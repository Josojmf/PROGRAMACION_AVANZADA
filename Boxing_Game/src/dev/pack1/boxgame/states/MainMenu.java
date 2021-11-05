/**
 * @author: José María Fernández
 * Clase que se encarga de las funcionalidades del menu principal, estas son seleccionar el mapa y las categorias que implican diferentes atributos para los jugadores,
 * tambien se encarga de transicionar al siguiente estado por medion de un objeto boton
 * @param handler objeto que se encarga del manejo de variables entre clases
 *  
 */
package dev.pack1.boxgame.states;

import java.awt.Color;


import java.awt.Graphics;

import dev.pack1.boxgame.Game;
import dev.pack1.boxgame.Handler;
import dev.pack1.boxgame.gfx.Assets;
import dev.pack1.boxgame.gfx.Text;
import dev.pack1.boxgame.sound.Sound;
import dev.pack1.boxgame.ui.ClickListener;
import dev.pack1.boxgame.ui.UIImageButton;
import dev.pack1.boxgame.ui.UIManager;

public class MainMenu extends State {
	private UIManager uiManager;
	private UIManager uiManager2;
	public State gameState;
	public UIImageButton sel1,sel2,sel3,map1,map2,map3,sel1j2,sel2j2,sel3j2;
	private String categorias;
	private boolean boton1or2=false,again=true;
	public int i=0;


	public MainMenu(Handler handler){
		super(handler);
		uiManager=new UIManager(handler);
		uiManager2=new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		addJ1Buttons();
		uiManager.addObject(new UIImageButton(350,150,200,200,Assets.boton,new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				handler.getGame().asignacionAtributos();
				gameState = new GameState(handler);
				State.setState(gameState);
				handler.getGame().creaJugadores=true;

			}}));
		map1=new UIImageButton(100,800,40,40,Assets.boton_seleccion,new ClickListener() {

			@Override
			public void onClick() {
				handler.getGame().setButtonMap1(true);




			}});
		uiManager.addObject(map1);

		map2=new UIImageButton(450,800,40,40,Assets.boton_seleccion,new ClickListener() {

			@Override
			public void onClick() {
				handler.getGame().setButtonMap2(true);


			}});

		uiManager.addObject(map2);
		map3=new UIImageButton(750,800,40,40,Assets.boton_seleccion,new ClickListener() {

			@Override
			public void onClick() {
				handler.getGame().setButtonMap3(true);


			}});
		uiManager.addObject(map3);

	}

	private void addJ1Buttons() {
		sel1=new UIImageButton(100,600,40,40,Assets.boton_seleccion, new ClickListener() {

			@Override
			public void onClick() {
				handler.getGame().setButton1(true);



			}

		});

		uiManager.addObject(sel1);
		sel2=new UIImageButton(450,600,40,40,Assets.boton_seleccion, new ClickListener() {

			@Override
			public void onClick() {
				handler.getGame().setButton2(true);


			}

		});

		uiManager.addObject(sel2);
		sel3=new UIImageButton(750,600,40,40,Assets.boton_seleccion, new ClickListener() {

			@Override
			public void onClick() {
				handler.getGame().setButton3(true);


			}

		});


		uiManager.addObject(sel3);

		

		
	}
	
	private void addJ2Buttons() {
		sel1j2=new UIImageButton(100,600,40,40,Assets.boton_seleccion, new ClickListener() {

			@Override
			public void onClick() {
				handler.getGame().setButton1J2(true);



			}

		});

		
		sel2j2=new UIImageButton(450,600,40,40,Assets.boton_seleccion, new ClickListener() {

			@Override
			public void onClick() {
				handler.getGame().setButton2J2(true);


			}

		});

		
		sel3j2=new UIImageButton(750,600,40,40,Assets.boton_seleccion, new ClickListener() {

			@Override
			public void onClick() {
				handler.getGame().setButton3J2(true);
				


			}

		});


		
		
	}
	
	private boolean checkChangeButtons(){
		if(handler.getGame().isButton1() || handler.getGame().isButton2() || handler.getGame().isButton3()) {
		return true;
		}
		
	return false;	
	}
	private void changeButtons(boolean check) {
		if (check) {
			addJ2Buttons();
			uiManager.removeObject(sel1);
			uiManager.removeObject(sel2);
			uiManager.removeObject(sel3);
			uiManager.addObject(sel1j2);
			uiManager.addObject(sel2j2);
			uiManager.addObject(sel3j2);
		}
		else addJ1Buttons();
		
	}
	
	private String returnCat() {
		if(checkChangeButtons()) {
			 return"Categoria J2";
			}
		
		else return"Categoria J1"; 
	}
	public void tick() {
		uiManager.tick();
		boton1or2=checkChangeButtons();
		uiManager2.tick();	
		if(boton1or2&&again) {	
				addJ2Buttons();
				uiManager.removeObject(sel1);
				uiManager.removeObject(sel2);
				uiManager.removeObject(sel3);
				uiManager.addObject(sel1j2);
				uiManager.addObject(sel2j2);
				uiManager.addObject(sel3j2);
				again=false;
		}

	}


	public void render(Graphics g) {
		g.drawImage(Assets.fondo[0], 0,0,900,900, null);
		uiManager.render(g);
		uiManager2.render(g);
		Text.drawString(g, "Light-Weight",140, 500,true, Color.RED,Assets.font28);
		Text.drawString(g, "Welter-Weight",450, 500,true, Color.RED,Assets.font28);
		Text.drawString(g, "Heavy-Weight",750, 500,true, Color.RED,Assets.font28);
		Text.drawString(g, "Map-1",140, 700,true, Color.RED,Assets.font28);
		Text.drawString(g, "Map-2",450, 700,true, Color.RED,Assets.font28);
		Text.drawString(g, "Map-3",750, 700,true, Color.RED,Assets.font28);
		Text.drawString(g, "MINI BOXING",450, 50,true, Color.RED,Assets.font100);
		Text.drawString(g,returnCat(),270, 420,false, Color.RED,Assets.fontpix50);

		
	

	}



}
