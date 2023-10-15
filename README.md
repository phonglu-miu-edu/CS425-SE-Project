# CS425-SE


## Backend deployement ports:
 - ### Gateway (Proxy): 8080
 - ### Eureka Server (Server Registry): 8761
 - ### Configuration Server: 8088
 - ### Authentication Service: 8082
 - ### Administration Service: 8083
 - ### Book Service: 8084
 - ### Student Service: 8085

## HTTP Codes:
 - 200: OK / Success
 - 400: Bad request
 - 401: Unauthorized access
 - 404: Not Found
 - 500: Internal Server Error

## REST APIs Specification
 - ### 1. Authentication Service
   - #### 1.1 Login 
     - Method: POST
     - URL: /lms/auth/login
     - Body:
     ```
     {
        "userName": "bao",
        "password": "123"
     }
     ```
     - Response:
     ```
     {
        "status_code": 200,
        "data": {
           "id": 1,
           "userName": "bao",
           "password": null,
           "roleCd": "Admin",
           "firstName": "Bao Quoc",
           "lastName": "Nguyen",
           "email": "baonguyen@miu.edu"
           "phoneNumber": "6418196666",
           "address": "13120 Chellen Dr, Dallas, TX 12345, US"
        }
     }
     ```
   - #### 1.2 Logout
     - Method: POST
     - URL: /lms/auth/logout
     - Body: 
     ```
     {
        "userName": "bao",
        "roleCd": "Admin"
     }
     ```
     - Response:
     ```
     {
        "status_code": 200,
        "data": "The user has logged out successfully."
      }
     ```
   - #### 1.3 Verify
     - Method: POST
     - URL: /lms/auth/verify
     - Body: just token value getting from Cookie
     ```
     eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzd2Vwcm9qZWN0IiwiaWQiOiJiYW8iLCJyb2xlX2NkIjoiQWRtaW4iLCJjcmVfZHQiOjE2OTcyNTY2MTg4Mjl8.uDiD7act-_U0LADinXZbYAWpoDWVqvLo3600-Xp_8rs
     ```
     - Response:
     ```
     {
        "status_code": 200,
        "data": {
            "userName": "bao",
            "roleCd": "Admin",
            "creDt": "2023-10-13 23:38:48"
        },
        "message": "TOKEN IS VERIFIED SUCCESSFULLY"
     }
     ```
 - ### 2. Admin Service
   - #### 2.1 Add user
     - Method: POST
     - URL: /lms/admin/users
     - Body:
   ```
   {
      "userName": "bao",
      "password": "123",
      "firstName": "Bao Quoc",
      "lastName": "Nguyen",
      "roleCd": "Admin",
      "email": "baonguyen@miu.edu"
      "phoneNumber": "6418196666",
      "address": "13120 Chellen Dr, Dallas, TX 12345, US"
   }
   ```
     - Response:
   ```
   {
      "status_code": 200,
      "data": {
         "id": 1,
         "userName": "bao",
         "roleCd": "Admin",
         "firstName": "Bao Quoc",
         "lastName": "Nguyen",
         "password": "123",
         "email": "baonguyen@miu.edu"
         "phoneNumber": "6418196666",
         "address": "13120 Chellen Dr, Dallas, TX 12345, US"
      }
   }
   ```
   - #### 2.2 Update user
     - Method: PUT
     - URL: /lms/admin/users/{userId}
     - Parameter(s):
       - userId: user ID 
     - Body:
   ```
   {
      "userName": "bao",
      "password": "12333333",
      "firstName": "Bao Quoc",
      "lastName": "Nguyen",
      "roleCd": "Admin",
      "password": "****",
      "email": "baonguyen@miu.edu"
      "phoneNumber": "6418196666",
      "address": "13120 Chellen Dr, Dallas, TX 12345, US"
   }
   ```
     - Response:
   ```
   {
      "status_code": 200,
      "data": {
         "id": 1,
         "userName": "bao",
         "roleCd": "Admin",
         "firstName": "Bao Quoc",
         "lastName": "Nguyen",
         "password": "****",
         "email": "baonguyen@miu.edu",
         "phoneNumber": "6418197777",
         "address": "13120 Chellen Dr, Dallas, TX 12345, US"
      },
      "message": "Updated user information successfully."
   }
   ```
     - #### 2.3 Detele user
       - Method: DELETE
       - URL: /lms/admin/users/{userId}
       - Parameter(s):
         - userId: user ID
       - Response:
   ```
   {
      "status_code": 200,
      "data": "Deleted user ID [1] successfully."
   }
   ```
     - #### 2.4 Get user
       - Method: GET
       - URL: /lms/admin/users/{userId}
       - Parameter(s):
       - userId: user ID
       - Response:
   ```
   {
      "status_code": 200,
      "data": {
         "id": 1,
         "userName": "bao",
         "roleCd": "Admin",
         "firstName": "Bao Quoc",
         "lastName": "Nguyen",
         "email": "baonguyen@miu.edu"
         "phoneNumber": "6418196666",
         "address": "13120 Chellen Dr, Dallas, TX 12345, US"
      }
   }
   ```
     - #### 2.5 Search users
       - Method: GET
       - URL: /lms/admin/users/search
       - Query Parameter(s):
         - q: query String
       - Response:
   ```
   {
      "status_code": 200,
      "data": [ 
         {
           "id": 1,
           "userName": "bao",
           "roleCd": "Admin",
           "firstName": "Bao Quoc",
           "lastName": "Nguyen",
           "email": "baonguyen@miu.edu"
           "phoneNumber": "6418196666",
           "address": "13120 Chellen Dr, Dallas, TX 12345, US"
         }
      ]
   }
   ```
     - #### 2.6 Add Book Category
       - Method: POST
       - URL: /lms/admin/book_categories
       - Body:
   ```
   {
      "categoryName": "Java",
      "description": "Java technology related books"
   }
   ```
       - Response:
   ```
   {
      "status_code": 200,
      "data": {
         "id": 1,
         "categoryName": "Java",
         "description": "Java related books"
      }
   }
   ```
     - #### 2.7 Update Book Category
       - Method: PUT
       - URL: /lms/admin/book_categories/{bookCategoryId}
       - Parameter(s):
         - bookCategoryId: book category ID
       - Body:
   ```
   {
      "categoryName": "Java",
      "description": "Java technology related books"
   }
   ```
   - Response:
   ```
   {
      "status_code": 200,
      "data": {
         "categoryName": "Java",
         "description": "Java technology related books"
      },
      "message": "Updated book information successfully."
   }
   ```
     - #### 2.8 Delete Book Category
       - Method: DELETE
       - URL: /lms/admin/book_categories/{bookCategoryId}
       - Parameter(s):
         - bookCategoryId: book category ID
       - Response:
   ```
   {
      "status_code": 200,
      "data": "Deleted book category successfully."
   }
   ```
     - #### 2.9 Get Book Category
       - Method: GET
       - URL: /lms/admin/book_categories/{bookCategoryId}
       - Parameter(s):
         - bookCategoryId: book Category ID
       - Response:
   ```
   {
      "status_code": 200,
      "data": {
         "id": 1,
         "categoryName": "Java",
         "description": "Java related books"
      }
   }
   ```
     - #### 2.10 Search Book Categories
       - Method: GET
       - URL: /lms/admin/book_categories/search
       - Query Parameter(s):
         - q: query String
       - Response:
   ```
   {
      "status_code": 200,
      "data": [ 
         {
           "id": 1,
           "categoryName": "Java",
           "description": "Java related books"
         }
      ]
   }
   ```
     - #### 2.11 Add Book
         - Method: POST
         - URL: /lms/admin/books
         - Body:
   ```
   {
      "title": "Core Java",
      "isbn": "1000",
      "authors": "Effective Java",
      "numOfCopies": 5,
      "bookCategoryId": 1
   }
   ```
       - Response:
   ```
   {
      "status_code": 200,
      "data": {
         "id": 1,
         "title": "Core Java",
         "isbn": "1000",
         "authors": "Effective Java",
         "numOfCopies": 5,
         "bookCategoryId": 1
      }
   }
   ```
     - #### 2.12 Update Book
       - Method: PUT
       - URL: /lms/admin/books/{bookId}
       - Parameter(s):
           - bookId: book ID
       - Body:
   ```
   {
      "title": "Core Java",
      "isbn": "1000",
      "authors": "Effective Java",
      "numOfCopies": 5,
      "bookCategoryId": 1
   }
   ```
       - Response:
   ```
   {
      "status_code": 200,
      "data": {
         "id": 1,
         "title": "Core Java",
         "isbn": "1000",
         "authors": "Effective Java",
         "numOfCopies": 5,
         "bookCategoryId": 1
      },
      "message": "Updated book information successfully."
   }
   ```
     - #### 2.13 Delete Book
       - Method: DELETE
       - URL: /lms/admin/books/{bookId}
       - Parameter(s):
           - bookId: book ID
       - Response:
   ```
   {
      "status_code": 200,
      "data": "Deleted book successfully."
   }
   ```
     - #### 2.14 Get Book
       - Method: GET
       - URL: /lms/admin/books/{bookId}
       - Parameter(s):
           - userId: book ID
       - Response:
   ```
   {
      "status_code": 200,
      "data": {
         "id": 1,
         "categoryName": "Java",
         "description": "Java related books"
      }
   }
   ```
     - #### 2.15 Search Book
       - Method: GET
       - URL: /lms/admin/books/search
       - Query Parameter(s):
             - q: query String
       - Response:
   ```
   {
      "status_code": 200,
      "data": [ 
         {
            "id": 1,
            "title": "Core Java",
            "isbn": "1000",
            "authors": "Effective Java",
            "numOfCopies": 5,
            "bookCategoryId": 1
         }
      ]
   }
   ```
     - #### 2.16 Get all configuration items
       - Method: GET
       - URL: /lms/admin/confis
       - Response:
   ```
   {
      "status_code": 200,
      "data": [ 
         {
            "id": 1,
            "itemName": "Number of days is free to borrow",
            "itemValue": "30"
         },
         {
            "id": 2,
            "itemName": "Fine for overdue (per day)",
            "itemValue": "5"
         },
         {
            "id": 3,
            "itemName": "Maximum number of books can borrow",
            "itemValue": "3"
         },
         {
            "id": 4,
            "itemName": "Number of overdues to be suspended",
            "itemValue": "3"
         },
         {
            "id": 5,
            "itemName": "Number of overdues to be locked",
            "itemValue": "5"
         },
         {
            "id": 6,
            "itemName": "Number of suspended days",
            "itemValue": "15"
         },
         {
            "id": 7,
            "itemName": "Number of days to be reminded",
            "itemValue": "5"
         }
      ]
   }
   ```
     - #### 2.17 Update Configuration
       - Method: PUT
       - URL: /lms/admin/configs
       - Body:
   ```
   [
     {
        "id": 1,
        "itemName": "Number of days is free to borrow",
        "itemValue": "30"
     },
     {
        "id": 2,
        "itemName": "Fine for overdue (per day)",
        "itemValue": "5"
     },
     {
        "id": 3,
        "itemName": "Maximum number of books can borrow",
        "itemValue": "3"
     },
     {
        "id": 4,
        "itemName": "Number of overdues to be suspended",
        "itemValue": "3"
     },
     {
        "id": 5,
        "itemName": "Number of overdues to be locked",
        "itemValue": "5"
     },
     {
        "id": 6,
        "itemName": "Number of suspended days",
        "itemValue": "15"
     },
     {
        "id": 7,
        "itemName": "Number of days to be reminded",
        "itemValue": "5"
     }
   ]
   ```
       - Response:
   ```
   {
      "status_code": 200,
      "data": "Configuration items are updated successfully."
   }
   ```
     - #### 2.18 Add Book Copy
         - Method: POST
         - URL: /lms/admin/book_copies
         - Body:
   ```
   {
      "id": 1,
      "numOfCopies": 3
   }
   ```
         - Reseponse
   ```
   {
      "status_code": 200,
      "data": "Added 3 copies for Book Id[1]"
   }
   ```
     - #### 2.19 Update Book Copy
         - Method: PUT
         - URL: /lms/admin/book_copies
         - Body:
   ```
   {
      copyId: {
        "bookId": 1,
        "seq": 3
      },
      "status": "Suspended",
      "statusDetail": "Exceed 5 times overdue."
   }
   ```
         - Reseponse
   ```
   {
      "status_code": 200,
      "data": "BookCopy information is updated successfully."
   }
   ```