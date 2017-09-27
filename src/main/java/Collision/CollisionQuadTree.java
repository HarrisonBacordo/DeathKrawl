package Collision;
import Entity.*;



import java.awt.*;
import java.util.ArrayList;

public class CollisionQuadTree {

    private int MAX_OBJECTS = 10;
    private int MAX_LEVELS = 5;


    //measures how deep you are in the tree
    private int deep;
    //list of the Entities within each tree
    private ArrayList<Entity> objects;
    //represents the tiles the tree takes over in the graphics
    private Rectangle bounds;
    //the four children nodes
    private CollisionQuadTree[] nodes;

    /*
     * Constructor
     */
    public CollisionQuadTree(int deep, Rectangle bounds) {
        this.bounds = bounds;
        this.nodes = new CollisionQuadTree[4];
        this.deep = deep;
        this.objects = new ArrayList<>();
    }


    /*
     * Return all objects that could collide with the given object
     */
    public ArrayList retrieve(ArrayList<Entity> returnObjects, Rectangle pRect) {

        int index = getIndex(pRect);
        if (index != -1 && nodes[0] != null) {
            nodes[index].retrieve(returnObjects, pRect);
        }

        returnObjects.addAll(objects);

        return returnObjects;
    }


    /*
 * Clears the quadtree
 */
    public void clear() {
        objects.clear();

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].clear();
                nodes[i] = null;
            }
        }
    }


    /*
 * Splits the node into 4 subnodes
 */
    private void split() {
        int subWidth = (int)(bounds.getWidth() / 2);
        int subHeight = (int)(bounds.getHeight() / 2);
        int x = (int)bounds.getX();
        int y = (int)bounds.getY();

        nodes[0] = new CollisionQuadTree(deep+1, new Rectangle(x + subWidth, y, subWidth, subHeight));
        nodes[1] = new CollisionQuadTree(deep+1, new Rectangle(x, y, subWidth, subHeight));
        nodes[2] = new CollisionQuadTree(deep+1, new Rectangle(x, y + subHeight, subWidth, subHeight));
        nodes[3] = new CollisionQuadTree(deep+1, new Rectangle(x + subWidth, y + subHeight, subWidth, subHeight));
    }


    /*
 * Determine which node the object belongs to. -1 means
 * object cannot completely fit within a child node and is part
 * of the parent node
 */
    private int getIndex(Rectangle pRect) {
        int index = -1;
        double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
        double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);

        // Object can completely fit within the top quadrants
        boolean topQuadrant = (pRect.getY() < horizontalMidpoint && pRect.getY() + pRect.getHeight() < horizontalMidpoint);
        // Object can completely fit within the bottom quadrants
        boolean bottomQuadrant = (pRect.getY() > horizontalMidpoint);

        // Object can completely fit within the left quadrants
        if (pRect.getX() < verticalMidpoint && pRect.getX() + pRect.getWidth() < verticalMidpoint) {
            if (topQuadrant) {
                index = 1;
            }
            else if (bottomQuadrant) {
                index = 2;
            }
        }
        // Object can completely fit within the right quadrants
        else if (pRect.getX() > verticalMidpoint) {
            if (topQuadrant) {
                index = 0;
            }
            else if (bottomQuadrant) {
                index = 3;
            }
        }

        return index;
    }

    /*
 * Insert the object into the quadtree. If the node
 * exceeds the capacity, it will split and add all
 * objects to their corresponding nodes.
 */
    public void insert(Entity entity) {
        if (nodes[0] != null) {
            int index = getIndex(entity.getBoundingBox());

            if (index != -1) {
                nodes[index].insert(entity);

                return;
            }
        }

        objects.add(entity);

        if (objects.size() > MAX_OBJECTS && deep < MAX_LEVELS) {
            if (nodes[0] == null) {
                split();
            }

            int i = 0;
            while (i < objects.size()) {
                int index = getIndex(objects.get(i).getBoundingBox());
                if (index != -1) {
                    nodes[index].insert(objects.remove(i));
                }
                else {
                    i++;
                }
            }
        }
    }

}