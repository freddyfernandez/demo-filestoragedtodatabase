package pe.ffernacu.filestoragedtodatabase.dto;

import lombok.Getter;
import lombok.Setter;
import pe.ffernacu.filestoragedtodatabase.model.Producto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class listarProductoDto implements Serializable {
    private int codigo;
    private String nombre;
    private String imgAnversoUrl;
    private String imgReversoUrl;
    public static List<listarProductoDto> listarProductosResponse(List<Producto> listarTodos) {
        List<listarProductoDto> listarProductosResponse= new ArrayList<>();
        for (Producto p:listarTodos) {
            listarProductoDto productoResponse= new listarProductoDto();
            productoResponse.setCodigo(p.getCodigo());
            productoResponse.setNombre(p.getNombre());
            productoResponse.setImgAnversoUrl(p.getImgAnversoUrl());
            productoResponse.setImgReversoUrl(p.getImgReversoUrl());
            listarProductosResponse.add(productoResponse);
        }
        return listarProductosResponse;
    }
}
