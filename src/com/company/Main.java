package com.company;

import java.util.Scanner;
import java.util.Arrays;
class Node
{
    int value;
    Node leftchild, rightchild;

    Node(int item)
    {
        value = item;
        leftchild = rightchild = null;
    }
}

class BinaryTree
{
    Node root;
    static int preIndex = 0;
    static int index=0;
    static int sumIndex=0;

    Node constructTree(int in[], int pre[], int inStrt, int inEnd)
    {
        if (inStrt > inEnd)
            return null;

        Node tNode = new Node(pre[preIndex++]);

        if (inStrt == inEnd)
            return tNode;

        int inIndex = search(in, inStrt, inEnd, tNode.value);

        tNode.leftchild = constructTree(in, pre, inStrt, inIndex - 1);
        tNode.rightchild = constructTree(in, pre, inIndex + 1, inEnd);

        return tNode;
    }

    int search(int arr[], int strt, int end, int value)
    {
        int i;
        for (i = strt; i <= end; i++)
        {
            if (arr[i] == value)
                return i;
        }
        return i;
    }

    // public static final int sumTree[] = new int[20];
    //int index1=0;
    int sumBinaryTree(Node node, int[] sumTree)
    {
        // Write the logic to recursively create Binary Tree consisting of sum of all its children
        //sumTree= new int[20];

        int mySum, leftSum, rightSum;

        if ( node == null )
        {
            mySum = 0;        // Solution for the base case

            return mySum;     // Return solution
        }
        else
        {
            leftSum  = sumBinaryTree(node.leftchild,sumTree);      // Solve smaller problem 1
            rightSum = sumBinaryTree(node.rightchild,sumTree);     // Solve smaller problem 2

            mySum = node.value + leftSum + rightSum;
            // Solve my problem using
            // solution of smaller problem
            // System.out.print(mySum+" ");
            sumTree[sumIndex++]= mySum;

            return mySum;     // Return solution
            //return 0;
        }
    }

    void printPostorder(Node node)
    {
        if (node == null)
            return;

        // first recur on left subtree
        printPostorder(node.leftchild);

        // then recur on right subtree
        printPostorder(node.rightchild);

        // now deal with the node
        System.out.print(node.value + " ");
    }

    void inOrder(Node node, int array[])
    {
        if (node == null)
            return;
        inOrder(node.leftchild, array);
        array[index++] = node.value;
        inOrder(node.rightchild, array);

    }

    Node ArrayToBST(int arr[], int start, int end) {

        if(start > end){
            return null;
        }

        int mid = (start+end)/2;
        Node node = new Node(arr[mid]);
        node.leftchild = ArrayToBST(arr, start, mid-1);
        node.rightchild = ArrayToBST(arr, mid+1, end);

        return node;

        // Write logic to convert the array representing Binary Tree to Binary Search Tree
    }



}
 class Source{



    // driver program to test above functions
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int in[] = new int[len];
        int pre[] = new int[len];
        for(int i=0;i<len;i++){
            in[i] = scanner.nextInt();
        }
        for(int i=0;i<len;i++){
            pre[i] = scanner.nextInt();
        }
        BinaryTree tree = new BinaryTree();
        Node root = tree.constructTree(in, pre, 0, len - 1);
        int[] inSumTree = new int[len];

        tree.sumBinaryTree(root,inSumTree);

        Node bstRoot = tree.ArrayToBST(inSumTree, 0, len-1);

        tree.inOrder(bstRoot, inSumTree);
        Arrays.sort(inSumTree);
        tree.printPostorder(bstRoot);

    }
}