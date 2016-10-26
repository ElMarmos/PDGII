package controllers;

import java.util.List;

import javax.persistence.Query;

import play.db.jpa.JPA;
import play.mvc.Controller;

public class Cirugia extends Controller {

	 public static void cirugiaDetail(int idCirugia){
		 models.Cirugia cirugia = models.Cirugia.findById(idCirugia);
		 render(cirugia);
	 }
	
}
