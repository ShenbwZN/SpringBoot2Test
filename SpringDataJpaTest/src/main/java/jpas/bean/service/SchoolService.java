package jpas.service;

import jpas.bean.Clazz;
import jpas.bean.Teacher;
import jpas.repository.ClazzRepository;
import jpas.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SchoolService {

    @Resource
    private TeacherRepository teacherRepository;
    @Resource
    private ClazzRepository clazzRepository;

    @Transactional
    public void saveClazz(Clazz clazz) {
        clazzRepository.save(clazz);
    }

    @Transactional
    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public Clazz findClazzByName(String clazzName) {
        return clazzRepository.findByName(clazzName);
    }

    public Teacher findTeaByName(String teaName) {
        return teacherRepository.findByName(teaName);
    }

    public List<Teacher> findTeachersByClazzName(String clazzName) {
//        return teacherRepository.findTeachersByClazzName(clazzName);
        return teacherRepository.findByClazz_Name(clazzName);
    }

    public List<String> findNameByClazzNameAndSex(String clazzName, char sex) {
        // 1-注解
        // return teacherRepository.findNameByClazzNameAndSex(clazzName, sex);
        // 2-下画线
        List<Teacher> byClazz_nameAndSex = teacherRepository.findByClazz_NameAndSex(clazzName, sex);
        List<String> list = new ArrayList<String>();
        for (Teacher teacher : byClazz_nameAndSex) {
            list.add(teacher.getName());
        }
        return list;
    }

    public String findClazzNameByTeaName(String teaName) {
        // 1-非注解
        /*
        Teacher teacher = teacherRepository.findByName(teaName);
        if (teacher != null) {
            Optional<Clazz> clazz = clazzRepository.findById(teacher.getClazz().getId());
            return clazz.map(Clazz::getName).orElse(null);
        }
        return null;
         */
        // 2-注解
        return teacherRepository.findClazzNameByTeaName(teaName);
    }


    @Transactional
    public int deleteTeacherByTeaName(String teaName) {
        // return teacherRepository.deleteByName(teaName);
        // 2-注解形式
        return teacherRepository.deleteTeacherByTeaName(teaName);
    }

    public List<Map<String, Object>> findNameAndSexByClazzName(String clazzName) {
        return teacherRepository.findNameAndSexByClazzName(clazzName);
    }

}
