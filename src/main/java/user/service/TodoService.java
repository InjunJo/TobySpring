package user.service;
/*
import org.modelmapper.ModelMapper;
import todo.dao.TodoDAO;
import todo.domain.TodoVO;
import todo.dto.TodoDTO;
import todo.util.MapperUtil;

import java.util.ArrayList;
import java.util.List;

public enum TodoService {
    INSTANCE;

    private TodoDAO dao;
    private ModelMapper mapper;

    TodoService(){
        dao = new TodoDAO();
        mapper = MapperUtil.INSTANCE.getMapper();
    }

    public void register(TodoDTO dto){
        TodoVO vo = mapper.sqlMap(dto,TodoVO.class);
        dao.insert(vo);

    }

    public List<TodoDTO> getList(){
        List<TodoDTO> list = new ArrayList<>();
        List<TodoVO> voList = dao.getList();

        for(TodoVO vo : voList){
            TodoDTO dto = mapper.sqlMap(vo,TodoDTO.class);
            list.add(dto);
        }

        return list;
    }

    public List<TodoDTO> getList(String id){
        List<TodoDTO> list = new ArrayList<>();
        List<TodoVO> voList = dao.getList(id);

        for(TodoVO vo : voList){
            TodoDTO dto = mapper.sqlMap(vo,TodoDTO.class);
            list.add(dto);
        }

        return list;
    }

    public TodoDTO getOne(int tno){
        TodoVO vo = dao.selectOne(tno);

        TodoDTO dto = mapper.sqlMap(vo,TodoDTO.class);

        return dto;
    }

    public void update(TodoDTO dto){
        TodoVO vo = mapper.sqlMap(dto,TodoVO.class);
        dao.updateOne(vo);

    }

    public void delete(int tno){
        dao.deleteOne(tno);

    }

    public void sortTno(){
        dao.sortTno();

    }


}*/
