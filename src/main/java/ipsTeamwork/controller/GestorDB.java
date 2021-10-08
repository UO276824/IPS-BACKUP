package ipsTeamwork.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import ipsTeamwork.model.atleta.AtletaDto;
import ipsTeamwork.model.inscripcion.InscripcionDto;


/**
 * Clase que accede a la base de datos y tiene métodos para sacar y añadir datos
 * 
 * @author Sergio Arroni del Riego
 *
 */
public class GestorDB {

	private Connection conn = null;

	private PreparedStatement pst = null;

	private ResultSet rs = null;

	/**
	 * Método que establece una conexion con la base de datos.
	 */
	private void conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:database.db");
		} catch (ClassNotFoundException e) {
			System.out.println("Error en la conexión de la base de datos: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error en la conexión de la base de datos: " + e.getMessage());
		}
	}

	/**
	 * Metodo que cierra la base de datos
	 */
	private void cerrar() {
		try {

			if (pst != null)
				pst.close();

			if (rs != null)
				rs.close();

			if (conn != null)
				conn.close();

		} catch (SQLException e) {
			System.out.println("Error al cerrar la base de datos: " + e.getMessage());
		}
	}

	// ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇
	// |CREAR TABLAS|
	// ˭˭˭˭˭˭˭˭˭˭˭˭˭˭

	public void crearTablas() {
		conectar();
		try {

			pst = conn.prepareStatement(SQLStrings.createAtleta);
			pst.execute();
			pst = conn.prepareStatement(SQLStrings.createCarrera);
			pst.execute();
			pst = conn.prepareStatement(SQLStrings.createInscripcion);
			pst.execute();

		} catch (SQLException e) {
			System.out.println("Error al crear tabla en la base de datos: " + e.getMessage());
		} finally {
			cerrar();
		}
	}

	// ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇
	// |BORRAR TABLAS|
	// ˭˭˭˭˭˭˭˭˭˭˭˭˭˭

	public void borrarTablas() {
		conectar();
		try {
			pst = conn.prepareStatement("drop table atleta");
			pst.execute();

			pst = conn.prepareStatement("drop table carrera");
			pst.execute();

			pst = conn.prepareStatement("drop table inscripcion");
			pst.execute();

		} catch (SQLException e) {
			System.out.println("Error al borrar tabla en la base de datos: " + e.getMessage());
		} finally {
			cerrar();
		}
	}

	public void borrarTablas(boolean all) {
		if (!all) {
			conectar();
			try {
				pst = conn.prepareStatement("drop table Inscripcion");
				pst.execute();
			} catch (SQLException e) {
				System.out.println("Error en la la base de datos: " + e.getMessage());
			} finally {
				cerrar();
			}
		} else {
			conectar();
			try {
				pst = conn.prepareStatement("drop table *");
				pst.execute();
				pst.close();
			} catch (SQLException e) {
				System.out.println("Error en la la base de datos: " + e.getMessage());
			} finally {
				cerrar();
			}
		}
	}

	// ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇
	// |INSERT DATOS |
	// ˭˭˭˭˭˭˭˭˭˭˭˭˭˭˭˭˭

	public void insertarAtleta() {
		conectar();
		try {

			pst = conn.prepareStatement(SQLStrings.insterUsain);
			pst.execute();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: " + e.getMessage());
		} finally {
			cerrar();
		}
	}

	public void insertarCarrera() {
		conectar();
		try {
			pst = conn.prepareStatement(SQLStrings.insterNewYork);
			pst.execute();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: " + e.getMessage());
		} finally {
			cerrar();
		}
	}

	public void insertarInscripcion() {
		conectar();
		try {
			pst = conn.prepareStatement(SQLStrings.insterInscripcion1);
			pst.execute();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: " + e.getMessage());
		} finally {
			cerrar();
		}
	}

	// ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇ ͇
	// |BORRAR CARRERAS|
	// ˭˭˭˭˭˭˭˭˭˭˭˭˭˭˭˭˭

	public void deleteAtleta() {
		conectar();
		try {
			pst = conn.prepareStatement("delete from atleta where idAtleta = '69' ");
			pst.execute();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos: " + e.getMessage());
		} finally {
			cerrar();
		}
	}

	public void runScript(String script) {
		conectar();
		try {
			PreparedStatement ps = conn.prepareStatement(script);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			System.out.println("Error de script de DB: " + e.getMessage());
		} finally {
			cerrar();
		}
	}

	public void selectAtletas() {
		conectar();
		try {
			PreparedStatement ps = conn.prepareStatement(SQLStrings.AtletaEjemplo);
			ResultSet rs = ps.executeQuery();

			printResultSet(rs);

		} catch (SQLException e) {
			System.out.println("Error de script de DB: " + e.getMessage());
		} finally {
			cerrar();
		}
	}

	public void selectCarrera() {
		conectar();
		try {
			PreparedStatement ps = conn.prepareStatement(SQLStrings.CarreraEjemplo);
			ResultSet rs = ps.executeQuery();
			printResultSet(rs);
		} catch (SQLException e) {
			System.out.println("Error de script de DB: " + e.getMessage());
		} finally {
			cerrar();
		}
	}

	public void selectInscripcion() {
		conectar();
		try {
			PreparedStatement ps = conn.prepareStatement(SQLStrings.InscripcionEjemplo);
			ResultSet rs = ps.executeQuery();

			printResultSet(rs);

		} catch (SQLException e) {
			System.out.println("Error de script de DB: " + e.getMessage());
		} finally {
			cerrar();
		}
	}

	/**
	 * utilidad para imprimir resultsets por consola
	 * 
	 * @param rs
	 * @throws SQLException
	 */
	public static void printResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1)
					System.out.print(" | ");
				System.out.print(rs.getString(i));
			}
			System.out.println("");
		}
	}

	/**
	 * @author Sergio Arroni
	 * 
	 *         Partiendo de la lista de competiciones y seleccionando una, el
	 *         organizador visualizará un listado con los atletas que están
	 *         inscritos hasta el momento actual (DNI, Nombre, Categoría, Fecha de
	 *         Inscripción y Estado de Inscripción). Estarán ordenados por fecha
	 *         inscripción y estado de la inscripción.
	 * @return
	 * 
	 */
	public ArrayList<InscripcionDto> estadoInscripcion(String idCarrera) {
		conectar();

		ArrayList<InscripcionDto> atletasInscritos = new ArrayList<InscripcionDto>();

		try {
			pst = conn.prepareStatement(SQLStrings.estadoInscipcion);

			pst.setString(1, idCarrera);

			rs = pst.executeQuery();

			while (rs.next()) {

				AtletaDto nuevoAtleta = new AtletaDto();
				InscripcionDto inscripcion = new InscripcionDto();

				nuevoAtleta.dni = rs.getString("DNI");
				nuevoAtleta.edad = rs.getInt("edad");
				nuevoAtleta.sexo = rs.getString("sexo");
				nuevoAtleta.nombre = rs.getString("nombre");
				// Esto es para que me lea bien la fecha
				inscripcion.estadoInscripcion = rs.getString("estadoInscripcion");
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("fechaInscripcion"));
				inscripcion.fechaInscripcion = new java.sql.Date(date.getTime());
				inscripcion.atleta = nuevoAtleta;

				atletasInscritos.add(inscripcion);
			}

		} catch (SQLException e) {
			System.out.println("Error en la base de datos: " + e.getMessage());
		} catch (ParseException e) {
			System.out.println("Error en el date: " + e.getMessage());
		} finally {
			cerrar();
		}
		return atletasInscritos;
	}
	
	
	/**
	 * @author Juan Torrente
	 * 
	 * este metodo solamente llama a los demas poblarXX() de cada tabla
	 */
	public void poblarTablas() {
		poblarCarreras(25);
	}
	
	public void poblarCarreras(int i) {
		conectar();
		Random r = new Random();
		try {
			for (int j = 0; j < i; j++) {
				PreparedStatement pst = conn.prepareStatement(SQLStrings.insertCarreraValues);
				pst.setString(1, UUID.randomUUID().toString());
				pst.setString(2, (r.nextBoolean() ? "Asfalto" : "Montaña"));
				pst.setInt(3, r.nextInt(128));
				
				pst.executeUpdate();
				pst.close();
			}
		} catch (Exception e) {
			
		} finally {
			cerrar();
		}
	}

}