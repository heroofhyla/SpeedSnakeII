import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class GameScene extends SnakeScene{
	//TODO: Food and score
	private int xTiles;
	private int yTiles;
	private int tileSize;
	private int width;
	private int height;
	private Snake snake;
	private int foodX;
	private int foodY;
	BufferedImage bgImage;
	BufferedImage foodImage;
	public GameScene(int xTiles, int yTiles, int tileSize){
		try {
			bgImage = ImageIO.read(this.getClass().getResource("grassypale.png"));
			foodImage = ImageIO.read(this.getClass().getResource("mouse_bright.png"));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Error loading images.png");
			e1.printStackTrace();
		}
		foodX = (int)(Math.random()*xTiles);
		foodY = (int)(Math.random()*yTiles);
		snake = new Snake(xTiles/2, yTiles/2, 5);
		this.xTiles = xTiles;
		this.yTiles = yTiles;
		this.tileSize = tileSize;
		this.width = xTiles * tileSize;
		this.height = yTiles * tileSize;
	}
	@Override
	public void tick(Graphics2D g, int key) {
		g.drawImage(bgImage, 0, 0, null);
		g.setColor(Color.black);
		//g.fillRect(foodX*tileSize-1, foodY*tileSize-1, tileSize+2, tileSize+2);

		//g.setColor(Color.red);
		//g.fillRect(foodX*tileSize, foodY*tileSize, tileSize, tileSize);
		g.drawImage(foodImage, foodX*tileSize-1, foodY*tileSize-1, null);
		snake.draw(g, tileSize);
		boolean gameover = !snake.attemptStep(key, xTiles, yTiles);
		if (gameover){
			setNextSceneType(SceneType.HIGHSCORE);
			alertReadyForNextScene();
		}
		if (snake.getHeadX() == foodX && snake.getHeadY() == foodY){
			snake.grow();
			addScore(1000);
			foodX = (int)(Math.random()*xTiles);
			foodY = (int)(Math.random()*yTiles);

		}
		addScore(1);
	}
}
