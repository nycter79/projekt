package Stavy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import Entity.*;
import TileMap.*;

public class Level1 extends Stavy {
	
	private MapaDlaz tileMap;
	private Pozadi bg;
	
	public static int pocetTahu=20;
	public static int pocetTahu2=0;
	
	public static boolean tah=true;
	public static boolean tah2=false;
	
	public boolean timer1=false;
	public boolean timer2=false;
	
	public static boolean won=false;
	public Vyhra vyhra;
	
	public int enemyPom = 0;
	
	public static ArrayList<Enemy> enemies;
	public static ArrayList<Hrac> players;
	public static ArrayList<Hud> statusy;



	
	public Level1(PoradacStavu gsm){
		this.gsm = gsm;
		init();
	}
	
	public void init(){
		tileMap = new MapaDlaz(30);
		tileMap.loadTiles("/Dlazdice/ground.gif");
		tileMap.loadMap("/Mapy/dung2.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		bg = new Pozadi("/Pozadi/trava.bmp",0.1);
		
		enemies = new ArrayList<Enemy>();
		players = new ArrayList<Hrac>();
		statusy = new ArrayList<Hud>();
		vyhra = new Vyhra();
		
		for(int i = 0; i < 6; i++){
		Enemy enemy = new Enemy(tileMap);
		enemies.add(enemy);
		}
		
		
		Hrac player = new Hrac(tileMap, "/Hrac/knight.gif");
		Hrac player2 = new Hrac(tileMap, "/Hrac/knight2.gif");
		players.add(player);
		players.add(player2);		
		
		players.get(0).setPosition(75,435);
		players.get(1).setPosition(555,435);
		
		enemies.get(0).setPosition(105,225);
		enemies.get(1).setPosition(405,195);
		enemies.get(2).setPosition(255,195);
		enemies.get(3).setPosition(345,375);
		enemies.get(4).setPosition(75,105);
		enemies.get(5).setPosition(345,105);
		
		for(int i = 0; i < 2; i++){
			Hud status = new Hud(players.get(i));
			statusy.add(status);
		}
		
	}
	public void update(){

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
			pocetTahu2=20;

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
			pocetTahu=20;
		}
		
		for(int i = 0; i < players.size(); i++) {
			players.get(i).update();
			
			if(players.get(i).shouldRemove()) {
				players.remove(i);
				i--;
				}
		}
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
			
			if(enemies.get(i).shouldRemove()) {
				enemies.remove(i);
				i--;
					}
		}
		
		if(enemyPom<300){
			for(int i = 0; i < Level1.enemies.size(); i++){
			enemies.get(i).setRight();
			enemies.get(i).setFiring();
			}
		}
		
		
		if(enemyPom<600 && enemyPom>300){
			for(int i = 0; i < Level1.enemies.size(); i++){
			enemies.get(i).setLeft();
			enemies.get(i).setFiring();
			}
		}
		
		if(enemyPom<900 && enemyPom>600){
			for(int i = 0; i < Level1.enemies.size(); i++){
			enemies.get(i).setDown();
			enemies.get(i).setFiring();
			}
		}
		
		if(enemyPom<1200 && enemyPom>900){
			for(int i = 0; i < Level1.enemies.size(); i++){
			enemies.get(i).setUp();
			enemies.get(i).setFiring();
			}
		}
		
		if(enemyPom==1200){
			
			enemyPom=0;
		}
		enemyPom++;

		//if(won) gsm.setState(PoradacStavu.MENUSTATE);
		
		
	}
	


	public void draw(Graphics2D g){
	
		bg.draw(g);
		tileMap.draw(g);	
		
		for(int i = 0; i < players.size(); i++) {
			players.get(i).draw(g);
		}
		
		if(tah==true && timer1==true && won==false) {statusy.get(0).draw(g);}
		if(tah2==true && timer2==true && won==false) {statusy.get(1).draw(g);}
		
		if(won){
			vyhra.draw(g);
		}
		
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}

	}
	public void keyPressed(int k) {
		
		if(tah && won==false){
			
		int i=0;
			
		if(k == KeyEvent.VK_LEFT){
			players.get(i).setLeft();
			players.get(i).animation.setFrames(players.get(i).sprites.get(Hrac.LEFT));
		}
		if(k == KeyEvent.VK_RIGHT){
			players.get(i).setRight();
			players.get(i).animation.setFrames(players.get(i).sprites.get(Hrac.RIGHT));
		}
		if(k == KeyEvent.VK_UP){
			players.get(i).setUp();
			players.get(i).animation.setFrames(players.get(i).sprites.get(Hrac.UP));
		}
		if(k == KeyEvent.VK_DOWN){
			players.get(i).setDown();	
			players.get(i).animation.setFrames(players.get(i).sprites.get(Hrac.DOWN));	
		}
		if(k == KeyEvent.VK_SPACE) {players.get(i).setFiring();pocetTahu--;}
		}
		
		if(tah2 && won==false){
			
		int i=1;
		
		if(k == KeyEvent.VK_LEFT){
			players.get(i).setLeft();
			players.get(i).animation.setFrames(players.get(i).sprites.get(Hrac.LEFT));
		}
		if(k == KeyEvent.VK_RIGHT){
			players.get(i).setRight();
			players.get(i).animation.setFrames(players.get(i).sprites.get(Hrac.RIGHT));
		}
		if(k == KeyEvent.VK_UP){
			players.get(i).setUp();
			players.get(i).animation.setFrames(players.get(i).sprites.get(Hrac.UP));
		}
		if(k == KeyEvent.VK_DOWN){
			players.get(i).setDown();	
			players.get(i).animation.setFrames(players.get(i).sprites.get(Hrac.DOWN));	
		}
		if(k == KeyEvent.VK_SPACE) {players.get(i).setFiring();pocetTahu2--;}
			
		}
		
		if(k == KeyEvent.VK_F5) saveGame();
		if(k == KeyEvent.VK_F6) loadGame();
	}
	
	public void keyReleased(int k) {}
	
	public void saveGame() {	
		
        PrintWriter save = null; 
        
        try  
        {   
            save = new PrintWriter(new FileWriter(new File("C:/Users/Tomáš Wagner/Disk Google/vsb/2_1/PJ1/projekt/Resources/Save/savedgame.txt"), false));  

            for(int i = 0; i < players.size(); i++) {
            	save.println(players.get(i).getmyx());  
            	save.println(players.get(i).getmyy());  
            	save.flush(); 
            }
             
            for(int i = 0; i < enemies.size(); i++) {
            	save.println(enemies.get(i).death);  
            	save.flush(); 
            }
            
            save.println(pocetTahu);   
            save.flush(); 
            save.println(pocetTahu2);   
            save.flush(); 
            
            save.println(tah);   
            save.flush(); 
            save.println(tah2);   
            save.flush(); 
            
            save.println(timer1);   
            save.flush(); 
            save.println(timer2);   
            save.flush();     
            
            save.println(won);   
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
            load = new LineNumberReader(new FileReader(new File("C:/Users/Tomáš Wagner/Disk Google/vsb/2_1/PJ1/projekt/Resources/Save/savedgame.txt")));  
            
            String x, y;
            
            for(int i = 0; i < players.size(); i++) {
            	x = load.readLine();   
                y = load.readLine();
            	players.get(i).setPosition(Integer.parseInt(x), Integer.parseInt(y));              
            }
            
            for(int i = 0; i < enemies.size(); i++) {
            	enemies.get(i).death = Boolean.valueOf(load.readLine());           	
            }
        
            pocetTahu = Integer.parseInt(load.readLine());
            pocetTahu2 = Integer.parseInt(load.readLine());
            
            tah = Boolean.valueOf(load.readLine());
            tah2 = Boolean.valueOf(load.readLine());
            
            timer1 = Boolean.valueOf(load.readLine());
            timer2 = Boolean.valueOf(load.readLine());    
            
            won = Boolean.valueOf(load.readLine());
              
            load.close();  
            load = null;   
              
        }  
        catch(Exception e) {  
            System.out.println("Exception caught: "+e.getMessage());  
        } 
        
        for(int i = 0; i < players.size(); i++) {
        	players.get(i).update();              
        }
        
        for(int i = 0; i < enemies.size(); i++) {
        	enemies.get(i).update();          	
        }
        
        try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
	

