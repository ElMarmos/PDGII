package controllers;

import java.util.List;

import javax.persistence.Query;

import models.Cirugia;
import models.ProfesionalSalud;
import models.Quirofano;
import play.db.jpa.JPA;
import play.mvc.*;

public class Programacion extends Controller {

	
	
    public static void index() {
    	
		List<ProfesionalSalud> cirujanos = ProfesionalSalud.find("byProfesion", "Cirujano").fetch();
		List<ProfesionalSalud> anestesiologos = ProfesionalSalud.find("byProfesion", "Anestesiologos").fetch();
		List<Quirofano> quirofanos = Quirofano.findAll();
		List<ProfesionalSalud> asistentes = ProfesionalSalud.find("byProfesion", "Asistentes").fetch();

		render(cirujanos,anestesiologos,quirofanos,asistentes);        
    }
    
    public static void initProgramacion(){
    	
    }
    
    public static void matchCirugias(){
    	Query query = JPA.em().createQuery("select c from Cirugia c where (select count(*) from CirugiaProfesionalSalud cp where cp.cirugia = c and cp.rol = 'Cirujano') = 0 and c.estado = 'Solicitada'");
        List<Cirugia> cirugias = query.getResultList();
        List<ProfesionalSalud> cirujanos = ProfesionalSalud.find("byProfesion", "Cirujano").fetch();
        render(cirugias, cirujanos);
    }

}
