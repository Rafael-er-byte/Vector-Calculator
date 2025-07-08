package project.application.interfaces;
import java.util.ArrayList;

import project.application.dtos.VectorDto;

public interface iVectorUseCases{
    void createVector(VectorDto vec) throws Exception;
    ArrayList<VectorDto> getAllVectors()throws Exception;
    void deleteByName(VectorDto vec) throws Exception;
    VectorDto getByName(VectorDto vec) throws Exception;
    void updateVector(VectorDto vec) throws Exception;
    void clearStorage()throws Exception;
}
