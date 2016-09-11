package controllers;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Query;

import org.apache.log4j.helpers.QuietWriter;
import org.h2.constant.SysProperties;

import com.google.gson.Gson;

import models.Cirugia;
import models.Indicador;
import models.ProfesionalSalud;
import models.Programacion;
import play.db.jpa.JPA;
import play.mvc.Controller;

public class Planeacion extends Controller {
	
	 public static void index() {
		 List<models.Planeacion> planeaciones = models.Planeacion.find("order by fechaProgramacion desc").fetch();
		 /* Query query = JPA.em().createQuery("SELECT p, COUNT(p.fechaProgramacion) as numProgramacion FROM "
		 		+ "Programacion p GROUP BY p.fechaProgramacion");
		 List<Object[]> programaciones = query.getResultList(); */
		 render(planeaciones);
	 }
	 
	 public static void programacionesPorFecha (String dateProgramacion){
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S", Locale.ENGLISH);
		 try {
			Date date = format.parse(dateProgramacion);
			
			Query query = JPA.em().createQuery("SELECT p, AVG(i.valor) as avgindicador FROM "
			 		+ "Programacion p LEFT JOIN p.programacionIndicadores i WHERE p.fechaProgramacion = :datepro GROUP BY p.idProgramacion ORDER BY avgindicador DESC");
			query.setParameter("datepro", date);
			List<Object[]> programaciones = query.getResultList();
			render(programaciones);
		 } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	 }
	 
	 public static void programacionesPorPlaneacion (int idPlaneacion){
		 try {
			Query query = JPA.em().createQuery("SELECT p, AVG(i.valor) as avgindicador FROM "
			 		+ "Programacion p LEFT JOIN p.programacionIndicadores i WHERE p.planeacion.idPlaneacion = :idPlaneacion GROUP BY p.idProgramacion ORDER BY avgindicador DESC");
			query.setParameter("idPlaneacion", idPlaneacion);
			List<Object[]> programaciones = query.getResultList();
			render(programaciones);
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	 }
	 
	 public static void programacionDetails(String idProgramacion){
		 
		 Programacion programacion = Programacion.findById(Integer.parseInt(idProgramacion));
		 List<Cirugia> cirugias = Cirugia.find("byProgramacion", programacion ).fetch();
		 render(cirugias);
		 
		 /**try{
			 Programacion programacion = Programacion.findById(Integer.parseInt(idProgramacion));
			 
			 System.out.println(dcirugias.size());
			 System.out.println(dcirugias.get(0).getIdCirugia());
			 JSONObject cirugias = new JSONObject();
			 JSONArray arrSurge = new JSONArray();
			 for (Cirugia cirugia : dcirugias) {
				JSONObject surgery = new JSONObject();
				surgery.put("idQuirofano", cirugia.getQuirofano().getIdQuirofano());
				surgery.put("nombreQuirofano", cirugia.getQuirofano().getNombreQuirofano());
				surgery.put("fechaIngreso", cirugia.getFechaIngreso());
				surgery.put("horaCierre", cirugia.getHoraCierre());
				arrSurge.put(surgery);
			 }
			 cirugias.put("cirugias", arrSurge);
			 System.out.println(cirugias.toString());
			 render(cirugias);
		 }catch(JSONException je){
			 String error = je.getMessage();
			 render(error);
		 }catch(Exception e){
			 String error = e.getMessage();
			 render(error);
		 }
		 */
	 }
	 
	 
}
