package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.BloqueQuirurgico;
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

public class Programacion extends Controller {

	
	
    public static void index() {
    	
		List<ProfesionalSalud> cirujanos = ProfesionalSalud.find("profesion = ? ORDER BY nombres", "Cirujano").fetch();
		List<ProfesionalSalud> anestesiologos = ProfesionalSalud.find("profesion = ? ORDER BY nombres", "Anestesiologo").fetch();
		List<Quirofano> quirofanos = Quirofano.find("ORDER BY nombreQuirofano ").fetch();
		List<ProfesionalSalud> asistentes = ProfesionalSalud.find("profesion = ? ORDER BY nombres", "Asistente").fetch();

		render(cirujanos,anestesiologos,quirofanos,asistentes);        
    }
    
    
    public static void initProgramacion(String dateBegin, String dateEnd, int numPacientes, int[] cirujanos, int[] anestesiologos, int[] quirofanos, int[] asistentes, int timeProcessing ){
    	//int numPacientes = Integer.parseInt(params.get("numPacientes"));
    	//String DateInit = params.get("fechainit");
    	//String DateEnd = params.get("fechaend");
    	Planeacion planeacion = new Planeacion();
    	DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
    	JSONObject json = new JSONObject();
    	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    	try {
    		
	    	Date fechaInit = sourceFormat.parse(dateBegin);
	    	Date fechaEnd = sourceFormat.parse(dateEnd);
	    	planeacion.setFechaInicio(fechaInit);
	    	planeacion.setFechaProgramacion(Calendar.getInstance().getTime());
	    	planeacion.setFechaFin(fechaEnd);
	    	planeacion.setNumeroPacientes(numPacientes);
	    	planeacion.setTiempoProcesamiento(timeProcessing);
	    	
	    	planeacion = planeacion.save();
	    	
	    	json.put("planeacion", new JSONObject(gson.toJson(planeacion)));
	    	
	    	
	    	String sql = "fechaProgramacion IS NULL AND cirujano IS NOT NULL ORDER BY paciente.prioridad LIMIT "+ numPacientes;
	    	List<Solicitud> solicitudes = Solicitud.find(sql).fetch();
	    	json.put("solicitudes", new JSONArray(gson.toJson(solicitudes)));
	    	
	    	
	    	List<Quirofano> quirofanosList = new ArrayList<Quirofano>();
	    	for (int idQuirofano : quirofanos) {
				Quirofano quirofano = Quirofano.findById(idQuirofano);
				PlaneacionQuirofanos planeacionQuirofanos = new PlaneacionQuirofanos();
				planeacionQuirofanos.setPlaneacion(planeacion);
				planeacionQuirofanos.setQuirofano(quirofano);
				planeacionQuirofanos.save();
				quirofanosList.add(quirofano);
			}
	    	json.put("quirofanos", new JSONArray(gson.toJson(quirofanosList)));
	    	
	    	List<ProfesionalSalud> cirujanosList = new ArrayList<ProfesionalSalud>();
	    	for (int idProfesional : cirujanos) {
	    		ProfesionalSalud profeSalud = ProfesionalSalud.findById(idProfesional);
				PlaneacionProfesionales planeacionProfesionales = new PlaneacionProfesionales();
				planeacionProfesionales.setPlaneacion(planeacion);
				planeacionProfesionales.setProfesionalSalud(profeSalud);
				planeacionProfesionales.setRol("Cirujano");
				planeacionProfesionales.save();
				cirujanosList.add(profeSalud);
			}
	    	json.put("cirujanos", new JSONArray(gson.toJson(cirujanosList)));
	    	
	    	List<ProfesionalSalud> anestesiologosList = new ArrayList<ProfesionalSalud>();
	    	for (int idProfesional : anestesiologos) {
	    		ProfesionalSalud profeSalud = ProfesionalSalud.findById(idProfesional);
				PlaneacionProfesionales planeacionProfesionales = new PlaneacionProfesionales();
				planeacionProfesionales.setPlaneacion(planeacion);
				planeacionProfesionales.setProfesionalSalud(profeSalud);
				planeacionProfesionales.setRol("Anestesiologo");
				planeacionProfesionales.save();
				anestesiologosList.add(profeSalud);
			}
	    	json.put("anestesiologos", new JSONArray(gson.toJson(anestesiologosList)));
	    	
	    	List<ProfesionalSalud> asistentesList = new ArrayList<ProfesionalSalud>();
	    	for (int idProfesional : asistentes) {
	    		ProfesionalSalud profeSalud = ProfesionalSalud.findById(idProfesional);
				PlaneacionProfesionales planeacionProfesionales = new PlaneacionProfesionales();
				planeacionProfesionales.setPlaneacion(planeacion);
				planeacionProfesionales.setProfesionalSalud(profeSalud);
				planeacionProfesionales.setRol("Asistente");
				planeacionProfesionales.save();
				asistentesList.add(profeSalud);
			}
	    	json.put("asistentes", new JSONArray(gson.toJson(asistentesList)));
	    	
	    	
	    	List<BloqueQuirurgico> bloquesQuirurgicos = BloqueQuirurgico.find(" habilitado = 1 ").fetch();
	    	json.put("bloquesQuirurgicos", new JSONArray(gson.toJson(bloquesQuirurgicos)));
	    	
	    	File file = new File(".\\public\\outputs\\"+planeacion.getIdPlaneacion()+"_planeacion.json");
	    	FileWriter writer = new FileWriter(file);
	    	writer.write(json.toString());
	    	writer.flush();
	    	writer.close();
	    	
	    	renderTemplate("Programacion/programming.html", planeacion);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public static void matchCirugias(String successMessage, String errorMessage){
    	Query query = JPA.em().createQuery("select s from Solicitud s where s.cirujano is null or s.cirujano.idCirujano = 0");
    	List<Solicitud> solicitudes = query.getResultList();
    	
        List<ProfesionalSalud> cirujanos = ProfesionalSalud.find("byProfesion", "Cirujano").fetch();
        render(solicitudes, cirujanos,successMessage, errorMessage);
    }
    
    public static void saveMatchSurgeries(){
    	Query query = JPA.em().createQuery("select s from Solicitud s where s.cirujano is null or s.cirujano.idCirujano = 0");
    	List<Solicitud> solicitudes = query.getResultList();
    	
    	String successMessage = "";
    	String errorMessage = "";
    	
    	for (Solicitud solicitud : solicitudes) {
			if(params._contains(solicitud.getIdSolicitud()+"")){
				try{
					int idProfesional = Integer.parseInt(params.get(solicitud.getIdSolicitud()+""));
					if(idProfesional != 0){
						Cirujano cirujano = Cirujano.find("select c from Cirujano c where c.profesionalsalud.idProfesionalSalud = ?", idProfesional).first();
						solicitud.setCirujano(cirujano);
						solicitud.save();
						successMessage = "Se realizaron los cambios exitosamente.";
					}
				}catch(Exception e){
					errorMessage = "Ocurrio un error al intentar guardar los cambios, error: " + e.getMessage();
					e.printStackTrace();
				}
				
			}
		}
    	matchCirugias(successMessage, errorMessage);
    }

}
