package project.application.interfaces;

import project.application.dtos.NumberDto;
import project.application.dtos.VectorDto;

public interface iOperationUseCases {
    public VectorDto add(VectorDto v1, VectorDto v2)throws Exception;
    public VectorDto scalar(VectorDto vec, NumberDto num) throws Exception;
    public NumberDto average(VectorDto vec)throws Exception;
    public VectorDto opposite(VectorDto vec)throws Exception;
    public NumberDto sumatory(VectorDto vec)throws Exception;
    public VectorDto normalization(VectorDto vec)throws Exception;
    public NumberDto maximum(VectorDto vec)throws Exception;
    public NumberDto minimum(VectorDto vec)throws Exception;
    public VectorDto projection(VectorDto v1, VectorDto v2)throws Exception;
    public NumberDto magnitude(VectorDto vec)throws Exception;
    public NumberDto dot(VectorDto v1, VectorDto v2)throws Exception;   
}
