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
       - URL: /lms/admin/users/{userId}
       - Parameter(s):
         - userId: user ID
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
           "categoryName": "Java",
           "description": "Java related books"
         }
      ]
   }
   ```

