import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import example.GraphqlApp;
import example.animal.Animal;
import example.animal.PetInput;
import example.service.PetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphqlApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    private PetService petService;

    @Test
    public void testCreatePet() throws IOException{
        //given
        ObjectNode petParam = new ObjectMapper().createObjectNode();
        petParam.put("type", Animal.DOG.toString());
        petParam.put("name", "petName");
        petParam.put("age", 10);

        ObjectNode queryVariables = new ObjectMapper().createObjectNode();
        queryVariables.replace("pet", petParam);

        //when
        GraphQLResponse response = graphQLTestTemplate.perform("create-pet.graphql", queryVariables);

        //then
        assertNotNull(response);
        assertNotNull(response.get("$.data.createPet.name"));
        assertEquals("petName", response.get("$.data.createPet.name"));
    }

    @Test
    public void testGetPets() throws IOException {
        //given
        PetInput petInput = new PetInput();
        petInput.setName("my_pet");
        petInput.setType(Animal.BADGER);
        petInput.setAge(20);
        petService.createPet(petInput);

        //when
        GraphQLResponse response = graphQLTestTemplate.postForResource("post-pets.graphql");

        //then
        assertNotNull(response);
        assertTrue(response.isOk());
        assertEquals("my_pet", response.get("$.data.pets[0].name"));
    }


}
