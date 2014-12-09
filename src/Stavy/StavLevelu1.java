package Stavy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.concurrent.TimeUnit;

import Entity.*;
import Main.GamePanel;
import TileMap.*;

public class StavLevelu1 extends Stavy {
	
	private MapaDlaz tileMap;
	private Pozadi bg;
	
	public static int pocetTahu=8;
	public static int pocetTahu2=0;
	public boolean tah=true;
	public boolean tah2=false;
	public boolean timer1=false;
	public boolean timer2=false;
	public boolean death=false;
	public boolean death2=false;
	private Hrac player;
	private Hrac2 player2;

	private Status hud;
	private Hud hud12;
	private Status2 hud2;
	private Hud2 hud22;
	private Vyhra1 vyhra1;
	private Vyhra2 vyhra2;

	
	public StavLevelu1(PoradacStavu gsm){
		this.gsm = gsm;
		init();
	}
	
	public void init(){
		tileMap = new MapaDlaz(30);
		tileMap.loadTiles("/Dlazdice/ground.gif");
		tileMap.loadMap("/Mapy/dung.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		bg = new Pozadi("/Pozadi/trava.bmp",0.1);
		
		player = new Hrac(tileMap);
		player.setPosition(75,75 );
		
		player2 = new Hrac2(tileMap);
		player2.setPosition(855,885);


		hud = new Status(player);
		hud12 = new Hud(player);
		hud2 = new Status2(player2);
		hud22 = new Hud2(player2);
		vyhra1 = new Vyhra1(player);
		vyhra2 = new Vyhra2(player2);
	}
	public void update(){
		
		if(Hrac.health==0) death=true;
		if(Hrac2.health==0) death2=true;
		
		if(pocetTahu > 0 && tah2==false){
			tah=true;
			tah2=false;	
			pocetTahu2++;
		}
		
		if(pocetTahu == 10 && tah2==true) {

			try {
				tah2=false;
				timer2=true;
				TimeUnit.SECONDS.sleep(2);
				timer1=false;
				tah2=true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(pocetTahu == 0 && tah2==false) {
			tah=false;
			tah2=true;
			pocetTahu2=8;

		}

		
		if(pocetTahu2 > 0 && tah==false){
			tah2=true;
			tah=false;		
			pocetTahu++;
		}
			
			if(pocetTahu2 == 10 && tah==true) {

				try {
					tah=false;
					timer1=true;
					TimeUnit.SECONDS.sleep(2);
					timer2=false;
					tah=true;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
		}
			
		if(pocetTahu2 == 0 && tah==false) {
			tah2=false;
			tah=true;
			pocetTahu=8;
		}

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
		if(death==false){
			player.draw(g);
		}

		if(death2==false){
			player2.draw(g);
		}

		
		
		if(tah==true && timer1==true && death==false && death2==false) {hud.draw(g); hud12.draw(g);}
		if(tah2==true && timer2==true && death2==false && death==false) {hud2.draw(g); hud22.draw(g);}
		

		if(death==true) vyhra2.draw(g);
		if(death2==true) vyhra1.draw(g);


		
	}
	public void keyPressed(int k) {
		

		
		if(tah && death==false && death2==false){
		if(k == KeyEvent.VK_LEFT) player.setLeft();
		if(k == KeyEvent.VK_RIGHT) player.setRight();
		if(k == KeyEvent.VK_UP) player.setUp();
		if(k == KeyEvent.VK_DOWN) player.setDown(); 
		if(k == KeyEvent.VK_SPACE) {player.setFiring();pocetTahu--;}
		}
		
		if(tah2 && death2==false && death==false){
		if(k == KeyEvent.VK_LEFT) player2.setLeft();
		if(k == KeyEvent.VK_RIGHT) player2.setRight();
		if(k == KeyEvent.VK_UP) player2.setUp();
		if(k == KeyEvent.VK_DOWN) player2.setDown();
		if(k == KeyEvent.VK_SPACE) {player2.setFiring();pocetTahu2--;}
		}
		
		if(k == KeyEvent.VK_F5) saveGame();
		if(k == KeyEvent.VK_F6) loadGame();
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
	
	public void saveGame() {	
		
        PrintWriter save = null; 
        
        try  
        {   
            save = new PrintWriter(new FileWriter(new File("/Save/savedgame.txt"), false));  

            save.println(player.getmyx());  
            save.flush(); 
            save.println(player.getmyy());  
            save.flush(); 
            
            save.println(player2.getmyx());  
            save.flush(); 
            save.println(player2.getmyy());   
            save.flush(); 
             
            save.close();  
            save = null;  
              
        }  
        catch(Exception e) {  
            System.out.println("Exception caught: "+e.getMessage());  
        } 
	 
	}
	
	public void loadGame() {
		
		LineNumberReader load = null;  
		
        try  
        {  
            load = new LineNumberReader(new FileReader(new File("/Save/savedgame.txt")));  
            
            String x, y;
            
            x = load.readLine();   
            y = load.readLine();  
            player.setPosition(Integer.parseInt(x), Integer.parseInt(y));
            
            x = load.readLine();   
            y = load.readLine();  
            player2.setPosition(Integer.parseInt(x), Integer.parseInt(y));
              
            load.close();  
            load = null;   
              
        }  
        catch(Exception e) {  
            System.out.println("Exception caught: "+e.getMessage());  
        } 
		
	}
	
}

