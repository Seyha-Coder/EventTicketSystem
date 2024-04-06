package org.example.exceptionhandling.Service.EventService;

import org.example.exceptionhandling.Model.EventModel.Event;
import org.example.exceptionhandling.Model.EventModel.EventRequest;

import java.util.List;

public interface EventService {
    List<Event> getAll(Integer offset, Integer limit);
    Event geyById(int id);
    Event insertEvent(EventRequest eventRequest);
    Event updateEvent(int id,EventRequest eventRequest);
    void deleteEvent(int id);
}
