package players;

import events.GameEventData;
import events.GameEventHandler;

public class Audience extends User {
    public Audience(String name) {
        super(name);
    }
    public void echoEvent(GameEventData eventData) {
        System.out.print("hi I'm " + getName() + " and I observed the move ");
        System.out.println(eventData.toString());
    }

    public GameEventHandler getGameEventHandler() {
        return this::echoEvent;
    }
}
