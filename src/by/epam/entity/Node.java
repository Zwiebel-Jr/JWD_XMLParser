package by.epam.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node implements Serializable {
    private String name;
    private String value;

    private List<Attribute> attributes = new ArrayList<Attribute>();
    private List<Node> childs = new ArrayList<Node>();

    public Node(){
    }

    public Node(String name, String value, List<Attribute> attributes, List<Node> childs) {
        this.name = name;
        this.value = value;
        this.attributes = attributes;
        this.childs = childs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return name.equals(node.name) && Objects.equals(value, node.value) && Objects.equals(attributes, node.attributes) && Objects.equals(childs, node.childs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, attributes, childs);
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", attributes=" + attributes +
                ", childs counts=" + childs.size() +
                "}";
    }

    public void copy(Node node){
        this.name = node.getName();
        this.value = node.getValue();
        this.attributes = node.getAttributes();
        this.childs = node.getChilds();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Node> getChilds() {
        return childs;
    }

    public void setChilds(List<Node> childs) {
        this.childs = childs;
    }

    public void addChilds(Node node){
        this.childs.add(node);
    }

    public  void addAttribute(Attribute attribute){
        this.attributes.add(attribute);
    }
}
