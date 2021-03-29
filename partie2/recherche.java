// Utilisation des classes Java pour une lecture d'une ligne au clavier
// Va permettre d'entrer via le clavier les informations recherch�es 
import java.io.*;

// Utilisation des differentes classes de Lucene 
import org.apache.lucene.analysis.standard.*;
import org.apache.lucene.queryparser.classic.*;
import org.apache.lucene.analysis.fr.*;
import org.apache.lucene.analysis.en.*;
import org.apache.lucene.analysis.*;
import org.apache.lucene.search.*;
import org.apache.lucene.index.*;
import org.apache.lucene.document.*;
import org.apache.lucene.util.*;
import org.apache.lucene.store.*;


public class recherche {
	public static void main(String[] args) { 
		String s_id = "id"; 
		String s_titre = "titre"; 
		String s_auteur = "auteur"; 
		String s_contenu = "contenu"; 
	
		try{ 
			// Identifier le répertoire de l'index
			String rep_index = ".\\index\\"; 
			// Sélection de l'analyseur par defaut
			StandardAnalyzer analyseur = new StandardAnalyzer(Version.LUCENE_46); 
			//// Analyzer analyseur = new FrenchAnalyzer(Version.LUCENE_46);
            //// Analyzer analyseur = new EnglishAnalyzer(Version.LUCENE_46);
			// La recherche 
			String[] champs_recherche = {s_titre, s_auteur, s_contenu}; 
			QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_46, champs_recherche, analyseur);

    		BufferedReader in = null;
			// Boucle permettant de faire plusieurs recherches
			while (1 == 1) {

				// entr�e des informations recherchées au clavier
				System.out.print("Entrez le texte à chercher - ou tapez enter pour sortir: ");
      			in = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
		      	String lettres = in.readLine();

				// Si l'utilisateur a tap� enter, on sort
				if (lettres.length() == 0) break;

				// lancement de la recherche
				Query query = parser.parse(lettres);
				//Query query = MultiFieldQueryParser.parse(lettres, champs_recherche, analyseur); 
				IndexSearcher searcher =  new IndexSearcher(DirectoryReader.open(FSDirectory.open(new File(rep_index)))); 
				TopDocs results = searcher.search(query,4096); 
				ScoreDoc[] hits = results.scoreDocs;

				// Impression des résultats
				System.out.println("Resultat: " + hits.length + " documents contenaient " + lettres); 
				for(int i = 0; i < hits.length; i++){ 
					Document doc = searcher.doc(hits[i].doc); 
					int lucene_id = hits[i].doc; 
					String my_id = doc.get(s_id); 
					String titre = doc.get(s_titre); 
					String auteur = doc.get(s_auteur); 
					System.out.println("ID: " + my_id + " - Lucene ID: " + lucene_id + " - Titre: " + titre + " - Auteur: " + auteur ); 
			} 
				}
		} catch (Exception e){ 
			System.err.println("Erreur rencontrée: " + e.toString());		} 
	}
}
