package org.lucene.searcher.impl;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.lucene.indexer.Indexable;
import org.lucene.searcher.Searcher;

public abstract class AbstractSearcher implements Searcher {

	private static final Logger LOGGER = Logger.getLogger(AbstractSearcher.class);
	
	protected IndexSearcher indexSearcher;

	public AbstractSearcher(String indexDirectoryPath) throws IOException {
		Directory directory = FSDirectory.open(Paths.get(indexDirectoryPath));
		IndexReader indexReader = DirectoryReader.open(directory);
		indexSearcher = new IndexSearcher(indexReader);
	}

	public IndexSearcher getIndexSearcher() {
		return indexSearcher;
	}

	public void setIndexSearcher(IndexSearcher indexSearcher) {
		this.indexSearcher = indexSearcher;
	}
}