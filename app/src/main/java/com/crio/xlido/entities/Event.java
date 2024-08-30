package com.crio.xlido.entities;

public class Event {
    
    private final Long eventId;
    private final String title;
    private final Long organizerId;

    
    public Event( String title, Long organizerId) {
        this.eventId = null;
        this.title = title;
        this.organizerId = organizerId;
    }

    public Event(Long id, Event event) {
        this.eventId = id;
        this.title = event.title;
        this.organizerId = event.organizerId;
    }

    public Long getEventId() {
        return eventId;
    }
    public String getTitle() {
        return title;
    }
    public Long getOrganizerId() {
        return organizerId;
    }


}
