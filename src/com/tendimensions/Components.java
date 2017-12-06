package com.tendimensions;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Components {

    private List<String> values;
    private String hashToFind;
    private int permutationsCount;

    private List<List<String>> permutations;


    public Components() {
        values = new ArrayList<>();
    }

    public void initialize() {

        if ((values.size() < 1) || hashToFind == null) {
            throw new IllegalArgumentException("Missing values in one or both text files.");
        }

        // As a check to make sure we've reached all possible combinations
        permutationsCount = factorial(values.size());

        permutations = generatePermutations(values);
    }

    private int factorial(int size) {
        if (size == 1) return size;
        return factorial(size - 1) * size;
    }


    @SuppressWarnings("unchecked")
    public Pair<String, String> findHash() {

        for (List<String> strings : permutations) {
            StringBuilder sb = new StringBuilder();

            for (String s : strings) {
                sb.append(s);
            }

            String stringToMatch = sb.toString();

            System.out.println(String.format("Computing hashes for string: [%s]", stringToMatch));

            if (hashToFind.equals(Hashes.getMD5(stringToMatch))) {
                return new Pair(stringToMatch, "MD5");
            }

            if (hashToFind.equals(Hashes.getSHA1(stringToMatch))) {
                return new Pair(stringToMatch, "SHA1");
            }

            if (hashToFind.equals(Hashes.getSHA256(stringToMatch))) {
                return new Pair(stringToMatch, "SHA256");
            }

            if (hashToFind.equals(Hashes.getSHA512(stringToMatch))) {
                return new Pair(stringToMatch, "SHA512");
            }
        }

        return null;
    }

    public <E> List<List<E>> generatePermutations(List<E> original) {
        if (original.size() == 0) {
            List<List<E>> result = new ArrayList<List<E>>();
            result.add(new ArrayList<E>());
            return result;
        }
        E firstElement = original.remove(0);
        List<List<E>> returnValue = new ArrayList<List<E>>();
        List<List<E>> permutations = generatePermutations(original);
        for (List<E> smallerPermutated : permutations) {
            for (int index=0; index <= smallerPermutated.size(); index++) {
                List<E> temp = new ArrayList<E>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }
    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public String getHashToFind() {
        return hashToFind;
    }

    public void setHashToFind(String hashToFind) {
        this.hashToFind = hashToFind;
    }

    public int getPermutationsCount() {
        return permutationsCount;
    }

    public void setPermutationsCount(int permutationsCount) {
        this.permutationsCount = permutationsCount;
    }
}
