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
	public GameScene(int xTiles, int yTiles, int tileSize){
		snake = new Snake(xTiles/2, yTiles/2, 30);
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
		snake.draw(g, tileSize);

		boolean gameover = !snake.attemptStep(key, xTiles, yTiles);
		if (gameover){
			setNextSceneType(SceneType.HIGHSCORE);
			alertReadyForNextScene();
		}
		//TODO
		
	}
}
