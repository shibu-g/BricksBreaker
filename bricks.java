package BrickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class bricks {
public int map[][];
public int brickwidth;
public int brickhight;

public bricks(int row,int col) {
	map=new int[row][col];
	for(int i=0;i<map.length;i++) {
		for(int j=0;j<map[0].length;j++) {
			map[i][j]=1;
		}
	}
	brickwidth=540/col;
	brickhight=150/row;
}
public void draw(Graphics2D g) {
	for(int i=0;i<map.length;i++) {
		for(int j=0;j<map[0].length;j++) {
			if(map[i][j]>0) {
				g.setColor(Color.white);
				g.fillRect(j*brickwidth+80, i*brickhight+50, brickwidth, brickhight);
				
				g.setStroke(new BasicStroke(3));
				g.setColor(Color.black);
				g.drawRect(j*brickwidth+80, i*brickhight+50, brickwidth, brickhight);
			}
		}
		}
}
public void setbrickvalue(int val,int row,int col) {
	map[row][col]=val;
}
}
