package Stavy;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;

import Entity.*;
import Main.GamePanel;
import TileMap.*;

public class StavLevelu1 extends Stavy {
	
	private MapaDlaz tileMap;
	private Pozadi bg;
	
	public static int pocetTahu=20;
	public static int pocetTahu2=0;
	public static boolean tah=true;
	public static boolean tah2=false;
	public boolean timer1=false;
	public boolean timer2=false;
	public boolean death=false;
	public boolean death2=false;
	public boolean death3=false;
	public boolean death4=false;
	public boolean death5=false;
	public boolean death6=false;
	public boolean death7=false;
	public boolean death8=false;
	public int enemyPom = 0;
	public static Hrac player;
	public static Hrac2 player2;
	public static Enemy enemy1;
	public static Enemy enemy2;
	public static Enemy enemy3;
	public static Enemy enemy4;
	public static Enemy enemy5;
	public static Enemy enemy6;

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
		tileMap.loadMap("/Mapy/dung2.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		bg = new Pozadi("/Pozadi/trava.bmp",0.1);
		
		player = new Hrac(tileMap);
		player.setPosition(75,435);
		
		player2 = new Hrac2(tileMap);
		player2.setPosition(555,435);
		
		enemy1 = new Enemy(tileMap);
		enemy1.setPosition(105,225);

		enemy2 = new Enemy(tileMap);
		enemy2.setPosition(405,195);
		
		enemy3 = new Enemy(tileMap);
		enemy3.setPosition(255,195);

		enemy4 = new Enemy(tileMap);
		enemy4.setPosition(345,375);
		
		enemy5 = new Enemy(tileMap);
		enemy5.setPosition(75,105);

		enemy6 = new Enemy(tileMap);
		enemy6.setPosition(345,105);
		
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
		if(enemy1.health==0) death3=true;
		if(enemy2.health==0) death4=true;
		if(enemy3.health==0) death5=true;
		if(enemy4.health==0) death6=true;
		if(enemy5.health==0) death7=true;
		if(enemy6.health==0) death8=true;
		
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

		player.update();
		player2.update();
		enemy1.update();
		enemy2.update();
		enemy3.update();
		enemy4.update();
		enemy5.update();
		enemy6.update();
		
		/*
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
		*/
		if(enemyPom<300){
			enemy1.setRight();
			if(death3==false)enemy1.setFiring();
			enemy2.setRight();
			if(death4==false)enemy2.setFiring();
			enemy3.setRight();
			if(death5==false)enemy3.setFiring();
			enemy4.setRight();
			if(death6==false)enemy4.setFiring();
			enemy5.setRight();
			if(death7==false)enemy5.setFiring();
			enemy6.setRight();
			if(death8==false)enemy6.setFiring();
		}
		
		if(enemyPom<600 && enemyPom>300){
			enemy1.setLeft();
			if(death3==false)enemy1.setFiring();
			enemy2.setLeft();
			if(death4==false)enemy2.setFiring();
			enemy3.setLeft();
			if(death5==false)enemy3.setFiring();
			enemy4.setLeft();
			if(death6==false)enemy4.setFiring();
			enemy5.setLeft();
			if(death7==false)enemy5.setFiring();
			enemy6.setLeft();
			if(death8==false)enemy6.setFiring();
		}
		
		if(enemyPom<900 && enemyPom>600){
			enemy1.setDown();
			if(death3==false)	enemy1.setFiring();
			enemy2.setDown();
			if(death4==false)	enemy2.setFiring();
			enemy3.setDown();
			if(death5==false)	enemy3.setFiring();
			enemy4.setDown();
			if(death6==false)	enemy4.setFiring();
			enemy5.setDown();
			if(death7==false)enemy5.setFiring();
			enemy6.setDown();
			if(death8==false)enemy6.setFiring();
		}
		
		if(enemyPom<1200 && enemyPom>900){
			enemy1.setUp();
			if(death3==false) enemy1.setFiring();
			enemy2.setUp();
			if(death4==false)enemy2.setFiring();
			enemy3.setUp();
			if(death5==false)enemy3.setFiring();
			enemy4.setUp();
			if(death6==false)enemy4.setFiring();
			enemy5.setUp();
			if(death7==false)enemy5.setFiring();
			enemy6.setUp();
			if(death8==false)enemy6.setFiring();
		}
		
		if(enemyPom==1200){
			
			enemyPom=0;
		}
		enemyPom++;
		
		/*
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		int x = (int) b.getX();
		int y = (int) b.getY();
		System.out.print(x + " " + y+"\n");
		*/
		

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



		if(death3==false){
			enemy1.draw(g);
		}
		if(death4==false){
			enemy2.draw(g);
		}
		if(death5==false){
			enemy3.draw(g);
		}
		if(death6==false){
			enemy4.draw(g);
		}

		if(death7==false){
			enemy5.draw(g);
		}
		if(death8==false){
			enemy6.draw(g);
		}



		

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
	
	/*
	int prevX=0;
	int prevY=0;
	public void mousePressed(MouseEvent evt){
	    prevX = evt.getXOnScreen();
	    prevY = evt.getYOnScreen();
	    prevY=prevY/3;
	    prevX=prevX/3;
		System.out.print(prevX + " " + prevY+"\n");
	}
*/

	
	
	
	
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
            save = new PrintWriter(new FileWriter(new File("C:/Users/Tomáš Wagner/Disk Google/vsb/2_1/PJ1/projekt/Resources/Save/savedgame.txt"), false));  

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
            load = new LineNumberReader(new FileReader(new File("C:/Users/Tomáš Wagner/Disk Google/vsb/2_1/PJ1/projekt/Resources/Save/savedgame.txt")));  
            
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
        
        player.update();
        player2.update();
		
	}
	
}

