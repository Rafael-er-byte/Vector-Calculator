package project.domain.repositories;
import java.util.ArrayList;
import project.domain.entities.Vector;

public interface iVectorRepository {
    void save(Vector vec);
    ArrayList<Vector> getAll();
    Vector getByName(String name)throws Exception;
    void clearAll();
    void deleteByName(String name); 
    void deleteAll();
}
