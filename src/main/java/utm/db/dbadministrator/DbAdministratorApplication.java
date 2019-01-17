package utm.db.dbadministrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import utm.db.dbadministrator.frames.Desktop;
import utm.db.dbadministrator.services.StudentService;

@EntityScan
@Component
@SpringBootApplication
@EnableJpaRepositories
@EnableSpringConfigured
@EnableTransactionManagement
public class DbAdministratorApplication {

    @Autowired
    private StudentService studentService;

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
//        SpringApplication.run(DbAdministratorApplication.class, args);
//        StudentService studentService = new DbAdministratorApplication().studentService;
//        studentService.getStudentRepository().findAll().forEach(System.out::println);
        new SpringApplicationBuilder(DbAdministratorApplication.class)
                .headless(false).run(args);
        new Desktop();
    }
}

