package org.lucene.searcher;

import java.util.List;

import org.apache.lucene.document.Document;
import org.lucene.exception.SearcherException;

public interface Searcher {
	List<Document> search(String textQuery) throws SearcherException;
}