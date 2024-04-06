package org.example.exceptionhandling.Model.VenueModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venue {
    int id;
    String name;
    String location;
}
