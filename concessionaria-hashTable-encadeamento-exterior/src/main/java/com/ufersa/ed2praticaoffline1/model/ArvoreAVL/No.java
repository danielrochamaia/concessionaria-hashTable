package com.ufersa.ed2praticaoffline1.model.ArvoreAVL;

public class No<T> implements Comparable<Integer> {

    int chave;
    T valor;
    int alturaNo;
    No<T> esq, dir;

    public No() {}

    public No(int ch, T v) {

        this.setChave(ch);
        this.setValor(v);
        this.setEsq(null);
        this.setDir(null);

    }


    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public void setEsq(No<T> esq) {
        this.esq = esq;
    }

    public No<T> getEsq() {
        return esq;
    }

    public void setDir(No<T> dir) {
        this.dir = dir;
    }

    public No<T> getDir() {
        return dir;
    }

    @Override
    public int compareTo(Integer o) {

        if(o > this.getChave())
            return 1;
        if(o < this.getChave())
            return -1;

        return 0;
    }

}
