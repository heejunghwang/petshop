import example.CoreApp;
import example.domain.Animal;
import example.domain.Pet;
import example.domain.PetInput;
import example.service.PetService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetServiceTest {
    @Autowired
    private PetService petService;

    @Test
    public void testCreatePet(){
        //given
        PetInput petInput = new PetInput();
        petInput.setAge(10);
        petInput.setType(Animal.DOG);
        petInput.setName("name");

        //when
        Pet pet = petService.createPet(petInput);

        //then
        Assert.assertEquals("name", pet.getName());
    }

    @Test
    public void getPets(){
        //given
        PetInput petInput = new PetInput();
        petInput.setAge(10);
        petInput.setType(Animal.DOG);
        petInput.setName("name");
        petService.createPet(petInput);

        //when
        List<Pet> petList = petService.findPets();

        //then
        Assert.assertEquals("name", petList.get(0).getName());
    }

}
