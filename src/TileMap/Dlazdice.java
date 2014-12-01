package TileMap;

import java.awt.image.BufferedImage;

public class Dlazdice {

	private BufferedImage image;
	private int type;
	
	//tiles type
	public static final int NORMAL=0;
	public static final int BLOCKED=1;
	
	public Dlazdice(BufferedImage image, int type){
		this.image = image;
		this.type = type;
	}
	
	public BufferedImage getImage() {return image;}
	public int  getType() {return type;}
	
	
}
