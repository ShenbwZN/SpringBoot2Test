package jpas.controller;

import jpas.bean.Clazz;
import jpas.bean.Teacher;
import jpas.service.SchoolService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SchoolController {

    @Resource
    private SchoolService schoolService;

    @PostMapping("/clazz")
    public String saveClazz(Clazz clazz) {
        String clazzName = clazz.getName().toLowerCase();
        Clazz clazzByName = schoolService.findClazzByName(clazzName);
        if (clazzByName == null) {
            clazz.setName(clazzName);
            schoolService.saveClazz(clazz);
            return "班级保存成功";
        }
        return "班级已经存在";
    }

    @PostMapping("/teacher")
    public String saveTeacher(Teacher teacher) {
        if (schoolService.findTeaByName(teacher.getName()) == null) {
            Clazz byName = schoolService.findClazzByName(teacher.getClazz().getName().toLowerCase());
            if (byName != null) {
                teacher.setClazz(byName);
                schoolService.saveTeacher(teacher);
                return "老师保存成功";
            }
            return "班级选择错误";
        }
        return "该老师已经存在";
    }

    @GetMapping("/teacher/{clazzName}")
    public ArrayList<Map<String, Object>> findTeacherByClazzName(
            @PathVariable("clazzName") String clazzName) {
        List<Teacher> teachersByClazzName = schoolService.findTeachersByClazzName(clazzName);
        ArrayList<Map<String, Object>> results = new ArrayList<>();
        for (Teacher teacher : teachersByClazzName) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", teacher.getName());
            map.put("age", teacher.getAge());
            map.put("sex", teacher.getSex());
            map.put("address", teacher.getAddress());
            map.put("clazz", teacher.getClazz().getName());
            results.add(map);
        }
        return results;
    }

    @GetMapping("/teacher")
    public List<String> findNameByClazzNameAndSex(
            @RequestParam("clazzName") String clazzName,
            @RequestParam("sex") char sex) {
        return schoolService.findNameByClazzNameAndSex(clazzName, sex);
    }

    @GetMapping("/teacherName")
    public String findClazzNameByTeaName(@RequestParam("teaName") String teaName) {
        return schoolService.findClazzNameByTeaName(teaName);
    }

    @DeleteMapping("/teacher")
    public String deleteByName(@RequestParam("teaName") String teaName) {
        if (schoolService.deleteTeacherByTeaName(teaName) == 1) {
            return "删除成功";
        }
        return "不存在";
    }

    @GetMapping("teacher/ns")
    public List<Map<String, Object>> findNameAndSexByClazz_Name(String clazzName) {
//        /*
        List<Teacher> teachersByClazzName = schoolService.findTeachersByClazzName(clazzName);
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (Teacher teacher : teachersByClazzName) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name", teacher.getName());
            map.put("sex", teacher.getSex());
            list.add(map);
        }
        return list;
//         */
//        return schoolService.findNameAndSexByClazzName(clazzName);
    }


}
