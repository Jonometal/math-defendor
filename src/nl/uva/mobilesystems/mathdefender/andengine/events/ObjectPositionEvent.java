package nl.uva.mobilesystems.mathdefender.andengine.events;

import java.util.EventObject;

public class ObjectPositionEvent extends EventObject {

	/** Just a internal Java thing, Eclipse shouts at me otherwise..*/
	private static final long serialVersionUID = 1L;
	
	/** Check out EventConstants class for codes*/
	private int eventCode;
	
	public ObjectPositionEvent(Object source, int eventCode) {
		super(source);
		this.setEventCode(eventCode);
	}

	public int getEventCode() {
		return eventCode;
	}

	public void setEventCode(int eventCode) {
		this.eventCode = eventCode;
	}

}
