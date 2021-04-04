package jpas.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    private String id;

    @Column(unique = true, length = 32)
    private String name;
    private String address;
    private int age;
    private char sex;

    // 关联
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Clazz.class)
    @JoinColumn(name = "clazz_id", referencedColumnName = "id")
    private Clazz clazz;

}
