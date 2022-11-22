package pe.ffernacu.filestoragedtodatabase.dto;

import lombok.Getter;
import lombok.Setter;
import pe.ffernacu.filestoragedtodatabase.model.Producto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class registrarProductoDto implements Serializable {
    private int codigo;
    private String nombre;
    private String imgAnversoUrl;
    private String imgReversoUrl;

    public static Producto registrarProveedorRequest(registrarProductoDto registrarProductoDto){
        Producto producto = new Producto();
        producto.setNombre(registrarProductoDto.getNombre());
        producto.setImgAnversoUrl(registrarProductoDto.getImgAnversoUrl());
        producto.setImgReversoUrl(registrarProductoDto.getImgReversoUrl());
        return producto;
    }
    public static registrarProductoDto registrarProveedorResponse(Producto producto){
        registrarProductoDto registrarProductoDto = new registrarProductoDto();
        registrarProductoDto.setCodigo(producto.getCodigo());
        registrarProductoDto.setNombre(producto.getNombre());
        registrarProductoDto.setImgAnversoUrl(producto.getImgAnversoUrl());
        registrarProductoDto.setImgReversoUrl(producto.getImgReversoUrl());
        return registrarProductoDto;
    }

}
