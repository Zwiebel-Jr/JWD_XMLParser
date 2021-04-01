package by.epam.parser;

import by.epam.entity.Attribute;
import by.epam.entity.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class XMLParser {

    private LinkedList<Node> tree = new LinkedList<>();
    private final String PATH_TO_FILE = "resources/task.xml";

    private Pattern pattern = Pattern.compile("<.+>.+<.+>");
    private Pattern openingTagAndValue = Pattern.compile("<.+>.+");
    private Pattern patternStart = Pattern.compile("<.+>");
    private Pattern patternEnd = Pattern.compile("<\\/.+>");

    public Node parse(){
        try {
            Stream<String> lines = Files.lines(Path.of(PATH_TO_FILE));
            XMLFileReader fileReader = new XMLFileReader(PATH_TO_FILE);
            String l;

            while ((l=fileReader.readOneLine())!=null){
                Matcher matcher = pattern.matcher(l);
                Matcher matcherEnd = patternEnd.matcher(l);
                Matcher matcherStart = patternStart.matcher(l);
                Matcher matcherOpeningTagAndValue = openingTagAndValue.matcher(l);

                if(matcher.find()){
                    Node node = new Node();

                    String name = getNodeName(l);
                    String value = getNodeValue(l);
                    node.setName(name);
                    node.setValue(value);

                    tree.peekLast().addChilds(node);
                }else if(matcherEnd.find()){
                    if(tree.size()>1) {
                        tree.get(tree.size()-2).addChilds(tree.pollLast());
                    }
                }else if(matcherOpeningTagAndValue.find()){
                    Node node = new Node();

                    String name = getNodeName(l);
                    String value = getNodeValue(l);
                    node.setName(name);
                    node.setValue(value);

                    tree.addLast(node);
                }else if(matcherStart.find()){
                    Node node = new Node();
                    String[] AtCheck = l.trim().split("\\s");
                    if(AtCheck.length>1){
                        for( int i=1; i<AtCheck.length; i++){
                            String values[] = AtCheck[i].split("=");

                            Attribute attribute = new Attribute();
                            attribute.setName(values[0]);
                            attribute.setValue(values[1].split(">")[0]);

                            node.addAttribute(attribute);
                        }
                    }
                    String name = l.split("<")[1].split(">")[0].split(" ")[0];
                    node.setName(name);
                    tree.addLast(node);
                }
                else{
                    tree.peekLast().setValue(l.trim());
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return tree.getFirst();
    }

    public String getNodeName(String str){
        return str.split("<")[1].split(">")[0];
    }

    public String getNodeValue(String str){
        return str.split(">")[1].split("<")[0];
    }
}
