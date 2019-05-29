package example.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import example.domain.Pet;
import example.domain.PetInput;
import example.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private PetService petService;

    public Pet createPet(PetInput petInput){
        return petService.createPet(petInput);
    }
}
