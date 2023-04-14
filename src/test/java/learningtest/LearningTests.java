package learningtest;

import chap6.Mytest;
import lombok.Builder;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

public class LearningTests {
    @Test
    public void test(){

        LearningCompare compare1 = LearningCompare.builder().age(20).name("joinjun").build();
        LearningCompare compare2 = LearningCompare.builder().age(18).name("joinjun").build();

        System.out.println(compare1.compareTo(compare2));

    }

    @Builder
    static class LearningCompare implements Comparable{

        private int age;

        private String name;

        @Override
        public int compareTo(Object o) {

            LearningCompare compare = (LearningCompare) o;

            int result = this.age-compare.age;

            if(result >0) return 1;
            else if(result==0) return 0;
            else return -1;

        }

    }

    @Test
    public void testXmlParser() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse("src/test/resources/test.xml");



        System.out.println(document.getDocumentElement().getFirstChild().getTextContent());

    }

    @Test
    public void testAnnotation(){
        Reflections reflections = new Reflections("src/main/java/chap6");

        Set set = reflections.getTypesAnnotatedWith(Builder.class);


        System.out.println(set.size());
    }

}


