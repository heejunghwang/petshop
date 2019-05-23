package example.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import example.animal.Pet;
import example.animal.PetInput;
import example.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hwang on 21/05/2019.
 */
@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private PetService petService;

    public Pet createPet(PetInput petInput){
        return petService.createPet(petInput);
    }
}
