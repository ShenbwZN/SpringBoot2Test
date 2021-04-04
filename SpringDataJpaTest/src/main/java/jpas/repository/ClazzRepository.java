package jpas.repository;

import jpas.bean.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClazzRepository extends JpaRepository<Clazz, String> {

    Clazz findByName(String clazzName);
}
