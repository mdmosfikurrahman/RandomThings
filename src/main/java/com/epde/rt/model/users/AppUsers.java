package com.epde.rt.model.users;


import com.epde.rt.model.users.enums.UserGender;
import com.epde.rt.model.users.enums.UserRole;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@ToString
public class AppUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NonNull
    private String userFirstName;
    @NonNull
    private String userLastName;
    @NonNull
    private String userEmail;
    @NonNull
    private UserGender userGender;
    @NonNull
    private String userAddress;
    @NonNull
    private String userContactNumber;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private UserRole userRole;
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userDateOfBirth;

    public AppUsers() {

    }
}
