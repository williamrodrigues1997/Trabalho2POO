package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Prontuario.class)
public abstract class Prontuario_ {

	public static volatile SingularAttribute<Prontuario, Date> data;
	public static volatile SingularAttribute<Prontuario, Paciente> paciente;
	public static volatile SingularAttribute<Prontuario, String> medico;
	public static volatile SingularAttribute<Prontuario, String> prescricaoTratamento;
	public static volatile SingularAttribute<Prontuario, Integer> id;
	public static volatile SingularAttribute<Prontuario, String> sintomas;
	public static volatile SingularAttribute<Prontuario, String> diagnosticoDoenca;

}

