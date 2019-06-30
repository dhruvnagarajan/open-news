package data.persistence

/**
 * @author Dhruvaraj Nagarajan
 */
interface Cache<K, V> {

    fun get(key: K): V

    fun put(key: K, value: V)
}