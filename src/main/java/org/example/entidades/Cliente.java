package org.example.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"factura"})
@Audited
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreCliente;

    private String apellidoCliente;

    @Column(unique = false)
    private int dni;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilioCliente")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "cliente")
    private Set<Factura> factura = new HashSet<Factura>();

    public Cliente(String nombreCliente, String apellidoCliente, int dni) {
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.dni = dni;
    }

}
