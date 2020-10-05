package ru.javaweb.tracker;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javaweb.tracker.model.Patient;
import ru.javaweb.tracker.model.Role;
import ru.javaweb.tracker.model.User;
import ru.javaweb.tracker.repository.UserRepository;
import ru.javaweb.tracker.service.UserService;
import ru.javaweb.tracker.web.patient.PatientRestController;
import ru.javaweb.tracker.web.user.AdminRestController;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User("firstName", "middleName", "lastName", "user"));

            PatientRestController patientRestController  = appCtx.getBean(PatientRestController.class);
            patientRestController.create(new Patient("first", "middle", "last", 123, 1));
        }
    }
}
