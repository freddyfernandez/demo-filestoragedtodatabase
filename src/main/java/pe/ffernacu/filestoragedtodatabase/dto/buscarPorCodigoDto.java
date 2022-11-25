package pe.ffernacu.filestoragedtodatabase.dto;

import lombok.Getter;
import lombok.Setter;
import pe.ffernacu.filestoragedtodatabase.model.Producto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class buscarPorCodigoDto implements Serializable {
    private int codigo;
    private String nombre;

    public static buscarPorCodigoDto buscarPorCodigoResponse(Optional<Producto> p) {
        buscarPorCodigoDto productoResponse= new buscarPorCodigoDto();
        productoResponse.setCodigo(p.get().getCodigo());
        productoResponse.setNombre(p.get().getNombre());
        return productoResponse;
    }

}
