package org.example.exceptionhandling.Service.AttendeeService;

import org.example.exceptionhandling.Exception.AllNotfoundException;
import org.example.exceptionhandling.Model.Attendee.Attendee;
import org.example.exceptionhandling.Model.Attendee.AttendeeRequest;
import org.example.exceptionhandling.Repository.AttendeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeServiceImpl implements  AttendeeService{
    private AttendeeRepository attendeeRepository;
    public AttendeeServiceImpl(AttendeeRepository attendeeRepository){
        this.attendeeRepository = attendeeRepository;
    }
    @Override
    public List<Attendee> getALl(Integer offset, Integer limit) {
        return attendeeRepository.getAll(offset,limit);
    }

    @Override
    public Attendee getById(int id) {
        if(attendeeRepository.getById(id) == null){
            throw new AllNotfoundException("Attendee with id"+ id +" does not exist");
        }
        return attendeeRepository.getById(id);
    }

    @Override
    public Attendee insertAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepository.insertAttendee(attendeeRequest);
    }

    @Override
    public Attendee updateAttendee(int id,AttendeeRequest attendeeRequest) {
        getById(id);
        return attendeeRepository.updateAttendee(id,attendeeRequest);
    }

    @Override
    public Attendee deleteAttendee(int id) {
        getById(id);
        return attendeeRepository.deleteAttendee(id);
    }
}
