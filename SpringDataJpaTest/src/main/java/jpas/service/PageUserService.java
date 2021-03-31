package jpas.service;

import jpas.bean.User;
import jpas.repository.PageUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PageUserService {

    @Resource
    private PageUserRepository repository;

    public Iterable<User> findAllSort(Sort sort){
        return repository.findAll(sort);
    }

    public Page<User> findAll(Pageable page){
        return repository.findAll(page);
    }
}
