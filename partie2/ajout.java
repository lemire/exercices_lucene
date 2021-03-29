import java.io.*;

// Utilisation des différentes classes de Lucene 
import org.apache.lucene.analysis.standard.*;
import org.apache.lucene.analysis.fr.*;
import org.apache.lucene.analysis.en.*;
import org.apache.lucene.analysis.*;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.util.*;
import org.apache.lucene.store.*;

public class ajout {
	public static void main(String[] args) { 
		String s_id = "id"; 
		String s_titre = "titre"; 
		String s_auteur = "auteur"; 
		String s_contenu = "contenu"; 
		String doc1_contenu = "a b"; 
		String doc2_contenu = "b"; 
		String doc3_contenu = "a b c"; 
		String doc4_contenu = "d"; 
		String doc5_contenu = "a b c d e"; 
		String[] doc_contenu = {"", doc1_contenu, doc2_contenu, doc3_contenu, doc4_contenu, doc5_contenu}; 
	
		try{ 
			// Identifier le répertoire de l'index
			String rep_index = ".\\index\\"; 
			// Sélection de l'analyseur par defaut
			Analyzer analyseur = new StandardAnalyzer(Version.LUCENE_46); 
            //// Analyzer analyseur = new FrenchAnalyzer(Version.LUCENE_46);
            //// Analyzer analyseur = new EnglishAnalyzer(Version.LUCENE_46);
			// Ajout des documents 
			IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_46,analyseur);
			Directory dir = FSDirectory.open(new File(rep_index));
			IndexWriter writer = new IndexWriter(dir, conf); 
		
			for(int i = 1; i < doc_contenu.length; i++){ 
				Document doc = new Document(); 
				doc.add(new StringField(s_id, "Doc" + i,Field.Store.NO)); 
				doc.add(new StringField(s_titre, "titre" + i,Field.Store.YES)); 
				doc.add(new StringField(s_auteur, "auteur" + i,Field.Store.YES)); 
				doc.add(new TextField(s_contenu, doc_contenu[i],Field.Store.NO)); 
				writer.addDocument(doc); 
			} 
			writer.close(); 
			System.out.println("Index créé." ); 

		} catch (Exception e){ 
			System.err.println("Erreur rencontrée: " + e.toString());
		} 
	}
}
