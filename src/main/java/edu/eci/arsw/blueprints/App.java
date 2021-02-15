package edu.eci.arsw.blueprints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.filtro.Filtro;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

@Service
public class App 
{
	public static void main(String arg[])
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bs = ac.getBean(BlueprintsServices.class);
        
        //@Autowired
        Filtro filtroR;
        
        Filtro filtroS;
        
        try {
        	bs.addNewBlueprint(new Blueprint("Carlos", "plano1", new Point[] {new Point(1,1)}) ); 	//Añadimos el nuevo blue print
			bs.getBlueprintsByAuthor("Carlos");														//obtenemos el blue print por autor
			bs.addNewBlueprint(new Blueprint("Andrés", "plano1", new Point[] {new Point(1,1)}) );
			bs.getBlueprintsByAuthor("Andrés");
			bs.addNewBlueprint(new Blueprint("Camilo", "plano1", new Point[] {new Point(1,1)}) );
			bs.getBlueprintsByAuthor("Camilo");
			//System.out.println(bs.getBlueprintsByAuthor("Carlos"));		//Obtenemos por autor
			//System.out.println(bs.getAllBlueprints());					//Obtenemos todos
			//System.out.println(bs.getBlueprint("Carlos", "plano1"));	//Obtenemos por autor y plano
		} catch (BlueprintNotFoundException e) {
			e.printStackTrace();
		}
	}
}
