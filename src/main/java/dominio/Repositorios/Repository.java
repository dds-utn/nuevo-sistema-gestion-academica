package dominio.Repositorios;

import dominio.Repositorios.daos.IDAO;

import java.util.ArrayList;
import java.util.List;

public class Repository<T> {
    private IDAO<T> dao;
    private Class<T> clase;

    public Repository(IDAO<T> dao, Class<T> clase) {
        this.dao = dao;
        this.clase = clase;
    }

    public void setDao(IDAO<T> dao) {
        this.dao = dao;
    }

    public boolean exist(int id){
        return this.dao.exist(id);
    }

    public T find(int id){
        return this.dao.find(id, this.clase);
    }

    public List<T> findAll(){
        return this.dao.findAll(this.clase);
    }

    public void delete(T object){
        this.dao.delete(object);
    }

    public void update(T object){
        this.dao.update(object);
    }

    public void insert(T object){
        this.dao.insert(object);
    }
}