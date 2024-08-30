package com.crio.xlido.services;

import java.util.Optional;
import com.crio.xlido.entities.Event;
import com.crio.xlido.entities.User;
import com.crio.xlido.repositories.IEventRepository;
import com.crio.xlido.repositories.IUserRepository;

public class EventService {

   private final IEventRepository eventRepository;
   private final IUserRepository userRepository;

   public EventService(IEventRepository eventRepository, IUserRepository userRepository){
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
   }

   public Event CREATE_EVENT(String title, Long organizerId){

        Event event = new Event(title, organizerId);
        User user = userRepository.findById(organizerId).orElseThrow(()->new RuntimeException("User with an id "+organizerId+" does not exist"));
     
        return eventRepository.save(event);

   }

   public void DELETE_EVENT(Long eventId , Long userId ){

     User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User with an id "+userId+" does not exist"));
     Event event = eventRepository.findById(eventId).orElseThrow(()->new RuntimeException("Event with an id "+eventId+" does not exist"));
     if (event.getOrganizerId()!=userId){
          throw new RuntimeException("User with an id "+userId+" is not a organizer of Event with an id "+eventId);
     }
     eventRepository.delete(eventId, userId);
     
   }
    
}
