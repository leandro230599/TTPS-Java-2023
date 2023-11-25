import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ttps.spring.clasesDAO.UsuariosDAO;

public class TestSpringSimple {
 public static void main(String[] args) {
 AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

//registra una o más componentes que serán procesadas
 System.out.println("ejecuta main");
ctx.register(ttps.spring.config.PersistenceConfig.class);
System.out.println("PASO REGISTER");
//Busca clases anotadas en el paquete base pasado por parámetro
ctx.scan("ttps.spring");
System.out.println("PASO SCAN");
ctx.refresh();
System.out.println("PASO REFRESH");
UsuariosDAO usrDAO = ctx.getBean("usuariosDAOHibernateJPA", UsuariosDAO.class);
System.out.println(usrDAO.recuperarPorEmailYPassword("leo@gmail.com","123probando").getFirst_name());
 }
}