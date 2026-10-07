package br.edu.utfpr.pb.pw44s.server.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Long id;

    private LocalDateTime dateTime;

    @NotNull
    @Pattern(regexp = "PIX|CARTAO_CREDITO|BOLETO", message = "Use PIX, CARTAO_CREDITO ou BOLETO")
    private String paymentMethod;

    @NotNull
    private Long addressId;

    private AddressDTO deliveryAddress;

    @NotEmpty
    @Valid
    private List<OrderItensDTO> orderItems;

    private BigDecimal total;
}