package Entity;

import Stavy.StavLevelu1;
import TileMap.Dlazdice;
import TileMap.MapaDlaz;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Strela2 extends ObjectAbstr2 {
	
	private boolean hit;
	private boolean remove;
	
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	
	public Strela2(MapaDlaz tm) {
		
		super(tm);
		
		moveSpeed = 4;
		width = 30;
		height = 30;
		cwidth = 14;
		cheight = 14;
		
		if(faceRight){
			R=true;
			L=false;
			U=false;
			D=false;
		}
		if(faceLeft){
			R=false;
			L=true;
			U=false;
			D=false;
		}
		if(faceUp){
			R=false;
			L=false;
			U=true;
			D=false;
		}
		if(faceDown){
			R=false;
			L=false;
			U=false;
			D=true;
		}
		
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Hrac/fireball.gif"
				)
			);
			
			sprites = new BufferedImage[4];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(
					i * width,
					0,
					width,
					height
				);
			}
			
			hitSprites = new BufferedImage[3];
			for(int i = 0; i < hitSprites.length; i++) {
				hitSprites[i] = spritesheet.getSubimage(
					i * width,
					height,
					width,
					height
				);
			}
			
			animation = new Animace();
			animation.setFrames(sprites);
			animation.setDelay(70);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setHit() {
		if(hit) return;
		hit = true;
		animation.setFrames(hitSprites);
		animation.setDelay(70);
		xdest = 0;
	}
	
	
	public boolean shouldRemove() { return remove; }
	
	public void update() {
		
		if(x == Hrac.myxx && y == Hrac.myyy){
			setHit();
			Hrac.health = Hrac.health-1;
		}
		
		if(x == StavLevelu1.enemy1.myx && y == StavLevelu1.enemy1.myy) {
			setHit();
			StavLevelu1.enemy1.health = StavLevelu1.enemy1.health-1;
		}
		
		if(x == StavLevelu1.enemy2.myx && y == StavLevelu1.enemy2.myy) {
			setHit();
			StavLevelu1.enemy2.health = StavLevelu1.enemy2.health-1;
		}
		if(x == StavLevelu1.enemy1.myx && y == StavLevelu1.enemy1.myy) {
			setHit();
			StavLevelu1.enemy1.health = StavLevelu1.enemy1.health-1;
		}
		
		if(x == StavLevelu1.enemy3.myx && y == StavLevelu1.enemy3.myy) {
			setHit();
			StavLevelu1.enemy3.health = StavLevelu1.enemy3.health-1;
		}
		if(x == StavLevelu1.enemy4.myx && y == StavLevelu1.enemy4.myy) {
			setHit();
			StavLevelu1.enemy4.health = StavLevelu1.enemy4.health-1;
		}
		
		if(x == StavLevelu1.enemy5.myx && y == StavLevelu1.enemy5.myy) {
			setHit();
			StavLevelu1.enemy5.health = StavLevelu1.enemy5.health-1;
		}
		if(x == StavLevelu1.enemy6.myx && y == StavLevelu1.enemy6.myy) {
			setHit();
			StavLevelu1.enemy6.health = StavLevelu1.enemy6.health-1;
		}
		
		if(Hrac2.R){
			
			setRight2();
			if(tileMap.getType(rowTile, colTile + 1) == Dlazdice.BLOCKED) {
				setHit();
			}
			
		}
		if(Hrac2.L){
			
			setLeft2();
			if(tileMap.getType(rowTile, colTile - 1) == Dlazdice.BLOCKED) {
				setHit();
			}
			
		}
		if(Hrac2.U){
			
			setUp2();
			if(tileMap.getType(rowTile -1, colTile) == Dlazdice.BLOCKED) {
				setHit();
			}
			
		}
		if(Hrac2.D){
			
			setDown2();
			if( tileMap.getType(rowTile + 1, colTile) == Dlazdice.BLOCKED) {
				setHit();
			}
		}
		
		super.update();
	
		animation.update();
		
		if(hit) {
			remove = true;
		}
	}
	
	

	public void draw(Graphics2D g){
		setMapPosition();
		super.draw(g);
		
	}
	
}