package org.example.exceptionhandling.Service.EventService;

import org.example.exceptionhandling.Exception.AllNotfoundException;
import org.example.exceptionhandling.Model.EventModel.Event;
import org.example.exceptionhandling.Model.EventModel.EventRequest;
import org.example.exceptionhandling.Repository.EventRepository;
import org.example.exceptionhandling.Repository.VenueRepository;
import org.example.exceptionhandling.Service.AttendeeService.AttendeeService;
import org.example.exceptionhandling.Service.VenueService.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;
    private final VenueService venueService;
    private final AttendeeService attendeeService;

    public EventServiceImpl(EventRepository eventRepository, VenueService venueService, AttendeeService attendeeService){
        this.eventRepository = eventRepository;
        this.venueService = venueService;
        this.attendeeService = attendeeService;
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
        venueService.getById(eventRequest.getVenueId());
        Integer event = eventRepository.insertEvent(eventRequest).getId();
        for(Integer attendeeId : eventRequest.getAttendees()){
            attendeeService.getById(attendeeId);
            eventRepository.insertEventAttendee(event,attendeeId);
        }
        return geyById(event);

    }

    @Override
    public Event updateEvent(int id,EventRequest eventRequest) {
        geyById(id);
        venueService.getById(eventRequest.getVenueId());
        eventRepository.deleteEventAttendeeByEventId(id);
        for(int attendeeId : eventRequest.getAttendees()){
            attendeeService.getById(attendeeId);
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
