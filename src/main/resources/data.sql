-- INSERT ROLES
INSERT INTO ROLE (ID, ROLE_NAME) VALUES (10, 'USER');
INSERT INTO ROLE (ID, ROLE_NAME) VALUES (20, 'ADMIN');
INSERT INTO ROLE (ID, ROLE_NAME) VALUES (30, 'INSTRUCTOR');

-- INSERT USER
INSERT INTO USER_DATA (ID, USER_NAME, PASSWORD, EMAIL) VALUES (10, 'testUser', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'TEST1@GMAIL.COM'),
                                                              (20, 'Naresh', '$2a$12$sjkLU.SRlQlM/Muijrh.quM7sFgCIhC8SWao3U5JBSLT.l34cADDy', 'naresh@gmail.com'),
                                                              (30, 'Bibek', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'bibek@gmail.com'),
                                                              (40, 'sashi', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'sashi@gmail.com'),
                                                              (50, 'sahilesh', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'sahilesh@gmail.com'),
                                                              (60, 'bishnu', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'bishnu@gmail.com'),
                                                              (70, 'marco', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'marco@gmail.com');




-- ASSOCIATE USER WITH ROLES
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (10, 10); -- USER
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (10, 20); -- ADMIN
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (20, 10); -- USER
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (30, 10); -- USER
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (40, 10); -- USER
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (50, 10); -- USER
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (60, 10); -- USER
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (70, 10); -- USER



INSERT INTO INSTRUCTOR (
    ID,
    USER_ID,
    TITLE,
    QUALIFICATION_AND_EXPERIENCE,
    PAYPAL_EMAIL,
    IS_APPROVED
) VALUES (
             10,
             20, -- This references the user inserted above
             'Professor',
             'PhD in Computer Science with 10 years of experience ',
             'naresh@example.com',
             true
         ),
         (
             20,
             30, -- userid
             'Dr',
             'Computer Hacker with 10 years of experience',
             'bibek@gmail.com',
             true
         ),
         (
             30,
             40, -- userid
             'Dr',
             'Computer Networking with @Google',
             'sahi@gmail.com',
             false
         ),
         (
             40,
             50, -- userid
             'Msc',
             'DAta Science with 10 years of experience',
             'sahilesh@gmail.com',
             false
         );


INSERT INTO COURSE (
    ID,
    NAME,
    DESCRIPTION,
    CATEGORY,
    PRICE
) VALUES (
             1,
             'Java Programming',
             'Java Programming for Beginners',
             'Java',
             100.00
         ),
         (
             2,
             'Spring Boot',
             'Spring Boot for Beginners',
             'Spring',
             150.00
         ),
         (
             3,
             'Spring Security',
             'Spring Security for Beginners',
             'Spring',
             200.00
         ),
         (
             4,
             'Spring Data JPA',
             'Spring Data JPA for Beginners',
             'Spring',
             250.00
         ),
         (
             5,
             'Spring MVC',
             'Spring MVC for Beginners',
             'Spring',
             300.00
         ),
         (
             6,
             'Spring AOP',
             'Spring AOP for Beginners',
             'Spring',
             350.00
         ),
         (
             7,
             'Spring Cloud',
             'Spring Cloud for Beginners',
             'Spring',
             400.00
         ),
         (
             8,
             'Spring Batch',
             'Spring Batch for Beginners',
             'Spring',
             450.00
         ),
         (
             9,
             'Spring Kafka',
             'Spring Kafka for Beginners',
             'Spring',
             500.00
         ),
         (
             10,
             'Spring Microservices',
             'Spring Microservices for Beginners',
             'Spring',
             550.00
         );

