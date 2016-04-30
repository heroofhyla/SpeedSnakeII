import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class HighScoreScene extends SnakeScene{
	private static final int SCORE_LIST_SIZE = 10;
	ArrayList<Integer> scores = new ArrayList<>(SCORE_LIST_SIZE);
	int count = 0;
	int newIndex = -1;
	BufferedImage bgImage;
	public HighScoreScene(int newScore) {
		try {
			bgImage = ImageIO.read(this.getClass().getResource("gameoverscreen.png"));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Couldn't find gameoverscreen.png");
			e1.printStackTrace();
		}
		for (int i = 0; i < SCORE_LIST_SIZE; ++i){
			scores.add(0);
		}
		File scoresFile = new File("highscores.txt");
		if (scoresFile.exists()){
			try (Scanner reader = new Scanner(scoresFile)){
				int writeHead = 0;
				while (reader.hasNextInt() && writeHead < scores.size()){
					scores.set(writeHead, reader.nextInt());
					++writeHead;
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Couldn't read from highscores.txt");
				e.printStackTrace();
			}
		}
		for (int i = 0; i < scores.size(); ++i){
			if (newScore > scores.get(i)){
				newIndex = i;
				System.out.println(newScore + " > " + scores.get(i));
				scores.add(i,newScore);
				while (scores.size() > SCORE_LIST_SIZE){
					scores.remove(scores.size() - 1);
				}
				break;
			}
		}
		if (newIndex >= 0){
			try (FileWriter writer = new FileWriter("highscores.txt")){
				for (Integer i: scores){
					writer.write(i + " ");
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Couldn't write to highscores.txt");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void tick(Graphics2D g, int key) {
		g.drawImage(bgImage, 0, 0, null);
		for (int i = 0; i < scores.size(); ++i){
			if (newIndex == i){
				g.setColor(Color.cyan);
			}else{
				g.setColor(Color.white);
			}
			g.drawString(scores.get(i).toString(), 50,160 + 20*i);
		}
				
		if (key == KeyEvent.VK_ENTER){
			setNextSceneType(SceneType.TITLE);
			alertReadyForNextScene();
		}
	}

}
