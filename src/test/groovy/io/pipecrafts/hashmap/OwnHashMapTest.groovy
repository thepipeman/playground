package io.pipecrafts.hashmap

import org.apache.commons.lang3.tuple.Pair
import spock.lang.Specification

class OwnHashMapTest extends Specification {

  def "should properly put data"() {
    given:
    OwnHashMap<String, String> ownMap = new OwnHashMap()
    def mapKey = key as String
    def value = val as String

    when:
    ownMap.put(mapKey, value)

    then:
    value == ownMap.get(key)

    where:
    key     | val
    "Jane"  | "Ganadin"
    "Aaron" | "Rasing"
  }

  def "should override existing key"() {
    given:
    OwnHashMap<String, String> ownMap = new OwnHashMap()
    def dupKey = "Jane"
    def overrideValue = "Rasing"

    when:
    ownMap.put(dupKey, "Ganadin")
    ownMap.put(dupKey, overrideValue)

    then:
    overrideValue == ownMap.get(dupKey)
  }

  // jane, jane11, jane22
  def "should properly resolve collision"() {
    given:
    OwnHashMap<String, String> ownHashMap = new OwnHashMap<>()
    Pair<String, String> pair1 = Pair.of("jane", "ganadin")
    Pair<String, String> pair2 = Pair.of("jane11", "oliveros")
    Pair<String, String> pair3 = Pair.of("jane22", "rasing")

    when:
    ownHashMap.put(pair1.getLeft(), pair1.getRight())
    ownHashMap.put(pair2.getLeft(), pair2.getRight())
    ownHashMap.put(pair3.getLeft(), pair3.getRight())

    then:
    pair1.getRight() == ownHashMap.get(pair1.getLeft())
    pair2.getRight() == ownHashMap.get(pair2.getLeft())
    pair3.getRight() == ownHashMap.get(pair3.getLeft())
    pair1.getRight() != ownHashMap.get(pair3.getLeft())

  }

}
