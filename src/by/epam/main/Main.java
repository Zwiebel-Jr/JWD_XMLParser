package by.epam.main;

import by.epam.entity.Node;
import by.epam.parser.XMLParser;

import java.util.LinkedList;

public class Main {
    private static final String PATH_TO_FILE = "resources/task.xml";

    private static LinkedList<Node> tree = new LinkedList<>();

    public static void main(String[] args) {
        XMLParser parser = new XMLParser();
        Node node = parser.parse();
        System.out.println(node);
    }
}
