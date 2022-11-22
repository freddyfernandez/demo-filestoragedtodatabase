package pe.ffernacu.filestoragedtodatabase.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.ffernacu.filestoragedtodatabase.model.Producto;

public interface ProductoDao extends JpaRepository<Producto,Integer> {
}
