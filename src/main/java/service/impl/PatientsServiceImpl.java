package service.impl;

import entity.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import repository.PatientsRepository;
import service.PatientsService;

import java.util.List;

public class PatientsServiceImpl implements PatientsService {
    @Autowired
    private PatientsRepository patientsRepository;

    @Override
    public Patients read(Integer id) {
        return patientsRepository.findById(id).get();
    }

    @Override
    public List<Patients> read() {
        return patientsRepository.findAll();
    }

    @Override
    public void save(Patients entity) {
        patientsRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        patientsRepository.deleteById(id);
    }
}
