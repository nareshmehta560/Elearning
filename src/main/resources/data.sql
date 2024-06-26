-- INSERT ROLES
INSERT INTO ROLE (ID, ROLE_NAME) VALUES (1, 'USER');
INSERT INTO ROLE (ID, ROLE_NAME) VALUES (2, 'ADMIN');
INSERT INTO ROLE (ID, ROLE_NAME) VALUES (3, 'INSTRUCTOR');


-- INSERT USER
INSERT INTO USER_DATA (ID, USER_NAME, PASSWORD, EMAIL) VALUES (10, 'testUser', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'TEST1@GMAIL.COM'),
                                                              (20, 'Naresh', '$2a$12$sjkLU.SRlQlM/Muijrh.quM7sFgCIhC8SWao3U5JBSLT.l34cADDy', 'naresh@gmail.com'),
                                                              (30, 'Bibek', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'bibek@gmail.com'),
                                                              (40, 'sashi', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'sashi@gmail.com'),
                                                              (50, 'sahilesh', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'sahilesh@gmail.com'),
                                                              (60, 'bishnu', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'bishnu@gmail.com'),
                                                              (70, 'marco', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'marco@gmail.com'),
                                                              (80, 'kim', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'kim@gmail.com'),
                                                              (90, 'lee', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'lee@gmail.com'),
                                                              (100, 'parker', '$2a$12$gjtM8RCZKZd717KRpUOQleLxr7A88R8wds/JAdxwDX5vkBzndN51u', 'parker@gmail.com');




-- ASSOCIATE USER WITH ROLES
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (10, 1); -- USER
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (10, 2); -- ADMIN
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (10, 3); -- ADMIN
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (20, 1); -- USER
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (30, 1); -- USER
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (40, 1); -- USER
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (50, 1); -- USER
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (60, 1); -- USER
INSERT INTO USER_ROLE (USER_ID, ROLE_ID) VALUES (70, 1); -- USER



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
    AUTHOR,
    DESCRIPTION,
    CATEGORY,
    PRICE
) VALUES (
             1000,
             'Java Programming',
             'James Gosling',
             'Java Programming for Beginners',
             'Java',
             100.00
         ),
         (
             2000,
             'Spring Boot',
             'VMWare',
             'Spring Boot for Beginners',
             'Spring',
             150.00
         ),
         (
             3000,
             'Spring Security',
             'VMWare',
             'Spring Security for Beginners',
             'Spring',
             200.00
         ),
         (
             4000,
             'Spring Data JPA',
             'VMWare',
             'Spring Data JPA for Beginners',
             'Spring',
             250.00
         ),
         (
             5000,
             'Spring MVC',
             'VMWare',
             'Spring MVC for Beginners',
             'Spring',
             300.00
         ),
         (
             6000,
             'Spring AOP',
             'VMWare',
             'Spring AOP for Beginners',
             'Spring',
             350.00
         ),
         (
             7000,
             'Spring Cloud',
             'VMWare',
             'Spring Cloud for Beginners',
             'Spring',
             400.00
         ),
         (
             8000,
             'Spring Batch',
             'VMWare',
             'Spring Batch for Beginners',
             'Spring',
             450.00
         ),
         (
             9000,
             'Spring Kafka',
             'VMWare',
             'Spring Kafka for Beginners',
             'Spring',
             500.00
         ),
         (
             10000,
             'Spring Microservices',
             'VMWare',
             'Spring Microservices for Beginners',
             'Spring',
             550.00
         ),(
             11,
             'MatheMatics',
             'Archimedes',
             'Mathematics for Beginners',
             'Mathe',
             550.00
         ),(
             111,
             'Advanced Mathematics',
             'Archimedes',
             'Advanced Mathematics for Beginners',
             'Mathe',
             550.00
         ),(
                12,
                'Physics',
                'Newton',
                'Physics for Beginners',
                'Physics',
                550.00
            ),(
                13,
                'Advanced Physics',
                'Newton',
                'Advanced Physics for Beginners',
                'Physics',
                550.00
            ),(
                14,
                'Chemistry',
                'Rutherford',
                'Chemistry for Beginners',
                'Chemistry',
                550.00
            ),(
                15,
                'Advanced Chemistry',
                'Rutherford',
                'Advanced Chemistry for Beginners',
                'Chemistry',
                550.00
            ),(
                16,
                'Biology',
                'Darwin',
                'Biology for Beginners',
                'Biology',
                550.00
            ),(
                17,
                'Advanced Biology',
                'Darwin',
                'Advanced Biology for Beginners',
                'Biology',
                550.00
            ),(
                18,
                'History',
                'Herodotus',
                'History for Beginners',
                'History',
                550.00
            ),(
                19,
                'Advanced History',
                'Herodotus',
                'Advanced History for Beginners',
                'History',
                550.00
            ),(
                20,
                'Geography',
                'Eratosthenes',
                'Geography for Beginners',
                'Geography',
                550.00
            ),(
                21,
                'Advanced Geography',
                'Eratosthenes',
                'Advanced Geography for Beginners',
                'Geography',
                550.00
            ),(
                22,
                'Economics',
                'Adam Smith',
                'Economics for Beginners',
                'Economics',
                550.00
            ),(
                23,
                'Advanced Economics',
                'Adam Smith',
                'Advanced Economics for Beginners',
                'Economics',
                550.00
            ),(
                24,
                'Political Science',
                'Aristotle',
                'Political Science for Beginners',
                'Political Science',
                550.00
            ),(
                25,
                'Advanced Political Science',
                'Aristotle',
                'Advanced Political Science for Beginners',
                'Political Science',
                550.00
            ),(
                26,
                'Sociology',
                'Auguste Comte',
                'Sociology for Beginners',
                'Sociology',
                550.00
            ),(
                27,
                'Advanced Sociology',
                'Auguste Comte',
                'Advanced Sociology for Beginners',
                'Sociology',
                550.00
            ),(
                28,
                'Psychology',
                'Sigmund Freud',
                'Psychology for Beginners',
                'Psychology',
                550.00
            ),(
                29,
                'Advanced Psychology',
                'Sigmund Freud',
                'Advanced Psychology for Beginners',
                'Psychology',
                550.00
            ),(
                30,
                'Philosophy',
                'Plato',
                'Philosophy for Beginners',
                'Philosophy',
                550.00
            ),(
                31,
                'Advanced Philosophy',
                'Plato',
                'Advanced Philosophy for Beginners',
                'Philosophy',
                550.00
            ),(
                32,
                'Literature',
                'Homer',
                'Literature for Beginners',
                'Literature',
                550.00
            ),(
                33,
                'Advanced Literature',
                'Homer',
                'Advanced Literature for Beginners',
                'Literature',
                550.00
            ),(
                34,
                'Art',
                'Leonardo da Vinci',
                'Art for Beginners',
                'Art',
                550.00
            ),(
                35,
                'Advanced Art',
                'Leonardo da Vinci',
                'Advanced Art for Beginners',
                'Art',
                550.00
            ),(
                36,
                'Music',
                'Beethoven',
                'Music for Beginners',
                'Music',
                550.00
            ),(
                37,
                'Advanced Music',
                'Beethoven',
                'Advanced Music for Beginners',
                'Music',
                550.00
            ),(
                38,
                'Dance',
                'Martha Graham',
                'Dance for Beginners',
                'Dance',
                550.00
            ),(
                39,
                'Advanced Dance',
                'Martha Graham',
                'Advanced Dance for Beginners',
                'Dance',
                550.00
            ),(
                40,
                'Theatre',
                'Shakespeare',
                'Theatre for Beginners',
                'Theatre',
                550.00
            ),(
                41,
                'Advanced Theatre',
                'Shakespeare',
                'Advanced Theatre for Beginners',
                'Theatre',
                550.00
            ),(
                42,
                'Film',
                'Alfred Hitchcock',
                'Film for Beginners',
                'Film',
                550.00
            ),(
                43,
                'Advanced Film',
                'Alfred Hitchcock',
                'Advanced Film for Beginners',
                'Film',
                550.00
            ),(
                44,
                'Photography',
                'Ansel Adams',
                'Photography for Beginners',
                'Photography',
                550.00
            ),(
                45,
                'Advanced Photography',
                'Ansel Adams',
                'Advanced Photography for Beginners',
                'Photography',
                550.00
            ),(
                46,
                'Fashion',
                'Coco Chanel',
                'Fashion for Beginners',
                'Fashion',
                550.00
            ),(
                47,
                'Advanced Fashion',
                'Coco Chanel',
                'Advanced Fashion for Beginners',
                'Fashion',
                550.00
            ),(
                48,
                'Culinary Arts',
                'Julia Child',
                'Culinary Arts for Beginners',
                'Culinary Arts',
                550.00
            ),(
                49,
                'Advanced Culinary Arts',
                'Julia Child',
                'Advanced Culinary Arts for Beginners',
                'Culinary Arts',
                550.00
            ),(
                50,
                'Gardening',
                'Vita Sackville-West',
                'Gardening for Beginners',
                'Gardening',
                550.00
            ),(
                51,
                'Advanced Gardening',
                'Vita Sackville-West',
                'Advanced Gardening for Beginners',
                'Gardening',
                550.00
            ),(
                52,
                'Home Improvement',
                'Bob Vila',
                'Home Improvement for Beginners',
                'Home Improvement',
                550.00
            ),(
                53,
                'Advanced Home Improvement',
                'Bob Vila',
                'Advanced Home Improvement for Beginners',
                'Home Improvement',
                550.00
            );

