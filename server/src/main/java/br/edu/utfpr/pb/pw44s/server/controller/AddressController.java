package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.AddressDTO;
import br.edu.utfpr.pb.pw44s.server.mapper.AddressMapper;
import br.edu.utfpr.pb.pw44s.server.model.Address;
import br.edu.utfpr.pb.pw44s.server.service.IAddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("addresses")
public class AddressController {

    private final IAddressService addressService;
    private final AddressMapper addressMapper;

    public AddressController(IAddressService addressService, AddressMapper addressMapper) {
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }

    // POST http://localhost:8080/addresses  (precisa de token)
    @PostMapping
    public ResponseEntity<AddressDTO> save(@RequestBody @Valid AddressDTO addressDTO) {
        Address addressSaved = addressService.save(addressMapper.toEntity(addressDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(addressMapper.toDTO(addressSaved));
    }

    // GET http://localhost:8080/addresses  (precisa de token)
    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAll() {
        return ResponseEntity.ok(
                addressService.findByAuthenticatedUser()
                        .stream()
                        .map(addressMapper::toDTO)
                        .collect(Collectors.toList())
        );
    }

    // GET http://localhost:8080/addresses/1  (precisa de token)
    @GetMapping("{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id) {
        Address address = addressService.findByIdAndAuthenticatedUser(id);
        if (address != null) {
            return ResponseEntity.ok(addressMapper.toDTO(address));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}