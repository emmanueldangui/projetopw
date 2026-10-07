package br.edu.utfpr.pb.pw44s.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "tb_address")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 100)
    private String street;

    @NotNull
    @Column(length = 10)
    private String number;

    @Column(length = 100)
    private String complement;

    @Column(length = 50)
    private String neighborhood;

    @NotNull
    @Column(length = 50)
    private String city;

    @NotNull
    @Column(length = 2)
    private String state;

    @NotNull
    @Column(length = 9)
    private String zipCode;

    @JsonIgnore
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}