package com.epde.rt.dto;

import com.epde.rt.exception.InvalidEnumValueException;
import com.epde.rt.model.users.enums.AppUserGender;
import com.epde.rt.model.users.enums.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userGender;
    private String userAddress;
    private String userContactNumber;
    private String username;
    private String password;
    private String userRole;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date userDateOfBirth;

    public void validateUserGender() {
        try {
            AppUserGender.valueOf(userGender);
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException("Invalid Gender: " + userGender);
        }
    }

    public void validateUserRole() {
        try {
            AppUserRole.valueOf(userRole);
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException("Invalid Role: " + userRole);
        }
    }
}
