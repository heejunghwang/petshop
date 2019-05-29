import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

import example.GraphqlApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GraphqlApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetRestTemplateClientTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getPetList(){
        //given
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");
        String query = "mutation createPetMutation($pet:PetInput) {  createPet(input: $pet) {   type   name    age  }}";
        String variables = "{\"pet\":{\"type\":\"DOG\",\"name\":\"petName\",\"age\":10}}";
        HttpEntity<Object> request = this.forMultipart(query, variables, headers);

        //when
        ResponseEntity<String> response = testRestTemplate.exchange("/graphql", HttpMethod.POST, request, String.class);

        //then
        assertNotNull(response);
        assertTrue(response.getBody().contains("DOG"));
    }

    private HttpEntity<Object> forJson(String json, HttpHeaders headers) {
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new HttpEntity<>(json, headers);
    }

    private HttpEntity<Object> forMultipart(String query, String variables, HttpHeaders headers) {
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        LinkedMultiValueMap<String, Object> values = new LinkedMultiValueMap<>();
        values.add("query", forJson(query, new HttpHeaders()));
        values.add("variables", forJson(variables, new HttpHeaders()));
        return new HttpEntity<>(values, headers);
    }
}
