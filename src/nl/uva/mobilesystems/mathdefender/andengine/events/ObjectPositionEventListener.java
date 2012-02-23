package nl.uva.mobilesystems.mathdefender.andengine.events;

import java.util.EventObject;

/**
 * It's for imporatnt events that relate to object position:
 * - disappearing from Camera's surface (screen)
 * - 
 * @author siemionides
 *
 */
public interface ObjectPositionEventListener {
	public void handleObjectPositionEvent(ObjectPositionEvent e);
}
