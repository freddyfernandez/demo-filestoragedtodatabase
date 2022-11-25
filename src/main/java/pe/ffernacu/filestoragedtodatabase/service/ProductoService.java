package pe.ffernacu.filestoragedtodatabase.service;

import org.springframework.web.multipart.MultipartFile;
import pe.ffernacu.filestoragedtodatabase.model.Producto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    public Producto registrar(Producto  producto);
    public List<Producto> listarTodos();
    public Optional<Producto> buscarXid(int codigo);
    public void UploadFileService(MultipartFile file, MultipartFile file2) throws IOException;
    public byte[] getBytes(String uriFile) throws IOException;
}
