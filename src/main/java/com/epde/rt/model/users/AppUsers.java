package com.epde.rt.model.users;


import com.epde.rt.model.users.enums.AppUserGender;
import com.epde.rt.model.users.enums.AppUserRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
@Data
@ToString
@NoArgsConstructor
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
    private AppUserGender userGender;
    @NonNull
    private String userAddress;
    @NonNull
    private String userContactNumber;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private AppUserRole userRole;
    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userDateOfBirth;

    public AppUsers(@NonNull String userFirstName, @NonNull String userLastName, @NonNull String userEmail, @NonNull AppUserGender userGender, @NonNull String userAddress, @NonNull String userContactNumber, @NonNull String username, @NonNull String password, @NonNull AppUserRole userRole, @NonNull Date userDateOfBirth) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userGender = userGender;
        this.userAddress = userAddress;
        this.userContactNumber = userContactNumber;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.userDateOfBirth = userDateOfBirth;
    }
}
