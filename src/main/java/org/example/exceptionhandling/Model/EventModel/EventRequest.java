package org.example.exceptionhandling.Model.EventModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.exceptionhandling.Model.VenueModel.Venue;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class EventRequest {
    @NotBlank(message = "Name must be not blank or null!")
    @NotNull(message = "Name must not be blank or null!")
    String name;
//    @NotNull(message = "Date must not be blank or null!")
//    @NotBlank(message = "Date must be not blank or null!")
    Date date;
//    @NotNull(message = "VenueId must not be blank or null!")
//    @NotBlank(message = "VenueId must be not blank or null!")
    int venueId;
//    @NotNull(message = "AttendeeId must not be blank or null!")
//    @NotBlank(message = "AttendeeId must be not blank or null!")
    List<Integer> attendees;
}
