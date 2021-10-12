package ipsTeamwork.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ipsTeamwork.model.carrera.CarreraDto;

public class DtoBuilder {

	public static List<CarreraDto> toCarreraDtoList(ResultSet rs) {
		List<CarreraDto> ret = new ArrayList<CarreraDto>();
		CarreraDto dto = null;
		
		try {
			while (rs.next()) {
				dto = new CarreraDto();
				dto.setId(rs.getString("idCarrera"));
				dto.setNombre(rs.getString("nombre"));
				dto.setFecha(rs.getDate("fecha"));
				dto.setTipo(rs.getString("tipo"));
				dto.setDistancia(rs.getDouble("distancia"));
				dto.setCuota(rs.getFloat("cuota"));
				dto.setFechaFin(rs.getDate("fechaFinInsc"));
				dto.setPlazasDisp(rs.getInt("plazasDisp"));
				
				ret.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
}