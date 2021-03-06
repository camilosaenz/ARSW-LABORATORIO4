/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {
    
    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        
        ibpp.saveBlueprint(bp0);
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        ibpp.saveBlueprint(bp);
        
        assertNotNull("Loading a previously stored blueprint returned null.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()));
        
        assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);
        
    }


    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint",pts2);

        try{
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){
            
        }
    }
    
    // PRUEBA DEL METODO: getBlueprint(String author,String name)
    @Test
    public void testGetBluePrintWithAuthorAndName() throws BlueprintPersistenceException, BlueprintNotFoundException {
    	
    	InMemoryBlueprintPersistence mbp = new InMemoryBlueprintPersistence();
    	Point[] puntos = new Point[] {new Point(10, 10), new Point(20, 20)};
    	Blueprint blue = new Blueprint("Carlos", "Plano1", puntos);
    	mbp.saveBlueprint(blue);
    	Blueprint bp = mbp.getBlueprint("Carlos", "Plano1");
    	assertEquals(blue, bp);
    	
    }
    
    // PRUEBA DEL METODO: getBlueprintsByAuthor
    @Test
    public void testGetAllBlueprintsByAuthor() throws BlueprintPersistenceException, BlueprintNotFoundException {
    	
    	InMemoryBlueprintPersistence mbp = new InMemoryBlueprintPersistence();
    	Point[] puntos = new Point[] {new Point(10, 10), new Point(20, 20)};
    	Blueprint blue = new Blueprint("Carlos", "Plano1", puntos);
    	Blueprint blue1 = new Blueprint("Carlos", "Plano2", puntos);
    	Set<Blueprint> listBlueprint = new HashSet<Blueprint>();
    	
    	listBlueprint.add(blue);
    	listBlueprint.add(blue1);
    	
    	mbp.saveBlueprint(blue);
    	mbp.saveBlueprint(blue1);
    	
    	Set<Blueprint> bp = mbp.getAllBlueprintsByAuthor("Carlos"); 
    	
    	assertEquals(listBlueprint, bp);
    	
    }
    
    // PRUEBA FILTRO Muestreo.
    // Para que esta prueba funcione se debe cambiar la anotacion de la etiqueta @Qualifier por "Muestreo".
    @Test
    public void testMuestreo() throws BlueprintNotFoundException {
    	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bs = ac.getBean(BlueprintsServices.class);
        Point[] puntos = new Point[] {new Point(1,1), new Point(7,9), new Point(7,4), new Point(20,30)};
        bs.addNewBlueprint(new Blueprint("Carlos", "plano1", puntos));
        Blueprint bp = bs.getBlueprint("Carlos", "plano1");
    	assertEquals(bp.getPoints().get(0).getX(), 1);
    	assertEquals(bp.getPoints().get(0).getY(), 1);
    	assertEquals(bp.getPoints().get(1).getX(), 7);
    	assertEquals(bp.getPoints().get(1).getY(), 4);
    	assertEquals(bp.getPoints().size(), 2);
    }

    // PRUEBA FILTRO Redundancia.
    // Para que esta prueba funcione se debe cambiar la anotacion de la etiqueta @Qualifier por "Redundancia".
    @Test
    public void testRedundancia() throws BlueprintNotFoundException {
    	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bs = ac.getBean(BlueprintsServices.class);
        Point[] puntos = new Point[] {new Point(10,15), new Point(10,15), new Point(20,30), new Point(20,30)};
        bs.addNewBlueprint(new Blueprint("Camilo", "plano1", puntos));
        Blueprint bp = bs.getBlueprint("Camilo", "plano1");
    	assertEquals(bp.getPoints().get(0).getX(), 10);
    	assertEquals(bp.getPoints().get(0).getY(), 15);
    	assertEquals(bp.getPoints().get(1).getX(), 20);
    	assertEquals(bp.getPoints().get(1).getY(), 30);
    	assertEquals(bp.getPoints().size(), 2);
    }
    
}
