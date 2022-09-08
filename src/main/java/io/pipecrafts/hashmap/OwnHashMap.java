package io.pipecrafts.hashmap;

public class OwnHashMap<K, V> implements BareMap<K, V> {

  private int bucketSize = 16;
  private Node<K, V>[] buckets;
  private int actualSize = 0;

  public OwnHashMap() {
    buckets = new Node[bucketSize];
  }

  @Override
  public void put(K key, V value) {
    int index = calculateIndex(key);
    var node = buckets[index];


    if (node == null) {
      final var nodeToAdd = createNode(key, value);
      buckets[index] = nodeToAdd;
      actualSize++;
      // terminate
      return;
    }

    while (true) {
      if (node.getKey().equals(key)) {
        // present, put
        node.setValue(value);
        // terminate
        return;
      }

      final var next = node.getNext();
      if (next == null) {
        node.setNext(createNode(key, value));
        actualSize++;
        // terminate
        return;
      }

      node = next;
    }
  }

  @Override
  public V get(K key) {
//    return null;
    int index = calculateIndex(key);
    var node = buckets[index];

    if (node == null) {
      // guard clause
      return null;
    }

    if (node.getKey().equals(key)) {
      return node.getValue();
    }

    while (node != null) {
      // problem is the last
      if (node.getKey().equals(key)) {
        return node.getValue();
      }

      node = node.getNext();
    }

    // collision resolved, but no equal key.
    return null;
  }

  @Override
  public V remove(K key) {
    return null;
  }

  private int calculateIndex(K key) {
    return key.hashCode() & (bucketSize - 1);
  }

  private Node<K, V> createNode(K key, V value) {
    return Node.<K, V>builder()
      .key(key)
      .value(value)
      .build();
  }
}
