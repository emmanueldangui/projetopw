package br.edu.utfpr.pb.pw44s.server.service;

import br.edu.utfpr.pb.pw44s.server.model.Address;

import java.util.List;

public interface IAddressService {
    List<Address> findAll();

    Address findById(Long id);

    Address save(Address address);

    void deleteById(Long id);

    boolean exists(Long id);

    long count();

    List<Address> findByUserId(Long userId);

    List<Address> findByAuthenticatedUser();

    Address findByIdAndAuthenticatedUser(Long id);
}