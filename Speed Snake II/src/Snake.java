import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;



public class Snake{

	private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	private int direction = UP;
	private BufferedImage[] headImage;
	private BufferedImage[] tailImage;
	private LinkedList<SnakeSegment> segments;
	private Color snakeGreen = new Color(76, 255, 0);
	
	public Snake(int startx, int starty, int segCount){
		headImage = new BufferedImage[4];
		tailImage = new BufferedImage[4];
		try {
			headImage[UP] = ImageIO.read(this.getClass().getResource("head_outlined_north.png"));
			headImage[DOWN] = ImageIO.read(this.getClass().getResource("head_outlined_south.png"));
			headImage[LEFT] = ImageIO.read(this.getClass().getResource("head_outlined_west.png"));
			headImage[RIGHT] = ImageIO.read(this.getClass().getResource("head_outlined_east.png"));
			tailImage[UP] = ImageIO.read(this.getClass().getResource("tail_north.png"));
			tailImage[DOWN] = ImageIO.read(this.getClass().getResource("tail_south.png"));
			tailImage[LEFT] = ImageIO.read(this.getClass().getResource("tail_west.png"));
			tailImage[RIGHT] = ImageIO.read(this.getClass().getResource("tail_east.png"));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Error loading images.png");
			e1.printStackTrace();
		}
		segments = new LinkedList<SnakeSegment>();
		for (int i = 0; i < segCount; ++i){
			segments.add(new SnakeSegment(startx, starty,direction));
		}
	}
	
	public void draw(Graphics2D g, int tileSize){
		int index = 0;
		for (SnakeSegment s: segments){
			if (index == 0){
				
			}else if (index == segments.size() -1){
				//nothing
			}else{
				g.setColor(Color.BLACK);
				g.fillRect(s.getX() * tileSize-1, s.getY() * tileSize-1, tileSize+2, tileSize+2);
			}
			++index;
		}
		index = 0;
		for (SnakeSegment s: segments){
			if (index == 0){
				g.drawImage(tailImage[segments.get(1).getDirection()],s.getX() * tileSize -1,s.getY() * tileSize -1,null);
			}else if (index == segments.size() -1){
				g.drawImage(headImage[direction],s.getX() * tileSize -1,s.getY() * tileSize -1,null);
			}else{
				g.setColor(snakeGreen);
				g.fillRect(s.getX() * tileSize, s.getY() * tileSize, tileSize, tileSize);
			}
			++index;
		}
	}
	public boolean attemptStep(int key, int xTiles, int yTiles){
		int i = key;
		
		int targetX = segments.getLast().getX();
		int targetY = segments.getLast().getY();
		
		if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && direction != RIGHT)
		{
			direction = LEFT;
		}

		if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && direction != LEFT)
		{
			direction = RIGHT;
		}

		if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && direction != DOWN)
		{
			direction = UP;
		}

		if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && direction != UP)
		{
			direction = DOWN;
		}
		
		switch(direction){
		case UP:
			targetY -= 1;
			break;
		case DOWN:
			targetY += 1;
			break;
		case LEFT:
			targetX -= 1;
			break;
		case RIGHT:
			targetX += 1;
			break;
		}
		for (SnakeSegment s: segments){
			if (s.getX() == targetX && s.getY() == targetY){
				return false;
			}
			if (targetX < 0 || targetX >= xTiles || targetY < 0 || targetY >= yTiles){
				return false;
			}
		}
		SnakeSegment s = segments.getFirst();
		segments.removeFirst();
		segments.addLast(s);
		s.setX(targetX);
		s.setY(targetY);
		s.setDirection(direction);
		return true;
	}
	
	public void grow(){
		segments.addFirst(new SnakeSegment(segments.getFirst().getX(),segments.getFirst().getY(),direction));
	}
	
	public int getHeadX(){
		return segments.getLast().getX();
	}
	
	public int getHeadY(){
		return segments.getLast().getY();
	}
}