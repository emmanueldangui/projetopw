package br.edu.utfpr.pb.pw44s.server.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

    private Long id;

    @NotNull
    private String street;

    @NotNull
    private String number;

    private String complement;

    private String neighborhood;

    @NotNull
    private String city;

    @NotNull
    @Size(min = 2, max = 2)
    private String state;

    @NotNull
    private String zipCode;
}