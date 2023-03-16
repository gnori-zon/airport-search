package org.gnori;

import java.util.List;

public interface IndexStorage {

  List<Integer> getIdsFor(String startString);

  void insert(int id, String data);

}
