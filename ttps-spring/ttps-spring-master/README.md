# ttps-spring

Que no se pueda meter un usuario a un grupo al que ya pertenece


Controllers/DAO/Etc para:

- Invitar amigo
- Aceptar amigo
- Eliminar amigo
- Salir de grupo
- Actualizar grupo
- Actualizar gasto
- Crear pago
- Obtener lista de amigos, integrantes de grupos a los que pertenece o ha pertenecido
- Ver gastos realizados por integrante y deudas de cada uno


Problemas

- Al crear usuario, si ID duplicada > error, agarrarlo y hacer algo : java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '1' for key 'PRIMARY'
- Revisar respuestas para que devuelva JSON