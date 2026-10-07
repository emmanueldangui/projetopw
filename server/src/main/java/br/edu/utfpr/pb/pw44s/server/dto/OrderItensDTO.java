package br.edu.utfpr.pb.pw44s.server.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItensDTO {

    private Long id;

    @NotNull
    private Long productId;

    private String productName;

    @NotNull
    @Min(1)
    private Integer quantity;

    private BigDecimal price;
}