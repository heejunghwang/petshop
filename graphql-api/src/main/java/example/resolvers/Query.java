package example.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import example.domain.Pet;
import example.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private PetService petService;

    public List<Pet> pets() {
        return petService.findPets();
    }
}