package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.example.entidades.*;

public class Main {
    public static void main(String[] args) {

        // crea el EntytyManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");
        System.out.printf("EntityManagerFactory Creado");

        // crea el EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.printf("EntityManager Creado");

        try {
            //Inicio del EntityManager
            entityManager.getTransaction().begin();

            //Crear Factura
            Factura fact1 = new Factura();
            fact1.setNumero(1);
            fact1.setFecha("10/08/2024");

            //Crear Domicilio
            Domicilio domi1 = new Domicilio("Belgrano", 1234);

            //Crear Cliente
            Cliente cli1 = new Cliente("Juan", "Pérez", 123456);
            cli1.setDomicilio(domi1);
            fact1.setCliente(cli1);

            //Crear Categorias
            Categoria perecedero = new Categoria("Perecedero");
            Categoria lacteos = new Categoria("Lacteo");
            Categoria limpieza = new Categoria("Limpieza");

            //Crear Articulos
            Articulo art1 = new Articulo(200, "Yogur Serenito Sabor vainilla", 20);
            Articulo art2 = new Articulo(350, "Detergente Magistral", 30);

            //Relacionar Articulos y Cateogrias
            art1.getCategoria().add(perecedero);
            art1.getCategoria().add(lacteos);
            lacteos.getArticulo().add(art1);
            perecedero.getArticulo().add(art1);

            art2.getCategoria().add(limpieza);
            limpieza.getArticulo().add(art2);

            //Crear DetalleFactura
            DetalleFactura det1 = new DetalleFactura();
            det1.setArticulo(art1);
            det1.setCantidad(2);
            det1.setSubtotal(40);

            //Relacionarlo con Articulo y Factura
            art1.getDetalleFactura().add(det1);
            fact1.getDetalleFactura().add(det1);
            det1.setFactura(fact1);

            //Crear otro DetalleFactura
            DetalleFactura det2 = new DetalleFactura();

            //Relacionarlo con Articulo y Factura
            det2.setArticulo(art2);
            det2.setCantidad(1);
            det2.setSubtotal(89);

            art2.getDetalleFactura().add(det2);
            fact1.getDetalleFactura().add(det2);
            det2.setFactura(fact1);

            fact1.setTotal(120);

            //Persistir la factura con lo que va a llevar todo lo relacionado a la base de datos.
            entityManager.persist(fact1);

            // Consultar y mostrar la entidad persistida
            Factura facturaHecha = entityManager.find(Factura.class, fact1.getId());
            System.out.println("Factura subida: " + facturaHecha.getFecha());

            //Modifica la factura dejando un historial en la base de datos.
            //entityManager.merge(fact1);

            //Eliomina la factura dejando un historial en la base de datos.
            //entityManager.remove(fact1);

            entityManager.flush();

            entityManager.getTransaction().commit();
        } catch (
                Exception e) {

            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("Error inesperado durante la ejecución");
        }



        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}