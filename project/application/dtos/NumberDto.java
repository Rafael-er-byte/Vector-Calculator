package project.application.dtos;

public class NumberDto {
    private final double num;

    public NumberDto(double n){
        this.num = n;
    }

    public double getNum(){
        return this.num;
    }
}
