package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Usuario extends Model {
	
	public String usuario;
	
	public String contrasena;
    
}
