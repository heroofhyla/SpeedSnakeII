import java.awt.Graphics2D;

import javax.swing.SwingUtilities;

public class SnakeGame {
	private static final int TILE_SIZE = 10;
	private static final int X_TILES = 50;
	private static final int Y_TILES = 50;
	private static final int GAME_WIDTH = TILE_SIZE * X_TILES;
	private static final int GAME_HEIGHT = TILE_SIZE * Y_TILES;
	private static final int FRAMES_PER_SECOND = 20;
	private KeyboardManager keyboardManager;
	private GUI gui;
	private SnakeScene currentScene;
	public SnakeGame(){
		keyboardManager = new KeyboardManager();
		gui = new GUI(GAME_WIDTH, GAME_HEIGHT, keyboardManager);
		currentScene = new TitleScene();
	}
	public static void main(String[] args){
		System.out.println(SwingUtilities.isEventDispatchThread());
		SnakeGame game = new SnakeGame();
		long startTime;
		while (true){
			startTime = System.nanoTime();
			Graphics2D g = game.gui.getGraphics();
			game.currentScene.tick(g, game.keyboardManager.getKey());
			g.dispose();
			game.gui.canvas.paintImmediately(0, 0, GAME_WIDTH, GAME_HEIGHT);
			if (game.currentScene.readyForNextScene()){
				System.out.println("Transitioning to next scene.");
				game.keyboardManager.flush();
				switch (game.currentScene.nextSceneType()){
				case GAME:
					game.currentScene = new GameScene(X_TILES,Y_TILES,TILE_SIZE);
					break;
				case HIGHSCORE:
					game.currentScene = new HighScoreScene(game.currentScene.getScore());
					break;
				case TITLE:
					game.currentScene = new TitleScene();
					break;
				default:
					//oh the humanity!
					break;
				
				}
			}
			long deltaTimeMilli = (System.nanoTime() - startTime)/(1000000); 
			
			try {
				Thread.sleep(Math.max(1000/FRAMES_PER_SECOND - deltaTimeMilli,0));	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
