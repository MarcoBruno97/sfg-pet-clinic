package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity,ID extends Long> {//extends Long lo puoi togliere, dato che Long e' final

    protected Map<Long,T> map = new HashMap<>();
    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    T save(T object){

        // guarding clause
        if (object == null) {
            throw new RuntimeException("Object cannot be null");
        }

        // update id
        if (object.getId() == null) {
            object.setId(getNextId());
        }
        map.put(object.getId(), object);

        return object;
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextId(){
        return map.isEmpty() ? 1L : Collections.max(map.keySet()) + 1L;
    }
}
