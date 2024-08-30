package com.crio.xlido.repositories;

import java.util.List;
import java.util.Optional;
import com.crio.xlido.entities.Event;
import com.crio.xlido.entities.User;

public interface IEventRepository {

    Event save(Event event);

    List<Event> findAll();

    Optional<Event> findById(Long id);

    void delete(Long eventId, Long userId);
}
