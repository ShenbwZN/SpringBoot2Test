package jpas.bean;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
//    @GeneratedValue(generator = "UUIDGenerator")
//    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "login_name", nullable = false, length = 32, unique = true)
    private String loginName;
    @Column(name = "username", nullable = false, length = 32)
    private String username;
    private char sex;
    private int age;
}
