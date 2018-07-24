package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DadosAdicionaisPaciente.class)
public abstract class DadosAdicionaisPaciente_ {

	public static volatile SingularAttribute<DadosAdicionaisPaciente, Boolean> diabete;
	public static volatile SingularAttribute<DadosAdicionaisPaciente, Boolean> doencaCardiaca;
	public static volatile SingularAttribute<DadosAdicionaisPaciente, Boolean> fuma;
	public static volatile SingularAttribute<DadosAdicionaisPaciente, Paciente> paciente;
	public static volatile SingularAttribute<DadosAdicionaisPaciente, Boolean> colesterol;
	public static volatile SingularAttribute<DadosAdicionaisPaciente, Boolean> bebe;
	public static volatile SingularAttribute<DadosAdicionaisPaciente, Integer> id;
	public static volatile ListAttribute<DadosAdicionaisPaciente, Adicionais> cirurgias;
	public static volatile ListAttribute<DadosAdicionaisPaciente, Adicionais> alergias;

}

