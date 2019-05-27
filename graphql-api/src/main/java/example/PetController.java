package example;

import example.animal.Pet;
import example.animal.PetInput;
import example.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/pets")
    public List<Pet> petList(){
        return petService.findPets();
    }

    @PostMapping("/add/pet")
    public Pet test(@RequestBody PetInput petInput){
        return petService.createPet(petInput);
    }
}
