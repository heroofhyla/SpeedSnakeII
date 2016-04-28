import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.LinkedList;



public class Snake{

	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	int direction;
	
	LinkedList<SnakeSegment> segments;
	
	public Snake(int startx, int starty, int segCount){
		segments = new LinkedList<SnakeSegment>();
		for (int i = 0; i < segCount; ++i){
			segments.add(new SnakeSegment(startx, starty));
		}
	}
	
	public void draw(Graphics2D g, int tileSize){
		for (SnakeSegment s: segments){
			g.setColor(Color.BLACK);
			g.fillRect(s.getX() * tileSize-1, s.getY() * tileSize-1, tileSize+2, tileSize+2);
		}
		for (SnakeSegment s: segments){
			g.setColor(Color.GREEN);
			g.fillRect(s.getX() * tileSize, s.getY() * tileSize, tileSize, tileSize);
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
		return true;
	}
	
	public void grow(){
		segments.addFirst(new SnakeSegment(segments.getFirst().getX(),segments.getFirst().getY()));
	}
	
	public int getHeadX(){
		return segments.getLast().getX();
	}
	
	public int getHeadY(){
		return segments.getLast().getY();
	}
}