package org.example.exceptionhandling.Model.Attendee;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AttendeeRequest {

    @NotNull(message = "Name must not be null!")
    @NotBlank(message = "Name must not be blank!")
    String name;
    @Email(message = "Invalid Email address!")
    String email;
}
