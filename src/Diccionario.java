import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Diccionario {

    private Set < String > W = new HashSet < > (); // ensemble de toutes les mots
    private HashMap < String, Set > S = new HashMap < > (); // ensemble de toutes les pseudo-mots
    /**
     * Constructeur des ensembles W et S apartir d'un fichier.
     * @param secondFile fichier diccionaire pour construire les ensembles.
     */
    public Diccionario(String secondFile) throws IOException {

        try {
            FileReader fileReader = new FileReader(secondFile);
            BufferedReader nextLine = new BufferedReader(fileReader);
            String linea;

            while ((linea = nextLine.readLine()) != null) {

                W.add(linea);
                Set < String > tempSet = new HashSet < > ();
                for (int i = 0; i < linea.length(); i++) {
                    String localWord1 = linea.substring(0, i);
                    String localWord2 = linea.substring(i + 1, linea.length());
                    tempSet.add(localWord1 + localWord2);
                }
                S.put(linea, tempSet);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cas B : On verifie s'il existe un mot incorrecte dans le set S
     * @param mot courrant
     */

    public HashSet < String > b1(String mot) {

        HashSet < String > tabu = new HashSet < > ();
        Iterator itr = S.entrySet().iterator();

        // cas de la lettre manquante
        while (itr.hasNext()) {

            Map.Entry mapSet = (Map.Entry) itr.next(); // acceder aux elements
            String Str = ((String) mapSet.getKey()); // obtenir un mot du dicctionnaire
            HashSet < String > AA = (HashSet) S.get(Str); // obtenir le set de suggestions

            if (mot.length() == Str.length() - 1) {
                Iterator itr2 = AA.iterator();
                int counter = 0;
                while (itr2.hasNext()) {
                    String ss = (String) itr2.next(); // mot de l'ensemble de suggestions
                    for (int i = 0; i < ss.length(); i++) {

                        if (ss.charAt(i) == mot.charAt(i)) {
                            counter++;
                        }
                    }
                    if (counter == Str.length() - 1) {
                        tabu.add(Str);
                    }
                    counter = 0;


                }
            }
        }

        return tabu;
    }

    /**
     * methode pour verifier l'existence d'un mot dans W
     * @param word mot a verifier dans l'ensemble W
     */
    public boolean verifyWord(String word) {
        if (W.contains(word)) {
            return true;
        }
        word = word.toLowerCase();
        if (W.contains(word)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Cas b2 On verifie le cas b2 (enonce du devoir)
     * @param mot courant a verfier
     */
    public HashSet < String > b2(String mot) {
        HashSet < String > setMot = new HashSet < > ();
        HashSet < String > tab = new HashSet < > ();

        for (int i = 0; i < mot.length(); i++) {
            String localWord = mot.substring(0, i);
            String localWord2 = mot.substring(i + 1);
            setMot.add(localWord + localWord2);
        }
        Iterator itr = S.entrySet().iterator();
        while (itr.hasNext()) {

            Map.Entry mapSet = (Map.Entry) itr.next(); // acceder aux elements
            String Str = ((String) mapSet.getKey()); // obtener un mot du dicctionnaire
            HashSet < String > A = (HashSet) S.get(Str); // obtenir le set de suggestions
            Iterator itr2 = A.iterator();

            // iterer le set de suggestions
            while (itr2.hasNext()) {
                Iterator itr3 = setMot.iterator();
                String wd = ((String) itr2.next());

                while (itr3.hasNext()) {
                    String pseudoMot = (String) itr3.next(); //pseudomot construite au debut
                    if (pseudoMot.equals(wd)) {

                        tab.add(Str);

                    }
                    if (pseudoMot.equals(Str) && !tab.contains(Str)) {

                        tab.add(Str);
                    }
                }
            }

        }
        return tab;
    }

}