package org.example.exceptionhandling.Service.AttendeeService;

import org.example.exceptionhandling.Model.Attendee.Attendee;
import org.example.exceptionhandling.Model.Attendee.AttendeeRequest;

import java.util.List;

public interface AttendeeService {
    List<Attendee> getALl(Integer offset, Integer limit);
    Attendee getById(int id);
    Attendee insertAttendee(AttendeeRequest attendeeRequest);
    Attendee updateAttendee(int id,AttendeeRequest attendeeRequest);
    Attendee deleteAttendee(int id);
}
