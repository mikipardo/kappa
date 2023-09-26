package es.redsys.seguridad.secure.simuladores.accesoDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import es.redsys.seguridad.secure.simuladores.constants.ConfigConstantsSimulator;


public class OperacionesBBDD extends SerClsSimuladorBD {

  private static Logger logger = Logger.getLogger(OperacionesBBDD.class);

  public boolean existsOpFromID(String id) throws Exception {

    String card = this.getCardFromID(id);

    if (card.equalsIgnoreCase(id)) {
      return true;
    } else {
      logger.error("Campo vacio o nulo");
      return false;
    }

  }

  public Map<String,String> getMapOpFromID(String id) {
    // usamos   LinkedHashMap para no desordenar los campos
    LinkedHashMap<String,String>mapOp= new   LinkedHashMap<String, String>();
    PreparedStatement stmt = null;


    try {
      if (id != null | !id.isEmpty()) {
       
        stmt = crearLogPreparedStatement(ConfigConstantsSimulator.QUERY_CZOCOPER_ACS);
        stmt.setString(1, id);// inyectamos campo posible
        ResultSet rs = stmt.executeQuery();
        ResultSetMetaData rsMetaData = rs.getMetaData();
       
        if (rs.next()) {
            for(int i = 1; i<=rsMetaData.getColumnCount();i++) {//para establecer nombre colum directamente al k,v
              if(rsMetaData.getColumnName(i).equals("CZOPOPER")) {
                String hora = rs.getString(i);// formateador de hora
                if(hora!=null&&!hora.isEmpty()) {
                  hora = String.valueOf(hora.subSequence(0, hora.lastIndexOf(".")));
                }
                mapOp.put(rsMetaData.getColumnName(i),hora);
              }else {
                mapOp.put(rsMetaData.getColumnName(i),rs.getString(i));
              }
            }         
        }else {
          
          logger.error("No se ha encontrado operacion para el QUERY_CZOCOPER_ACS: ",new Throwable(id));
        }
      } else {
        logger.error("Campo vacio o nulo: {}", new Throwable(ConfigConstantsSimulator.INVALID_CARD));

      }
      closeStatement(stmt);

    } catch (Exception e) {
      logger.error("[getOpFromID()]Error la busqueda de tarjeta = " + e.getMessage());

    }
    return mapOp;
  }
  
  public String getCardFromID(final String id) {

    PreparedStatement stmt = null;
    String card = "";

    try {
      if (id != null | !id.isEmpty()) {

        stmt = crearLogPreparedStatement(ConfigConstantsSimulator.QUERY_CZOCOPER_ACS);
        stmt.setString(1, id);// inyectamos campo posible
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
          card = rs.getString("CZOCTARJ");
          logger.info("Tarjeta obtenida de BD=[" + card + "]");

        }else {
          logger.info("No hay next=[" + card + "]->"+id);
        }
      } else {
        logger.error("Campo vacio o nulo: {}", new Throwable(ConfigConstantsSimulator.INVALID_CARD));
        card = ConfigConstantsSimulator.INVALID_CARD;
      }
      closeStatement(stmt);

    } catch (Exception e) {
      logger.error("[getOpFromID()]Error la busqueda de tarjeta = " + e.getMessage());

    }

    return card;
  }

}
