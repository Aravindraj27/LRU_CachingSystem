package com.gar.CachingSystem.cache.Factories;

import com.gar.CachingSystem.cache.Cache;
import com.gar.CachingSystem.cache.Policies.LRUEvictionPolicy;
import com.gar.CachingSystem.cache.Storage.HashMapBasedStorage;

public class CacheFactory<Key, Value> {
    public Cache<Key, Value> defaultCache (final int capacity) {
        return new Cache<Key, Value> (new LRUEvictionPolicy<Key>(),
                new HashMapBasedStorage<Key, Value>(capacity));
    }
}
