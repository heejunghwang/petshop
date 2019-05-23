package example.animal;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Pet {
    @Id
    private long id;

    private String name;

    private Animal type;

    private int age;
}