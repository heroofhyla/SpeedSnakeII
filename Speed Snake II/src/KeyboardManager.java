import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.LinkedList;

public class KeyboardManager implements KeyListener{
	
	HashMap<Integer, Boolean> keyIsHeld = new HashMap<>();
	LinkedList<Integer> keyPressQueue = new LinkedList<>();
	LinkedList<Integer> keyHoldStack = new LinkedList<>();
	
	@Override
	public void keyPressed(KeyEvent k) {
		if (!(keyIsHeld.containsKey(k.getKeyCode()) && !keyIsHeld.get(k.getKeyCode()))){
			keyPressQueue.addLast(k.getKeyCode());
			keyHoldStack.addLast(k.getKeyCode());
		}
		keyIsHeld.put(k.getKeyCode(), true);
		
	}

	@Override
	public void keyReleased(KeyEvent k) {
		keyIsHeld.put(k.getKeyCode(), false);
		keyHoldStack.remove(k.getKeyCode());
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
