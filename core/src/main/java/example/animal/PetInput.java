package example.animal;

import lombok.Data;

@Data
public class PetInput {
    private String name;

    private Animal type;

    private int age;
}
