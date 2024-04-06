package org.example.exceptionhandling.Model.VenueModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class VenueResponse <T>{
    String message;
    HttpStatus status;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    T payload;
    int code;
    LocalDateTime time;
}
