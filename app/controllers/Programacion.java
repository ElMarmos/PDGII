package controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import models.Cirugia;
import models.Cirujano;
import models.Planeacion;
import models.PlaneacionProfesionales;
import models.PlaneacionQuirofanos;
import models.ProfesionalSalud;
import models.Quirofano;
import models.Solicitud;
import play.db.jpa.JPA;
import play.mvc.*;
import play.mvc.results.RenderTemplate;

@With(Secure.class)
public class Programacion extends Controller {

	
	
    public static void index() {
    	
		List<ProfesionalSalud> cirujanos = ProfesionalSalud.find("byProfesion", "Cirujano").fetch();
		List<ProfesionalSalud> anestesiologos = ProfesionalSalud.find("byProfesion", "Anestesiologo").fetch();
		List<Quirofano> quirofanos = Quirofano.findAll();
		List<ProfesionalSalud> asistentes = ProfesionalSalud.find("byProfesion", "Asistente").fetch();

		render(cirujanos,anestesiologos,quirofanos,asistentes);        
    }
    
    
    public static void initProgramacion(String dateBegin, String dateEnd, int numPacientes, int[] cirujanos, int[] anestesiologos, int[] quirofanos, int[] asistentes, int timeProcessing ){
    	//int numPacientes = Integer.parseInt(params.get("numPacientes"));
    	//String DateInit = params.get("fechainit");
    	//String DateEnd = params.get("fechaend");
    	Planeacion planeacion = new Planeacion();
    	DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		
	    	Date fechaInit = sourceFormat.parse(dateBegin);
	    	Date fechaEnd = sourceFormat.parse(dateEnd);
	    	planeacion.setFechaInicio(fechaInit);
	    	planeacion.setFechaProgramacion(Calendar.getInstance().getTime());
	    	planeacion.setFechaFin(fechaEnd);
	    	planeacion.setNumeroPacientes(numPacientes);
	    	planeacion.setTiempoProcesamiento(timeProcessing);
	    	
	    	planeacion = planeacion.save();
	    	
	    	for (int idQuirofano : quirofanos) {
				Quirofano quirofano = Quirofano.findById(idQuirofano);
				PlaneacionQuirofanos planeacionQuirofanos = new PlaneacionQuirofanos();
				planeacionQuirofanos.setPlaneacion(planeacion);
				planeacionQuirofanos.setQuirofano(quirofano);
				planeacionQuirofanos.save();
			}
	    	
	    	for (int idProfesional : cirujanos) {
	    		ProfesionalSalud profeSalud = ProfesionalSalud.findById(idProfesional);
				PlaneacionProfesionales planeacionProfesionales = new PlaneacionProfesionales();
				planeacionProfesionales.setPlaneacion(planeacion);
				planeacionProfesionales.setProfesionalSalud(profeSalud);
				planeacionProfesionales.setRol("Cirujano");
				planeacionProfesionales.save();
			}
	    	
	    	for (int idProfesional : anestesiologos) {
	    		ProfesionalSalud profeSalud = ProfesionalSalud.findById(idProfesional);
				PlaneacionProfesionales planeacionProfesionales = new PlaneacionProfesionales();
				planeacionProfesionales.setPlaneacion(planeacion);
				planeacionProfesionales.setProfesionalSalud(profeSalud);
				planeacionProfesionales.setRol("Anestesiologo");
				planeacionProfesionales.save();
			}
	    	
	    	for (int idProfesional : asistentes) {
	    		ProfesionalSalud profeSalud = ProfesionalSalud.findById(idProfesional);
				PlaneacionProfesionales planeacionProfesionales = new PlaneacionProfesionales();
				planeacionProfesionales.setPlaneacion(planeacion);
				planeacionProfesionales.setProfesionalSalud(profeSalud);
				planeacionProfesionales.setRol("Asistente");
				planeacionProfesionales.save();
			}
	    	redirect("/");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public static void matchCirugias(){
    	Query query = JPA.em().createQuery("select s from Solicitud s where s.cirujano is null or s.cirujano.idCirujano = 0");
    	List<Solicitud> solicitudes = query.getResultList();
    	
        List<ProfesionalSalud> cirujanos = ProfesionalSalud.find("byProfesion", "Cirujano").fetch();
        render(solicitudes, cirujanos);
    }
    
    public static void saveMatchSurgeries(){
    	Query query = JPA.em().createQuery("select s from Solicitud s where s.cirujano is null or s.cirujano.idCirujano = 0");
    	List<Solicitud> solicitudes = query.getResultList();
    	for (Solicitud solicitud : solicitudes) {
			if(params._contains(solicitud.getIdSolicitud()+"")){
				try{
					int idProfesional = Integer.parseInt(params.get(solicitud.getIdSolicitud()+""));
					if(idProfesional != 0){
						Cirujano cirujano = Cirujano.find("select c from Cirujano c where c.profesionalsalud.idProfesionalSalud = ?", idProfesional).first();
						solicitud.setCirujano(cirujano);
						solicitud.save();
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
    	redirect("/");
    }

}
