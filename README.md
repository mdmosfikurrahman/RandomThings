# Random Things
A Spring Boot project with Random Things that pings up my mind at anytime that requires to complete the project and practice.

_It's nothing but something will come up at the end._
_-- EPDE_

### RandomThingsApplication Project Structure:
```css
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   ├── epde
│   │   │   │   │   ├── rt
│   │   │   │   │   │   ├── controller
│   │   │   │   │   │   │   ├── AppUsersController.java
│   │   │   │   │   │   │   ├── AssignmentController.java
│   │   │   │   │   │   │   ├── TasksController.java
│   │   │   │   │   │   ├── dto
│   │   │   │   │   │   │   ├── AppUserDto.java
│   │   │   │   │   │   │   ├── TaskDto.java
│   │   │   │   │   │   ├── exception
│   │   │   │   │   │   │   ├── ErrorResponse.java
│   │   │   │   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   │   │   │   ├── InvalidEnumValueException.java
│   │   │   │   │   │   │   ├── ResourceAlreadyExistsException.java
│   │   │   │   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   │   │   ├── model
│   │   │   │   │   │   │   ├── assignments
│   │   │   │   │   │   │   │   ├── Assignment.java
│   │   │   │   │   │   │   │   ├── AssignmentRequest.java
│   │   │   │   │   │   │   │   ├── AssignmentResponse.java
│   │   │   │   │   │   │   ├── tasks
│   │   │   │   │   │   │   │   ├── enums
│   │   │   │   │   │   │   │   │   ├── TaskPriority.java
│   │   │   │   │   │   │   │   ├── Tasks.java
│   │   │   │   │   │   │   ├── users
│   │   │   │   │   │   │   │   ├── enums
│   │   │   │   │   │   │   │   │   ├── AppUserGender.java
│   │   │   │   │   │   │   │   │   ├── AppUserRole.java
│   │   │   │   │   │   │   │   ├── AppUsers.java
│   │   │   │   │   │   ├── repository
│   │   │   │   │   │   │   ├── AppUsersRepository.java
│   │   │   │   │   │   │   ├── AssignmentRepository.java
│   │   │   │   │   │   │   ├── TasksRepository.java
│   │   │   │   │   │   ├── service
│   │   │   │   │   │   │   ├── assignments
│   │   │   │   │   │   │   │   ├── AssignmentService.java
│   │   │   │   │   │   │   │   ├── AssignmentServiceImpl.java
│   │   │   │   │   │   │   ├── tasks
│   │   │   │   │   │   │   │   ├── TaskstService.java
│   │   │   │   │   │   │   │   ├── TasksServiceImpl.java
│   │   │   │   │   │   │   ├── users
│   │   │   │   │   │   │   │   ├── AppUsersService.java
│   │   │   │   │   │   │   │   ├── AppUsersServiceImpl.java
│   │   │   │   │   │   ├── RandomThingsApplication.java
│   │   ├── resources
│   │   │   ├── application.properties
```

