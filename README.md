# Library Management System

Spring Boot Web application to provide REST API

## 1. Getting started

### 1.1. Clone the repository

```
$ git clone git@github.com:AgileSpirit/spring-boot-sample.git
```

### 1.2. Run the application

```
$ ./mvnw spring-boot:run
```

## 2. API

### 2.1 Interact with API

 - You could interact with the application's APIS by postman (https://www.postman.com/), Also you could use thunder client a visual studio code extension (https://www.thunderclient.com/)
 - There is no Authentication Mechanism 

### 2.2 Entities 

- **Patron**	has One-To-Many Assosciation with BorrowingRecord

- **ContactInformation** has One-To-One Assosciation with patron

- **Book**	has One-To-Many Assosciation with BorrowingRecord

- **BorrowingRecord**	Acts a juntion/join table between Patron and book, has Many-To-One Assosciation with Patron And Book

### 2.3 API Routes

#### **DO NOT FORGET** to add localhost/8080 before each request as the server runs on local host on port 8080 

#### Patron

Method | Path             | Description                   |
-------|------------------|-------------------------------|
GET    | /api/patron      | retrieve all patrons      	  |
GET    | /api/patron/id   | retrieve one patron by its ID |
POST   | /api/patron      | store a new patron            |
PUT    | /api/patron/id   | update an existing patron     |
DELETE | /api/patron/id   | remove a patron by its ID     |


#### Book

Method | Path           | Description                 |
-------|----------------|-----------------------------|
GET    | /api/book      | retrieve all books          |
GET    | /api/book/id   | retrieve one book by its ID |
POST   | /api/book      | store a new book            |
PUT    | /api/book/id   | update an existing book     |
DELETE | /api/book/id   | remove a book by its ID     |


#### BorrowingRecord

Method | Path           | Description                                         |
-------|----------------|-----------------------------------------------------|
POST   | /api/borrow/bookId/patron/patronId | used by patron to borrow a book |
PUT    | /api/return/bookId/patron/patronId | used by patron to return a book |

## 3. API Request Body With JSON Data Samples

### 3.1 Save Patron Request

```
{
  "email": "moataz@gmail.com",
  "password": "125456654654",
  "contactInformation": {
    "name": "moataz nasr",
    "phoneNumber": "01065415094",
    "age": 15
  }
}
```

### 3.2 Save Book Request

```
{
  "title":"the seven secret of wealth",
  "author":"Sarah Adam",
  "publicationYear": 1920,
  "isbn": "1205-5032-5004-1983"
}
```
