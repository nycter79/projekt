package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class TimerHud {
	
	private Hrac player;
	
	private BufferedImage image;
	private Font font;
	public static int pocitadlo;
	
	Timer refresh;
	
	public TimerHud(Hrac p) {
		player = p;

	}

	public void draw(Graphics2D g) {
		
		try {
			while(pocitadlo!=0){
				TimeUnit.SECONDS.sleep(1);
				pocitadlo=pocitadlo-1;
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		g.drawImage(image, 0, 10, null);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(
				player.getTahy() + "/" + pocitadlo,
				90,
				65
			);
		
	}
	
}













