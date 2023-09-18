package com.ufersa.ed2praticaoffline1.model.hashTable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HashTableEncadExt<K, V> {
    private static final double LOAD_FACTOR = 0.7;
    private LinkedList<Entry<K, V>>[] table;
    private int size;

    public HashTableEncadExt(int capacity) {
        table = new LinkedList[capacity];
        size = 0;
    }

    public void put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");

        int hash = hash(key);
        if (table[hash] == null) {
            table[hash] = new LinkedList<>();
        }

        for (Entry<K, V> entry : table[hash]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        table[hash].add(new Entry<>(key, value));
        size++;
        addLog("Inserção");
        // Verifica o fator de carga e redimensiona a tabela se necessário
        if ( fatorDeCarga() >= LOAD_FACTOR) {
            resizeTable();
        }
    }

    private double fatorDeCarga(){
        return (double) size / table.length;
    }

    public V get(K key) {
        int hash = hash(key);
        if (table[hash] != null) {
            for (Entry<K, V> entry : table[hash]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public void remove(K key) {
        int hash = hash(key);
        if (table[hash] != null) {
            table[hash].removeIf(entry -> entry.getKey().equals(key));
        }
        addLog("Remoção");
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    private void resizeTable() {
        int newCapacity = table.length * 2;
        LinkedList<Entry<K, V>>[] newTable = new LinkedList[newCapacity];

        for (LinkedList<Entry<K, V>> chain : table) {
            if (chain != null) {
                for (Entry<K, V> entry : chain) {
                    int newHash = hash(entry.getKey(), newCapacity);
                    if (newTable[newHash] == null) {
                        newTable[newHash] = new LinkedList<>();
                    }
                    newTable[newHash].add(entry);
                }
            }
        }

        table = newTable;
        addLog("RedimensionarTabela");
    }

    private int hash(K key, int capacity) {
        return Math.abs(key.hashCode() % capacity);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entrySet = new HashSet<>();

        for (List<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    entrySet.add(entry);
                }
            }
        }

        return entrySet;
    }

    public void addLog(String metodo){
        try {
            FileWriter fileWriter = new FileWriter("log.txt", true);
            fileWriter.write(metodo + "|" + "Fator de carga: " + fatorDeCarga());
            fileWriter.write(System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}