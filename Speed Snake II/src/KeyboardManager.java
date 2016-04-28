import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class KeyboardManager implements KeyListener{
	
	HashMap<Integer, Boolean> keyIsHeld = new HashMap<>();
	LinkedList<Integer> keyPressQueue = new LinkedList<>();
	LinkedList<Integer> keyHoldStack = new LinkedList<>();
	
	ArrayList<Integer> validKeys = new ArrayList<>();
	
	public KeyboardManager(){
		int[] validKeysArray = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, 
				KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_H, KeyEvent.VK_ENTER};
		for (int i = 0; i < validKeysArray.length; ++i){
			validKeys.add(validKeysArray[i]);
		}
	}
	
	public void flush(){
		keyPressQueue.clear();
		keyHoldStack.clear();
		for (int i: validKeys){
			keyIsHeld.put(i, false);
		}
	}
	@Override
	public void keyPressed(KeyEvent k) {
		if (validKeys.contains(k.getKeyCode())){
			if ((!keyIsHeld.containsKey(k.getKeyCode()) || !keyIsHeld.get(k.getKeyCode()))){
				keyPressQueue.addLast(k.getKeyCode());
				keyHoldStack.addLast(k.getKeyCode());
				System.out.println("Keycode:" + k.getKeyCode() + " pressed");
			}
			keyIsHeld.put(k.getKeyCode(), true);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent k) {
		if (validKeys.contains(k.getKeyCode())){
			System.out.println("Keycode:" + k.getKeyCode() + " released");
			keyIsHeld.put(k.getKeyCode(), false);
			/*if (keyHoldStack.contains(k.getKeyCode())){
				keyHoldStack.remove((Integer)(k.getKeyCode()));
			}*/
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// nothing
	}
	
	public int getKey(){
		if (keyPressQueue.isEmpty()){
			while (!keyHoldStack.isEmpty()){
				if (keyIsHeld.get(keyHoldStack.getLast())){
					return keyHoldStack.getLast();
				}else{//we shouldn't reach this state, but it's here just in case
					keyHoldStack.removeLast();
				}
			}
			return KeyEvent.VK_UNDEFINED;
		}
		else{
			return keyPressQueue.removeFirst();
		}
	}
}
