package project.application.useCases;

import project.domain.repositories.iVectorRepository;
import project.application.dtos.NumberDto;
import project.application.dtos.VectorDto;
import project.application.interfaces.iOperationUseCases;
import project.domain.entities.Vector;

public class OperationUseCases implements iOperationUseCases{
    iVectorRepository repo;
    
    public OperationUseCases(iVectorRepository _repo){
        this.repo = _repo;
    }

    public VectorDto add(VectorDto v1, VectorDto v2)throws Exception{
        Vector result = null;
        try {
            Vector vec1 = this.repo.getByName(v1.getName());
            Vector vec2 = this.repo.getByName(v2.getName());
            result = vec1.add(vec2);
        } catch (Exception e) {
            throw new Exception("Something went wrong " + e.getMessage());
        }
        return new VectorDto(result.getName(),result.toString());
    }
    public VectorDto scalar(VectorDto vec, NumberDto num)throws Exception{
        Vector result = null;
        try {
            Vector vector = this.repo.getByName(vec.getName());
            result = vector.scalar(num.getNum());
        } catch (Exception e) {
            throw new Exception("Something went wrong " + e.getMessage());
        }
        return new VectorDto(result.getName(), result.toString());
    }
    public NumberDto average(VectorDto vec)throws Exception{
        double result = 0;
        try {
            Vector vector = this.repo.getByName(vec.getName());
            result = vector.average();
        } catch (Exception e) {
            throw new Exception("Something went wrong " + e.getMessage());
        }
        return new NumberDto(result);
    }
    public VectorDto opposite(VectorDto vec)throws Exception{
        Vector result = null;
        try {
            Vector vector = this.repo.getByName(vec.getName());
            result = vector.opposite();
        } catch (Exception e) {
            throw new Exception("Something went wrong " + e.getMessage());
        }
        return new VectorDto(result.getName(), result.toString());
    }
    public NumberDto sumatory(VectorDto vec)throws Exception{
        double result = 0;
        try {
            Vector vector = this.repo.getByName(vec.getName());
            result = vector.average();
        } catch (Exception e) {
            throw new Exception("Something went wrong " + e.getMessage());
        }
        return new NumberDto(result);
    }
    public VectorDto normalization(VectorDto vec)throws Exception{
        Vector result = null;
        try {
            Vector vector = this.repo.getByName(vec.getName());
            result = vector.normalization();
        } catch (Exception e) {
            throw new Exception("Something went wrong " + e.getMessage());
        }
        return new VectorDto(result.getName(), result.toString());
    }
    public NumberDto maximum(VectorDto vec)throws Exception{
        double maximum = 0;
        try {
            Vector vector = this.repo.getByName(vec.getName());
            maximum = vector.maximum();
        } catch (Exception e) {
            throw new Exception("Something went wrong " + e.getMessage());
        }
        return new NumberDto(maximum);
    }
    public NumberDto minimum(VectorDto vec)throws Exception{
        double minimum = 0;
        try {
            Vector vector = this.repo.getByName(vec.getName());
            minimum = vector.minimum();
        } catch (Exception e) {
            throw new Exception("Something went wrong " + e.getMessage());
        }
        return new NumberDto(minimum);
    }
    public VectorDto projection(VectorDto v1, VectorDto v2)throws Exception{
        Vector result = null;
        try {
            Vector vec1 = this.repo.getByName(v1.getName());
            Vector vec2 = this.repo.getByName(v2.getName());
            result = vec1.projection(vec2);
        } catch (Exception e) {
            throw new Exception("Something went wrong " + e.getMessage());
        }
        return new VectorDto(result.getName(), result.toString());
    }
    public NumberDto magnitude(VectorDto vec)throws Exception{
        double result = 0;
        try {
            Vector vector = this.repo.getByName(vec.getName());
            result = vector.magnitude();
        } catch (Exception e) {
            throw new Exception("Something went wrong " + e.getMessage());
        }
        return new NumberDto(result);
    }
    public NumberDto dot(VectorDto v1, VectorDto v2)throws Exception{
        double result = 0;
        try {
            Vector vec1 = this.repo.getByName(v1.getName());
            Vector vec2 = this.repo.getByName(v2.getName());
            result = vec1.dot(vec2);
        } catch (Exception e) {
            throw new Exception("Something went wrong " + e.getMessage());
        }
        return new NumberDto(result);
    }
}
