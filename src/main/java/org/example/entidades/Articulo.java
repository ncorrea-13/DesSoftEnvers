package org.example.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Entity
@Table(name = "articulo")
@Data
@EqualsAndHashCode(exclude = { "categoria", "detalleFactura" })
@Audited
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;

    private String denominacion;

    private int precio;

    @OneToMany(mappedBy = "articulo", cascade = CascadeType.PERSIST)
    private Set<DetalleFactura> detalleFactura = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "articulo_categoria", joinColumns = @JoinColumn(name = "id_articulo"), inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private Set<Categoria> categoria = new HashSet<>();

    public Articulo(int cantidad, String denominacion, int precio) {
        this.cantidad = cantidad;
        this.denominacion = denominacion;
        this.precio = precio;
    }
}
