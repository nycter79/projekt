package Stavy;


import java.util.ArrayList;

public class PoradacStavu {
	
	private ArrayList<Stavy> gameStates;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;


	
	public PoradacStavu(){
		
		gameStates = new ArrayList<Stavy>();
		currentState = 0;
		gameStates.add(new MenuStav(this));
		gameStates.add(new StavLevelu1(this));
	}
	

	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();	
	}
	
	public void update() {
		gameStates.get(currentState).update();	
	}
	
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);	
	}
	
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);	
	}
	
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);	
	}
	

}
