package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }


    @Override
    public Owner save(Owner object) {
        if(object == null){
            return null;
        }
        persistencePets(object);
        return super.save(object);
    }

    private void persistencePets(Owner owner){

        if(owner.getPets() != null){
            owner.getPets().forEach(this::persistencePet);
        }

    }

    private void persistencePet(Pet pet){

        if(pet.getPetType() != null){
            createPetTypeIfNeeded(pet);
        } else {
            throw new RuntimeException("Pet type is required");
        }
        savePetIfNeeded(pet);
    }

    private void createPetTypeIfNeeded(Pet pet) {
        if (pet.getPetType().getId() == null) {
            pet.setPetType(petTypeService.save(pet.getPetType()));
        }
    }


    private void savePetIfNeeded(Pet pet) {
        if (pet.getId() == null) {
            pet.setId(petService.save(pet).getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
