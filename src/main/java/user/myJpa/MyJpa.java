package user.myJpa;

import config.AppContext;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import user.domain.TodoVO;
import user.domain.User;
import user.sqlService.SqlService;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;


@Component @Data
public class MyJpa<T> implements InitializingBean {

    private final SqlService sqlService;

    private Map<String, Object> map = new HashMap<>();

    private List<String> entityName = new ArrayList<>();

    private Class targetEntity;

    private String tableName = "users";

    private final JdbcTemplate jdbcTemplate;

    private List<Object> entityAttribute = new ArrayList<>();

    private Map<String,Type> entityType = new HashMap<>();

    public void persist(TodoVO vo) throws Exception {

        logic(vo);

        String str = "insert into "+tableName+" "+getAttributeName()+" values "+getValues();

        jdbcTemplate.update(str,map);

    }


    public String getValues(){
        String str = "";
        str = nameLogic2(map.values());


        return str;

    }


    public String getAttributeName(){

        Set<String> set = map.keySet();

        String str = nameLogic3(set);

        return str;
    }


    public String nameLogic3(Set set){
        String str = "";

        Object[] arr = set.toArray();

        for(int i=0; i<arr.length; i++){

            if(i== arr.length-1){
                str += arr[i]+")";
            } else if (i ==0) {
                str += "("+arr[i]+",";
            }else str += arr[i]+",";

        }

        return str;


    }

    public String nameLogic2(Collection values){

        String str = "";

        Object[] arr = values.toArray();

        for(int i=0; i<arr.length; i++){

            if(i== values.size()-1){
                str += arr[i]+")";
            } else if (i ==0) {
                str += "("+arr[i]+",";
            }else str += arr[i]+",";

        }

        return str;
    }

    public String nameLogic(){

        String str = "";

        for(int i=0; i<entityName.size(); i++){

            if(i== entityName.size()-1){
                str += entityName.get(entityName.size()-1)+")";
            } else if (i ==0) {
                str += "("+entityName.get(i)+",";
            }else str += entityName.get(i)+",";

        }

        return str;
    }


    public void getAttribute(Object vo) throws Exception {

        Method[] methods= vo.getClass().getMethods();

        for(Method m : methods){
            String methodName = m.getName();
            if(m.getName().startsWith("get") && !methodName.equals("getClass")){

                String attributeName = m.getName().substring(3).toLowerCase();
                System.out.println("attributeName "+attributeName);
                System.out.println("find "+findType(attributeName));
                System.out.println("cast : "+findType(attributeName).getClass().cast(m.invoke(vo)));

                map.put(attributeName,m.invoke(vo));

            }

        }
    }

    public Type findType(String key){

        Type type = entityType.get(key);

        return type;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public void logic(Object entity) throws Exception {

        Annotation annotation = entity.getClass().getAnnotation(MyEntity.class);

        if(annotation != null){

            Field[] fields = entity.getClass().getDeclaredFields();
            Method[] methods = entity.getClass().getDeclaredMethods();

            for(Field f: fields){
                entityName.add(f.getName());
                entityType.put(f.getName(),f.getType());

                System.out.println("f.getName() : "+f.getName());
            }

            getAttribute(entity);
        }

        System.out.println(entityName);
        System.out.println(entityType);

    }

    public static void main(String[] args) throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);

        MyJpa myJpa= context.getBean("myJpa",MyJpa.class);
        TodoVO vo = TodoVO.builder().tno(5).title("한글테스트2").dueDate(LocalDate.now()).writer("dignzh").build();

        /*myJpa.persist(vo);*/
        myJpa.logic(vo);
        myJpa.getAttribute(vo);




    }
}
