package org.example.event;

import javafx.event.Event;
import javafx.event.EventType;

public class UserEvent extends Event{
    public static final EventType<UserEvent> ANY = new EventType<>(Event.ANY,"ANY");


    public UserEvent(EventType<? extends Event> eventType){
        super(eventType);
    }

}
