package org.example.entidades;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalleFactura")
@Data
@NoArgsConstructor
@Audited
public class DetalleFactura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;

    private int subtotal;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "articuloDeDetalleFactura")
    private Articulo articulo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "facturaOrginal")
    private Factura factura;

    public DetalleFactura(int cantidad, int subtotal) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

}
