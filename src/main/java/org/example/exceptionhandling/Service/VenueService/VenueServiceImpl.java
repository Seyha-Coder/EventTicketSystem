package org.example.exceptionhandling.Service.VenueService;

import org.example.exceptionhandling.Exception.AllNotfoundException;
import org.example.exceptionhandling.Model.VenueModel.Venue;
import org.example.exceptionhandling.Model.VenueModel.VenueRequest;
import org.example.exceptionhandling.Repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService{
    private VenueRepository venueRepository;
    public VenueServiceImpl(VenueRepository venueRepository){
        this.venueRepository=venueRepository;
    }

    @Override
    public List<Venue> getAll(Integer offset, Integer limit) {
        return venueRepository.getAll(offset,limit);
    }

    @Override
    public Venue getById(int id) {
        if(venueRepository.getById(id) == null){
            throw new AllNotfoundException("Venue with id "+ id+ " does not exist");
        }
        return venueRepository.getById(id);
    }

    @Override
    public Venue insertVenue(VenueRequest venueRequest) {
        return venueRepository.insertVenue(venueRequest);
    }

    @Override
    public Venue updateVenue(Integer id , VenueRequest venueRequest) {
        getById(id);
        return venueRepository.updateVenue(id,venueRequest);
    }

    @Override
    public void deleteVenue(Integer id) {
        getById(id);
        venueRepository.deleteVenue(id);
    }
}
