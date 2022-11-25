package pe.ffernacu.filestoragedtodatabase.dto;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class buscarPorCodigoByteDto implements Serializable {
    private int codigo;
    private byte[] imgAnverso;
    private byte[] imgReverso;
    public static buscarPorCodigoByteDto buscarPorCodigoByteResponse(buscarPorCodigoUriDto p) {
        buscarPorCodigoByteDto productoResponse= new buscarPorCodigoByteDto();
        productoResponse.setCodigo(p.getCodigo());
        return productoResponse;
    }
}
