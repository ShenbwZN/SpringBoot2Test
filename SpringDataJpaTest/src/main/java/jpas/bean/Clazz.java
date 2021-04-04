package jpas.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "clazz")
@NoArgsConstructor
@AllArgsConstructor
public class Clazz {

    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    private String id;

    @Column(unique = true, length = 32)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Teacher.class, mappedBy = "clazz")
    private Set<Teacher> teachers = new HashSet<>();

    @Override
    public String toString() {
        return "Clazz{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
