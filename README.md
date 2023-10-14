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

## REST APIs Specification
 - ### Authentication Service (/lms/auth)
   - #### Login
     - Method: POST
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
           "roleCd": "Admin"
        }
     }
     ```
   - #### Logout
     - Method: POST
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
   - #### Verify
     - Method: POST
     - Body: just token value getting from Cookie
     ```
     eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzd2Vwcm9qZWN0IiwiaWQiOiJiYW8iLCJyb2xlX2NkIjoiQWRtaW4iLCJjcmVfZHQiOjE2OTcyNTY2MTg4Mjl8.uDiD7act-_U0LADinXZbYAWpoDWVqvLo3600-Xp_8rs
     ```
     - Response:
     ```
     {
        "status_code": 200,
        "data": {
            "user_id": "bao",
            "role_cd": "Admin",
            "cre_dt": "2023-10-13 23:38:48"
        },
        "message": "TOKEN IS VERIFIED SUCCESSFULLY"
     }
     ```
 -  