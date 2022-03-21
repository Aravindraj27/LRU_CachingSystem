package com.gar.CachingSystem.cache.Storage;

import com.gar.CachingSystem.cache.exception.NotFoundException;
import com.gar.CachingSystem.cache.exception.StorageFullException;

public interface Storage<Key, Value> {
    void remove(Key key) throws NotFoundException;
    Value get(Key key) throws NotFoundException;

    void add(Key key, Value value) throws StorageFullException;
}
