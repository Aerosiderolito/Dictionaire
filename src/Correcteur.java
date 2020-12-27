import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 * AUTHOR: CESAR AUGUSTO RODRIGUEZ
 * PROGRAMME PRINCIPALE QUI LIT DEUX FICHIERS ET
 * CORRIGE DES FAUTES
 * */
public class Correcteur {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Attention: 2 arguments sont attendus");
            System.exit(-1);
        }

        // fichiers rentres en parametres
        String firstFile = args[0];
        String secondFile = args[1];
        Diccionario dicc = new Diccionario(secondFile);

        String concat = "";

        Pattern palabra = Pattern.compile("[a-zA-Z\\u00C0-\\u017F]+");
        Pattern separador = Pattern.compile("[^a-zA-Z\\u00C0-\\u017F]+");

        try {
            FileReader fileReader = new FileReader(firstFile);
            Scanner s = new Scanner(fileReader);
            s.useDelimiter("\\b");

            while (s.hasNext(palabra)) {
                // Lire un mot
                String word = s.next(palabra);
                String separateur = s.next(separador);
                HashSet < String > tempoSet = new HashSet < > ();
                boolean test = dicc.verifyWord(word);
                // true si le mot lui-même est dans la liste associative
                if (test) {

                    concat += word + separateur;

                } else {

                    HashSet<String> correc = dicc.b1(word);

                    HashSet<String> correct2 = dicc.b2(word);
                    HashSet<String> correct3 = new HashSet<>();

                    correct3.addAll(correc);
                    correct3.addAll(correct2);

                    if (correct3.size() == 0) {
                        correct3.add("(?)");
                    }
                    Iterator<String> itrC = correct3.iterator();
                    String conc = "["+word+" => ";
                    int contador = 0;
                    while (itrC.hasNext()){
                        contador++;
                        if(contador<correct3.size()){
                            conc += itrC.next()+",";
                        }
                        else {
                            conc += itrC.next();
                            contador=0;
                        }
                    }

                    concat += conc +"]" + separateur;

                }
            }

            s.close();
            System.out.println(concat);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
