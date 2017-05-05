package pruebajpa;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Personal.class)
public abstract class Personal_ {

	public static volatile SingularAttribute<Personal, String> identificacionPersonal;
	public static volatile SingularAttribute<Personal, String> apellido;
	public static volatile SingularAttribute<Personal, TipoDocumento> tpDocumento;
	public static volatile SingularAttribute<Personal, String> nombre;

}

