package org.alexkekiy;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String role;

    public UserDTO(String email, String firstName, String lastName, String role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Map<String, String> getProperties() {
        Map<String, String> propertiesMap = new LinkedHashMap<>();
        propertiesMap.put("last_name", lastName);
        propertiesMap.put("first_name", firstName);
        propertiesMap.put("email", email);
        propertiesMap.put("role", role);
        return propertiesMap;
    }
}
