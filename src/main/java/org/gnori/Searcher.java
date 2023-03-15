package org.gnori;

import java.util.List;

public interface Searcher {

  List<String> searchData(String searchTerm);

  long getTimeSpent();

}
