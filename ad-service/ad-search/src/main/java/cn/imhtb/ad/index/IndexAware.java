package cn.imhtb.ad.index;

/**
 * @author PinTeh
 * @date 2019/7/29
 */
public interface IndexAware<K,V> {

    V get(K key);

    void add(K key, V value);

    void update(K key, V value);

    void delete(K key, V value);

}
