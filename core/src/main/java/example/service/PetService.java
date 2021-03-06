package example.service;

import example.domain.Pet;
import example.domain.PetInput;
import example.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet createPet(PetInput petInput){
        Pet pet = new Pet();
        pet.setAge(petInput.getAge());
        pet.setName(petInput.getName());
        pet.setType(petInput.getType());
        return petRepository.save(pet);
    }

    public List<Pet> findPets() {
        return petRepository.findAll();
    }
}
