package Stavy;

import java.awt.*;
import java.awt.event.KeyEvent;

import Entity.*;
import Main.GamePanel;
import TileMap.*;

public class StavLevelu1 extends Stavy {
	
	private MapaDlaz tileMap;
	private Pozadi bg;
	
	public static int pocetTahu=5;
	public static int pocetTahu2=0;
	public boolean tah=true;
	public boolean tah2=false;
	private Hrac player;
	private Hrac2 player2;

	private Status hud;
	private Hud hud12;
	private Status2 hud2;
	private Hud2 hud22;
	
	public StavLevelu1(PoradacStavu gsm){
		this.gsm = gsm;
		init();
	}
	
	public void init(){
		tileMap = new MapaDlaz(30);
		tileMap.loadTiles("/Dlazdice/mojetile.gif");
		tileMap.loadMap("/Mapy/velka.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		bg = new Pozadi("/Pozadi/trava.bmp",0.1);
		
		player = new Hrac(tileMap);
		player.setPosition(43, 44);
		
		player2 = new Hrac2(tileMap);
		player2.setPosition(193, 194);


		hud = new Status(player);
		hud12 = new Hud(player);
		hud2 = new Status2(player2);
		hud22 = new Hud2(player2);
	}
	public void update(){

		player.update();
		player2.update();

		if(tah){
		tileMap.setPosition(
				GamePanel.WIDTH / 2 - player.getx(),
				GamePanel.HEIGHT / 2 - player.gety()
				);
		}
		if(tah2){
		tileMap.setPosition(
				GamePanel.WIDTH / 2 - player2.getx(),
				GamePanel.HEIGHT / 2 - player2.gety()
				);
		}

	}

	public void draw(Graphics2D g){
	
		bg.draw(g);
		tileMap.draw(g);
		player.draw(g);
		player2.draw(g);
		
		if(tah==true) {hud.draw(g); hud12.draw(g);}
		if(tah2==true) {hud2.draw(g); hud22.draw(g);}

		

		
	}
	public void keyPressed(int k) {
		
		if(pocetTahu != 0 && tah2==false){
			tah=true;
			tah2=false;		
		}
		if(pocetTahu == 0 && tah2==false) {
			tah=false;
			tah2=true;
			pocetTahu2=5;
		}
		
		if(pocetTahu2 != 0 && tah==false){
			tah2=true;
			tah=false;		
		}
		if(pocetTahu2 == 0 && tah==false) {
			tah2=false;
			tah=true;
			pocetTahu=5;
		}
		
		if(tah){
		if(k == KeyEvent.VK_LEFT) player.setLeft();
		if(k == KeyEvent.VK_RIGHT) player.setRight();
		if(k == KeyEvent.VK_UP) player.setUp();
		if(k == KeyEvent.VK_DOWN) player.setDown(); 
		if(k == KeyEvent.VK_SPACE) player.setFiring();
		}
		
		if(tah2){
		if(k == KeyEvent.VK_LEFT) player2.setLeft();
		if(k == KeyEvent.VK_RIGHT) player2.setRight();
		if(k == KeyEvent.VK_UP) player2.setUp();
		if(k == KeyEvent.VK_DOWN) player2.setDown();
		if(k == KeyEvent.VK_SPACE) player2.setFiring();
		}

	}
	
	public void keyReleased(int k) {
		/*
		if(k == KeyEvent.VK_LEFT) player.setLeft();
		if(k == KeyEvent.VK_RIGHT) player.setRight();
		if(k == KeyEvent.VK_UP) player.setUp();
		if(k == KeyEvent.VK_DOWN) player.setDown();
		if(k == KeyEvent.VK_SPACE) player.stopFiring();
		*/
	}
	
}

