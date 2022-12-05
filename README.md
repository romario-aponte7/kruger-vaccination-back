# Krugger Challenge
1.	Clonar el proyecto en instalar las dependencias.
2.	Crear una base de datos en postgress localmente llamada vaccination.
3.	Iniciar por primera vez el proyecto
•	Se crean las estructuras de datos
•	Se cargan datos maestros.
4.	Autenticación (JWT)
•	{
    "username": "romario.aponte",
    "password": "TestProd#2122"
}
•	http://localhost:8080/login

5.	Posteriormente, el token que nos devolvió la petición hay que colocar en el Authorization
•	Type: Bearer Token 
      

6.	 En caso de que no tengan acceso, se les muestra respuesta 403 Forbiden, caso contrario se devuelve la información de acuerdo con la petición
 

   ENDPONITS A UTILIZAR PARA EMPLEADOS
@GetMapping("/getEmployeeId")
7.	http://localhost:8080/getEmployeesPaginated?searchValue=&page=0&size=10&initDate=1990-12-05%2011:10:57&endDate=2022-12-05%2011:10:57&status=VACCINE,NOT_VACCINE

@GetMapping("/getEmployeesPaginated")
8.	http://localhost:8080/getEmployeeId?employeeId=b80fd57e-7c09-4822-a6b1-96091072b86a

@PostMapping("saveUpdateEmployee")

9.	http://localhost:8080/saveUpdateEmployee


   ENDPONITS A UTILIZAR PARA VACUNAS
@GetMapping("/getVaccines")

10.	http://localhost:8080/getVaccines


