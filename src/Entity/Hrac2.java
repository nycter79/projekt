package Entity;

import TileMap.*;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Hrac2 extends ObjectAbstr2 {
	
	public int health;
	private int maxHealth;
	private int fire;
	private int maxFire;
	public int FireX;
	public int FireY;
	//private boolean dead;
	
	public static boolean crush2=false;

	
	// fireball
	private boolean firing;
	private int fireCost;
	//private int fireBallDamage;
	private ArrayList<Strela2> fireBalls;
	
	
	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
		2, 2, 2, 2
	};
	
	// animation actions
	private static final int UP = 0;
	private static final int RIGHT = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;
	
	public Hrac2(MapaDlaz tm) {
		
		super(tm);
		
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
		moveSpeed = 3;
		
		health = maxHealth = 5;
		fire = maxFire = 2500;
		
		fireCost = 200;
		//fireBallDamage = 5;
		fireBalls = new ArrayList<Strela2>();
		
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Hrac/polda.gif"
				)
			);
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 4; i++) {
				
				BufferedImage[] bi =
					new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++) {
					
					if(i != 4) {
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
		animation.setDelay(400);
		
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
	public int getMaxFire() { return maxFire; }
	
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
		
		// update position
		super.update();
		
		
		if(faceLeft) {
				animation.setFrames(sprites.get(LEFT));
				animation.setDelay(100);	
		}
		if(faceRight) {
			animation.setFrames(sprites.get(RIGHT));
			animation.setDelay(100);	
		}
		if(faceUp) {
			animation.setFrames(sprites.get(UP));
			animation.setDelay(100);	
		}
		if(faceDown) {
			animation.setFrames(sprites.get(DOWN));
			animation.setDelay(100);	
		}
	
				// fireBall attack
				fire += 1;
				if(fire > maxFire) fire = maxFire;
				if(firing) {
					if(fire > fireCost) {
						fire -= fireCost;
						Strela2 fb = new Strela2(tileMap);
						fb.setPosition(x, y);
						fireBalls.add(fb);
						FireX=fb.getx();
						FireY=fb.gety();
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
	}


	public void draw(Graphics2D g) {
		
		for(int i = 0; i < fireBalls.size(); i++) {
			fireBalls.get(i).draw(g);
		}
		
		super.draw(g);
		
	}
}
	

















