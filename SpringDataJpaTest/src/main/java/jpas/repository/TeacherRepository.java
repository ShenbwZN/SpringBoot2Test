package jpas.repository;

import jpas.bean.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {

    // 根据姓名查找
    Teacher findByName(String teaName);

    // 简单条件查询，通过方法名中的"_"下画线来标识。

    /**
     * 根据班级查找
     */
    List<Teacher> findByClazz_Name(String clazzName);

    @Query("select t from Teacher t where t.clazz.name = ?1")
    List<Teacher> findTeachersByClazzName(String clazzName);


    @Query("select new Map(t.name as name,t.sex as sex) from Teacher t where t.clazz.name = ?1")
    List<Map<String, Object>> findNameAndSexByClazzName(String clazzName);

    /**
     * 根据班级和性别查找老师姓名
     */
    List<Teacher> findByClazz_NameAndSex(String clazz, char sex);

    @Query("select t.name from Teacher t where t.clazz.name = :clazzName and t.sex = :sex")
    List<String> findNameByClazzNameAndSex(@Param("clazzName") String clazzName, @Param("sex") char sex);

    /**
     * 查询学生属于那个班级
     */
    @Query("select c.name from Clazz c inner join c.teachers t where t.name = ?1")
    String findClazzNameByTeaName(String teaName);

    /**
     * 删除
     */
    @Modifying
    @Query("delete from Teacher t where t.name = ?1")
    int deleteTeacherByTeaName(String teaName);

    int deleteByName(String teaName);
}
