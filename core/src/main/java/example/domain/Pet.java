package example.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class Pet {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private Animal type;

    private int age;

}