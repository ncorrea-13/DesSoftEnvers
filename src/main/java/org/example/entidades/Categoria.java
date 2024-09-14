package org.example.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "articulo" })
@Audited
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String denominacion;

    @ManyToMany(mappedBy = "categoria")
    private Set<Articulo> articulo = new HashSet<>();

    public Categoria(String denominacion) {
        this.denominacion = denominacion;
    }
}