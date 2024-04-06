package org.example.exceptionhandling.Repository;

import org.apache.ibatis.annotations.*;
import org.example.exceptionhandling.Model.EventModel.Event;
import org.example.exceptionhandling.Model.EventModel.EventRequest;

import java.util.List;

@Mapper
public interface EventRepository {

    @Select("""
    SELECT e.* FROM events e INNER JOIN event_attendee ea on e.event_id = ea.event_id
     LIMIT #{limit} OFFSET (#{offset}-1) * #{limit}
    """)
    @Results(id = "eventMapping", value = {
            @Result(property = "id", column = "event_id"),
            @Result(property = "name", column = "event_name"),
            @Result(property = "date", column = "event_date"),
            @Result(property = "venue", column = "venue_id",
                    one = @One(select = "org.example.exceptionhandling.Repository.VenueRepository.getById")

            ),
            @Result(property = "attendees",column = "event_id",
            many = @Many(select = "org.example.exceptionhandling.Repository.AttendeeRepository.getById")
            )

    })
    List<Event> getAll(@Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("""
                SELECT e.* FROM events e INNER JOIN event_attendee ea on e.event_id = ea.event_id 
                WHERE e.event_id = #{id} LIMIT 1
            """)
    @ResultMap("eventMapping")
    Event getById(int id);

    @Select("""
                INSERT INTO events (event_name, event_date, venue_id) VALUES (#{event.name},#{event.date},#{event.venueId}) RETURNING *
            """)
    @ResultMap("eventMapping")
    Event insertEvent(@Param("event") EventRequest eventRequest);

    @Select("""
    INSERT INTO event_attendee(event_id, attendee_id)  VALUES (#{eventId},#{attendeeId}) 
    """)
    void insertEventAttendee(int eventId,int attendeeId);

    @Select("""
                UPDATE events SET event_name = #{event.name}, event_date = #{event.date}, venue_id = #{event.venueId} WHERE event_id = #{id}
                RETURNING  *
            """)
    @ResultMap("eventMapping")
    Event updateEvent(int id, @Param("event") EventRequest eventRequest);
    @Delete("""
        DELETE FROM event_attendee WHERE event_id = #{id};
    """)
    void deleteEventAttendeeByEventId(int id);

    @Select("""
                DELETE FROM events WHERE event_id = #{id}
            """)
    void deleteEvent(int id);
}
