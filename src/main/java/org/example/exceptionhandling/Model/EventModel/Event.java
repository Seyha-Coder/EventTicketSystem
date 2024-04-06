package org.example.exceptionhandling.Model.EventModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.exceptionhandling.Model.Attendee.Attendee;
import org.example.exceptionhandling.Model.VenueModel.Venue;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    int id;
    String name;
    String date;
    Venue venue;
    List<Attendee> attendees;
}
