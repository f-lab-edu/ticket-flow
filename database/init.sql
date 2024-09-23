CREATE TABLE User (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    phone_number CHAR(11) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE LeaveUser (
    leave_user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    phone_number CHAR(11) NOT NULL,
    created_at DATETIME NOT NULL,
    deleted_at DATETIME NULL DEFAULT NULL
);

CREATE TABLE Category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(10) NOT NULL,
    category_level INT NOT NULL,
    parent_category_id INT NULL
);

CREATE TABLE EventLocation (
    event_location_id INT AUTO_INCREMENT PRIMARY KEY,
    event_location_name VARCHAR(100) NOT NULL,
    total_seats INT NOT NULL
);


CREATE TABLE Event (
    event_id INT AUTO_INCREMENT PRIMARY KEY,
    event_location_id INT NOT NULL,
    event_name VARCHAR(100) NOT NULL,
    event_description TEXT NULL,
    date TIMESTAMP NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modify_at DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at DATETIME NULL DEFAULT NULL
);

CREATE TABLE CategoryEvent (
    category_event_id INT AUTO_INCREMENT PRIMARY KEY,
    category_id INT NOT NULL,
    event_id INT NOT NULL
);

CREATE TABLE SeatGrade (
    seat_grade_id INT AUTO_INCREMENT PRIMARY KEY,
    event_location_id INT NOT NULL,
    seat_grade VARCHAR(10) NOT NULL,
    seat_grade_price INT NOT NULL,
    seat_grade_total_seats INT NOT NULL
);

CREATE TABLE Seat (
    seat_id INT AUTO_INCREMENT PRIMARY KEY,
    seat_grade_id INT NOT NULL,
    seat_zone VARCHAR(5) NOT NULL,
    seat_row INT NOT NULL,
    seat_number INT NOT NULL
);

CREATE TABLE GradeTickerInfo (
    grade_ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    event_location_id INT NOT NULL,
    seat_grade_id INT NOT NULL,
    number_of_total_ticket INT NOT NULL,
    number_of_remaining_ticket INT NOT NULL
);

CREATE TABLE Ticket (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    event_location_id INT NOT NULL,
    seat_id INT NOT NULL,
    seat_grade_id INT NOT NULL,
    ticket_price INT NOT NULL,
    create_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    reservation_status CHAR(1) NOT NULL
);

CREATE TABLE PAYMENT (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    number_of_ticket INT NOT NULL,
    payment_price INT NOT NULL,
    create_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at DATETIME NULL DEFAULT NULL
);

CREATE TABLE PaymentTicket (
    payment_ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    payment_id INT NOT NULL,
    ticket_id INT NOT NULL
);

CREATE TABLE LIKES (
    like_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    user_id INT NOT NULL
);

CREATE TABLE REVIEW (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    user_id INT NOT NULL,
    scope INT NOT NULL,
    content INT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modify_at DATETIME NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at DATETIME NULL DEFAULT NULL
);