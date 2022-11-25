package pe.ffernacu.filestoragedtodatabase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.ffernacu.filestoragedtodatabase.config.BlobStorageConfig;
import pe.ffernacu.filestoragedtodatabase.dto.*;
import pe.ffernacu.filestoragedtodatabase.service.ProductoService;
import pe.ffernacu.filestoragedtodatabase.util.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("producto")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class ProductoController {
    private ProductoService productoService;
    private BlobStorageConfig blobStorageConfig;
    private ResourceLoader resourceLoader;

    @PostMapping(value="/registrarProducto", consumes={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<registrarProductoDto> registrarProducto(@RequestPart("producto") String producto,
                                                                  @RequestPart("file1") MultipartFile fileAnverso,
                                                                  @RequestPart("file2") MultipartFile fileReverso) throws IOException
    {
        fileAnverso.getInputStream();
        fileReverso.getInputStream();

        registrarProductoDto productoRequest = new registrarProductoDto();
        ObjectMapper objectMapper=new ObjectMapper();
        productoRequest=objectMapper.readValue(producto,registrarProductoDto.class);

        MultipartFile newNameFileAnverso = MockMultipartFile.getNewFile((productoRequest.getNombre()+" Anverso").replace(' ','_')+"."+FilenameUtils.getExtension(fileAnverso.getOriginalFilename()), fileAnverso);
        MultipartFile newNameFileReverso = MockMultipartFile.getNewFile((productoRequest.getNombre()+" Reverso").replace(' ','_')+"."+FilenameUtils.getExtension(fileReverso.getOriginalFilename()), fileReverso);
        productoService.UploadFileService(newNameFileAnverso,newNameFileReverso);

        productoRequest.setImgAnversoUrl(blobStorageConfig.getUriFile()+newNameFileAnverso.getOriginalFilename());
        productoRequest.setImgReversoUrl(blobStorageConfig.getUriFile()+newNameFileReverso.getOriginalFilename());
        return new ResponseEntity<>(registrarProductoDto.registrarProveedorResponse((productoService.registrar(registrarProductoDto.registrarProveedorRequest(productoRequest)))), HttpStatus.OK);

    }
    @GetMapping(value = "/buscarPorIdByte/{id}")
    public ResponseEntity<buscarPorCodigoByteDto> buscarPorIdByte(@PathVariable("id") int id) throws IOException {
        buscarPorCodigoUriDto productoResponse=new buscarPorCodigoUriDto();
        buscarPorCodigoByteDto productobyteResponse=new buscarPorCodigoByteDto();
        productoResponse=buscarPorCodigoUriDto.buscarPorCodigoUriResponse(productoService.buscarXid(id));
        productobyteResponse=buscarPorCodigoByteDto.buscarPorCodigoByteResponse(productoResponse);
        productobyteResponse.setImgAnverso(productoService.getBytes(productoResponse.getImgAnversoUrl()));
        productobyteResponse.setImgReverso(productoService.getBytes(productoResponse.getImgReversoUrl()));
        return new ResponseEntity<>(productobyteResponse,HttpStatus.OK);
    }
    @GetMapping(value = "/buscarPorId/{id}")
    public ResponseEntity<buscarPorCodigoDto> buscarPorId(@PathVariable("id") int id) throws IOException {
        buscarPorCodigoDto productoResponse=new buscarPorCodigoDto();
        productoResponse=buscarPorCodigoDto.buscarPorCodigoResponse(productoService.buscarXid(id));
        return new ResponseEntity<>(productoResponse,HttpStatus.OK);
    }
    @GetMapping(value = "/listarProductos")
    public ResponseEntity<List<listarProductoDto>> listarProductos() throws IOException {
        List<listarProductoDto> listadoResponse = listarProductoDto.listarProductosResponse(productoService.listarTodos());
        List<buscarPorCodigoByteDto> listaproductobyteResponse=new ArrayList<>();
        /*
        for (listarProductoDto b:listadoResponse){
            buscarPorCodigoByteDto buscarPorCodigoByteDto=new buscarPorCodigoByteDto();
            buscarPorCodigoByteDto.setCodigo(b.getCodigo());
            buscarPorCodigoByteDto.setNombre(b.getNombre());
            buscarPorCodigoByteDto.setImgAnverso(productoService.getBytes(b.getImgAnversoUrl()));
            buscarPorCodigoByteDto.setImgReverso(productoService.getBytes(b.getImgReversoUrl()));
            listaproductobyteResponse.add(buscarPorCodigoByteDto);
        }
        return new ResponseEntity<>(listaproductobyteResponse,HttpStatus.OK);
        */
        return new ResponseEntity<>(listadoResponse,HttpStatus.OK);
    }
    /*
    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> getImageXFile(String file) throws IOException {
        InputStream in = resourceLoader.getResource("https://storage10242.blob.core.windows.net/myfiles/"+file).getInputStream();
        byte[] bytes= StreamUtils.copyToByteArray(in);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
        //InputStream in = resourceLoader.getResource("https://storage10242.blob.core.windows.net/myfiles/creditoA.jpg").getInputStream();
        //return IOUtils.toByteArray(in);
    }

    @PostMapping(value="/registrarProducto", consumes={MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<registrarProductoDto> registrarProducto(@RequestBody registrarProductoDto productoRequest){
        return new ResponseEntity<>(registrarProductoDto.registrarProveedorResponse((productoService.registrar(registrarProductoDto.registrarProveedorRequest(productoRequest)))), HttpStatus.OK);

    }

    @PostMapping(path = "/uploadFile")
    public String UploadFile(@RequestParam("file") final MultipartFile file) throws IOException {
        file.getInputStream();
        productoService.UploadFileService(file);
        //iFileDirectory.saveFileDirectory(file.getOriginalFilename());
        return "ok";
    }*/
}
