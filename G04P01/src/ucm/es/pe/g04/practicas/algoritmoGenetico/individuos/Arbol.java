package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.Random;

public class Arbol implements Cloneable {
    private String valor;
    private Arbol padre = null;
    private ArrayList<Arbol> hijos;
    private int numHijos;

    public int getNumNodos() {
        return numNodos;
    }

    private int numNodos;

    private int index = -1;


    private int max_prof = 4;
    private int profundidad;
    private boolean useIF = true;

    private boolean esHoja;
    private boolean esRaiz;

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public ArrayList<Arbol> getHijos() {
        return hijos;
    }

    public Arbol getPadre() {
        return padre;
    }

    public void clearPadre() {
        padre = null;
        index = -1;
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

    public int getMaxProfundidad() {
        return max_prof;
    }

    public void setMaxProfundidad(int max_profundidad) {
        this.max_prof = max_profundidad;
    }

    private Random rand;

    public Arbol(String v) {
        this();
        valor = v;
    }

    public Arbol(int prof, boolean IF) {
        this();
        max_prof = prof;
        useIF = IF;
    }

    public Arbol() {
        rand = new Random();
    }


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
            a.index = numHijos -1;
        } else{
            hijos.set(index, a);
            a.index = index;
        }
        return a;
    }

    // Insertar un arbol en otro arbol.
    public void insert(Arbol a, int index) {
        if (this.hijos.contains(a))
            return;
        if (index == -1) {
            hijos.add(a);
            numHijos = hijos.size();
            a.index = numHijos -1;
        } else {
            if (hijos.get(index) != null) {
                hijos.get(index).index = -1;
                hijos.get(index).padre = null;
            }
            hijos.set(index, a);
            a.index = index;
        }

        a.padre = this;
        propagarNodos();
    }

    public void remove(int index) {
        if (index >= numHijos)
            return;
        remove(hijos.get(index));
    }

    public void remove(Arbol a) {
        if (hijos.contains(a)) {
            hijos.remove(a);
            numHijos = hijos.size();
            a.padre = null;
            propagarNodos();
        }
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

    public void inicializacionCompleta(int p, IndividuoArbol ind) {
        if (p < max_prof) {
            setProfundidad(p);
            Random rnd = new Random();
            int func = 0;
            if (useIF) {
                func = rnd.nextInt(ind.getFunciones().length);
            } else {
                func = rnd.nextInt(ind.getFunciones().length - 1);
            }
            this.valor = ind.getFunciones()[func];
            this.setEsRaiz(true);

            this.hijos = new ArrayList<>();
            for (int i = 0; i < ind.getElemsPorFuncion()[func]; i++) {
                Arbol a = new Arbol();
                a.inicializacionCompleta(p + 1, ind);
                this.insert(a, -1);
            }

        } else {
            Random rnd = new Random();
            int term = rnd.nextInt(ind.getTerminales().length);
            this.valor = ind.getTerminales()[term];
            this.setEsHoja(true);
        }
        propagarNodos();
    }

    public int inicializacionCreciente(int p, IndividuoArbol ind) {
        int n = 1;
        if (p < max_prof) {
            int func = 0;
            if (rand.nextInt() % 2 == 0) {
                setProfundidad(p);
                if (useIF) {
                    func = rand.nextInt(ind.getFunciones().length);
                } else {
                    func = rand.nextInt(ind.getFunciones().length - 1);
                }

                this.valor = ind.getFunciones()[func];
                this.setEsRaiz(true);

                this.hijos = new ArrayList<>();
                for (int i = 0; i < ind.getElemsPorFuncion()[func]; i++) {
                    Arbol a = new Arbol();
                    propagarNodos();
                    n += a.inicializacionCreciente(p + 1, ind);
                    this.insert(a, -1);
                }
            } else {
                func = rand.nextInt(ind.getTerminales().length);
                this.valor = ind.getTerminales()[func];
                this.setEsHoja(true);
            }
        } else {
            int term = rand.nextInt(ind.getTerminales().length);
            this.valor = ind.getTerminales()[term];
            this.setEsHoja(true);
        }
        this.numNodos = n;
        return n;
    }

    public Arbol getRandomHijo(double probInterno, boolean recursive) {
        Arbol ret;
        if (recursive)
            ret = getRandomHijo(this, probInterno);
        else
            ret = rand.nextDouble() < probInterno ? getRandomFuncion() : getRandomTerminal();
        return ret == null ? this : ret;
    }

    private Arbol getRandomHijo(Arbol a, double probInterno) {
        propagarNodos();
        double r = rand.nextDouble();
        boolean check = (a.padre != null) && (((r < probInterno) && (a.esRaiz)) || ((r < 1 - probInterno) && (a.esHoja)));
        if (check)
            return a;
        Arbol ret = null;
        if (!a.esHoja) {
            for (Arbol hijo : a.getHijos()) {
                if (ret == null) ret = getRandomHijo(hijo, probInterno);
            }
        }
        return ret;
    }


    public Object clone() {
        try {
            Arbol newClone = (Arbol) super.clone();
            newClone.hijos = null;
            if (this.hijos != null) {
                newClone.hijos = new ArrayList<>(numHijos);
                for (int i = 0; i < hijos.size(); i++) {
                    Arbol a = (Arbol) this.hijos.get(i).clone();
                    newClone.hijos.add(a);
                    a.padre = newClone;
                    a.index = i;
                }
            }
            return newClone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        String s = "";
        s = toStringRecursive(s);
        return s;
    }

    public String toStringRecursive(String s) {
        s += valor + " ";

        if (esRaiz) {
            s += "(";
            for (Arbol a : hijos) {
               s = a.toStringRecursive(s);
            }
            s = s.substring(0,s.length() -1);
            s += ")";
        }

        return s;
    }

    public Arbol getRandomTerminal() {
        ArrayList<Arbol> terminales = new ArrayList<>();
        getAllTerminals(terminales);
        return terminales.get(rand.nextInt(terminales.size()));
    }

    public void getAllTerminals(ArrayList<Arbol> lista) {
        if (esHoja) lista.add(this);
        else {
            for (Arbol a : hijos) {
                a.getAllTerminals(lista);
            }
        }
    }

    public Arbol getRandomFuncion() {
        ArrayList<Arbol> funciones = new ArrayList<>();
        getAllFunctions(funciones);
        if (funciones.size() == 0) {
            return null;
        } else return funciones.get(rand.nextInt(funciones.size()));
    }

    public void getAllFunctions(ArrayList<Arbol> lista) {
        if (esRaiz) {
            lista.add(this);
            for (Arbol a : hijos) {
                a.getAllFunctions(lista);
            }
        }
    }


    public void propagarNodos() {
        Arbol raiz = getRaiz();
        raiz.actualizaNumNodos();
    }

    public int actualizaNumNodos() {

        numNodos = 1;

        for (int i = 0; i < numHijos; i++) {
            hijos.get(i).padre = this;
            hijos.get(i).index = i;
            numNodos += hijos.get(i).actualizaNumNodos();
        }

        return numNodos;
    }

    public Arbol getRaiz() {
        Arbol raiz = this;

        while (raiz.padre != null) {
            raiz = raiz.getPadre();
        }

        return raiz;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public int getIndex() {
        return index;
    }
}