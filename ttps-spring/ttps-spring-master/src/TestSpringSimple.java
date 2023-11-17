import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ttps.spring.clasesDAO.UsuariosDAO;

public class TestSpringSimple {
 public static void main(String[] args) {
 AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

//registra una o más componentes que serán procesadas
ctx.register(ttps.spring.config.PersistenceConfig.class);
//Busca clases anotadas en el paquete base pasado por parámetro
ctx.scan("ttps.spring");
ctx.refresh();
UsuariosDAO usrDAO = ctx.getBean("usuarioDAOImpl", UsuariosDAO.class);
System.out.println(usrDAO.recuperarPorEmailYPassword("leo@gmail.com","123probando").getFirst_name());
 }
}