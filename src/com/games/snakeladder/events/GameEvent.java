package events;

import java.util.ArrayList;
import java.util.List;

public class GameEvent {
    private List<GameEventHandler> handlers = new ArrayList<>();

    public void addListener(GameEventHandler handler) {
        this.handlers.add(handler);
    }

    public void dispatchEvent(GameEventData eventData) {
        for (GameEventHandler hander : handlers) {
            hander.handleEvent(eventData);
        }
    }
}
