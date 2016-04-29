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
	private int appleX;
	private int appleY;
	BufferedImage bgImage;
	public GameScene(int xTiles, int yTiles, int tileSize){
		try {
			bgImage = ImageIO.read(this.getClass().getResource("grassy.png"));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Couldn't find grassy.png");
			e1.printStackTrace();
		}
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
		g.drawImage(bgImage, 0, 0, null);
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
