package jpas.controller;

import jpas.bean.User;
import jpas.service.PageUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class PageUserController {

    @Resource
    private PageUserService service;

    @GetMapping("/sort")
    public Iterable<User> sortUser() {
        // 指定排序参数对象：根据 username 进行降序查询
        Sort sort = Sort.by(Sort.Direction.DESC, "username");
        return service.findAllSort(sort);
    }

    @GetMapping("/page")
    public List<User> sortPageUser(int pageIndex) {
        // 指定排序参数对象：根据 id 进行降序查询
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        /**
         * 封装分页实体
         * 参数一：表示当前查询的是第几页，默认从0开始；
         * 参数二：每页要查询的数量；
         * 参数三：封装的排序对象。
         */
        int countOfPage = 5;
        PageRequest page = PageRequest.of(pageIndex - 1, countOfPage, sort);
        Page<User> allData = service.findAll(page);
        int totalPages = allData.getTotalPages();
        long totalElements = allData.getTotalElements();
        int number = allData.getNumber() + 1;
        int numberOfElements = allData.getNumberOfElements();
        System.out.println("总页数：" + totalPages);
        System.out.println("总记录数：" + totalElements);
        System.out.println("当前页数：" + number);
        System.out.println("当前页记录数：" + numberOfElements);

        // 查询结果集合
        List<User> users = allData.getContent();
        System.out.println("当前页集合：" + users);
        return users;


    }


}
