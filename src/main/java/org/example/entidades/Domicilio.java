package org.example.entidades;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "domicilio")
@Data
@NoArgsConstructor
@Audited
public class Domicilio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombreCalle;

    @Column(unique = false)
    private int numero;

    @OneToOne(mappedBy = "domicilio")
    private Cliente cliente;

    public Domicilio(String nombreCalle, int numero) {
        this.nombreCalle = nombreCalle;
        this.numero = numero;
    }
}
