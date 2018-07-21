package modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Paciente.class)
public abstract class Paciente_ {

	public static volatile SingularAttribute<Paciente, String> endereco;
	public static volatile SingularAttribute<Paciente, String> rg;
	public static volatile SingularAttribute<Paciente, String> cpf;
	public static volatile SingularAttribute<Paciente, Convenio> tipoConvenio;
	public static volatile SingularAttribute<Paciente, String> nome;
	public static volatile SingularAttribute<Paciente, Integer> id;
	public static volatile SingularAttribute<Paciente, String> telefoneCelular;
	public static volatile SingularAttribute<Paciente, Date> dataNascimento;
	public static volatile SingularAttribute<Paciente, String> email;

}

