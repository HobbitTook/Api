package in.reqres.models;


import lombok.Data;

@Data
public class AuthorizeRequestModelLombok {
    private String email,
            password;
}
