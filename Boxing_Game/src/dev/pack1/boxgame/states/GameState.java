/**
 * @author: José María Fernández
 * Clase que se encarga de la funcionalidad de juego del round 1, en esta clase se definen los jugadores y el comportamiento de estos 
 * tambien se definen la transicion al estado siguiente dependiendo de los eventos del estado actual
 * @param handler objeto que se encarga del manejo de variables entre clases
 *  
 */
package dev.pack1.boxgame.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.pack1.boxgame.Game;
import dev.pack1.boxgame.Handler;
import dev.pack1.boxgame.boxeadores.Boxeador;
import dev.pack1.boxgame.boxeadores.categoria.Jugador;
import dev.pack1.boxgame.gfx.Assets;
import dev.pack1.boxgame.gfx.Text;
import dev.pack1.boxgame.states.State;
import dev.pack1.boxgame.ui.UIManager;
import dev.pack1.boxgame.worlds.World;
import dev.pack1.boxgame.sound.Sound;

public class GameState extends State {

	private Jugador player;
	private UIManager uiManager;
	private Jugador player2;
	private World world;
	private boolean defJ1=false,hitJ1=false;
	private boolean defJ2=false,hitJ2=false,printH=false;
	public boolean move,st1=false,st2=false;
	public boolean j1Limit=false,j2Limit=false;
	public String path;
	private long lastTime,timer=0,timerS=0,lastTimeS;
	public int J2D,J1D,Pticks;//health,naturalStrength,Pticks;
	public float speed;
	public Sound soundHit,soundBlock,ding;
	public EndGame endGame;	
	public Round2 round2;

	public GameState(Handler handler){
		super(handler);
		
		world= new World(handler);
		handler.setWorld(world);
		ding= new Sound();
		ding.setFile("/C:/Users/josoj/OneDrive/Escritorio/INFORMATICA/PROGRAMACION AVANZADA/Practicas/Box_Game13-10/res/sounds/ding.wav");
		ding.play();
		soundHit= new Sound();
		soundHit.setFile("/C:/Users/josoj/OneDrive/Escritorio/INFORMATICA/PROGRAMACION AVANZADA/Practicas/Box_Game13-10/res/sounds/hit.wav");
		soundBlock= new Sound();
		soundBlock.setFile("/C:/Users/josoj/OneDrive/Escritorio/INFORMATICA/PROGRAMACION AVANZADA/Practicas/Box_Game13-10/res/sounds/block.wav");
		uiManager=new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		crearJugadores();
		
		
		
	
	}
		
	
		


	public void crearJugadores() {
		player= new Jugador(handler,0,"izqda",handler.getGame().health,handler.getGame().naturalStrength,handler.getGame().speed,handler.getGame().tamX1,handler.getGame().tamY1);	
		player2= new Jugador(handler,570,"dcha",handler.getGame().health2,handler.getGame().naturalStrength2,handler.getGame().speed2,handler.getGame().tamX2,handler.getGame().tamY2);
		
	}
	




	public boolean CollisionDetector() {

	
		if(((player.getX()+120>=player2.getX()-120) && (handler.getKeyManager().hit1 || handler.getKeyManager().hit2))) {
			if(handler.getKeyManager().block1){
				defJ1=true;
			}else if((handler.getKeyManager().hit1 && !handler.getKeyManager().block2)&&player.checkAlive()) {
				hitJ1=true;
			}else hitJ1=false;
			if(handler.getKeyManager().block2){
				defJ2=true;
			}else if((handler.getKeyManager().hit2 &&!handler.getKeyManager().block1)&&player2.checkAlive()) {
				hitJ2=true;
			}else hitJ2=false;
			return true;
			

		}else {

			defJ1=false;
			defJ2=false;
			hitJ1=false;
			hitJ2=false;
			return false;
		
	}
	}

	public void LimitDetector() {
		
		if((player.getX()+120>=player2.getX()-120)) {
			player.enMovef=false;
			player2.enMoveb=false;
		}else {
			player.enMovef=true;
			player2.enMoveb=true;
		}
		if(player.getX()<=-119) {
			player.enMoveb=false;
		}else player.enMoveb=true;
		
		if(player2.getX()>=720) {
			player2.enMovef=false;
		}else player2.enMovef=true;

	}
	
	

	public void DeduccionSalud() {

		if(hitJ1&&player2.checkAlive()) {
			player2.setHealth(player2.getHealth()-1*handler.getGame().naturalStrength);
			System.out.println("Golpe de J1");
			soundHit.play();
		}
		if(defJ1) {
			System.out.println("Defendido por J1");
			soundBlock.play();
			
		}
		if(defJ2) {
			System.out.println("Defendido por J2");
			soundBlock.play();
			
		}

		if(hitJ2 && player.checkAlive()) {
			player.setHealth(player.getHealth()-1*handler.getGame().naturalStrength2);
			System.out.println("Golpe de J2");
			soundHit.play();
		

		}


	}








	@Override
	public void tick() {
		world.tick();
		System.out.println(player2.getX());
		if(handler.getGame().creaJugadores) {
		CollisionDetector();
		lastTimeS=System.currentTimeMillis();
		if(CollisionDetector()) {
		timerS+=System.currentTimeMillis()-lastTimeS;
		lastTimeS=System.currentTimeMillis();
		timerS++;
		if(timerS>10) {
			DeduccionSalud();
			timerS=0;
		}
			
		}
		LimitDetector();
		}
		if(player.checkAlive()&&handler.getGame().creaJugadores) {
			player.tick();
		}else handler.getGame().J11D=true;
		if(player2.checkAlive()&&handler.getGame().creaJugadores) {
			player2.tick();
		}else handler.getGame().J21D=true;
		if(handler.getKeyManager().r && handler.getKeyManager().e) 
			
		lastTime=System.currentTimeMillis();
		if((!player.checkAlive() || !player2.checkAlive())&&handler.getGame().creaJugadores) {
			timer+=System.currentTimeMillis()-lastTime;
			lastTime=System.currentTimeMillis();
			timer++;
			if(timer>70) {
				round2=new Round2(handler);
				State.setState(round2);
			}
			if(handler.getGame().timer>=1000000000)
				 Pticks=handler.getGame().ticks;
		} 
		






	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		player.render(g);
		player2.render(g);
		Text.drawString(g, "Salud J1:" + (int)player.getHealth(),140, 100,true, Color.RED,Assets.font50);
		Text.drawString(g, "Salud J2:" + (int)player2.getHealth(),740, 100,true, Color.RED,Assets.font50);
		Text.drawString(g, "Round 1",450, 100,true, Color.BLUE,Assets.font75);
		
		Text.drawString(g, Integer.toString(handler.getGame().ticks),30, 50,true, Color.BLUE,Assets.font28);





	}




}
