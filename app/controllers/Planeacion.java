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
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import models.Cirugia;
import models.Indicador;
import models.ProfesionalSalud;
import models.Programacion;
import models.Quirofano;
import play.db.jpa.JPA;
import play.libs.Time;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
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
		 List<Programacion> programaciones = Programacion.find("byPlaneacion", programacion.getPlaneacion()).fetch();
		 //List<Programacion> programaciones = programacion.getPlaneacion().getPlaneacionProgramaciones();
		 
		 List<Cirugia> cirugias = Cirugia.find("programacion = ? order by quirofano", programacion).fetch();
		 List<Quirofano> quirofanos = new ArrayList<Quirofano>();
		 for (Cirugia cirugia : cirugias) {
			if(!quirofanos.contains(cirugia.getQuirofano())){
				quirofanos.add(cirugia.getQuirofano());
			}
		}
		 render(cirugias,quirofanos, programacion, programaciones);
	 }
	 
	 public static void compareProgramacion(int idProgramacion){
		 Programacion programacion = Programacion.findById(idProgramacion);
		 render (programacion);
	 }
	 
	 public static String modificarCirugia(String json){
		 
		 String message = "";
		 try {
			 JSONArray cirugias = new JSONArray(json);
			 for(int i = 0; i < cirugias.length(); i++){
				 JSONObject cirugiajson = cirugias.getJSONObject(i);
				 Cirugia cirugia = Cirugia.findById(cirugiajson.getInt("id"));
				 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				 
		        String initDate = cirugiajson.getString("start_date");
		        String endDate = cirugiajson.getString("end_date");
		        
		        	
		            Date dateStart = formatter.parse(initDate);
		            Date dateEnd = formatter.parse(endDate);
		            cirugia.setFechaIngreso(dateStart);
		            cirugia.setHoraCierre(dateEnd);
		            cirugia.save();
			 }
			 message = "Se realizo la modificación exitosamente";
		 } catch (ParseException e) {
            e.printStackTrace();
            message = "Ocurrio un error al hacer la modificación";
		 }
		 
		 return message;
	}
	 
	 public static void changeQuirofanoCirugia(int idProgramacion){
		 Programacion programacion = Programacion.findById(idProgramacion);
		 render(programacion);
	 }
	 
	 public static void changeSurgeryQuirofano(int cirugia, int quirofano, Date dateinit, Date dateEnd, Time timeInit, Time timeEnd){
		 Cirugia cirugiaObj = Cirugia.findById(cirugia);
	 }

	 
	 public static String establecerProgramacionPrincipal(int idProgramacion){
		 Programacion programacion = Programacion.findById(idProgramacion);
		 
		 try{
			 for (Programacion pro : programacion.getPlaneacion().getPlaneacionProgramaciones()) {
				if(pro.getIdProgramacion() == idProgramacion){
					pro.setPrincipal(true);
					pro.save();
				}else{
					pro.setPrincipal(false);
					pro.save();
				}
			}
			 return "Se establecio la programación exitosamente";
		 }catch(Exception  e){
			 e.printStackTrace();
		 }
		 return "Error al establecer la programación com principal";
	 }
}
