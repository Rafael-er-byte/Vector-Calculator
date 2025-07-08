package project.infraestructure;

import java.util.HashMap;

import project.domain.entities.Vector;
import project.domain.repositories.iVectorRepository;

import java.util.ArrayList;
public class InMemoryVectorRepository implements iVectorRepository{
    private  HashMap<String, Vector> db = new HashMap<>();

    public void save(Vector vec){
        this.db.put( vec.getName(), vec);
    }

    public ArrayList<Vector> getAll(){
        ArrayList<Vector> vectors = new ArrayList<>();
        for(Vector vec : this.db.values()){
            vectors.add(vec);
        }   
        return vectors;
    }

    public Vector getByName(String name)throws Exception{
        Vector vec = this.db.get(name);
        if(vec.getLength() == 0)throw new Exception("The vector doesnt exists");
        return vec;
    }  

    public void clearAll(){
        this.db = new HashMap<>();
    }

    public void deleteByName(String name){
        this.db.remove(name);
    }

    public void deleteAll(){
        this.db = new HashMap<>();
    }
}
