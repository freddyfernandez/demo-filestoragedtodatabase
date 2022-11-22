package pe.ffernacu.filestoragedtodatabase.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="producto")
@Data
public class Producto {
    @Id
    @SequenceGenerator(name="producto_seq", sequenceName ="producto_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producto_seq")
    @Column(name = "codigo")
    private int codigo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "imganversourl")
    private String imgAnversoUrl;
    @Column(name = "imgreversourl")
    private String imgReversoUrl;
}
