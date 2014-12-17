// The game object superclass.
// This class has all the logic required
// to move around a tile based map.

package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import Stavy.Level1;
import TileMap.Dlazdice;
import TileMap.MapaDlaz;

public abstract class ObjectAbstr {
	
	// dimensions
	protected int width;
	protected int height;
	protected int cwidth;
	protected int cheight;
	
	protected static boolean faceLeft;
	protected static boolean faceRight;
	protected static boolean faceDown;
	protected static boolean faceUp;
	
	protected static boolean L;
	protected static boolean R;
	protected static boolean U;
	protected static boolean D;
	
	// position
	protected int x;
	protected int y;
	protected int xdest;
	protected int ydest;
	protected int rowTile;
	protected int colTile;
	
	// movement
	protected boolean moving;
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	
	// attributes
	protected int moveSpeed;
	
	// tilemap
	protected MapaDlaz tileMap;
	protected int tileSize;
	protected int xmap;
	protected int ymap;
	
	// animation
	public Animace animation;
	protected int currentAnimation;
	
	protected boolean crush = false;
	
	public ObjectAbstr(MapaDlaz tm) {
		tileMap = tm;
		tileSize = tileMap.getTileSize();
	}
	
	public int getx() { return x; }
	public int gety() { return y; }
	public int getRow() { return rowTile; }
	public int getCol() { return colTile; }
	
	public void setPosition(int i1, int i2) {
		x = i1;
		y = i2;
		xdest = x;
		ydest = y;
	}
	public void setMapPosition() {
		xmap = tileMap.getx();
		ymap = tileMap.gety();
	}
	public void setTilePosition(int i1, int i2) {
		y = i1 * tileSize + tileSize / 2;
		x = i2 * tileSize + tileSize / 2;
		xdest = x;
		ydest = y;
	}
	
	public void setLeft() {
		if(moving) return;
		left = true;
		moving = validateNextPosition();
	}
	public void setRight() {
		if(moving) return;
		right = true;
		moving = validateNextPosition();
	}
	public void setUp() {
		if(moving) return;
		up = true;
		moving = validateNextPosition();
	}
	public void setDown() {
		if(moving) return;
		down = true;
		moving = validateNextPosition();
	}
	
	public void setLeft2() {
		if(moving) return;
		left = true;
		moving = validateNextPosition2();
	}
	public void setRight2() {
		if(moving) return;
		right = true;
		moving = validateNextPosition2();
	}
	public void setUp2() {
		if(moving) return;
		up = true;
		moving = validateNextPosition2();
	}
	public void setDown2() {
		if(moving) return;
		down = true;
		moving = validateNextPosition2();
	}
	
	public boolean intersects(ObjectAbstr o) {
		return getRectangle().intersects(o.getRectangle());
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(x, y, cwidth, cheight);
	}
	
	// Returns whether or not the entity can
	// move into the next position.
	public boolean validateNextPosition() {
		
		if(moving) return true;
		
		rowTile = (y / tileSize) ;
		colTile = (x / (tileSize)) ;
		
		if(left) {
			if(colTile == 0 || tileMap.getType(rowTile, colTile - 1) == Dlazdice.BLOCKED) {
				return false;
			}
			else {
				xdest = x - 30;
				Level1.pocetTahu--;
				Level1.pocetTahu2--;
			}
		}
		if(right) {
			if(colTile == tileMap.getNumCols() || tileMap.getType(rowTile, colTile + 1) == Dlazdice.BLOCKED) {
				return false;
			}
			else {
				xdest = x + 30;
				Level1.pocetTahu--;
				Level1.pocetTahu2--;
			}
		}
		if(up) {
			if(rowTile == 0 || tileMap.getType(rowTile - 1, colTile) == Dlazdice.BLOCKED) {
				return false;
			}
			else {
				ydest = y - 30;
				Level1.pocetTahu--;
				Level1.pocetTahu2--;
			}
		}
		if(down) {
			if(rowTile == tileMap.getNumRows() - 1 || tileMap.getType(rowTile + 1, colTile) == Dlazdice.BLOCKED) {
				return false;
			}
			else {
				ydest = y + 30;
				Level1.pocetTahu--;
				Level1.pocetTahu2--;
			}
		}
		
		return true;
		
	}
	
	public boolean validateNextPosition2() {
		
		if(moving) return true;
		
		rowTile = (y / tileSize) ;
		colTile = (x / (tileSize)) ;
		
		if(left) {
			if(colTile == 0 || tileMap.getType(rowTile, colTile - 1) == Dlazdice.BLOCKED) {
				return false;
			}
			else {
				xdest = x - 30;

			}
		}
		if(right) {
			if(colTile == tileMap.getNumCols() || tileMap.getType(rowTile, colTile + 1) == Dlazdice.BLOCKED) {
				return false;
			}
			else {
				xdest = x + 30;

			}
		}
		if(up) {
			if(rowTile == 0 || tileMap.getType(rowTile - 1, colTile) == Dlazdice.BLOCKED) {
				return false;
			}
			else {
				ydest = y - 30;

			}
		}
		if(down) {
			if(rowTile == tileMap.getNumRows() - 1 || tileMap.getType(rowTile + 1, colTile) == Dlazdice.BLOCKED) {
				return false;
			}
			else {
				ydest = y + 30;

			}
		}
		
		return true;
		
	}
	
	// Calculates the destination coordinates.
	public void getNextPosition() {
		
		if(left && x > xdest) x -= moveSpeed;
		else left = false;
		if(left && x < xdest) x = xdest;
		
		if(right && x < xdest) x += moveSpeed;
		else right = false;
		if(right && x > xdest) x = xdest;
		
		if(up && y > ydest) y -= moveSpeed;
		else up = false;
		if(up && y < ydest) y = ydest;
		
		if(down && y < ydest) y += moveSpeed;
		else down = false;
		if(down && y > ydest) y = ydest;
		
	}
	
	public void update() {
		
		// get next position
		if(moving) getNextPosition();
		
		// check stop moving
		if(x == xdest && y == ydest) {
			left = right = up = down = moving = false;
			rowTile = y / tileSize;
			colTile = x / tileSize;
		}
		
		// update animation
		//animation.update();
		
	}
	
	// Draws the entity.
	public void draw(Graphics2D g) {
		setMapPosition();
		g.drawImage(
			animation.getImage(),
			x + xmap - width / 2,
			y + ymap - height / 2,
			null
		);
	}
	
}
