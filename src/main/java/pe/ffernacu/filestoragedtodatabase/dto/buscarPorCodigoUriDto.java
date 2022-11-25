package pe.ffernacu.filestoragedtodatabase.dto;

import lombok.Getter;
import lombok.Setter;
import pe.ffernacu.filestoragedtodatabase.model.Producto;

import java.util.Optional;
@Getter
@Setter
public class buscarPorCodigoUriDto {
    private int codigo;
    private String imgAnversoUrl;
    private String imgReversoUrl;

    public static buscarPorCodigoUriDto buscarPorCodigoUriResponse(Optional<Producto> p) {
        buscarPorCodigoUriDto productoResponse= new buscarPorCodigoUriDto();
        productoResponse.setCodigo(p.get().getCodigo());
        productoResponse.setImgAnversoUrl((p.get().getImgAnversoUrl()));
        productoResponse.setImgReversoUrl(p.get().getImgReversoUrl());
        return productoResponse;
    }
}
