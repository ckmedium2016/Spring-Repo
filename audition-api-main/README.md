# Audition API

 
## Getting Started

### Prerequisite tooling

- Any Springboot/Java IDE. Ideally IntelliJIdea.
- Java 17
- Gradle 8
  
### Prerequisite knowledge

- Java
- SpringBoot
- Gradle
- Junit

### Importing Google Java codestyle into INtelliJ

```
- Go to IntelliJ Settings
- Search for "Code Style"
- Click on the "Settings" icon next to the Scheme dropdown
- Choose "Import -> IntelliJ Idea code style XML
- Pick the file "google_java_code_style.xml" from root directory of the application
__Optional__
- Search for "Actions on Save"
    - Check "Reformat Code" and "Organise Imports"
---
## Additional Information based on the implementation
Following web security has been added in application.yml to restrict access to actuator endpoints other than health & info
  security:
    user:
      name: admin
      password: admin123
    roles: ADMIN
Also, the below additional files have been added to complete TODO tasks -

RequestResponseLoggingInterceptor
RestTemplateErrorHandler
ServiceUnAvailableException
UnAuthorizedException
AuditionComment
WebSecurity
IAuditionApplication

