package project;

import project.UI.GUI;
import project.application.interfaces.iOperationUseCases;
import project.application.interfaces.iVectorUseCases;
import project.config.AppConfig;

class Main {
    public static void main(String[] args) {
        iVectorUseCases vectorUseCase = AppConfig.setUseCaseVector();
        iOperationUseCases operationUseCases = AppConfig.setUseCaseOperations();
        GUI ui = new GUI(operationUseCases, vectorUseCase);
        ui.run();
    }
}