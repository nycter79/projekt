package Stavy;

import TileMap.Pozadi;

import java.awt.*;
import java.awt.event.KeyEvent;


public class MenuStav extends Stavy{
	
	private Pozadi bg;
	
	private int currentChoice = 0;
	private String[] options = {
			"Hra",
			"Policajt",
			"Zlodej",
			"Konec"
	};
	
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	public MenuStav(PoradacStavu gsm) {
		this.gsm = gsm;
		try{
			bg = new Pozadi("/Pozadi/menu.jpg", 1);
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic",
			Font.BOLD, 35);
			
			font = new Font("Arial", Font.PLAIN, 16);
		} 
		catch(Exception e){
		e.printStackTrace();	
		}
	}
	
	public void init() {}
	public void update() {
		bg.update();
	}
	public void draw(Graphics2D g) {
		bg.draw(g);
		
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("�t�k", 220, 70);
		
		g.setFont(font);
		for(int i = 0; i < options.length; i++){
			if(i == currentChoice) {
				g.setColor(Color.GREEN);
			}
			else g.setColor(Color.RED);
			
			g.drawString(options[i], 240, 140 + i * 15);
			
		}
		
	}
	
	private void select(){
		if(currentChoice == 0){
		gsm.setState(PoradacStavu.LEVEL1STATE);
		}
		if(currentChoice == 3){
			System.exit(0);	
		}
		
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			select();
		}
		if(k == KeyEvent.VK_UP){
			currentChoice--;
			if(currentChoice == -1){
				currentChoice = options.length -1;
			}
		}
		if(k == KeyEvent.VK_DOWN){
			currentChoice++;
			if(currentChoice == options.length){
				currentChoice = 0;
			}
		}
	}
	public void keyReleased(int k) {
		
	}

}
