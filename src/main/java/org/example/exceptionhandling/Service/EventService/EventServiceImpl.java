package org.example.exceptionhandling.Service.EventService;

import org.example.exceptionhandling.Exception.AllNotfoundException;
import org.example.exceptionhandling.Model.EventModel.Event;
import org.example.exceptionhandling.Model.EventModel.EventRequest;
import org.example.exceptionhandling.Repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    private EventRepository eventRepository;
    public EventServiceImpl(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }
    @Override
    public List<Event> getAll(Integer offset, Integer limit) {
        return eventRepository.getAll(offset,limit);
    }

    @Override
    public Event geyById(int id) {
        if(eventRepository.getById(id) == null){
            throw new AllNotfoundException("Event with id "+ id+ " does not exist");
        }
        return eventRepository.getById(id);
    }

    @Override
    public Event insertEvent(EventRequest eventRequest) {
        Integer event = eventRepository.insertEvent(eventRequest).getId();
        for(Integer attendeeId : eventRequest.getAttendees()){
            eventRepository.insertEventAttendee(event,attendeeId);
        }
        return geyById(event);

    }

    @Override
    public Event updateEvent(int id,EventRequest eventRequest) {
        geyById(id);
        eventRepository.deleteEventAttendeeByEventId(id);
        for(int attendeeId : eventRequest.getAttendees()){
            eventRepository.insertEventAttendee(id,attendeeId);
        }
        eventRepository.updateEvent(id,eventRequest);
        return geyById(id);
        //return eventRepository.updateEvent(id,eventRequest);
    }

    @Override
    public void deleteEvent(int id) {
        geyById(id);
        eventRepository.deleteEvent(id);
    }
}
