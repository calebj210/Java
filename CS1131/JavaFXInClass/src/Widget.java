import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

public class Widget {
    EventHandler actionEventHandler = null;

    public void setActionEventHandler(EventHandler eventHandler) {
        actionEventHandler = eventHandler;
    }

    public void callEventHandler() {
        if (actionEventHandler != null) {
            actionEventHandler.handle(new Event(new EventType("ActionEvent")));
        }
    }
}
