package Utils;

import Constaint.EntityState;

public class CachedEntity<T> {
    private T entity;
    private EntityState state;

    public CachedEntity(T entity, EntityState state) {
        this.entity = entity;
        this.state = state;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public EntityState getState() {
        return state;
    }

    public void setState(EntityState state) {
        this.state = state;
    }
}
