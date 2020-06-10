package dominio.Repositorios.daos;

import java.util.ArrayList;
import java.util.List;

public class DAOMemo<T> implements IDAO<T>{

    private List<T> data;

    public DAOMemo()
    {
        this.data = new ArrayList<T>();
    }

    @Override
    public boolean exist(int id) {
        return false;
    }

    @Override
    public T find(int id, Class<T> clase) {
        return null;
    }

    @Override
    public List<T> findAll(Class<T> clase) {
        return this.data;
    }

    @Override
    public void delete(T object) {
        this.data.remove(object);
    }

    @Override
    public void update(T object) {
        return;
    }

    @Override
    public void insert(T object) {
        this.data.add(object);
    }
}
