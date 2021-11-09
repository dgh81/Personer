package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static Person[] loadPersonsDataFraFil(String txtFileOfPersonsPath) throws FileNotFoundException {
        // Find metode til at finde antal linjer:
        Person[] person = new Person[3];
        File f = new File(txtFileOfPersonsPath);
        Scanner scanner = new Scanner(f);
        int i = 0;
        while (scanner.hasNext()) {
            person[i] = new Person();
            person[i].setName(scanner.nextLine());
            i++;
        }
        return person;
    }
    public static void printPersonsData(Person[] personer) {
        for (int i = 0; i < personer.length; i++) {
            System.out.println(personer[i].getName());
        }
    }
    public static void writeToDatFile(String filePath, Person[] personer) throws IOException {
        DataOutputStream output = new DataOutputStream(new FileOutputStream(filePath));
        for (int i = 0; i < personer.length; i++) {
            output.writeUTF(personer[i].getName());
            System.out.println(personer[i].getName());
        }
        output.close();
    }
    public static Person[] readPersonsFromDatFile(String filePath) throws IOException {
        DataInputStream input = new DataInputStream(new FileInputStream(filePath));
        Person[] personer = new Person[3];
        for (int i = 0; i < 3; i++) {
            personer[i] = new Person();
            personer[i].setName(input.readUTF());
        }
        input.close();
        return personer;
    }
    public static void writePersonsToSerFile(Person[] personer, String serFilename) throws IOException {
        DataOutputStream output2 = new DataOutputStream(new FileOutputStream(serFilename));
        for (int j = 0; j < personer.length; j++) {
            output2.writeUTF(personer[j].getName());
        }
        output2.close();
    }

    public static void main(String[] args) throws IOException {
        //BEMÆRK AT BÅDE // OG \\ VIRKER I FILEPATHS !?:
        
        //Sæt original persondata input:
        String txtFileOfPersonsPath = "c:\\temp\\personer.txt";

        //Load personer ind i person[] array:
        loadPersonsDataFraFil(txtFileOfPersonsPath);

        //Print personnavne fra personer array:
        printPersonsData(loadPersonsDataFraFil(txtFileOfPersonsPath));

        //Skriv personer array til fil .dat:
        writeToDatFile("C://temp//personer.dat", loadPersonsDataFraFil(txtFileOfPersonsPath));

        //Læs personer fra .dat fil til nyt person[] array:
        String filepathDataFile = "C://temp//personer.dat";
        readPersonsFromDatFile(filepathDataFile);

        //Print personnavne fra array fra .dat fil:
        printPersonsData(readPersonsFromDatFile(filepathDataFile));

        //Skriv personer array til fil .ser:
        writePersonsToSerFile(readPersonsFromDatFile(filepathDataFile),"C://temp//personer.ser");
    }
}
