package com.gar.CachingSystem.cache.Policies;

public interface EvictionPolicy<Key> {
    void keyAccessed(Key key);

    Key evitKey();
}
