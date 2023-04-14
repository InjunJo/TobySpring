package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import user.sqlService.*;

import javax.sql.DataSource;

@Configuration @ComponentScan(basePackages = "user")
public class SqlServiceContext {

    @Bean
    public SqlReader sqlReader(){

        return new OxmSqlReader(unmarshaller());
    }


    @Bean
    public SqlRegistry sqlRegistry(){
        EmbeddedDbSqlRegistry embeddedDbSqlRegistry = new EmbeddedDbSqlRegistry();

        embeddedDbSqlRegistry.setDataSource(embeddedDatabase());

        return embeddedDbSqlRegistry;
    }


    @Bean
    public Resource resource(){
        Resource resource = new ClassPathResource("sqlmap.xml");

        return resource;
    }



    @Bean
    public Unmarshaller unmarshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("user.xmlBinding");

        return jaxb2Marshaller;
    }

    @Bean
    public SqlService sqlService(){
        OxmSqlService oxmSqlService = new OxmSqlService(unmarshaller(),sqlRegistry());

        return oxmSqlService;
    }

    @Bean
    public DataSource embeddedDatabase() {
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .setName("embeddedDatabase")
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("/META-INF/embeddedDb/sqlRegistrySchema.sql")
                .addScript("/META-INF/embeddedDb/userData.sql")
                .build();

        return db;

    }



}
