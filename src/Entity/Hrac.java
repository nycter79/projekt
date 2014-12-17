package Entity;

import Stavy.Level1;
import TileMap.*;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Hrac extends ObjectAbstr {
	
	public int health;
	private int maxHealth;
	private int fire;
	private int maxFire;
	private boolean remove;
	public int FireX;
	public int FireY;
	//private boolean dead;
	
	public static boolean crush2=false;
	
	public int myxx;
	public int myyy;

	
	// fireball
	private boolean firing;
	private int fireCost;
	private ArrayList<Strela> fireBalls;
	
	
	// animations
	public ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
		2,2,2,2,4
	};

	
	// animation actions

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int UP = 3;
	
	public Hrac(MapaDlaz tm, String sprite) {
		
		super(tm);
		
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
		moveSpeed = 5;
		
		health = maxHealth = 5;
		fire = maxFire = 300;
		
		fireCost = 100;
		fireBalls = new ArrayList<Strela>();
		
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					sprite
				)
			);
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 5; i++) {
				
				BufferedImage[] bi =
					new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++) {
					
					if(i != 6) {
						bi[j] = spritesheet.getSubimage(
								j * width,
								i * height,
								width,
								height
						);
					}
					else {
						bi[j] = spritesheet.getSubimage(
								j * width * 2,
								i * height,
								width * 2,
								height
						);
					}
					
				}
				
				sprites.add(bi);
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		animation = new Animace();
		animation.setFrames(sprites.get(RIGHT));
		animation.setDelay(70);
		
	}
	
	
	public void setFiring() { 
		firing = true;
	}
	
	public void stopFiring() { 
		firing = false;
	}
	
	
	public int getHealth() { return health; }
	public int getMaxHealth() { return maxHealth; }
	public int getFire() { return fire; }
	public int getTahy() { return Level1.pocetTahu; }
	public int getTahy2() { return Level1.pocetTahu2; }
	public int getMaxFire() { return maxFire; }
	
	public int getmyx() { return myxx; }
	public int getmyy() { return myyy; }
	
	public void setDown() {
		super.setDown();
		faceLeft = false;
		faceRight = false;
		faceUp = false;
		faceDown = true;

	}
	public void setLeft() {
		super.setLeft();
		faceLeft = true;
		faceRight = false;
		faceUp = false;
		faceDown = false;

	}
	public void setRight() {
		super.setRight();
		faceLeft = false;
		faceRight = true;
		faceUp = false;
		faceDown = false;

	}
	public void setUp() {
		super.setUp();
		faceLeft = false;
		faceRight = false;
		faceUp = true;
		faceDown = false;

	}
	
	public static void crush2() { 
		
		crush2=true;

		}
	
	

	
	public int getFireX() { return FireX; }
	public int getFireY() { return FireY; }
	
	public void update() {
		
		super.update();
		
		
		
		myxx = x;
		myyy = y;
	
				// fireBall attack
				fire += 1;
				if(fire > maxFire) fire = maxFire;
				if(firing) {
					if(fire > fireCost) {
						fire -= fireCost;
						Strela fb = new Strela(tileMap);
						fb.setPosition(x, y);
						fireBalls.add(fb);
						stopFiring();
					}
				}

				// update fireBalls
				for(int i = 0; i < fireBalls.size(); i++) {
					fireBalls.get(i).update();
					
					if(fireBalls.get(i).shouldRemove()) {
						fireBalls.remove(i);
						i--;
							}
				}	

			animation.update();
				
	}


	public void draw(Graphics2D g) {
		
		
		
		for(int i = 0; i < fireBalls.size(); i++) {
			fireBalls.get(i).draw(g);
		}
		
		super.draw(g);
		
	}


	public boolean shouldRemove() {
		if(health == 0){
			remove = true;
			Level1.won=true;
		}
		return remove;
	}
}
	

















