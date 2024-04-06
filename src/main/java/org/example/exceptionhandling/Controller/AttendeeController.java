package org.example.exceptionhandling.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.exceptionhandling.Exception.AllNotfoundException;
import org.example.exceptionhandling.Model.Attendee.Attendee;
import org.example.exceptionhandling.Model.Attendee.AttendeeRequest;
import org.example.exceptionhandling.Model.Attendee.AttendeeResponse;
import org.example.exceptionhandling.Service.AttendeeService.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
public class AttendeeController {
    private AttendeeService attendeeService;
    public AttendeeController(AttendeeService attendeeService){
        this.attendeeService = attendeeService;
    }
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "1")

            @Positive(message = "Offset must not be 0 or negative!")
            Integer offset,
            @Positive(message = "Limit must not be 0 or negative!")
            @RequestParam(defaultValue = "3") Integer limit
    ){
        List<Attendee> attendees = attendeeService.getALl(offset,limit);
        AttendeeResponse<List<Attendee>> attendeeResponse = AttendeeResponse.<List<Attendee>>builder()
                .message("Get All attendees successfully.")
                .status(HttpStatus.OK)
                .payload(attendees)
                .code(200)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(attendeeResponse);

    }
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        Attendee attendee = attendeeService.getById(id);
        AttendeeResponse<Attendee> attendeeResponse = AttendeeResponse.<Attendee>builder()
                .message("Get attendees successfully.")
                .status(HttpStatus.OK)
                .payload(attendee)
                .code(200)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(attendeeResponse);

    }
    @PostMapping
    public ResponseEntity<?> insertAttendee(@Valid @RequestBody AttendeeRequest attendeeRequest){
        Attendee attendee = attendeeService.insertAttendee(attendeeRequest);
        AttendeeResponse<Attendee> attendeeResponse = AttendeeResponse.<Attendee>builder()
                .message("Created attendees successfully.")
                .status(HttpStatus.OK)
                .payload(attendee)
                .code(200)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(attendeeResponse);

    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateAttendee(@PathVariable int id,@Valid @RequestBody AttendeeRequest attendeeRequest){

        Attendee attendee = attendeeService.updateAttendee(id,attendeeRequest);
        AttendeeResponse<Attendee> attendeeResponse = AttendeeResponse.<Attendee>builder()
                .message("Updated attendees successfully.")
                .status(HttpStatus.OK)
                .payload(attendee)
                .code(200)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(attendeeResponse);

    }
    @Validated
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAttendee( @PathVariable int id){
         try {
             Attendee attendee=attendeeService.deleteAttendee(id);
             AttendeeResponse<Attendee> attendeeResponse = AttendeeResponse.<Attendee>builder()
                     .message("Deleted attendees successfully.")
                     .status(HttpStatus.OK)
                     .payload(null)
                     .code(200)
                     .time(LocalDateTime.now())
                     .build();
             return ResponseEntity.ok(attendeeResponse);
         }catch (Exception error){
             throw new AllNotfoundException("Attendee with id " + id + " does not exist");
         }

    }

}
