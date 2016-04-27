package events;

import java.util.ArrayList;
import java.util.List;

public class EventPool {
  private static List<Event> events = new ArrayList<Event>();
  
  public static List<Event> getEvents() {
    return events;
  }
  
  public static void clearEvents() {
    events.clear();
  }
  
  public static Event getEventAtIndex(int index) {
    return events.get(0);
  }
  
  public static void addEvent(Event e) {
    events.add(e);
  }
  
  public static void removeEvent(Event e) {
    events.remove(e);
  }
}
