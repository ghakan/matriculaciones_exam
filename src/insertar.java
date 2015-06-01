import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class insertar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USUARIO="root";
	private static final String CONTRASENA="zubiri";
	static final String URL_BD="jdbc:mysql://localhost/loginDB";
       
    public insertar() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		Statement sentencia = null;
		response.setContentType("text/html;charset=utf-8");
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String ano = request.getParameter("ano");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Mostrar </title>");
		out.println("<link rel='stylesheet' type='text/css' href='stylebd.css'>");
		out.println("</head>");
		out.println("<body>");
		out.println("DNI: "+dni);
		out.println("Nombre: "+nombre);
		out.println("Apellido: "+apellido);
		out.println("Año nacimiento: "+ano);
		out.println("<br> <a href='index.html'> <button>Volver</button> </a>");
		out.println("</body>");
		out.println("</html>");
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			try{
				con = DriverManager.getConnection(URL_BD,USUARIO,CONTRASENA);
				}catch(Exception e){
					Connection creacion = null;
					creacion = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=zubiri"); 
					sentencia = creacion.createStatement();
				    int resultado = sentencia.executeUpdate("CREATE DATABASE matriculaciones");
				    if(resultado==1){
						con = DriverManager.getConnection(URL_BD,USUARIO,CONTRASENA);
				    }else{
				    	//no se creo la base de datos
				    }
				    sentencia = con.createStatement();	        
					String sql;
					sql="CREATE TABLE IF NOT EXISTS matriculas (dni VARCHAR(9), nombre VARCHAR(20) apellido VARCHAR(20), ano_nacimiento VARCHAR(10) PRIMARY KEY (dni))";
					sentencia.executeUpdate(sql);
				}
		}catch(Exception e){
					
		}
		try {
			sentencia = con.createStatement();        
			String sql;
			sql="INSERT INTO matriculas(din, nombre, apellido, ano_nacimiento) VALUES (\""+dni+"\",\""+nombre+"\",\""+apellido+"\",\""+ano+"\")";
			System.out.println(sql);
			int crear=sentencia.executeUpdate(sql);
		}catch(Exception e){
			
		}
	}

}
