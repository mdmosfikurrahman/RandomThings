package com.epde.rt.dto;

import com.epde.rt.exception.InvalidEnumValueException;
import com.epde.rt.model.users.enums.AppUserGender;
import com.epde.rt.model.users.enums.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {
    private Long userId;
    @NotBlank(message = "First Name is Mandatory!")
    private String userFirstName;
    @NotBlank(message = "Last Name is Mandatory!")
    private String userLastName;
    @NotBlank(message = "Email Address is Mandatory!")
    @Pattern(regexp = "^[\\w-.]+@[\\w.-]+\\.[a-zA-Z]{2,}$", message = "Invalid Email Address!")
    private String userEmail;
    @NotBlank(message = "Gender is Mandatory!")
    private String userGender;
    @NotBlank(message = "Address is Mandatory!")
    private String userAddress;
    @NotBlank(message = "Contact Number is Mandatory!")
    @Pattern(regexp = "^[0-9]*$", message = "Invalid Contact Number!")
    @Size(min = 10, max = 10, message = "Invalid Contact Number Length!")
    private String userContactNumber;
    @NotBlank(message = "User Name is Mandatory!")
    private String username;
    @NotBlank(message = "Password is Mandatory!")
    private String password;
    @NotBlank(message = "Role is Mandatory!")
    private String userRole;
    @NotBlank(message = "Date of Birth is Mandatory!")
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
