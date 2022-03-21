package com.gar.CachingSystem.cache;

import com.gar.CachingSystem.cache.Policies.EvictionPolicy;
import com.gar.CachingSystem.cache.Storage.Storage;
import com.gar.CachingSystem.cache.exception.NotFoundException;
import com.gar.CachingSystem.cache.exception.StorageFullException;

public class Cache<Key, Value> {
    private final EvictionPolicy<Key> evictionPolicy;
    private final Storage<Key, Value> storage;

    public Cache(EvictionPolicy<Key> evictionPolicy, Storage<Key, Value> storage) {
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    public void put(Key key, Value value) {
        try {
            this.storage.add(key, value);
            this.evictionPolicy.keyAccessed(key);
        }
        catch (StorageFullException exception) {
            System.out.println("Maximum capacity reached removing the least used value.");
            Key keyToRemove = evictionPolicy.evitKey();
            if(keyToRemove == null) {
                throw new RuntimeException("Unexpected State. Storage full and no key to evict.");
            }
            this.storage.remove(keyToRemove);
            System.out.println("Removing Key - " + keyToRemove);
            put(key, value);
        }
    }
    public Value get(Key key) {
        try {
            Value value = this.storage.get(key);
            this.evictionPolicy.keyAccessed(key);
            return value;
        } catch(NotFoundException exception) {
            System.out.println("No value found for key " + key);
            return null;
        }
    }
}
