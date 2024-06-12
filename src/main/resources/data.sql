-- INSERT ROLES
INSERT INTO ROLE (ID, ROLE_NAME) VALUES (1, 'USER');
INSERT INTO ROLE (ID, ROLE_NAME) VALUES (2, 'ADMIN');
INSERT INTO ROLE (ID, ROLE_NAME) VALUES (3, 'INSTRUCTOR');

-- INSERT USER
INSERT INTO USER_DATA (ID, USER_NAME, PASSWORD, EMAIL) VALUES (1, 'testUser', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'TEST1@GMAIL.COM'),
                                                              (2, 'Naresh', '$2a$12$sjkLU.SRlQlM/Muijrh.quM7sFgCIhC8SWao3U5JBSLT.l34cADDy', 'naresh@gmail.com'),
                                                              (3, 'Bibek', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'bibek@gmail.com'),
                                                              (4, 'sashi', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'sashi@gmail.com'),
                                                              (5, 'sahilesh', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'sahilesh@gmail.com'),
                                                              (6, 'bishnu', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'bishnu@gmail.com'),
                                                              (7, 'marco', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'marco@gmail.com');




-- ASSOCIATE USER WITH ROLES
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (1, 1); -- USER
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (1, 2); -- ADMIN
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (2, 1); -- USER
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (3, 1); -- USER
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (4, 1); -- USER
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (5, 1); -- USER
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (6, 1); -- USER
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (7, 1); -- USER



INSERT INTO INSTRUCTOR (
    ID,
    USER_ID,
    TITLE,
    QUALIFICATION_AND_EXPERIENCE,
    PAYPAL_EMAIL,
    IS_APPROVED
) VALUES (
             1,
             2, -- This references the user inserted above
             'Professor',
             'PhD in Computer Science with 10 years of experience ',
             'naresh@example.com',
             true
         ),
         (
             2,
             3, -- userid
             'Dr',
             'Computer Hacker with 10 years of experience',
             'bibek@gmail.com',
             true
         ),
         (
             3,
             4, -- userid
             'Dr',
             'Computer Networking with @Google',
             'sahi@gmail.com',
             false
         ),
         (
             4,
             5, -- userid
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
    PRICE,
    INSTRUCTOR_ID
) VALUES (
             1,
             'Java Programming',
             'Java Programming for Beginners',
             'Java',
             100.00,
             2
         ),
         (
             2,
             'Spring Boot',
             'Spring Boot for Beginners',
             'Spring',
             150.00,
             2
         ),
         (
             3,
             'Spring Security',
             'Spring Security for Beginners',
             'Spring',
             200.00,
             2
         ),
         (
             4,
             'Spring Data JPA',
             'Spring Data JPA for Beginners',
             'Spring',
             250.00,
             2
         ),
         (
             5,
             'Spring MVC',
             'Spring MVC for Beginners',
             'Spring',
             300.00,
             2
         ),
         (
             6,
             'Spring AOP',
             'Spring AOP for Beginners',
             'Spring',
             350.00,
             2
         ),
         (
             7,
             'Spring Cloud',
             'Spring Cloud for Beginners',
             'Spring',
             400.00,
             2
         ),
         (
             8,
             'Spring Batch',
             'Spring Batch for Beginners',
             'Spring',
             450.00,
             2
         ),
         (
             9,
             'Spring Kafka',
             'Spring Kafka for Beginners',
             'Spring',
             500.00,
             2
         ),
         (
             10,
             'Spring Microservices',
             'Spring Microservices for Beginners',
             'Spring',
             550.00,
             2
         );