package project.application.dtos;

public class VectorDto {
    private final String name;
    private final String vector;

    public VectorDto(String _name, String _vector){
        this.name = _name;
        this.vector = _vector;
    }

    public VectorDto(String name){
        this.name = name;
        this.vector = "";
    }
    
    public String getName(){
        return this.name;
    }

    public String getVector(){
        return this.vector;
    }
}
