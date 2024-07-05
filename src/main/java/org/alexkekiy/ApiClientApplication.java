package org.alexkekiy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiClientApplication implements CommandLineRunner {


    private final ApiRequestManager apiRequestManager;

    public ApiClientApplication(ApiRequestManager apiRequestManager) {
        this.apiRequestManager = apiRequestManager;
        this.apiRequestManager.setURLContext("http://193.19.100.32:7000/api/");
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiClientApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String role = this.apiRequestManager.takeRole();

        String email = "example374052@gmail.com";
        String firstName = "example_name";
        String lastName = "example_last_name";
        UserDTO userDTO = new UserDTO(email, firstName, lastName, role);

        if (this.apiRequestManager.registerUser(userDTO)) {
            String code = this.apiRequestManager.takeCode(userDTO);
            this.apiRequestManager.setStatusIncreased(code, userDTO);
        }
    }
}
