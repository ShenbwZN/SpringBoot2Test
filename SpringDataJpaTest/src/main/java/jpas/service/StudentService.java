package jpas.service;

import jpas.bean.Student;
import jpas.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentService {

    @Resource
    private StudentRepository studentRepository;

    @Transactional
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student findByName(String name) {
        return studentRepository.findByName(name);
    }

    public List<Student> findByNameAndAddress(String name, String address) {
        return studentRepository.findByNameAndAddress(name, address);
    }

    public List<Student> findByNameLike(String name) {
        return studentRepository.findByNameLike("%" + name + "%");
    }

    public List<Student> findBySexOrAge(char sex, int age) {
        return studentRepository.findBySexOrAge(sex, age);
    }

    public List<Student> findByAgeBetween(int age1, int age2) {
        return studentRepository.findByAgeBetween(age1, age2);
    }

    public List<Student> findByAgeLessThan(int age) {

        return studentRepository.findByAgeLessThan(age);
    }

    public List<Student> findBySexOrderByAgeDesc(char sex) {
        return studentRepository.findBySexOrderByAgeDesc(sex);
    }

    public List<Student> findByNameContaining(String name) {
        return studentRepository.findByNameContaining(name);
    }

}
