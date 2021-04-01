package by.epam.parser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XMLFileReader {
    private final String pathFile;
    private final BufferedReader bufferedReader;

    public XMLFileReader(String pathFile) throws FileNotFoundException {
        this.pathFile = pathFile;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.pathFile), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage()); //TODO change to logger
            throw new FileNotFoundException();
        }
    }

    public String readOneLine(){
        String string;
        try {
            if ((string = bufferedReader.readLine()) != null) {
               return string;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }
}
