package project.domain.entities;
import java.util.ArrayList;

public class Vector{
    private ArrayList<Double> vector = new ArrayList<>();
    private String name;

    public Vector(ArrayList<Double> vec, String name){
        this.name = name;
        this.vector = vec;
    }

    public Vector(ArrayList<Double> _vector){
        this.vector = _vector;
        this.name = "";
    }

    public static ArrayList<Double> parse(String vec){
        ArrayList<Double> result = new ArrayList<>();
        try {
            for(int i = 0; i < vec.length(); i++){
                String value = "";
                while (i < vec.length() && vec.charAt(i) != ',' && vec.charAt(i) != ' ') {
                   value += vec.charAt(i);
                   i++;
                }

                if(value.length() > 0)result.add(Double.parseDouble(value));
            }
        } catch (Exception e) {
            System.out.println("Invalid input format\n");
        }
        return result;
    }

    public String toString(){
        return this.vector.toString();
    }

    public Vector add(Vector vector2) throws Exception{
        if(this.getLength() != vector2.getLength())throw new Exception("The vectors must match on size");
        ArrayList<Double> result = new ArrayList<>();
        for(int i = 0; i < this.getLength(); i++){
            result.add(this.vector.get(i) + vector2.getValues().get(i));
        }
        return new Vector(result);
    }

    public Vector scalar(double num){
        ArrayList<Double> result = new ArrayList<>();
        for(int i = 0; i < this.getLength(); i++){
            result.add(vector.get(i) * num);
        }
        return new Vector(result);
    }

    public double average(){
        return this.sumatory() / this.getLength();
    }

    public Vector opposite(){
        ArrayList<Double> result = new ArrayList<>();
        for (int i = 0; i < this.getLength(); i++){
            result.add(this.vector.get(i) * -1);
        }
        return new Vector(result);
    }

    public double sumatory(){
        double sum = 0;
        for (double val : this.vector)sum+=val;
        return sum;
    }

    public double magnitude(){
        double magnitude = 0;
        for(int i = 0; i < this.getLength(); i++){
            magnitude += Math.pow(this.vector.get(i), 2);
        }
        return Math.sqrt(magnitude);
    }

    public Vector normalization(){
        double magnitude = this.magnitude();
        if(magnitude == 0)throw new ArithmeticException();
        ArrayList<Double> result = new ArrayList<>();
        for(int i = 0; i < this.getLength(); i++){
            result.add(this.vector.get(i) / magnitude);
        }
        return new Vector(result);
    }

    public double maximum()throws Exception{
        double max = Double.MIN_VALUE;
        if(this.vector.isEmpty())throw new Exception("Empty array");
        for (Double val : this.vector) {
            if(val > max)max = val;            
        }
        return max;
    }

    public double minimum()throws Exception{
        double min = Double.MAX_VALUE;
        if(this.vector.isEmpty())throw new Exception("Empty array");
        for (Double val : this.vector) {
            if(val < min)min = val;            
        }
        return min;
    }

    public double dot(Vector v2)throws Exception{
        if(this.getLength() != v2.getLength())throw new Exception("The arguments must match");
        double result = 0;
        for(int i = 0; i < this.getLength(); i++){
            result += this.vector.get(i) * v2.getValues().get(i);
        }
        return result;
    }

    public Vector projection(Vector v2)throws Exception{
        if(this.getLength() != v2.getLength())throw new Exception("The arguments must match");
        double dotV1V2 = this.dot(v2);
        double dotV2V2 = v2.dot(v2);
        double scalar = dotV1V2 / dotV2V2;
        return new Vector(v2.scalar(scalar).getValues());
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Double> getValues(){
        return new ArrayList<>(this.vector);
    }

    public int getLength(){
        return this.vector.size();
    }
}
        