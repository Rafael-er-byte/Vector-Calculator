package project.application.useCases;

import java.util.ArrayList;

import project.application.dtos.VectorDto;
import project.application.interfaces.iVectorUseCases;
import project.domain.entities.Vector;
import project.domain.repositories.iVectorRepository;

public class VectorUseCases implements iVectorUseCases{
    iVectorRepository repo;

    public VectorUseCases(iVectorRepository _repo){
        this.repo = _repo;
    }

    public void createVector(VectorDto vec)throws Exception{
        try {
            Vector vector = new Vector(Vector.parse(vec.getVector()), vec.getName());
            this.repo.save(vector);
        } catch (Exception e) {
            throw new Exception("Something went wrong");
        }
    }

    public ArrayList<VectorDto> getAllVectors() throws Exception{
        ArrayList<VectorDto> result = new ArrayList<>();
        try {
            ArrayList<Vector> vectors = new ArrayList<>();
            vectors = this.repo.getAll();
            for (Vector vec : vectors) {
                result.add(new VectorDto(vec.toString(), vec.getName()));
            }
        } catch (Exception e) {
            throw new Exception("Something went wrong");
        }
        return result;
    }

    public void deleteByName(VectorDto vec)throws Exception{
        try {
            this.repo.getByName(vec.getName());
            this.repo.deleteByName(vec.getName());
        } catch (Exception e) {
            throw new Exception("Vector doesnt exists");
        }
    }

    public VectorDto getByName(VectorDto vec) throws Exception{
        Vector vector = null;
        try {
            vector = this.repo.getByName(vec.getName());
        } catch (Exception e) {
            throw new Exception("Vector not found");
        }
        return new VectorDto(vector.toString(), vector.getName());
    }

    public void updateVector(VectorDto vec) throws Exception{
        try {
            this.repo.getByName(vec.getName());
            Vector vector = new Vector(Vector.parse(vec.getVector()), vec.getName());
            this.repo.save(vector);
        } catch (Exception e) {
            throw new Exception("Vector doesnt exists");
        }
    }

    public void clearStorage(){
        this.repo.deleteAll();
    }
}
