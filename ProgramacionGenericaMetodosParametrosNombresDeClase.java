package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URLClassLoader;

public class CentroFuncion {

  public static void main(String[] args) {

 
    
    PersonaHabladora p =new PersonaHabladora();
    
    Class c = p.getClass(); new <Object> Object();
    try {
      Method lMethod=  c.getMethod("despedir",int.class, String.class);
      
     Method [] metodos = c.getMethods();
  // para saber los los tipos de argumentos por parmetros ejem arg0 int
     // en el caso de String es java.lang.String (viene nombre de paquete)
     for (Method method : metodos) {
       
      System.out.println("Metodo \n"+method.toString());
      System.out.println("Parametros");
      for (Parameter parameter : method.getParameters()) {
        System.out.println(parameter.getType().getCanonicalName());
      }
    }
      
      
      System.err.println(lMethod.toString()+"\n"+lMethod.getName());
      
      // con class for name con canoical name que viene con la extension
//      del paquete de la clase podemos instanciar de vuelta un objeto class que haya sido instanciado
      
      lMethod.invoke(Class.forName(c.getCanonicalName()).newInstance(), 3,"Manolo");
      
     System.err.println( c.getCanonicalName());
     System.out.println();
    }catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
