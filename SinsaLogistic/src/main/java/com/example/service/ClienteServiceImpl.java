package com.example.service;

import com.example.domain.Cliente;
import com.example.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getCliente(Cliente cliente) {
        return clienteRepository.findById(cliente.getIdCliente()).orElse(null);
    }

    @Override
    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public void delete(Cliente cliente) {
        clienteRepository.delete(cliente);
    }
}
