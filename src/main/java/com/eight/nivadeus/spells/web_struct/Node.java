package com.eight.nivadeus.spells.web_struct;

import java.util.PriorityQueue;

/**
 * node that allows for multiple parents and children as well as a position
 * @author Ethan Greenwood (KingOhzar)
 **/
public class Node {

    private double Xpos;
    private double Ypos;

    private final int maxParent;
    private final int maxChild;

    private Node[] parentConnections;
    private PriorityQueue<Integer> parentConAvalHeap;
    private int numParent;

    private Node[] childConnections;
    private PriorityQueue<Integer> childConAvalHeap;
    private int numChild;

    /**
     * constructor
     *
     * @param x starting x position
     * @param y starting y position
     * @param parentMax max parents.
     * @param childMax max children.
     */
    public Node (double x, double y, int parentMax, int childMax){
        this.Xpos = x;
        this.Ypos = y;

        this.maxParent = parentMax;
        this.maxChild = childMax;

        this.parentConnections = new Node[maxParent];
        this.parentConAvalHeap = new PriorityQueue<Integer>(maxParent);

        this.childConnections = new Node[maxChild];
        this.childConAvalHeap = new PriorityQueue<Integer>(maxChild);

        for(int i = 0; i < maxParent; i++){
            this.parentConAvalHeap.add(i);
        }
        for(int i = 0; i < maxChild; i++){
            this.childConAvalHeap.add(i);
        }

        numParent = 0;
        numChild = 0;

    }

    /**
     * constructor. Defaults maximum parent and child values to 5.
     *
     * @param x starting x position
     * @param y starting y position
     */
    public Node (double x, double y){
        this(x,y,5,5);
    }

    /**
     * adds a connection to a parent node
     *
     * @param newNode node to be added
     * @return Returns true on success.
     */
    public boolean addParent(Node newNode){
        if(newNode != null && numParent != maxParent) {
            parentConnections[parentConAvalHeap.remove()] = newNode;
            numParent++;
            return true;
        }else{
            return false;
        }
    }

    /**
     * adds a connection to a child node
     *
     * @param newNode node to be added
     * @return Returns true on success.
     */
    public boolean addChild(Node newNode){
        if(newNode != null && numChild != maxChild) {
            childConnections[childConAvalHeap.remove()] = newNode;
            numChild++;
            return true;
        }else{
            return false;
        }
    }

    /**
     * connects a parent and child both ways
     *
     * @param newNode child to be connected
     * @return Returns true on success.
     */
    public boolean addChildLoud(Node newNode){
        if(newNode != null && numChild != maxChild) {
            childConnections[childConAvalHeap.remove()] = newNode;
            newNode.addParent(this);
            numChild++;
            return true;
        }else{
            return false;
        }
    }

    /**
     * removes the connection to the passed parent node.
     *
     * @param node node to be removed
     * @return Returns true on success.
     */
    public boolean removeParentSilent(Node node){
        int i = findNode(node, true);

        if(i != -1){
            parentConnections[i] = null;
            parentConAvalHeap.add(i);
            numParent--;
            return true;
        }
        return false;
    }

    /**
     * removes the connection to the passed parent node, while removing itself from the parent node's child connections.
     *
     * @param node node to be removed
     * @return Returns true on success.
     */
    public boolean removeParent(Node node){
        int i = findNode(node, true);

        if(i != -1){
            parentConnections[i].removeChildSilent(this);
            parentConnections[i] = null;
            parentConAvalHeap.add(i);
            numParent--;
            return true;
        }
        return false;
    }

    /**
     * removes the connection to the passed child node.
     *
     * @param node node to be removed
     * @return Returns true on success.
     */
    public boolean removeChildSilent(Node node){
        int i = findNode(node, false);

        if(i != -1){
            childConnections[i] = null;
            childConAvalHeap.add(i);
            numChild--;
            return true;
        }
        return false;

    }

    /**
     * removes the connection to the passed child node, while removing itself from the child node's parent connections.
     *
     * @param node node to be removed
     * @return Returns true on success.
     */
    public boolean removeChild(Node node){
        int i = findNode(node, false);

        if(i != -1){
            childConnections[i].removeParentSilent(this);
            childConnections[i] = null;
            childConAvalHeap.add(i);
            numChild--;
            return true;
        }
        return false;

    }

    /**
     * looks for passed node in either parents or child, returning the index.
     *
     * @param node node to find
     * @param parent boolean value. True indicates searching through parent nodes, False indicates child nodes
     * @return Returns index of found node. Returns -1 if the node was not found.
     */
    private int findNode (Node node, boolean parent){

        if(parent){

            for (int i = 0; i < maxParent; i++){
                if(parentConnections[i] == node)
                    return i;
            }

        }else{

            for (int i = 0; i < maxChild; i++){
                if(childConnections[i] == node)
                    return i;
            }
        }

        return -1;
    }

    /**
     *  allows garbage collection to collect the node by removing all connections
     */
    public void delete(){

        for(int i = 0; i < maxParent; i++){
            if(parentConnections[i] != null)
                parentConnections[i].removeChild(this);
        }

        for(int i = 0; i < maxChild; i++){
            if(childConnections[i] != null)
                childConnections[i].removeParent(this);
        }

    }

    /**
     *  allows garbage collection to collect the node by removing all connections, propagates to all children
     */
    public void delete_cascade(){

        for(int i = 0; i < maxChild; i++){
            if(childConnections[i] != null)
                childConnections[i].delete_cascade();
        }

        for(int i = 0; i < maxParent; i++){
            if(parentConnections[i] != null)
                parentConnections[i].removeChild(this);
        }

    }

    public double getXpos(){
        return Xpos;
    }

    public double getYpos(){
        return Ypos;
    }

    public boolean setXpos(double x){
        return setPos(x, this.Ypos);
    }

    public boolean setYpos(double y){
        return setPos(this.Xpos, y);
    }

    public boolean setPos(double x, double y){

        this.Xpos = x;
        this.Ypos = y;
        return true;

    }

    @Override
    public String toString(){
        return "(" + String.valueOf(this.getXpos()) + ", " + String.valueOf(this.getYpos()) + ")";
    }

}
