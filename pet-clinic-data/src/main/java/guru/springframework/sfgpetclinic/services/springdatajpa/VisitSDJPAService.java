package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repository.VisitsRepository;
import guru.springframework.sfgpetclinic.services.VisitService;

import java.util.HashSet;
import java.util.Set;

public class VisitSDJPAService implements VisitService {

    private final VisitsRepository visitsRepository;

    public VisitSDJPAService(VisitsRepository visitsRepository) {
        this.visitsRepository = visitsRepository;
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        visitsRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(Long aLong) {

        return visitsRepository.findById(aLong).orElse(null);
    }

    @Override
    public Visit save(Visit object) {
        return visitsRepository.save(object);
    }

    @Override
    public void delete(Visit object) {
        visitsRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        visitsRepository.deleteById(aLong);
    }
}
