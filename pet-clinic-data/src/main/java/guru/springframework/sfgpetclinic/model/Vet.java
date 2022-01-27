package guru.springframework.sfgpetclinic.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Vet extends Person{

    @ManyToMany(fetch = FetchType.EAGER)//this means that when vet data will be fetched, will also be fetched specialties data from db
    @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_ud"), inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialities = new HashSet<>();// it's for NullPointerException

    public Vet(Long id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    public Vet() {

    }

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }
}
