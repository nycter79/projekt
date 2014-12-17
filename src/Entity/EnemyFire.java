package Entity;

import Stavy.Level1;
import TileMap.Dlazdice;
import TileMap.MapaDlaz;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class EnemyFire extends EnemyAbstr {
	
	private boolean hit;
	private boolean remove;
	
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	
	public EnemyFire(MapaDlaz tm) {
		
		super(tm);
		
		moveSpeed = 8;
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
		xdest = 0;
	}
	
	
	public boolean shouldRemove() { return remove; }
	
	public void update() {
		
		for(int i = 0; i < Level1.players.size(); i++){
			if(x == Level1.players.get(i).myxx && y == Level1.players.get(i).myyy){
				setHit();
				Level1.players.get(i).health = Level1.players.get(i).health-1;
			}
		}


		if(Enemy.R){
			
			setRight2();
			if(tileMap.getType(rowTile, colTile + 1) == Dlazdice.BLOCKED) {
				setHit();
			}
			
		}
		if(Enemy.L){
			
			setLeft2();
			if(tileMap.getType(rowTile, colTile - 1) == Dlazdice.BLOCKED) {
				setHit();
			}
			
		}
		if(Enemy.U){
			
			setUp2();
			if(tileMap.getType(rowTile -1, colTile) == Dlazdice.BLOCKED) {
				setHit();
			}
			
		}
		if(Enemy.D){
			
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