package todo;

import config.AppContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import user.dao.TodoDAO;
import user.domain.TodoVO;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppContext.class)
@Transactional
public class TodoDaoTests {

    @Autowired
    private TodoDAO todoDAO;

    private TodoVO vo1;
    private TodoVO vo2;
    private TodoVO vo3;

    @BeforeEach
    public void setUp(){

        vo1 = TodoVO.builder().title("한글테스트").dueDate(LocalDate.now()).writer("dignzh").build();
        vo2 = TodoVO.builder().title("한글테스트1").dueDate(LocalDate.now()).writer("dignzh").build();
        vo3 = TodoVO.builder().title("한글테스트2").dueDate(LocalDate.now()).writer("dignzh").build();
    }

    @Test
    public void testAdd(){

        todoDAO.insert(vo1);
        todoDAO.insert(vo2);
        todoDAO.insert(vo3);

    }

    @Test
    public void testDelete(){
        todoDAO.deleteAll();
    }

    @Test
    public void testGetOne(){

        TodoVO vo = todoDAO.getTodo(1);
        System.out.println(vo);
        Assertions.assertNotNull(vo);
    }

    @Test
    public void testgetAll(){
        List<TodoVO> list = todoDAO.getAllByList();

        list.forEach(System.out::println);

    }

    @Test
    public void testUpdate(){

        TodoVO vo = todoDAO.getTodo(1);
        vo1 = TodoVO.builder().tno(vo.getTno()).title("한글Test").dueDate(LocalDate.now()).writer("dignzh").build();
        todoDAO.updateOne(vo1);
    }

    @Test
    public void testSort(){

        todoDAO.sortTno();
    }
}
