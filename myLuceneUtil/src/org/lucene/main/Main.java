package org.lucene.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.lucene.alalysis.CustomAnalyzer;
import org.lucene.exception.DocumentNotIndexedException;
import org.lucene.exception.SearcherException;
import org.lucene.exception.SuggestException;
import org.lucene.indexer.Indexable;
import org.lucene.indexer.Indexer;
import org.lucene.manager.IndexerManager;
import org.lucene.manager.SearcherManager;
import org.lucene.manager.SuggesterManager;
import org.lucene.object.test.Book;
import org.lucene.searcher.Searcher;
import org.lucene.suggest.Suggester;
import org.lucene.suggest.impl.SuggesterType;

public class Main {

	private static final String DIR = "/home/soprano/Bureau/index";

	public static void main(String... args) {
	
		Indexer indexer = IndexerManager.getIndexer(DIR, new CustomAnalyzer(), true);
		List<Indexable> books = new ArrayList<>();
		for(int i =1 ; i<=5 ; i++){
			books.add(new Book(i, "Pays"+i, "/home/soprano/Bureau/villes/pays"+i+".docx"));
			
		}
		try {
			indexer.indexDocuments(books);
		} catch (DocumentNotIndexedException e) {
			e.printStackTrace();
		}
		
		

		try {
			Searcher searcher = SearcherManager.getSearcher(DIR, new CustomAnalyzer(),
					new String[] { "title", "content" });
			List<Document> results = searcher.search("Australia");
			for (Document doc : results) {
				System.out.println(doc.get("title"));
			}
		} catch (IOException | SearcherException e) {
			e.printStackTrace();
		}

		try {
			Suggester suggester = SuggesterManager.getSuggester(DIR, new CustomAnalyzer(), SuggesterType.FUZZY);
			for(String s : suggester.getSuggestions("pal",new String[]{"content","title"} )){
				System.out.println(s);
			}
		} catch (IOException | SuggestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
