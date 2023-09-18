package com.ufersa.ed2praticaoffline1.model.hashTable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HashTableEndAberto<K, V> {
    private static final double LOAD_FACTOR = 0.7;
    private Entry<K, V>[] table;
    private int size;

    public HashTableEndAberto(int capacity) {
        table = new Entry[capacity];
        size = 0;
    }

    public void put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");

        if ((double) size / table.length >= LOAD_FACTOR) {
            resizeTable();
        }

        int hash = hash(key);
        int index = hash;

        while (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                table[index].setValue(value);
                return;
            }
            index = (index + 1) % table.length;
        }

        table[index] = new Entry<>(key, value);
        size++;
        addLog("Inserção");
    }

    private double fatorDeCarga(){
        return (double) size / table.length;
    }

    public V get(K key) {
        int hash = hash(key);
        int index = hash;

        while (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                return table[index].getValue();
            }
            index = (index + 1) % table.length;
        }

        return null;
    }

    public void remove(K key) {
        int hash = hash(key);
        int index = hash;
        addLog("Remoção");

        while (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                table[index] = null;
                size--;
                return;
            }
            index = (index + 1) % table.length;
        }

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
        Entry<K, V>[] newTable = new Entry[newCapacity];

        for (Entry<K, V> entry : table) {
            if (entry != null) {
                int index = hash(entry.getKey(), newCapacity);
                while (newTable[index] != null) {
                    index = (index + 1) % newTable.length;
                }
                newTable[index] = entry;
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

        for (Entry<K, V> entry : table) {
            if (entry != null) {
                entrySet.add(entry);
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