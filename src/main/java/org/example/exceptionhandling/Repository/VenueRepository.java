package org.example.exceptionhandling.Repository;
import org.apache.ibatis.annotations.*;
import org.example.exceptionhandling.Model.VenueModel.Venue;
import org.example.exceptionhandling.Model.VenueModel.VenueRequest;
import java.util.List;

@Mapper
public interface VenueRepository {

    @Select("""
        SELECT *FROM venues LIMIT #{limit} OFFSET (#{offset}-1) * #{limit}
    """)
    @Results(id="venueMapping",value = {
            @Result(property = "id",column = "venue_id"),
            @Result(property = "name",column = "venue_name")
    })
    List<Venue> getAll(@Param("offset") Integer offset,@Param("limit") Integer limit);

    @ResultMap("venueMapping")
    @Select("""
    SELECT *FROM venues WHERE venue_id = #{id}
    """)

    Venue getById(int id);
    @Select("""
        INSERT INTO venues (venue_name, location) VALUES (#{venue.name},#{venue.location}) RETURNING *
    """)
    @ResultMap("venueMapping")
    Venue insertVenue(@Param("venue") VenueRequest venueRequest);
    @Select("""
        UPDATE venues SET venue_name = #{venue.name}, location = #{venue.location} WHERE venue_id = #{id}
         RETURNING *
    """)
    @ResultMap("venueMapping")
    Venue updateVenue(Integer id, @Param("venue") VenueRequest venueRequest);
    @Select("""
        DELETE FROM venues WHERE venue_id = #{id}
    """)
    void deleteVenue(Integer id);
}
