/*Nota fuera, una variable estatica afecta a la clase no al objeto, es decir que si creas muchos
hilos e intancias esa vaariable estática y cambias el resultado, afectará a todos los
obejtos de esa clase

Esto puede afectar a programacion multihilos o concurrente ya que puede
haber varios objetos que accedan a esa varirable que es Compartida
ya que el pc cuando hay muchos hilos puede suspender algún hilo por 
concurrencia y que salten los hilos de manera no jerarquica o
por orden
*/
private static String variableEjemplo;
public class unaClase{
public  unaClase(){
  
}
