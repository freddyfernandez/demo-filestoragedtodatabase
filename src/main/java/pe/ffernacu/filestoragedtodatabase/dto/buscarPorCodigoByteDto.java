package pe.ffernacu.filestoragedtodatabase.dto;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class buscarPorCodigoByteDto implements Serializable {
    private int codigo;
    private String nombre;
    private byte[] imgAnverso;
    private byte[] imgReverso;
    public static buscarPorCodigoByteDto buscarPorCodigoByteResponse(buscarPorCodigoDto p) {
        buscarPorCodigoByteDto productoResponse= new buscarPorCodigoByteDto();
        productoResponse.setCodigo(p.getCodigo());
        productoResponse.setNombre(p.getNombre());
        return productoResponse;
    }
}
