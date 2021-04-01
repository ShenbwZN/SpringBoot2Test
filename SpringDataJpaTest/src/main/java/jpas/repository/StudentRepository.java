package jpas.repository;

import jpas.bean.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    /**
     * 通过姓名查询
     */
    Student findByName(String name);

    /**
     * And-通过名字和地址查询
     */
    List<Student> findByNameAndAddress(String name, String address);

    /**
     * Like-姓名模糊查询
     */
    List<Student> findByNameLike(String name);

    /**
     * Or-年龄或者性别
     */
    List<Student> findBySexOrAge(char sex, int age);

    /**
     * Between
     */
    List<Student> findByAgeBetween(int age1, int age2);

    /**
     * LessThan
     */
    List<Student> findByAgeLessThan(int age);

    /**
     * OrderBy
     */
    List<Student> findBySexOrderByAgeDesc(char sex);

    /**
     * 模糊查询
     * Containing
     * StartingWith
     * EndingWidth
     */
    List<Student> findByNameContaining(String name);


}
