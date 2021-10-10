package ipsTeamwork.controller;

public class SQLStrings {

	/**
	 * Aqui ponemos todas las queries y luego las usamos con el nombre sin más, por
	 * tenerlas ordenadas
	 */

	// creates
	public static String createAtleta = "CREATE TABLE atleta (idAtleta varchar2 NOT NULL, dni varchar2 not null, nombre varchar2 not null, edad integer not null, sexo varchar not null, discapacitado bit NOT NULL, CONSTRAINT CHK_Atleta CHECK (edad >18 AND (sexo='M' OR sexo='F' OR sexo='NB')) , primary key (idAtleta))";

	public static String createInscripcion = "CREATE TABLE inscripcion (idAtleta varchar2 NOT NULL,idCarrera varchar NOT NULL, dorsal varchar2 NOT NULL, fechaInscripcion date not null, estadoInscripcion varchar2 not null, formaDePago varchar2 NOT NULL, CONSTRAINT CHK_Inscripcion CHECK ( (formaDePago='Transferencia' OR formaDePago='Tarjeta') AND (estadoInscripcion='Inscrito' OR estadoInscripcion='No inscrito' OR estadoInscripcion='Pendiente de pago')), primary key (idAtleta, idCarrera, dorsal), CONSTRAINT FK_idAtleta FOREIGN KEY (idAtleta) REFERENCES atleta(idAtleta), CONSTRAINT FK_idCarrera FOREIGN KEY (idCarrera) REFERENCES carrera(idCarrera) )";

	public static String createCarrera = "CREATE TABLE carrera (idCarrera varchar2 NOT NULL,tipo varchar2 NOT NULL, maxPlazas integer NOT NULL, CONSTRAINT CHK_Atleta CHECK (tipo = 'Asfalto' OR tipo = 'Montaña' ) , primary key (idCarrera))";

	// inserts

	public static String insterBolt = "Insert into atleta values('96','11122234A','Bolt',35,'F',0); ";
	public static String insterUsain = "Insert into atleta values('69','11122233A','Usain',25,'M',0); ";

	public static String insterNewYork = "Insert into carrera values('5','Asfalto',25); ";

	public static String insterInscripcion1 = "Insert into inscripcion values('96','5','009','2021-05-01','Inscrito', 'Tarjeta'); ";

	public static String insterInscripcion2 = "Insert into inscripcion values('96','a0931d2e-cf27-4965-b395-a086aa0228a5','015','2021-05-01','Inscrito', 'Tarjeta'); ";

	public static String insertCarreraValues = "insert into carrera(idCarrera, tipo, maxPlazas) values (?, ?, ?)";

	// selects
	public static String AtletaEjemplo = "select * from atleta";

	public static String CarreraEjemplo = "select * from carrera";

	public static String InscripcionEjemplo = "select * from inscripcion";

	// selects
	public static String selectCarrera = "select * from carrera";

	// Consulta para el metodo estadoInscripcion
	protected static String estadoInscipcion = "select * from inscripcion i where idCarrera = ? and estadoInscripcion = 'Inscrito' order by fechaInscripcion, estadoInscripcion";
	protected static String estadoInscipcionAtleta = "select * from atleta where idAtleta = ?";
}