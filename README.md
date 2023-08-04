# ![Document Uploader Api](doc/img/title.png)

> Documents Uploader API - Sube y hashea archivos


![rating](https://img.shields.io/badge/Java-17-orange?style=flat-square) ![rating](https://img.shields.io/badge/spring%20boot%20starter%20parent-3.1.2-brighred?style=flat-square)


## 🚩 Tabla de contenidos

- [Descipcion](#-descripcion)
- [Dependencias](#-dependencias)
- [Endpoints](#-endpoints)
- [Swagger](#-swagger)



## 🎩 Descripcion

Esta es una api que permite subir archivos, los cuales no se guardan fisicamente.
Los nombre de los archivos se guardan en una BD de PostgreSQL asi como su version pasado por 
SHA-256 o SHA-512

Esta api permite gestinar los usuarios de la misma y permisos de la misma.


## 📦 Dependencias


| Dependencias                                |
|---------------------------------------------|
| spring-boot-starter-data-jpa                |
| spring-boot-starter-web                     |
| spring-boot-devtools                        |
| postgresql                                  |
| lombok                                      |
| spring-boot-starter-test                    |
| springdoc-openapi-starter-webmvc-ui         |
| spring-boot-starter-validation              |
| guava                                       |
| spring-boot-starter-data-mongodb            |
| spring-security-oauth2-authorization-server |
| spring-security-oauth2-resource-server      |


# Para poder hacer un login se debe ingresar a la siguiente URL

https://oauthdebugger.com/debug

# ![](doc/img/01.gif)



## 📦 Endpoints


### Obtener token

> curl --location 'http://localhost:8080/oauth2/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Authorization: Basic Y2xpZW50OnNlY3JldA==' \
--header 'Cookie: JSESSIONID=8F3C03E633204CAA6ED7400E13A8C0D1' \
--data-urlencode 'redirect_uri=https://oauthdebugger.com/debug' \
--data-urlencode 'grant_type=authorization_code' \
--data-urlencode 'code=7PJqE0_QDqE4c_4MBTEmzoZVBkvgC9Vylje2w6j0EoIqK-GtlHTy6MuhfECD8NvDaZcuCk5Gc7RvpOtenwwHVu1Zdit6LYjob6TK5q4wF-PU3NkwWxhNcVnBhMX3BgtZ'

### Obtener todos los Documentos del usuario actual

> curl --location 'http://localhost:8080/api/documents/' \
--header 'accept: */*' \
--header 'Authorization: Bearer eyJraWQiOiIwY2FlZDE1Ni1lNzdiLTQwZWYtODBkMy05MzBiY2Q0MDc0NjMiLCJhbGciOiJSUzI1NiJ9.eyJvd25lciI6IkJPWF9DSEFMTEVOR0VSIiwic3ViIjoiYm94MiIsImF1ZCI6ImNsaWVudCIsIm5iZiI6MTY5MTE4MjIxOCwic2NvcGUiOlsid3JpdGUiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwIiwiZXhwIjoxNjkxMTgyNTE4LCJpYXQiOjE2OTExODIyMTgsImRhdGVfcmVxdWVzdCI6IjIwMjMtMDgtMDRUMTc6NTA6MTguMDQzMjM3In0.XjNSDF2b_atAzB6qSwvqgTyQT8L7GGo44oYkapYxOTNpJbj2OTgH6j3QZf9_zTgi32dNYHNs9-WgFVyLGmq99QsgBZNTo-0tTn2BxCX_OUNhMAbpHD0esLfuI-ysd11aCO02jQ4BhD7cCwLkXpdbaH9y73tZnbhoTEW6LmJ5BxXGphObUvFvuPC0Oy826vmxG3vJuKEW3i1POCOECqqPMw2LLUGWCauZuSfsCCSs9o2mowinhb03wLWG6qJk4_9hLLlp3VVHYG3y02EZENjP9Jcz6SQ9pcW14wZA3EY2tVAaiRUOBeQNj9qSYws9Ni8W6My0vpfYpVQf0EOWFUlvKQ'


### Obtener un documento del usuario actual usando un hash

> curl --location 'http://localhost:8080/api/documents/' \
--header 'accept: */*' \
--header 'Authorization: Bearer eyJraWQiOiIwY2FlZDE1Ni1lNzdiLTQwZWYtODBkMy05MzBiY2Q0MDc0NjMiLCJhbGciOiJSUzI1NiJ9.eyJvd25lciI6IkJPWF9DSEFMTEVOR0VSIiwic3ViIjoiYm94MiIsImF1ZCI6ImNsaWVudCIsIm5iZiI6MTY5MTE4MjIxOCwic2NvcGUiOlsid3JpdGUiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwIiwiZXhwIjoxNjkxMTgyNTE4LCJpYXQiOjE2OTExODIyMTgsImRhdGVfcmVxdWVzdCI6IjIwMjMtMDgtMDRUMTc6NTA6MTguMDQzMjM3In0.XjNSDF2b_atAzB6qSwvqgTyQT8L7GGo44oYkapYxOTNpJbj2OTgH6j3QZf9_zTgi32dNYHNs9-WgFVyLGmq99QsgBZNTo-0tTn2BxCX_OUNhMAbpHD0esLfuI-ysd11aCO02jQ4BhD7cCwLkXpdbaH9y73tZnbhoTEW6LmJ5BxXGphObUvFvuPC0Oy826vmxG3vJuKEW3i1POCOECqqPMw2LLUGWCauZuSfsCCSs9o2mowinhb03wLWG6qJk4_9hLLlp3VVHYG3y02EZENjP9Jcz6SQ9pcW14wZA3EY2tVAaiRUOBeQNj9qSYws9Ni8W6My0vpfYpVQf0EOWFUlvKQ'

### Subir un documento e implementar una hash

> curl --location 'http://localhost:8080/api/documents/hash?algorithm=SHA-512' \
--header 'accept: */*' \
--header 'Authorization: Bearer eyJraWQiOiIwY2FlZDE1Ni1lNzdiLTQwZWYtODBkMy05MzBiY2Q0MDc0NjMiLCJhbGciOiJSUzI1NiJ9.eyJvd25lciI6IkJPWF9DSEFMTEVOR0VSIiwic3ViIjoiYm94MiIsImF1ZCI6ImNsaWVudCIsIm5iZiI6MTY5MTE4MjIxOCwic2NvcGUiOlsid3JpdGUiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwIiwiZXhwIjoxNjkxMTgyNTE4LCJpYXQiOjE2OTExODIyMTgsImRhdGVfcmVxdWVzdCI6IjIwMjMtMDgtMDRUMTc6NTA6MTguMDQzMjM3In0.XjNSDF2b_atAzB6qSwvqgTyQT8L7GGo44oYkapYxOTNpJbj2OTgH6j3QZf9_zTgi32dNYHNs9-WgFVyLGmq99QsgBZNTo-0tTn2BxCX_OUNhMAbpHD0esLfuI-ysd11aCO02jQ4BhD7cCwLkXpdbaH9y73tZnbhoTEW6LmJ5BxXGphObUvFvuPC0Oy826vmxG3vJuKEW3i1POCOECqqPMw2LLUGWCauZuSfsCCSs9o2mowinhb03wLWG6qJk4_9hLLlp3VVHYG3y02EZENjP9Jcz6SQ9pcW14wZA3EY2tVAaiRUOBeQNj9qSYws9Ni8W6My0vpfYpVQf0EOWFUlvKQ' \
--form 'files=@"/C:/Users/maxim/OneDrive/Documentos/HKJHK.txt"'


## 🌐 Swagger

🔗 URL: http://localhost:8080/swagger-ui.html

🔗 API-DOC: http://localhost:8080/v3/api-docs


