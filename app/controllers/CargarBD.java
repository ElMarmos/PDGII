package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import models.Anestesiologo;
import models.BloqueDisponibilidad;
import models.BloqueQuirurgico;
import models.Cama;
import models.Cirujano;
import models.DisponibilidadProfesional;
import models.Dotacion;
import models.Especialidad;
import models.Pabellon;
import models.Paciente;
import models.Patientologia;
import models.Patologia;
import models.Procedimiento;
import models.ProfesionalSalud;
import models.ProfesionalSaludEspecialidad;
import models.Quirofano;
import models.QuirofanoEspecialidad;
import models.Recurso;
import models.Solicitud;
import models.SolicitudProcedimientos;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class CargarBD extends Controller {

	public static void index() {
		render();
	}

	public static void cargarBD(File excel){
		validation.required(excel).message("Por favor seleccione un archivo válido.");

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(excel);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			badRequest();
		}

		XSSFWorkbook myWorkBook = null;
		if(fis != null){
			try {
				myWorkBook = new XSSFWorkbook (fis);
			} catch (Exception e) {
				e.printStackTrace();
				badRequest();
			}
		}

		XSSFSheet hoja_paciente = myWorkBook.getSheetAt(0);
		XSSFSheet hoja_patologia = myWorkBook.getSheetAt(1);
		XSSFSheet hoja_patologiapaciente = myWorkBook.getSheetAt(2);
		XSSFSheet hoja_solicitud = myWorkBook.getSheetAt(3);
		XSSFSheet hoja_especialidad = myWorkBook.getSheetAt(4);
		XSSFSheet hoja_procedimiento = myWorkBook.getSheetAt(5);
		XSSFSheet hoja_solicitudprocedimiento = myWorkBook.getSheetAt(6);
		XSSFSheet hoja_profesionalsalud = myWorkBook.getSheetAt(7);
		XSSFSheet hoja_profesionalespecialidad = myWorkBook.getSheetAt(8);
		XSSFSheet hoja_disponibilidadprofesional = myWorkBook.getSheetAt(9);
		XSSFSheet hoja_bloquequirurjico = myWorkBook.getSheetAt(10);
		XSSFSheet hoja_bloquedisponibilidad = myWorkBook.getSheetAt(11);
		XSSFSheet hoja_pabellon = myWorkBook.getSheetAt(12);
		XSSFSheet hoja_quirofano = myWorkBook.getSheetAt(13);
		XSSFSheet hoja_quirofanoespecialidad = myWorkBook.getSheetAt(14);
		XSSFSheet hoja_dotacion = myWorkBook.getSheetAt(15);
		XSSFSheet hoja_recursos = myWorkBook.getSheetAt(16);
		XSSFSheet hoja_cama = myWorkBook.getSheetAt(17);


		Iterator<Row> rowIterator = hoja_paciente.iterator();

		Row row = rowIterator.next();

		ArrayList<Paciente> pacientes = new ArrayList<Paciente>();

		while(rowIterator.hasNext()) {
			row = rowIterator.next();

			String nombres = row.getCell(1).getStringCellValue();
			String apellidos = row.getCell(2).getStringCellValue();
			String tipoIdentificacion = row.getCell(3).getStringCellValue();
			String numeroIdentificacion = (int)row.getCell(4).getNumericCellValue()+"";
			Date fechaNac = row.getCell(5).getDateCellValue();
			String sexo = row.getCell(6).getStringCellValue();
			double peso = row.getCell(7).getNumericCellValue();
			String talla = (int)row.getCell(8).getNumericCellValue()+"";
			double prioridad = row.getCell(9).getNumericCellValue();
			String ciudadProcedencia = row.getCell(10).getStringCellValue();
			String eps = row.getCell(11).getStringCellValue();
			String numeroHistoriaClinica = (int)row.getCell(12).getNumericCellValue()+"";

			Paciente paciente = Paciente.find("byNumeroIdentificacion", numeroIdentificacion).first();
			if(paciente == null){
				paciente = new Paciente(nombres,apellidos,tipoIdentificacion,numeroIdentificacion,fechaNac,sexo,peso,talla,prioridad,ciudadProcedencia,eps,numeroHistoriaClinica);;
				pacientes.add(paciente);
				paciente.save();
			}else{
				pacientes.add(paciente);
			}
		}

		System.out.println("Listo pacientes");

		rowIterator = hoja_patologia.iterator();

		row = rowIterator.next();

		ArrayList<Patologia> patologias = new ArrayList<Patologia>();

		while(rowIterator.hasNext()) {
			row = rowIterator.next();

			String nombre = row.getCell(1).getStringCellValue();

			Patologia pato = Patologia.find("byNombre", nombre).first();
			if(pato == null){
				pato = new Patologia();
				pato.setNombre(nombre);
				patologias.add(pato);
				pato.save();
			}else{
				patologias.add(pato);
			}
		}

		System.out.println("Listo patologias");

		rowIterator = hoja_patologiapaciente.iterator();

		row = rowIterator.next();

		while(rowIterator.hasNext()){
			row = rowIterator.next();
			int paciNo = (int)row.getCell(0).getNumericCellValue();
			int patoNo = (int)row.getCell(1).getNumericCellValue();

			Paciente paciente = Paciente.find("byNumeroIdentificacion", pacientes.get(paciNo-1).getNumeroIdentificacion()).first();
			Patologia patologia = Patologia.find("byNombre", patologias.get(patoNo-1).getNombre()).first();

			Patientologia patien = new Patientologia(paciente, patologia);
			paciente.addPatientologia(patien);
			patologia.addPatientologia(patien);
			patien.save();
		}

		System.out.println("Listo paciente-patologia");

		rowIterator = hoja_especialidad.iterator();

		row = rowIterator.next();

		ArrayList<Especialidad> especialidades = new ArrayList<Especialidad>();

		while(rowIterator.hasNext()){
			row = rowIterator.next();
			String nombre = row.getCell(1).getStringCellValue();

			Especialidad especialidad = Especialidad.find("byNombreEspecialidad", nombre).first();
			if(especialidad == null){
				especialidad = new Especialidad(nombre);
				especialidades.add(especialidad);
				especialidad.save();
			}else{
				especialidades.add(especialidad);
			}
		}

		System.out.println("Listo especialidad");

		rowIterator = hoja_procedimiento.iterator();

		row = rowIterator.next();

		ArrayList<Procedimiento> procedimientos = new ArrayList<Procedimiento>();

		while(rowIterator.hasNext()){
			row = rowIterator.next();
			int codigo = (int)row.getCell(0).getNumericCellValue();
			int espeNo = (int)row.getCell(1).getNumericCellValue();
			String nombre = row.getCell(2).getStringCellValue();

			Procedimiento procedimiento = Procedimiento.find("byCodigoProcedimiento", codigo).first();
			if(procedimiento == null){
				Especialidad especialidad = especialidades.get(espeNo-1);
				procedimiento = new Procedimiento(codigo,nombre,especialidad);
				procedimiento.save();
				procedimientos.add(procedimiento);
			}else{
				procedimientos.add(procedimiento);
			}
		}

		System.out.println("Listo procedimiento");

		rowIterator = hoja_profesionalsalud.iterator();

		row = rowIterator.next();

		ArrayList<ProfesionalSalud> profesionalesSalud = new ArrayList<ProfesionalSalud>();

		while(rowIterator.hasNext()){
			row = rowIterator.next();
			String nombres = row.getCell(1).getStringCellValue();
			String apellidos = row.getCell(2).getStringCellValue();
			String profesion = row.getCell(3).getStringCellValue();
			String contrato = row.getCell(4).getStringCellValue();

			ProfesionalSalud profesional = ProfesionalSalud.find("nombres = ? and apellidos = ?", nombres,apellidos).first();
			if(profesional == null){
				profesional = new ProfesionalSalud(nombres,apellidos,profesion,contrato);
				switch(profesion){
				case "Cirujano":
					Cirujano c = new Cirujano(profesional);
					c.save();
					break;
				case "Anestesiologo":
					Anestesiologo a = new Anestesiologo(profesional);
					a.save();
					break;
				}
				profesional.save();
				profesionalesSalud.add(profesional);
			}else{
				profesionalesSalud.add(profesional);
				switch(profesion){
				case "Cirujano":
					Cirujano c = Cirujano.find("byProfesionalsalud", profesional).first();
					if(c == null){
						c = new Cirujano(profesional);
						c.save();
					}
					break;
				case "Anestesiologo":
					Anestesiologo a = Anestesiologo.find("byProfesionalsalud", profesional).first();
					if(a == null){
						a = new Anestesiologo(profesional);
						a.save();
					}
					break;
				}
			}
		}

		System.out.println("Listo profesionales");

		rowIterator = hoja_profesionalespecialidad.iterator();

		row = rowIterator.next();

		ArrayList<ProfesionalSaludEspecialidad> pses = new ArrayList<ProfesionalSaludEspecialidad>();

		while(rowIterator.hasNext()){
			row = rowIterator.next();
			int noProfe = (int)row.getCell(0).getNumericCellValue();
			int noEspec = (int)row.getCell(1).getNumericCellValue();
			int prioridad = (int)row.getCell(2).getNumericCellValue();

			ProfesionalSalud ps = profesionalesSalud.get(noProfe-1);
			Especialidad espe = especialidades.get(noEspec-1);

			ProfesionalSaludEspecialidad pse = ProfesionalSaludEspecialidad.find("profesionalsalud = ? and especialidad = ? and prioridad = ?", ps,espe,prioridad).first();
			if(pse == null){
				pse = new ProfesionalSaludEspecialidad(ps,espe,prioridad);
				pse.save();
				pses.add(pse);
			}else{
				pses.add(pse);
			}
		}

		System.out.println("Listo profesionalesespecialidad");

		rowIterator = hoja_solicitud.iterator();

		row = rowIterator.next();
		
		ArrayList<Solicitud> solicitudes = new ArrayList<Solicitud>();

		while(rowIterator.hasNext()){
			row = rowIterator.next();
			Date atencion = row.getCell(1).getDateCellValue();
			Date cirugia = row.getCell(2).getDateCellValue();
			Date programacion = row.getCell(3).getDateCellValue();
			Date solicitud = row.getCell(4).getDateCellValue();
			String jornada = row.getCell(5).getStringCellValue();
			String tipoPaciente = row.getCell(6).getStringCellValue();
			int noPaci = (int)row.getCell(7).getNumericCellValue();
			int noCiru = (int)row.getCell(8).getNumericCellValue();


			ProfesionalSalud ps = noCiru!=0 ? profesionalesSalud.get(noCiru-1) : null;
			Cirujano cir = Cirujano.find("byProfesionalsalud", ps).first();
			Paciente pac = pacientes.get(noPaci-1);

			Solicitud soli = Solicitud.find("fechaAtencion = ? and fechaCirugia = ? and fechaProgramacion = ? and fechaSolicitud = ?", new Timestamp(atencion.getTime()), new Timestamp(cirugia.getTime()), new Timestamp(programacion.getTime()), new Timestamp(solicitud.getTime())).first();
			if(soli == null){
				soli = new Solicitud(atencion, cirugia, programacion, solicitud, jornada, tipoPaciente, pac, cir);
				soli.save();
				solicitudes.add(soli);
			}else{
				solicitudes.add(soli);
			}
		}

		System.out.println("Listo solicitudes");
		
		rowIterator = hoja_solicitudprocedimiento.iterator();

		row = rowIterator.next();

		ArrayList<SolicitudProcedimientos> solicitudesProcedimientos = new ArrayList<SolicitudProcedimientos>();

		while(rowIterator.hasNext()){
			row = rowIterator.next();
			
			int soli = (int)row.getCell(0).getNumericCellValue();
			int proce = (int)row.getCell(1).getNumericCellValue();

			Solicitud solicitud = solicitudes.get(soli-1);
			Procedimiento procedimiento = procedimientos.get(proce-1);

			SolicitudProcedimientos soliProce = SolicitudProcedimientos.find("solicitud = ? and procedimiento = ?", solicitud, procedimiento).first();
			if(soliProce == null){
				soliProce = new SolicitudProcedimientos(solicitud, procedimiento);
				soliProce.save();
				solicitudesProcedimientos.add(soliProce);
			}else{
				solicitudesProcedimientos.add(soliProce);
			}
		}

		System.out.println("Listo solicitudesProcedimientos");
		
		rowIterator = hoja_dotacion.iterator();

		row = rowIterator.next();

		ArrayList<Dotacion> dotaciones = new ArrayList<Dotacion>();

		while(rowIterator.hasNext()){
			row = rowIterator.next();
			
			String nombre = row.getCell(1).getStringCellValue();

			Dotacion dotacion = Dotacion.find("byNombre", nombre).first();
			if(dotacion == null){
				dotacion = new Dotacion(nombre);
				dotacion.save();
				dotaciones.add(dotacion);
			}else{
				dotaciones.add(dotacion);
			}
		}

		System.out.println("Listo dotacion");
		
		rowIterator = hoja_recursos.iterator();

		row = rowIterator.next();

		ArrayList<Recurso> recursos = new ArrayList<Recurso>();

		while(rowIterator.hasNext()){
			row = rowIterator.next();
			
			String nombre = row.getCell(1).getStringCellValue();

			Recurso recurso = Recurso.find("byNombre", nombre).first();
			if(recurso == null){
				recurso = new Recurso(nombre);
				recurso.save();
				recursos.add(recurso);
			}else{
				recursos.add(recurso);
			}
		}

		System.out.println("Listo recurso");
		
		rowIterator = hoja_cama.iterator();

		row = rowIterator.next();

		ArrayList<Cama> camas = new ArrayList<Cama>();

		while(rowIterator.hasNext()){
			row = rowIterator.next();
			
			String codigo = row.getCell(1).getStringCellValue();
			String estado = row.getCell(2).getStringCellValue();

			Cama cama = Cama.find("byCodigo", codigo).first();
			if(cama == null){
				cama = new Cama(codigo, estado);
				cama.save();
				camas.add(cama);
			}else{
				cama.setEstado(estado);
				cama.save();
				camas.add(cama);
			}
		}

		System.out.println("Listo camas");
		
		rowIterator = hoja_disponibilidadprofesional.iterator();

		row = rowIterator.next();

		ArrayList<DisponibilidadProfesional> disposProfesional = new ArrayList<DisponibilidadProfesional>();

		while(rowIterator.hasNext()){
			row = rowIterator.next();
			
			int numProf = (int) row.getCell(1).getNumericCellValue();
			String horaInicio = row.getCell(2).getStringCellValue();
			String horaFin = row.getCell(3).getStringCellValue();
			String periodoInicio = row.getCell(4).getStringCellValue();
			String periodoFin = row.getCell(5).getStringCellValue();
			String repeticion = row.getCell(6).getStringCellValue();
			
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			long msInicio = 0;
			long msFin = 0;
			try {
				msInicio = format.parse(horaInicio).getTime();
				msFin = format.parse(horaFin).getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ProfesionalSalud p = profesionalesSalud.get(numProf-1);

			DisponibilidadProfesional dp = DisponibilidadProfesional.find("profesionalsalud = ? and horaInicio = ? and horaFin = ? and periodoInicio = ? and periodoFin = ? and repeticion = ?", p, new Time(msInicio), new Time(msFin), periodoInicio, periodoFin, repeticion).first();
			if(dp == null){
				dp = new DisponibilidadProfesional(p, new Time(msInicio), new Time(msFin), periodoInicio, periodoFin, repeticion);
				dp.save();
				disposProfesional.add(dp);
			}else{
				disposProfesional.add(dp);
			}
		}

		System.out.println("Listo disponibilidades");
		
		rowIterator = hoja_bloquequirurjico.iterator();

		row = rowIterator.next();

		ArrayList<BloqueQuirurgico> bloquesQuirurjicos = new ArrayList<BloqueQuirurgico>();

		while(rowIterator.hasNext()){
			row = rowIterator.next();
			
			int numEsp = (int) row.getCell(1).getNumericCellValue();
			String nombreBloque = row.getCell(2).getStringCellValue();
			Date fechaInicio = row.getCell(3).getDateCellValue();
			Date fechaFin = row.getCell(5).getDateCellValue();
			String horaInicio = row.getCell(4).getStringCellValue();
			String horaFin = row.getCell(6).getStringCellValue();
			String dias = row.getCell(7).getStringCellValue();
			
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			long msInicio = 0;
			long msFin = 0;
			try {
				msInicio = format.parse(horaInicio).getTime();
				msFin = format.parse(horaFin).getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Especialidad e = especialidades.get(numEsp-1);

			BloqueQuirurgico bq = BloqueQuirurgico.find("especialidad = ? and nombreBloque = ? and fechaInicio = ? and horaInicio = ? and fechaFin = ? and horaFinal = ? and dias = ?", e, nombreBloque, fechaInicio, new Timestamp(msInicio), fechaFin, new Timestamp(msFin), dias).first();
			if(bq == null){
				bq = new BloqueQuirurgico(nombreBloque, e, fechaInicio, new Timestamp(msInicio), fechaFin, new Timestamp(msFin), dias);
				bq.save();
				bloquesQuirurjicos.add(bq);
			}else{
				bloquesQuirurjicos.add(bq);
			}
		}

		System.out.println("Listo bloques quirurjico");
		
		rowIterator = hoja_bloquedisponibilidad.iterator();

		row = rowIterator.next();

		ArrayList<BloqueDisponibilidad> bloquesDisponibilidad = new ArrayList<BloqueDisponibilidad>();

		while(rowIterator.hasNext()){
			row = rowIterator.next();
			
			int numBloQuir = (int) row.getCell(0).getNumericCellValue();
			int numDisProf = (int) row.getCell(1).getNumericCellValue();
			Date fechaInicio = row.getCell(2).getDateCellValue();
			Date fechaFin = row.getCell(4).getDateCellValue();
			String horaInicio = row.getCell(3).getStringCellValue();
			String horaFin = row.getCell(5).getStringCellValue();
			String dias = row.getCell(6).getStringCellValue();
			
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			long msInicio = 0;
			long msFin = 0;
			try {
				msInicio = format.parse(horaInicio).getTime();
				msFin = format.parse(horaFin).getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			BloqueQuirurgico bq = bloquesQuirurjicos.get(numBloQuir-1);
			DisponibilidadProfesional dp = disposProfesional.get(numDisProf-1);

			BloqueDisponibilidad bd = BloqueDisponibilidad.find("bloquequirurgico = ? and disponibilidadprofesional = ? and fechaInicio = ? and horaInicio = ? and fechaFin = ? and horaFin = ? and dias = ?", bq, dp, fechaInicio, new Time(msInicio), fechaFin, new Time(msFin), dias).first();
			if(bd == null){
				bd = new BloqueDisponibilidad(bq, dp, fechaInicio, new Time(msInicio), fechaFin, new Time(msFin), dias);
				bd.save();
				bloquesDisponibilidad.add(bd);
			}else{
				bloquesDisponibilidad.add(bd);
			}
		}

		System.out.println("Listo bloques disponibilidad");
		
		rowIterator = hoja_pabellon.iterator();

		row = rowIterator.next();

		ArrayList<Pabellon> pabellones = new ArrayList<Pabellon>();

		while(rowIterator.hasNext()){
			row = rowIterator.next();
			
			String nombre = row.getCell(1).getStringCellValue();

			Pabellon pabellon = Pabellon.find("byNombre", nombre).first();
			if(pabellon == null){
				pabellon = new Pabellon(nombre);
				pabellon.save();
				pabellones.add(pabellon);
			}else{
				pabellones.add(pabellon);
			}
		}

		System.out.println("Listo pabellones");
		
		rowIterator = hoja_quirofano.iterator();

		row = rowIterator.next();

		ArrayList<Quirofano> quirofanos = new ArrayList<Quirofano>();

		while(rowIterator.hasNext()){
			row = rowIterator.next();
			
			int numPabellon = (int) row.getCell(1).getNumericCellValue();
			String nombre = row.getCell(2).getStringCellValue();

			Pabellon pabellon = pabellones.get(numPabellon-1);
			
			Quirofano quirofano = Quirofano.find("pabellon = ? and nombreQuirofano = ?", pabellon, nombre).first();
			if(quirofano == null){
				quirofano = new Quirofano(nombre, pabellon);
				quirofano.save();
				quirofanos.add(quirofano);
			}else{
				quirofanos.add(quirofano);
			}
		}

		System.out.println("Listo quirofanos");
		
		rowIterator = hoja_quirofanoespecialidad.iterator();

		row = rowIterator.next();

		while(rowIterator.hasNext()){
			row = rowIterator.next();
			
			int numEspe = (int) row.getCell(0).getNumericCellValue();
			int numQuir = (int) row.getCell(1).getNumericCellValue();
			double prioridad = row.getCell(2).getNumericCellValue();

			Especialidad e = especialidades.get(numEspe-1);
			Quirofano q = quirofanos.get(numQuir-1);
			
			QuirofanoEspecialidad qe = QuirofanoEspecialidad.find("especialidad = ? and quirofano = ? and prioridad = ?", e, q, prioridad).first();
			if(qe == null){
				qe = new QuirofanoEspecialidad(q, e, prioridad);
				qe.save();
			}
		}

		System.out.println("Listo quirofano especialidad");

		renderTemplate("CargarBD/index.html");
	}

}
