package co.edu.unbosque.model.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {

    public ReadFile() {

    }

    public String obtainFileData(String fileName) throws IOException {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder data = new StringBuilder();
        String linea;

        while ((linea = br.readLine()) != null)
            data.append(linea).append("\n");

        br.close();
        return data.toString();
    }

    public ArrayList<String[]> obtainFileTokensData(String fileName) throws IOException {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<String[]> tokens = new ArrayList<>();
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] items = linea.split("~");
            if (items.length == 3) {
                tokens.add(new String[]{items[0], items[1], items[2]});

            } else {
                return new ArrayList<>();
            }
        }
        br.close();
        return tokens;
    }
}
