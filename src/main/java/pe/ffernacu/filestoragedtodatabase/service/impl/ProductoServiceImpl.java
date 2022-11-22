package pe.ffernacu.filestoragedtodatabase.service.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import pe.ffernacu.filestoragedtodatabase.config.AsyncExecutorConfig;
import pe.ffernacu.filestoragedtodatabase.config.BlobStorageConfig;
import pe.ffernacu.filestoragedtodatabase.dao.ProductoDao;
import pe.ffernacu.filestoragedtodatabase.model.Producto;
import pe.ffernacu.filestoragedtodatabase.service.ProductoService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private ProductoDao productoDao;
    private BlobStorageConfig blobStorageConfig;
    private ResourceLoader resourceLoader;
    @Override
    public Producto registrar(Producto producto) {
        return productoDao.save(producto);
    }

    @Override
    public List<Producto> listarTodos() {
        return productoDao.findAll();
    }

    @Override
    public Optional<Producto> buscarXid(int codigo) {
        return productoDao.findById(codigo);
    }
    @Async(AsyncExecutorConfig.APP_ASYNC_EXECUTOR)
    @Override
    public void UploadFileService(MultipartFile fileAnverso,MultipartFile fileReverso,String data) throws IOException {
        BlobServiceClient storageClient = new BlobServiceClientBuilder()
                .endpoint(blobStorageConfig.getUriStorage()).connectionString(blobStorageConfig.getMyConnectionString())
                .buildClient();
        UploadFile(storageClient,fileAnverso);
        UploadFile(storageClient,fileReverso);
    }
    @Async(AsyncExecutorConfig.APP_ASYNC_EXECUTOR)
    public void UploadFile(BlobServiceClient storageClient,MultipartFile file)throws IOException{
        BlobClient blobClient = storageClient.getBlobContainerClient(blobStorageConfig.getContainerName()).getBlobClient(file.getOriginalFilename());//convierte tipo multipart a string
        blobClient.upload(file.getInputStream(),file.getSize(),true);//si ya existe, se sobreescribe los segmentos modificados
    }
    public byte[] getBytes(String uriFile) throws IOException {
        InputStream in = resourceLoader.getResource(uriFile).getInputStream();
        return StreamUtils.copyToByteArray(in);
    }

}
