package conectar;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.Statement; 
import java.sql.ResultSet; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement;

/**
 *
 * @author Aprendiz Sena
 */
public class ConeccionBD {
    private String url="jdbc:mysql://localhost:3306/";
    private String bd="sisbibi";
    private String user="root";
    private String password="";
    private String driver="com.mysql.cj.jdbc.Driver";
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    
    public ConeccionBD(){
        try{
            Class.forName(driver);
            con=DriverManager.getConnection(url + bd, user, password);
            st= con.createStatement();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
     public void insertar(){
        Statement st = null;
        try{
            st= con.createStatement();
            String query ="INSERT INTO autor (Cod_autor, Nombre ,apellido) VALUES (404, 'Paula' , 'Rosas')";
            st.executeUpdate(query);
            System.out.println("El autor se ingreso correctamente");
            
        } catch (Exception ex){
            ex.printStackTrace();
        }finally{
            try {
                if (st != null) st.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } 
    }
     
     
    public void consultar(){
        Statement st = null;
        ResultSet rs = null;
        try{
           st = con.createStatement();
           String query="SELECT * FROM autor";
           rs=st.executeQuery(query);
           while(rs.next()){
               int Cod_autor = rs.getInt("Cod_autor");
               String Nombre = rs.getString("Nombre");
               String apellido = rs.getString("apellido");
               System.out.println("--------\nCod_autor: " + Cod_autor + "\nNombre:" + Nombre + "\napellido:"+ apellido);
           }
        }catch(Exception ex){
            ex.printStackTrace();
        } finally{
            try{
                if (rs != null) rs.close();
                if (st != null) st.close();
            }catch(Exception ex){
               ex.printStackTrace();
            }
        }
    }
    
    
    public void actualizar(int Cod_autor, String nuevoNombre, String nuevoapellido){
        //consulta de parametros
        String query ="UPDATE autor SET Nombre =?, apellido =? WHERE Cod_autor=? ";
        try(PreparedStatement ps = con.prepareStatement(query)){
            
            ps.setString(1, nuevoNombre);
            ps.setString(2, nuevoapellido);
            ps.setInt(3, Cod_autor);
            
            //ejecucion de la actualizacion
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0){
                System.out.println("El autor con codigo" + Cod_autor + "se actualizo correctamente.");
            }else {
                System.out.println("No se encontro el autor con el codigo"+  Cod_autor + "para actualizar");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
   }
    
   public void eliminar(int Cod_autor){
       String query ="DELETE FROM autor WHERE Cod_autor =?";
       try(PreparedStatement ps =con.prepareStatement(query)){
           ps.setInt(1, Cod_autor);
           
           int filasAfectadas = ps.executeUpdate();
           if (filasAfectadas > 0){
               System.out.println("No se encontro el autor con el codigo " + Cod_autor + "para eliminar");
           }
       }
       catch (Exception ex){
           ex.printStackTrace();
       }
   } 
}
