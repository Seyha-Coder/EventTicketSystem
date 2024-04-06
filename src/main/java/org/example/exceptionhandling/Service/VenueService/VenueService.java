package org.example.exceptionhandling.Service.VenueService;

import org.example.exceptionhandling.Model.VenueModel.Venue;
import org.example.exceptionhandling.Model.VenueModel.VenueRequest;

import java.util.List;

public interface VenueService {
    List<Venue> getAll(Integer offset, Integer limit);
    Venue getById(int id);
    Venue insertVenue(VenueRequest venueRequest);
    Venue updateVenue(Integer id ,VenueRequest venueRequest);
    void deleteVenue(Integer id);
}
