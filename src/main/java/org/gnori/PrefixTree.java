package org.gnori;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrefixTree implements IndexStorage {

  private Integer id;
  private final char value;
  private List<PrefixTree> children;

  public PrefixTree(char value) {
    this.value = value;
  }

  @Override
  public void insert(int id, String data) {
    data = data.toUpperCase();
    if (data.length() > 0) {
      if (children == null) {
        children = new ArrayList<>();
      }
      char c = data.charAt(0);
      var child = findNodeByChar(c);
      if (child == null) {
        child = new PrefixTree(c);
        if (data.length() == 1) {
          child.id = id;
        }
        children.add(child);
      }
      child.insert(id, data.substring(1));
    }
  }

  /* поиск всех id, для startString, если для какого-то символа startString узел не найден, то
вернет пустой лист, иначе продолжит искать все Id у потомков узла последнего элемента строки*/
  @Override
  public List<Integer> getIdsFor(String startString) {
    startString = startString.toUpperCase();
    List<Integer> ids = new ArrayList<>();
    var current = this;
    // проверка на наличие строки в дереве
    for (int i = 0; i < startString.length(); i++) {
      current = current.findNodeByChar(startString.charAt(i));
      if (current == null) {
        return Collections.emptyList();
      }
    }
    // добавление id текущего узла, если такой есть
    if (id != null) {
      ids.add(id);
    }
    // добавление id потомков
    if (current.children != null) {
      for (var child : current.children) {
        child.getAllId(ids);
      }
    }
    ids.sort(Integer::compareTo);
    return ids;
  }

  private PrefixTree findNodeByChar(char c) {
    if (children != null) {
      for (PrefixTree child : children) {
        if (child.value == c) {
          return child;
        }
      }
    }
    return null;
  }

  public void getAllId(List<Integer> result) {
    if (id != null) {
      result.add(id);
    }
    if (children != null) {
      for (PrefixTree child : children) {
        child.getAllId(result);
      }
    }
  }

}
