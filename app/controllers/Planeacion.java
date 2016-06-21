package controllers;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.helpers.QuietWriter;
import org.h2.constant.SysProperties;

import models.Cirugia;
import models.Indicador;
import models.ProfesionalSalud;
import models.Programacion;
import play.db.jpa.JPA;
import play.mvc.Controller;

public class Planeacion extends Controller {
	
	 public static void index() {
		 Query query = JPA.em().createQuery("SELECT p, COUNT(p.fechaProgramacion) as numProgramacion FROM "
		 		+ "Programacion p GROUP BY p.fechaProgramacion");
		 List<Object[]> programaciones = query.getResultList();
		 render(programaciones);
	 }
	 
	 public static void programacionesPorFecha (Date fechaProgramacion){
		 Query query = JPA.em().createQuery("SELECT p, AVG(i.valor) as avgindicador FROM "
			 		+ "Programacion p LEFT JOIN p.programacionIndicadores i WHERE p.fechaProgramacion = :datepro GROUP BY p.idProgramacion ORDER BY avgindicador DESC");
		 
		 query.setParameter("datepro", fechaProgramacion);
		 List<Object[]> programaciones = query.getResultList();
		 render(programaciones);
	 }
	 
	 public static void programacionDetails(String idProgramacion){
		 Programacion programacion = Programacion.findById(Integer.parseInt(idProgramacion));
		 List<Cirugia> cirugias = Cirugia.find("byProgramacion", programacion ).fetch();
		 render(cirugias);
	 }
	 
	 
}
