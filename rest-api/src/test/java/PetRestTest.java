import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import example.RestApiApp;
import example.domain.Animal;
import example.domain.Pet;
import example.domain.PetInput;
import example.service.PetService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApiApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetRestTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private PetService petService;

    @Test
    public void getPetList(){
        //given
        Pet pet = new Pet();
        pet.setName("my_pet");
        pet.setType(Animal.BADGER);
        pet.setAge(20);
        List<Pet> petExpectedList = new ArrayList<>();
        petExpectedList.add(pet);
        when(petService.findPets()).thenReturn(petExpectedList);

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

        Pet expectedPet = new Pet();
        expectedPet.setName("my_pet");
        expectedPet.setType(Animal.BADGER);
        expectedPet.setAge(20);

        when(petService.createPet(any(PetInput.class))).thenReturn(expectedPet);

        //when
        ResponseEntity<String> response = testRestTemplate.postForEntity("/add/pet", petInput, String.class);

        //then
        Gson gson = new Gson();
        Pet pet = gson.fromJson(response.getBody(), new TypeToken<Pet>(){}.getType());
        assertEquals("my_pet", pet.getName());
    }
}
