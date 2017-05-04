/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebajpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author invitado
 */
public class PruebaJPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PruebaJPAPU");//PruebaJPAPU es el nombre de nuestra unidad de persistencia
        
        PersonalJpaController dao = new PersonalJpaController(emf);//Creamos un controlador de personal
        
        Personal persona = new Personal();//Creamos un objeto personal
        persona.setIdentificacionPersonal("1111111111");
        persona.setNombre("Juan");
        persona.setApellido("Bautista");
        
        TipoDocumentoJpaController tpdoc = new TipoDocumentoJpaController(emf);//Debido a que Tipo documento es una Foreing Key en Personal, debemos instanciar un controlador
        //de tipo TipoDocumento
        persona.setTpDocumento(tpdoc.findTipoDocumento(1));//Buscamos el tipo documento con primary key = 1 (C.C.) y se lo pasamos al objeto personal
        
        dao.create(persona);//Utilizamos el método create definido en el controlador personal para ingresar el objeto personal a la BD
        //Al ejecutar el método puede que salte una excepcion por lo que es importante lanzarla desde el main con throws Exception 
        emf.close();//Cerramos el EntityManagerFactory
    }
    
}
