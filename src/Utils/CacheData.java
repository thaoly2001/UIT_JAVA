package Utils;

import Constaint.EntityState;
import java.util.*;

public class CacheData<T> {

    private Map<Long, CachedEntity<T>> cache = new HashMap<>();

    public void put(Long id, T entity, EntityState state) {
        cache.put(id, new CachedEntity<>(entity, state));
    }

    public void putAll(Collection<T> entities, IdExtractor<T> extractor) {
        for (T e : entities) {
            Long id = extractor.getId(e);
            cache.put(id, new CachedEntity<>(e, EntityState.UNCHANGED));
        }
    }

    public CachedEntity<T> get(Long id) {
        return cache.get(id);
    }

    public List<CachedEntity<T>> getAll() {
        return new ArrayList<>(cache.values());
    }

    public void markDeleted(Long id) {
        CachedEntity<T> ce = cache.get(id);
        if (ce != null) {
            ce.setState(EntityState.DELETED);
        }
    }

    public void clear() {
        cache.clear();
    }

    public int size() {
        return cache.size();
    }

    public boolean contains(Long id) {
        return cache.containsKey(id);
    }

    public interface IdExtractor<T> {

        Long getId(T entity);
    }
}
