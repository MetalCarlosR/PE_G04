package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.Random;

public class Arbol implements Cloneable{
    private String valor;
    private Arbol padre = null;
    private ArrayList<Arbol> hijos;
    private int numHijos;
    private int numNodos;
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

    public Arbol getPadre(){
        return padre;
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

    private Random rand;

    public Arbol(String v) {
        this();
        valor = v;
    }
    public Arbol(int prof, boolean IF) {this(); max_prof = prof; useIF = IF;}
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
        } else
            hijos.set(index, a);
        return a;
    }

    // Insertar un arbol en otro arbol.
    public void insert(Arbol a, int index) {
        if(this.hijos.contains(a))
            return;
        if (index == -1) {
            hijos.add(a);
            numHijos = hijos.size();
        } else{
            if(hijos.get(index) != null){
                hijos.get(index).padre = null;
            }
            hijos.set(index, a);
        }
//
//        if(a.padre != null)
//            a.padre.remove(a);
        a.padre = this;
    }

    public void remove(int index){
        if(index >= numHijos)
            return;
        remove(hijos.get(index));
    }

    public void remove(Arbol a){
        if(hijos.contains(a)){
            hijos.remove(a);
            numHijos = hijos.size();
            a.padre = null;
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

    public int inicializacionCompleta(int p, IndividuoArbol ind) {
        int n = 1;
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
                n += a.inicializacionCompleta(p + 1, ind);
                this.insert(a, -1);
            }

        } else {
            Random rnd = new Random();
            int term = rnd.nextInt(ind.getTerminales().length);
            this.valor = ind.getTerminales()[term];
            this.setEsHoja(true);
        }
        this.numNodos = n;
        return n;
    }

    public int inicializacionCreciente(int p, IndividuoArbol ind) {
        int n = 1;
        if (p < max_prof) {
            Random rnd = new Random();
            int func = 0;
            if (rnd.nextInt() % 2 == 0)
            {
                setProfundidad(p);
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
                    n += a.inicializacionCreciente(p+1, ind);
                    this.insert(a, -1);
                }
            }
            else {
                func = rnd.nextInt(ind.getTerminales().length);
                this.valor = ind.getTerminales()[func];
                this.setEsHoja(true);
            }
        }
        else{
            Random rnd = new Random();
            int term = rnd.nextInt(ind.getTerminales().length);
            this.valor = ind.getTerminales()[term];
            this.setEsHoja(true);
        }
        this.numNodos = n;
        return n;
    }

    public Arbol getRandomHijo(double probInterno){
        Arbol ret = getRandomHijo(this,probInterno);
        return ret == null ? this : ret;
    }

    private Arbol getRandomHijo(Arbol a, double probInterno) {
        double r = rand.nextDouble();
        boolean check = (a.padre != null) && (((r < probInterno) && (a.esRaiz)) || ((r < 1 - probInterno) && (a.esHoja)));
        if(check)
            return a;
        Arbol ret = null;
        if(!a.esHoja){
            for (Arbol hijo: a.getHijos()) {
                if(ret == null) ret = getRandomHijo(hijo,probInterno);
            }
        }
        return ret;
    }


    public Object clone() {
        try {
            Arbol newClone = (Arbol) super.clone();
            newClone.hijos = null;
            if (this.hijos != null) {
                newClone.hijos = (ArrayList<Arbol>) this.hijos.clone();
                for (int i = 0; i < hijos.size(); i++) {
                    newClone.hijos.set(i, (Arbol) this.hijos.get(i).clone());
                }
            }
            return newClone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
    
    public Arbol getRandomTerminal(){
        ArrayList<Arbol> terminales = new ArrayList<>();
        getAllTerminals(terminales);
        return terminales.get(rand.nextInt(terminales.size()));
    }
    public void getAllTerminals(ArrayList<Arbol> lista)
    {
        if (esHoja) lista.add(this);
        else{
            for (Arbol a: hijos) {
                a.getAllTerminals(lista);
            }
        }
    }

    public Arbol getRandomFuncion(){
        ArrayList<Arbol> funciones = new ArrayList<>();
        getAllFunctions(funciones);
        return funciones.get(rand.nextInt(funciones.size()));
    }
    public void getAllFunctions(ArrayList<Arbol> lista)
    {
        if (esRaiz){
            lista.add(this);
            for (Arbol a: hijos) {
                a.getAllFunctions(lista);
            }
        }
    }
}