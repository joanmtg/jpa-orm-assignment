package pruebajpa;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TipoDocumento.class)
public abstract class TipoDocumento_ {

	public static volatile SingularAttribute<TipoDocumento, Integer> tpIdDocumento;
	public static volatile SingularAttribute<TipoDocumento, String> tpCodigo;
	public static volatile CollectionAttribute<TipoDocumento, Personal> personalCollection;
	public static volatile SingularAttribute<TipoDocumento, String> tpNombre;

}

