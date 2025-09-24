
CREATE DATABASE IF NOT EXISTS my_library
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE my_library;

DROP TABLE IF EXISTS books;

CREATE TABLE books (
  id         INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(200)  NOT NULL,
  author     VARCHAR(120)  NOT NULL,
  page       INT UNSIGNED  NOT NULL,
  stock      BOOLEAN       NOT NULL DEFAULT TRUE,   -- BOOLEAN = TINYINT(1)
  created_at DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CHECK (page > 0),
  CHECK (stock IN (0,1))
) ENGINE=InnoDB;

INSERT INTO books (name, author, page, stock) VALUES
('Kürk Mantolu Madonna','Sabahattin Ali',160, TRUE),
('Simyacı','Paulo Coelho',188, FALSE);


select * from books where author="Paulo Coelho" and page=188;

insert into books(name,author,page,stock) value("Sefiller","Victoe Hugo","1724",1);
select * from books
UPDATE books SET stock = 1 WHERE id = 2;
delete from books where id=3

CREATE TABLE IF NOT EXISTS books (
  id    INT AUTO_INCREMENT PRIMARY KEY,
  name  VARCHAR(255) NOT NULL,
  author VARCHAR(255) NOT NULL,
  page  INT NOT NULL,
  stock INT NOT NULL DEFAULT 0
);

USE my_library;

ALTER TABLE books DROP CHECK books_chk_2;


INSERT INTO books (name, author, page, stock) VALUES
('Sefiller', 'Victor Hugo', 999, 10),
('Savaş ve Barış', 'Lev Tolstoy', 999, 8),
('Anna Karenina', 'Lev Tolstoy', 864, 7),
('Suç ve Ceza', 'Fyodor Dostoyevski', 671, 10),
('Karamazov Kardeşler', 'Fyodor Dostoyevski', 1000, 6),
('Budala', 'Fyodor Dostoyevski', 736, 5),
('Madam Bovary', 'Gustave Flaubert', 432, 9),
('Kırmızı ve Siyah', 'Stendhal', 544, 7),
('Büyük Umutlar', 'Charles Dickens', 592, 6),
('İki Şehrin Hikâyesi', 'Charles Dickens', 448, 6),
('Gurur ve Önyargı', 'Jane Austen', 432, 10),
('Jane Eyre', 'Charlotte Brontë', 624, 5),
('Uğultulu Tepeler', 'Emily Brontë', 416, 5),
('Don Kişot', 'Miguel de Cervantes', 999, 4),
('Faust', 'Johann Wolfgang von Goethe', 464, 5),
('Notre Dame''ın Kamburu', 'Victor Hugo', 496, 6),
('1984', 'George Orwell', 352, 10),
('Hayvan Çiftliği', 'George Orwell', 152, 10),
('Yabancı', 'Albert Camus', 160, 10),
('Veba', 'Albert Camus', 312, 6),
('Dönüşüm', 'Franz Kafka', 128, 10),
('Dava', 'Franz Kafka', 304, 8),
('Şato', 'Franz Kafka', 464, 5),
('Yüzyıllık Yalnızlık', 'Gabriel García Márquez', 464, 7),
('Kırmızı Pazartesi', 'Gabriel García Márquez', 128, 9),
('Körlük', 'José Saramago', 336, 6),
('Sineklerin Tanrısı', 'William Golding', 240, 8),
('Fahrenheit 451', 'Ray Bradbury', 208, 9),
('Çavdar Tarlasında Çocuklar', 'J. D. Salinger', 240, 8),
('Otomatik Portakal', 'Anthony Burgess', 224, 7),
('Satranç', 'Stefan Zweig', 88, 10);

	SELECT COUNT(*) FROM books;
	SELECT id, name, author, page, stock FROM books LIMIT 5;
    
    ALTER TABLE books CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci;

CREATE database IF NOT EXISTS my_library
USE my_library;
CREATE TABLE members (
 id         INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(200)  NOT NULL,
  surname     VARCHAR(120)  NOT NULL,
  email       VARCHAR(200) NOT NULL,
  username     VARCHAR(120) NOT NULL,
  password     VARCHAR(120) NOT NULL
)ENGINE=InnoDB;

select* from members

SELECT IF(
EXISTS((SELECT 1 FROM members where username="user1" and password="123456asd")),'true','false')AS result;

ALTER TABLE my_library.members
  MODIFY username VARCHAR(100)
    CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NOT NULL,
    MODIFY password VARCHAR(100)
    CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NOT NULL,
  MODIFY email VARCHAR(255)
    CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NOT NULL;
select * from books