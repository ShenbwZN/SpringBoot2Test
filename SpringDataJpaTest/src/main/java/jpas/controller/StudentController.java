package jpas.controller;

import jpas.bean.Student;
import jpas.service.StudentService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/stu")
public class StudentController {
    @Resource
    private StudentService studentService;

    // 添加
    @PostMapping
    public Student save(Student student) {
        Student byName = studentService.findByName(student.getName());
        // name唯一，找不到则添加，不判断有异常
        if (byName == null) {
            return studentService.save(student);
        }
        return null;
    }

    // 更新
    @PutMapping
    public Student update(Student student) {
        Student byName = studentService.findByName(student.getName());
        // 找到则修改
        if (byName != null) {
//            byName.setName(student.getName());
            byName.setAge(student.getAge());
            byName.setSex(student.getSex());
            byName.setAddress(student.getAddress());
            return studentService.save(byName);
        }
        return null;
    }

    @GetMapping("/{name}")
    public Student findByName(@PathVariable("name") String name) {
        return studentService.findByName(name);
    }

    @GetMapping("/and")
    public List<Student> findByNameAndAddress(@RequestParam("name") String name,
                                              @RequestParam("address") String address) {
        return studentService.findByNameAndAddress(name, address);
    }

    @GetMapping("/like/{name}")
    public List<Student> findByNameLike(@PathVariable("name") String name) {
        List<Student> byNameLike = studentService.findByNameLike(name);
        if (byNameLike.size() > 0) {
            return byNameLike;
        }
        return null;
    }

    @GetMapping("/or")
    public List<Student> findBySexOrAge(char sex, int age) {
        List<Student> bySexOrAge = studentService.findBySexOrAge(sex, age);
        if (bySexOrAge.size() > 0) {
            return bySexOrAge;
        }
        return null;
    }

    @GetMapping("/between")
    public List<Student> findByAgeBetween(int age1, int age2) {
        return studentService.findByAgeBetween(age1, age2);
    }

    @GetMapping("/lessThan")
    public List<Student> findByAgeLessThan(int age) {
        return studentService.findByAgeLessThan(age);
    }

    @GetMapping("/order")
    public List<Student> findBySexOrderByAgeDesc(char sex) {
        return studentService.findBySexOrderByAgeDesc(sex);
    }

    @GetMapping("/contain")
    public List<Student> findByNameContaining(String name) {
        return studentService.findByNameContaining(name);
    }

}
