import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import example.RestApiApp;
import example.animal.Animal;
import example.animal.Pet;
import example.animal.PetInput;
import example.service.PetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApiApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetRestTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PetService petService;

    @Test
    public void getPetList(){
        //given
        PetInput petInput = new PetInput();
        petInput.setName("my_pet");
        petInput.setType(Animal.BADGER);
        petInput.setAge(20);
        petService.createPet(petInput);

        //when
        ResponseEntity<String> response = testRestTemplate.getForEntity("/pets", String.class);

        //then
        Gson gson = new Gson();
        List<Pet> petList = gson.fromJson(response.getBody(), new TypeToken<List<Pet>>(){}.getType());

        assertEquals("my_pet", petList.get(0).getName());
    }

    @Test
    public void createPet(){
        //given
        PetInput petInput = new PetInput();
        petInput.setName("my_pet");
        petInput.setType(Animal.BADGER);
        petInput.setAge(20);
        petService.createPet(petInput);

        //when
        ResponseEntity<String> response = testRestTemplate.postForEntity("/add/pet", petInput, String.class);

        //then
        Gson gson = new Gson();
        Pet pet = gson.fromJson(response.getBody(), new TypeToken<Pet>(){}.getType());

        assertEquals("my_pet", pet.getName());
    }
}
