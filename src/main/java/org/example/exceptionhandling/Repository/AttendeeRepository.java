package org.example.exceptionhandling.Repository;
import org.apache.ibatis.annotations.*;
import org.example.exceptionhandling.Model.Attendee.Attendee;
import org.example.exceptionhandling.Model.Attendee.AttendeeRequest;

import java.util.List;

@Mapper
public interface AttendeeRepository {

    @Select("""
        SELECT * FROM attendees LIMIT #{limit} OFFSET (#{offset}-1) * #{limit}
    """)
    @Results(id = "attendeeMapping" , value = {
            @Result(property = "id", column = "attendee_id"),
            @Result(property = "name", column = "attendee_name"),
    })
    List<Attendee> getAll(@Param("offset") Integer offset,@Param("limit") Integer limit);

    @Select("""
    SELECT  *FROM attendees WHERE attendee_id = #{id}
    """)
    @ResultMap("attendeeMapping")
    Attendee getById(int id);
    @Select("""
        INSERT INTO attendees (attendee_name, email) VALUES (#{attendee.name},#{attendee.email}) RETURNING *
    """)
    @ResultMap("attendeeMapping")
    Attendee insertAttendee(@Param("attendee")AttendeeRequest attendeeRequest);

    @Select("""
        UPDATE attendees SET attendee_name = #{attendee.name}, email = #{attendee.email} WHERE attendee_id = #{id}
        RETURNING *
    """)
    @ResultMap("attendeeMapping")
    Attendee updateAttendee(int id, @Param("attendee") AttendeeRequest attendeeRequest);

    @Select("""
        DELETE FROM attendees WHERE attendee_id = #{id}
    """)
    Attendee deleteAttendee(int id);
    @Select("""
    
    SELECT a.* FROM attendees a INNER JOIN event_attendee ea on a.attendee_id = ea.attendee_id
    WHERE ea.event_id= #{eventId} ORDER BY a.attendee_id;

    """)
    @ResultMap("attendeeMapping")

    List<Attendee> getAttendeeByEventId(Integer eventId);
}
