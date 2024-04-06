package org.example.exceptionhandling.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example.exceptionhandling.Model.EventModel.Event;
import org.example.exceptionhandling.Model.EventModel.EventRequest;
import org.example.exceptionhandling.Model.EventModel.EventResponse;
import org.example.exceptionhandling.Service.EventService.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService)   {
        this.eventService =eventService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "1")

            @Positive(message = "Offset must not be 0 or negative!")
            Integer offset,
            @Positive(message = "Limit must not be 0 or negative!")
            @RequestParam(defaultValue = "3") Integer limit
    ){
        List<Event> events = eventService.getAll(offset,limit);
        EventResponse<List<Event>> eventResponse = EventResponse.<List<Event>>builder()
                .message("Get all events successfully.")
                .status(HttpStatus.OK)
                .paylod(events)
                .code(200)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(eventResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        Event event = eventService.geyById(id);
        EventResponse<Event> eventResponse = EventResponse.<Event>builder()
                .message("Get "+id+" events successfully.")
                .status(HttpStatus.OK)
                .paylod(eventService.geyById(id))
                .code(200)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(eventResponse);
    }
    @PostMapping
    public ResponseEntity<?> insertEvent(@Valid @RequestBody EventRequest eventRequest){
        Event event = eventService.insertEvent(eventRequest);
        EventResponse<Event> eventResponse = EventResponse.<Event>builder()
                .message("Insert events successfully.")
                .status(HttpStatus.CREATED)
                .paylod(event)
                .code(201)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(eventResponse);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateEvent(@PathVariable int id ,@Valid @RequestBody EventRequest eventRequest){
        Event event = eventService.updateEvent(id,eventRequest);
        EventResponse<Event> eventResponse = EventResponse.<Event>builder()
                .message("Update events successfully.")
                .status(HttpStatus.OK)
                .paylod(event)
                .code(200)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(eventResponse);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable int id ){
        eventService.deleteEvent(id);
        EventResponse<Event> eventResponse = EventResponse.<Event>builder()
                .message("Deleted events successfully.")
                .status(HttpStatus.OK)
                .paylod(null)
                .code(200)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(eventResponse);
    }


}
