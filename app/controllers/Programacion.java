package controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import models.Cirugia;
import models.Planeacion;
import models.PlaneacionProfesionales;
import models.PlaneacionQuirofanos;
import models.ProfesionalSalud;
import models.Quirofano;
import play.db.jpa.JPA;
import play.mvc.*;

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
	    	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void matchCirugias(){
    	Query query = JPA.em().createQuery("select c from Cirugia c where (select count(*) from CirugiaProfesionalSalud cp where cp.cirugia = c and cp.rol = 'Cirujano') = 0 and c.estado = 'Solicitada'");
        List<Cirugia> cirugias = query.getResultList();
        List<ProfesionalSalud> cirujanos = ProfesionalSalud.find("byProfesion", "Cirujano").fetch();
        render(cirugias, cirujanos);
    }

}
