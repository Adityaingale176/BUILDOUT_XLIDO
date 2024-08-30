package com.crio.xlido.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.crio.xlido.entities.Event;
import com.crio.xlido.entities.User;

public class EventRepository implements IEventRepository{

    private final Map<Long, Event> storage = new HashMap<>();

    private AtomicLong eventCounter = new AtomicLong();

    @Override
    public Event save(Event entity) {
       
       Event event = new Event(eventCounter.incrementAndGet(), entity);
       storage.putIfAbsent(event.getEventId(), event);
       return event;
    }

    @Override
    public List<Event> findAll() {
       
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Event> findById(Long id) {
       
      return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void delete(Long eventId, Long userId) {
      
      storage.remove(eventId);
    }

    

}