package service.impl;


import entity.Doctors;
import org.springframework.beans.factory.annotation.Autowired;
import repository.DoctorsRepository;
import service.DoctorsService;

import java.util.List;

public class DoctorsServiceImpl implements DoctorsService {

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Override
    public Doctors read(Integer id) {
        return doctorsRepository.findById(id).get();
    }

    @Override
    public List<Doctors> read() {
        return doctorsRepository.findAll();
    }

    @Override
    public void save(Doctors entity) {
        doctorsRepository.save(entity);
    }

    @Override
    public void delete(Integer id) {
        doctorsRepository.deleteById(id);
    }
}
