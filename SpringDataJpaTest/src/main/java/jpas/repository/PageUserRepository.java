package jpas.repository;

import jpas.bean.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PageUserRepository extends PagingAndSortingRepository<User, Integer> {
}
