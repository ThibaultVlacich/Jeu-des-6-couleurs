package events;

import models.TileColor;

public class Event {
  private EventType type;
  
  private TileColor colorChoosed;
  
  public Event(EventType t) {
    type = t;
  }
  
  public EventType getType() {
    return type;
  }
  
  public void setColorChoosed(TileColor c) {
    colorChoosed = c;
  }
  
  public TileColor getColorChoosed() {
    return colorChoosed;
  }
}
