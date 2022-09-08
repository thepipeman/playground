package io.pipecrafts.hashmap;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Node<K, V> {
  private K key;
  private V value;
  private Node<K, V> next;
}
