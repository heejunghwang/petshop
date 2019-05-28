import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import example.GraphqlApp;
import example.domain.Animal;
import example.domain.Pet;
import example.domain.PetInput;
import example.resolvers.Mutation;
import example.resolvers.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphqlApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetGraphQLTemplateClientTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    private Query query;

    @MockBean
    private Mutation mutation;

    @Test
    public void testCreatePet() throws IOException{
        //given
        ObjectNode petParam = new ObjectMapper().createObjectNode();
        petParam.put("type", Animal.DOG.toString());
        petParam.put("name", "my_pet");
        petParam.put("age", 10);

        ObjectNode queryVariables = new ObjectMapper().createObjectNode();
        queryVariables.replace("pet", petParam);

        Pet pet = new Pet();
        pet.setId(1);
        pet.setAge(20);
        pet.setName("my_pet");
        pet.setType(Animal.DOG);
        when(mutation.createPet(any(PetInput.class))).thenReturn(pet);

        //when
        GraphQLResponse response = graphQLTestTemplate.perform("graphql/create-pet.graphql", queryVariables);

        //then
        assertNotNull(response);
        assertNotNull(response.get("$.data.createPet.name"));
        assertEquals("my_pet", response.get("$.data.createPet.name"));
    }

    @Test
    public void testGetPets() throws IOException {
        //given
        Pet pet = new Pet();
        pet.setId(1);
        pet.setAge(20);
        pet.setName("my_pet");
        pet.setType(Animal.DOG);
        List<Pet> petList = new ArrayList<>();
        petList.add(pet);
        when(query.pets()).thenReturn(petList);

        //when
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/post-pets.graphql");

        //then
        assertNotNull(response);
        assertTrue(response.isOk());
        assertEquals("my_pet", response.get("$.data.pets[0].name"));
    }


}
