package org.example.exceptionhandling.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example.exceptionhandling.Model.VenueModel.Venue;
import org.example.exceptionhandling.Model.VenueModel.VenueRequest;
import org.example.exceptionhandling.Model.VenueModel.VenueResponse;
import org.example.exceptionhandling.Service.VenueService.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {
    private final VenueService venueService;
    public VenueController(VenueService venueService){
        this.venueService = venueService;
    }

    @GetMapping
    public ResponseEntity<?> getAllVenue(
            @RequestParam(defaultValue = "1")

            @Positive(message = "Offset must not be 0 or negative!")
            Integer offset,
            @Positive(message = "Limit must not be 0 or negative!")
            @RequestParam(defaultValue = "3") Integer limit
    ){
        List<Venue> venues = venueService.getAll(offset,limit);
        VenueResponse<List<Venue>> venueResponse = VenueResponse.<List<Venue>>builder()
                .message("All venues has already fetched.")
                .status(HttpStatus.OK)
                .payload(venues)
                .code(200)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(venueResponse);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        Venue venue = venueService.getById(id);
        VenueResponse<Venue> venueResponse = VenueResponse.<Venue>builder()
                .message("A venue with id "+id+" has founded")
                .status(HttpStatus.OK)
                .payload(venue)
                .code(200)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(venueResponse);

    }

    @PostMapping
    public ResponseEntity<?> insertVenue(@Valid @RequestBody VenueRequest venueRequest){
        Venue venue = venueService.insertVenue(venueRequest);
        VenueResponse<Venue> venueResponse = VenueResponse.<Venue>builder()
                .message("Created successfully")
                .status(HttpStatus.CREATED)
                .payload(venue)
                .code(201)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(venueResponse);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateVenue( @PathVariable Integer id,@Valid @RequestBody VenueRequest venueRequest){
        Venue venue = venueService.updateVenue(id,venueRequest);
        VenueResponse<Venue> venueResponse = VenueResponse.<Venue>builder()
                .message("Updated successfully")
                .status(HttpStatus.OK)
                .payload(venue)
                .code(200)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(venueResponse);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteVenue(@PathVariable Integer id){
        venueService.deleteVenue(id);
        VenueResponse<Venue> venueResponse = VenueResponse.<Venue>builder()
                .message("Deleted successfully successfully")
                .status(HttpStatus.OK)
                .payload(null)
                .code(200)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(venueResponse);
    }
}
