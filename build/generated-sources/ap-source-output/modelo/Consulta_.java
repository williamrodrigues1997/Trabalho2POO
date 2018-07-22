package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Consulta.class)
public abstract class Consulta_ {

	public static volatile SingularAttribute<Consulta, TipoConsulta> tipo;
	public static volatile SingularAttribute<Consulta, Date> data;
	public static volatile SingularAttribute<Consulta, String> horario;
	public static volatile SingularAttribute<Consulta, Paciente> paciente;
	public static volatile SingularAttribute<Consulta, String> medico;
	public static volatile SingularAttribute<Consulta, Integer> id;

}

