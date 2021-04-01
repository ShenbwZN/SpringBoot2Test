package jpas.bean;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    private String id;

    @Column(unique = true, length = 32)
    private String name;
    private String address;
    private int age;
    private char sex;
}
