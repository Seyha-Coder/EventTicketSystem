package org.example.exceptionhandling.Model.VenueModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenueRequest {
    @NotNull(message = "Name must not be blank")
    @NotBlank(message = "Name must not be blank")
    String name;
    @NotNull(message = "Location must not be blank")
    @NotBlank(message = "Location must not be blank")
    String location;
}
