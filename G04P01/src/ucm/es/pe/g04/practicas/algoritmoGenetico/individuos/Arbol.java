package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.Random;

public class Arbol {
    private String valor;
    private ArrayList<Arbol> hijos;
    private int numHijos;
    private int numNodos;
    private int max_prof = 4;
    private int profundidad;
    private boolean useIF = true;

    private boolean esHoja;
    private boolean esRaiz;

    public String getValor() {
        return valor;
    }
    
    public ArrayList<Arbol> getHijos() {
        return hijos;
    }

    public int getNumHijos() {
        return numHijos;
    }
    public void setEsRaiz(boolean esRaiz) {
        this.esRaiz = esRaiz;
    }

    public void setEsHoja(boolean esHoja) {
        this.esHoja = esHoja;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }


    public Arbol(String v) { valor = v; }
    public Arbol(int prof, boolean IF) { max_prof = prof; useIF = IF; }
    public Arbol() {}


    // Devuelve el arbol en forma de array
    public ArrayList<String> toArray() {
        ArrayList<String> array = new ArrayList<String>();
        toArrayAux(array, this);
        return array;
    }

    // Insertar un valor en el arbol (nodo simple)
    public Arbol insert(String v, int index) {
        Arbol a = new Arbol(v);
        if (index == -1) {
            hijos.add(a);
            numHijos = hijos.size();
        } else
            hijos.set(index, a);
        return a;
    }

    // Insertar un arbol en otro arbol.
    public void insert(Arbol a, int index) {
        if (index == -1) {
            hijos.add(a);
            numHijos = hijos.size();
        } else
            hijos.set(index, a);
    }

    public Arbol at(int index) {
        return at(this, 0, index);
    }

    private Arbol at(Arbol a, int pos, int index) {
        Arbol s = null;
        if (pos >= index) s = a;
        else if (a.getNumHijos() > 0) {
            for (int i = 0; i < a.getNumHijos(); i++)
                if (s == null) s = at(a.getHijos().get(i), pos + i + 1, index);
        }
        return s;
    }

    private void toArrayAux(ArrayList<String> array, Arbol a) {
        array.add(a.valor);
        for (int i = 0; i < a.hijos.size(); i++) {
            toArrayAux(array, a.hijos.get(i));
        }
    }

    public int inicializacionCompleta(int p) {
        int n = 1;
        if (p < max_prof) {
            setProfundidad(p);
            Random rnd = new Random();
            int func = 0;
            if (useIF) {
                func = rnd.nextInt(IndividuoArbol1.funciones.length);
            } else {
                func = rnd.nextInt(IndividuoArbol1.funciones.length - 1);
            }
            this.valor = IndividuoArbol1.funciones[func];
            this.setEsRaiz(true);

            this.hijos = new ArrayList<>();
            for (int i = 0; i < IndividuoArbol1.elemsPorFuncion[func]; i++) {
                Arbol a = new Arbol();
                n += a.inicializacionCompleta(p+1);
                this.insert(a, -1);
            }

        }
        else{
            Random rnd = new Random();
            int term = rnd.nextInt(IndividuoArbol1.terminales.length);
            this.valor = IndividuoArbol1.terminales[term];
            this.setEsHoja(true);
        }
        this.numNodos = n;
        return n;
    }

    public int inicializacionCreciente(int p) {
        int n = 1;
        if (p < max_prof) {
            Random rnd = new Random();
            int func = 0;
            if (rnd.nextInt() % 2 == 0)
            {
                setProfundidad(p);
                if (useIF) {
                    func = rnd.nextInt(IndividuoArbol1.funciones.length);
                } else {
                    func = rnd.nextInt(IndividuoArbol1.funciones.length - 1);
                }

                this.valor = IndividuoArbol1.funciones[func];
                this.setEsRaiz(true);

                this.hijos = new ArrayList<>();
                for (int i = 0; i < IndividuoArbol1.elemsPorFuncion[func]; i++) {
                    Arbol a = new Arbol();
                    n += a.inicializacionCreciente(p+1);
                    this.insert(a, -1);
                }
            }
            else {
                func = rnd.nextInt(IndividuoArbol1.terminales.length);
                this.valor = IndividuoArbol1.terminales[func];
                this.setEsHoja(true);
            }
        }
        else{
            Random rnd = new Random();
            int term = rnd.nextInt(IndividuoArbol1.terminales.length);
            this.valor = IndividuoArbol1.terminales[term];
            this.setEsHoja(true);
        }
        this.numNodos = n;
        return n;
    }
}