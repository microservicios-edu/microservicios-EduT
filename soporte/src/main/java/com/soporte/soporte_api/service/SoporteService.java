package com.soporte.soporte_api.service;

import com.soporte.soporte_api.model.Soporte;
import com.soporte.soporte_api.repository.SoporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoporteService {

    @Autowired
    private SoporteRepository soporteRepository;

    public List<Soporte> getSoportes() {
        return soporteRepository.findAll();
    }

    public Soporte saveSoporte(Soporte soporte) {
        return soporteRepository.save(soporte);
    }

    public Soporte getSoporteId(int id) {
        return soporteRepository.findById(id).orElse(null);
    }

    public Soporte updateSoporte(Soporte soporte) {
        return soporteRepository.save(soporte);
    }

    public void deleteSoporte(int id) {
        soporteRepository.deleteById(id);
    }
}