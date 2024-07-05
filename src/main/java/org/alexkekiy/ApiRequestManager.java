package org.alexkekiy;

import lombok.Setter;
import org.alexkekiy.util.Base64Encrypter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ApiRequestManager {
    final static String getRolesAdress = "get-roles";
    final static String getCodeAdress = "get-code?email={email}";
    final static String setStatusAdress = "set-status";
    final static String signUpAdress = "sign-up/";
    ApiRequestService apiRequestService;
    @Setter
    private String URLContext;

    public ApiRequestManager(ApiRequestService apiRequestService) {
        this.URLContext = "";
        this.apiRequestService = apiRequestService;
    }

    public String takeRole() {
        String[] roles = apiRequestService.executeGet(this.URLContext + getRolesAdress, null).getBody().replace("[", "").replace("]", "").replace("{", "").replace("}", "").replaceAll("\"", "").replace("roles", "").replaceAll(":", "").split(",");
        if (roles.length == 0) {
            throw new RuntimeException("Получен пустой массив ролей");
        } else {
            int randomIndex = (int) (Math.random() * roles.length);

            return roles[randomIndex];
        }

    }

    public boolean registerUser(UserDTO userDTO) {

        return (this.apiRequestService.executePost(URLContext + signUpAdress, null, userDTO.getProperties()).getBody()).equals("\"Данные внесены\"");
    }

    public String takeCode(UserDTO userDTO) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("email", userDTO.getEmail());
        String code = this.apiRequestService.executeGet(URLContext + getCodeAdress, params).getBody();
        assert code != null;
        if (code.isBlank() || code.isEmpty()) {
            throw new RuntimeException("Получен пустой ответ вместо кода");
        } else {

            return code;
        }

    }

    public void setStatusIncreased(String code, UserDTO userDTO) {

        String token = Base64Encrypter.encodeEmailAndCode(code, userDTO.getEmail());
        Map<String, String> parametrs = new LinkedHashMap<>();
        parametrs.put("token", token);
        parametrs.put("status", "increased");
        if (this.apiRequestService.executePost(URLContext + setStatusAdress, parametrs, null).getBody().equals("Данные внесены")) {
            throw new RuntimeException("Получен неожиданный или невалидный ответ");
        }

    }
}
