package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import play.data.validation.Error;
import play.mvc.*;

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
		
		renderTemplate("CargarBD/index.html");
	}

}
