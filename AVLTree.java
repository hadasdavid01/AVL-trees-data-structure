package coronatree;

public class AVLTree {

    AVLNode root;    // The tree root.
    int size;        // The size of the tree.

    /**
     * Construct an empty tree.
     */
    public AVLTree() {
        this.root = null;
    }

    /**
     * Returns the size of the tree.
     *
     * @return the size of the tree.
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns the height of the tree.
     * Returns -1 if the tree is empty.
     *
     * @return the height of the tree.
     */
    public int height() {
        if (root == null) {
            return -1;
        }
        return root.height;
    }

    int height(AVLNode node) {
        if (node == null) {
            return -1;
        }

        return node.height;
    }

    /**
     * Inserts into the tree; You may assume that every person has a unique ID number.
     * That is, no person will appear twice.
     *
     * @param p - the person to insert.
     */
    private AVLNode createNewNode(Person p, AVLNode parent) {
        AVLNode node = new AVLNode(p);
        node.parent = parent;
        this.size++;
        return node;
    }

    public void insert(Person p) {
        if (this.root == null) {
            this.root = new AVLNode(p);
            this.root.height = 0;
            this.size++;
            return;
        }

        AVLNode parent = this.root;
        AVLNode node = null;

        while (parent != null) {
            if (p.compareTo(parent.data) > 0) {

                if (parent.right == null) {
                    node = this.createNewNode(p, parent);
                    parent.right = node;
                    break;
                }

                parent = parent.right;
            } else {
                if (parent.left == null) {
                    node = this.createNewNode(p, parent);
                    parent.left = node;
                    break;
                }

                parent = parent.left;
            }
        }

        parent.height = 1 + Math.max(height(parent.left), height(parent.right));
        balanceTree(node, p);
    }

    private int isBalance(AVLNode node) {
        if(node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // return new root
    private AVLNode balanceTree(AVLNode node, Person p) {

        // Left2Left Case
        if (isBalance(node) > 1 && p.compareTo(node.left.data) < 0) {
            return rightRotate(node);
        }

        // Right2Right Case
        if (isBalance(node) < -1 && p.compareTo(node.right.data) > 0) {
            return leftRotate(node);
        }

        // Left2Right Case
        if (isBalance(node) > 1 && p.compareTo(node.left.data) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right2Left Case
        if (isBalance(node) < -1 && p.compareTo(node.right.data) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private AVLNode rightRotate(AVLNode node) {
        AVLNode leftNode = node.left;
        AVLNode rightNode = leftNode.right;

        leftNode.right = node;
        node.parent = leftNode;
        node.left = rightNode;
        if (rightNode != null)
            rightNode.parent = node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        leftNode.height = 1 + Math.max(height(leftNode.left), height(leftNode.right));

        return rightNode;
    }

    private AVLNode leftRotate(AVLNode node) {
        AVLNode rightNode = node.right;
        AVLNode leftNode = rightNode.left;

        rightNode.left = node;
        node.parent = rightNode;
        node.right = leftNode;
        if (leftNode != null)
            leftNode.parent = node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        rightNode.height = 1 + Math.max(height(rightNode.left), height(rightNode.right));

        return rightNode;
    }

    /**
     * Search for a person in the tree.
     *
     * @param p the person to search for.
     * @return true iff 'p' is an element in the tree.
     */
    public boolean search(Person p) {
        AVLNode pointer = this.root;
        while (pointer != null) {
            if (p.compareTo(pointer.data) > 0) {
                pointer = pointer.right;
            } else if (p.compareTo(pointer.data) < 0) {
                pointer = pointer.left;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Traverse the contents of this tree in an 'inorder' manner and return and array
     * containing the traversal of the tree.
     *
     * @return a sorted array of the tree's content.
     */
    public Person[] inorder() {
        Person[] arr = new Person[this.size];

        if (this.root == null) {
            return arr;
        }

        this.inorderRecurse(this.root, arr, 0);

        return arr;
    }

    private int inorderRecurse(AVLNode node, Person[] arr, int startIndex) {
        int leftInserted = 0;
        int rightInserted = 0;

        if (node.left != null) {
            leftInserted = this.inorderRecurse(node.left, arr, startIndex);
        }

        arr[leftInserted + startIndex] = node.data;

        if (node.right != null) {
            rightInserted = this.inorderRecurse(node.right, arr, startIndex + leftInserted + 1);
        }

        return 1 + leftInserted + rightInserted;
    }

}
