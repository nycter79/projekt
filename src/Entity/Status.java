package Entity;

import java.awt.*;

public class Status {
	
	private Font titleFont;
	
	public Status(Hrac p) {

	}
	
	public void draw(Graphics2D g) {
		
		titleFont = new Font("Century Gothic",
		Font.BOLD, 35);
		g.setFont(titleFont);
		g.setColor(Color.RED);
		g.drawString(
			"Vyhral jsi.",
			220,
			70
		);
		
	}
	
}













