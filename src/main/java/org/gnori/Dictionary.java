package org.gnori;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Dictionary implements IndexStorage {

  private final Map<Character, HashMap<Integer, String>> dictionary;

  public Dictionary() {
    this.dictionary = new HashMap<>();
  }

  @Override
  public List<Integer> getIdsFor(String startString) {
    var sign = startString.toLowerCase().charAt(0);
    var chapter = dictionary.get(sign);
    if (chapter != null) {
      return chapter.entrySet().stream()
          .filter(entry -> entry.getValue().toLowerCase().startsWith(startString.toLowerCase()))
          .map(Entry::getKey)
          .sorted(Integer::compareTo)
          .collect(toList());
    } else {
      return Collections.emptyList();
    }
  }

  @Override
  public void insert(int id, String data) {
    var sign = data.toLowerCase().charAt(0);
    var chapter = dictionary.get(sign);
    if (chapter != null) {
      chapter.put(id, data);
    } else {
      chapter = new HashMap<>();
      chapter.put(id, data);
      dictionary.put(sign, chapter);
    }
  }
}
