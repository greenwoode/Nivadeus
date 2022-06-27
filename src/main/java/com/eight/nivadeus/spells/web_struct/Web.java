package com.eight.nivadeus.spells.web_struct;

import com.eight.nivadeus.spells.web_struct.Node;
import java.util.ArrayList;

public class Web <T extends Node> {

    private T root;
    private ArrayList<T> nodes;

    public Web() {
        this.nodes = new ArrayList<T>();
    }

    public boolean addRoot(T node){
        this.root = node;
        return false;
    }

    public boolean deleteRoot(){
        return false;
    }

    public boolean swapRoot(T node){
        deleteRoot();
        addRoot(node);
        return true;
    }

    public boolean addNode(T newNode){
        nodes.add(newNode);
        return true;
    }

    public boolean connectNodes(T parent, T child){
        if(nodes.contains(parent) && nodes.contains(child)){
            return parent.addChildLoud(child);
        }

        return false;
    }

    public boolean disconnectNodes(T parent, T child){
        if(nodes.contains(parent) && nodes.contains(child)){
            return parent.removeChild(child);
        }

        return false;
    }



}
