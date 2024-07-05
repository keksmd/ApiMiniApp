package org.alexkekiy;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;
@Getter
@Setter
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

    public Map<String, String> getProperties() {
        Map<String, String> propertiesMap = new LinkedHashMap<>();
        propertiesMap.put("last_name", lastName);
        propertiesMap.put("first_name", firstName);
        propertiesMap.put("email", email);
        propertiesMap.put("role", role);
        return propertiesMap;
    }
}
