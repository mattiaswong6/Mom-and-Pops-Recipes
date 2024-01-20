package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


// Represents a log of recipe list events, using Singleton Design Patter
public class EventLog implements Iterable<Event> {
    private static EventLog theLog;
    private Collection<Event> events;

    private EventLog() {
        events = new ArrayList<Event>();
    }

    // EFFECTS: Gets instance of EventLog - creates it if it doesn't already exist
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }

    // MODIFIES: this
    // EFFECTS: Adds an event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

    // MODIFIES: this
    // EFFECTS: Clear the event log and logs the event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    // EFFECTS: allows theLog to be iterated
    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
