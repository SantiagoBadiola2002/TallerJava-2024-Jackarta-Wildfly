package org.tallerjava.moduloPeaje.dominio;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // hay otras dos estrategias para el mapeo pero esta me pareció
//la mas simple para este caso
@Table(name = "peaje_tarifa")
public abstract class Tarifa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	protected double valor;
}
