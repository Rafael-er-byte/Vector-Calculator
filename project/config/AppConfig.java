package project.config;

import project.application.interfaces.iOperationUseCases;
import project.application.interfaces.iVectorUseCases;
import project.application.useCases.OperationUseCases;
import project.application.useCases.VectorUseCases;
import project.domain.repositories.iVectorRepository;
import project.infraestructure.InMemoryVectorRepository;

public class AppConfig{
    public static iVectorRepository repo = new InMemoryVectorRepository();
    public static iVectorUseCases setUseCaseVector(){
        iVectorUseCases controller = new VectorUseCases(repo);
        return controller;
    }

    public static iOperationUseCases setUseCaseOperations(){
        iOperationUseCases controller = new OperationUseCases(repo);
        return controller;
    }
}