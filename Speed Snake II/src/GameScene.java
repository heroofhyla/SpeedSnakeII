import java.awt.Color;
import java.awt.Graphics2D;

public class GameScene extends SnakeScene{
	//TODO: Food and score
	private int xTiles;
	private int yTiles;
	private int tileSize;
	private int width;
	private int height;
	private Snake snake;
	private int appleX;
	private int appleY;
	public GameScene(int xTiles, int yTiles, int tileSize){
		appleX = (int)(Math.random()*xTiles);
		appleY = (int)(Math.random()*yTiles);
		snake = new Snake(xTiles/2, yTiles/2, 5);
		this.xTiles = xTiles;
		this.yTiles = yTiles;
		this.tileSize = tileSize;
		this.width = xTiles * tileSize;
		this.height = yTiles * tileSize;
	}
	@Override
	public void tick(Graphics2D g, int key) {
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		g.fillRect(appleX*tileSize-1, appleY*tileSize-1, tileSize+2, tileSize+2);

		g.setColor(Color.red);
		g.fillRect(appleX*tileSize, appleY*tileSize, tileSize, tileSize);
		snake.draw(g, tileSize);
		boolean gameover = !snake.attemptStep(key, xTiles, yTiles);
		if (gameover){
			setNextSceneType(SceneType.HIGHSCORE);
			alertReadyForNextScene();
		}
		if (snake.getHeadX() == appleX && snake.getHeadY() == appleY){
			snake.grow();
			addScore(100);
			appleX = (int)(Math.random()*xTiles);
			appleY = (int)(Math.random()*yTiles);

		}
		addScore(1);
	}
}
