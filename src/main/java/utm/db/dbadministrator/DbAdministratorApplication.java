package utm.db.dbadministrator;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import utm.db.dbadministrator.frames.Desktop;

import javax.swing.*;

@EntityScan
@SpringBootApplication
@EnableJpaRepositories
@EnableSpringConfigured
@EnableTransactionManagement
public class DbAdministratorApplication implements CommandLineRunner {


    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(DbAdministratorApplication.class)
                .headless(false).bannerMode(Banner.Mode.OFF).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            new Desktop();
        });
    }
}

