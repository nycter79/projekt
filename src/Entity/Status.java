package Entity;

import java.awt.*;

public class Status {
	
	private Font titleFont;
	
	public Status(Hrac p) {

	}
	
	public void draw(Graphics2D g) {
		
		titleFont = new Font("Century Gothic",
		Font.BOLD, 25);
		g.setFont(titleFont);
		g.setColor(Color.RED);
		g.drawString(
			"Na tahu hr�� ��slo 1.",
			155,
			70
		);
		
	}
	
}













