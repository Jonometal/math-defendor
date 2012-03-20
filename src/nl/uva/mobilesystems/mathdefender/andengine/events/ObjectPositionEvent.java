package nl.uva.mobilesystems.mathdefender.andengine.events;

import java.util.EventObject;

public class ObjectPositionEvent extends EventObject {

	/** Just a internal Java thing, Eclipse shouts at me otherwise..*/
	private static final long serialVersionUID = 1L;
	
	/** Check out EventConstants.EVENT_<XXX> for codes*/
	private int eventCode;
	
	/** Check out EventCOnstants.ADD_EVENT_<XXX> for those codes*/
	private int additionalInfoCode;
	
	
	
	public ObjectPositionEvent(Object source, int eventCode) {
		super(source);
		this.setEventCode(eventCode);
	}
	
	public ObjectPositionEvent(Object source, int eventCode, int addInfoCode){
		super(source);
		this.setEventCode(eventCode);
		this.setAdditionalInfoCode(addInfoCode);
	}

	public int getEventCode() {
		return eventCode;
	}

	public void setEventCode(int eventCode) {
		this.eventCode = eventCode;
	}

	public int getAdditionalInfoCode() {
		return additionalInfoCode;
	}

	public void setAdditionalInfoCode(int additionalInfoCode) {
		this.additionalInfoCode = additionalInfoCode;
	}

}
