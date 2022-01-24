package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component//with this, when the Spring context is up, Spring will call the CommandLineRunner and its method run()
public class DataLoader implements CommandLineRunner {//CommandLineRunner is a Spring boot class, utilized to initialize data

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {

        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDog = petTypeService.save(dog);
        PetType cat = new PetType();
        cat.setName("Cat");
        cat = petTypeService.save(cat);


        Owner owner1 = new Owner();



        //owner1.setId(1L);
        owner1.setFirstName("Micheal");
        owner1.setLastName("Weston");
        owner1.setAddress("123 address");
        owner1.setCity("City1");
        owner1.setTelephone("12113213");


        ownerService.save(owner1);

        Pet michealPet = new Pet();
        michealPet.setPetType(savedDog);
        michealPet.setOwner(owner1);
        michealPet.setBirthDate(LocalDate.now());
        michealPet.setName("Pungo");
        owner1.getPets().add(michealPet);


        Owner owner2 = new Owner();
        //owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("1234 address2");
        owner2.setCity("City2");
        owner2.setTelephone("1321321123");
        ownerService.save(owner2);

        Pet fionaCat = new Pet();
        fionaCat.setName("CatPungo");
        fionaCat.setOwner(owner2);
        fionaCat.setBirthDate(LocalDate.now());
        fionaCat.setPetType(cat);
        owner2.getPets().add(fionaCat);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        //vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);


        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);
        System.out.println("Loaded Vets....");



    }
}
