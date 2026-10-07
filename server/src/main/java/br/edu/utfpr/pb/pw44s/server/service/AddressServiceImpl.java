package br.edu.utfpr.pb.pw44s.server.service.impl;

import br.edu.utfpr.pb.pw44s.server.model.Address;
import br.edu.utfpr.pb.pw44s.server.repository.AddressRepository;
import br.edu.utfpr.pb.pw44s.server.service.AuthService;
import br.edu.utfpr.pb.pw44s.server.service.IAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    private final AddressRepository addressRepository;
    private final AuthService authService;

    public AddressServiceImpl(AddressRepository addressRepository, AuthService authService) {
        this.addressRepository = addressRepository;
        this.authService = authService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Address findById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public Address save(Address address) {
        // o dono do endereço é sempre o usuário do token
        address.setUser(authService.getAuthenticatedUser());
        return addressRepository.save(address);
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return addressRepository.existsById(id);
    }

    @Override
    public long count() {
        return addressRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> findByUserId(Long userId) {
        return addressRepository.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Address> findByAuthenticatedUser() {
        return addressRepository.findByUserId(authService.getAuthenticatedUser().getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Address findByIdAndAuthenticatedUser(Long id) {
        return addressRepository
                .findByIdAndUserId(id, authService.getAuthenticatedUser().getId())
                .orElse(null);
    }
}