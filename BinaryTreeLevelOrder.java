import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/*

https://leetcode.com/problems/binary-tree-level-order-traversal/description/

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]


Analysis:
- the nulls are not needed in the output
- regular pre-order transversal would have been 3, 9, 20, 15, 7, but that wouldn't
  actually come up with the right answer if the 9 node had children, because the children of the
  first left node will all be visited before the right node is visited
- this is a breadth-first transversal
- by node, it does not lend itself to a recursive solution, because you can't the problem into sub-parts that way
- maybe you can break the problem into rows, and collect everything at a certain depth
- maybe you could keep a stack or queue of nodes who children still need to be visited
- you could use a depth-first tranversal if you built up the answer and kept the results
  according to the depth of where you found the nodes
- so this could be recursive if you passed along the results bucket and traversed in the correct order

Algorithm:
- create a results bucket
- create a helper function and call it with the tree root node, depth 1, and the empty results bucket
- for each node, pass in the depth counter, and the results bucket
- recursive through the tree using a depth-first search, making sure you visit the nodes
  on every row in the correct order so they'll be added to the solution bucket correctly
- create the solution bucket for this level if it is not already created
- place the current node into the solution bucket
- call this method again on the left and then right nodes (if they exist), adding one to the depth count
- once out of the help function, return the resulting bucket
 */


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class BinaryTreeLevelOrder {

    private void levelOrderHelper(TreeNode node, int depth, List<List<Integer>> results) {
        if (node == null) {
            return;
        }

        List<Integer> resultsThisLevel;
        if (results.size() < depth) {
            // create a result bucket for this level if it doesn't already exist
            resultsThisLevel = new ArrayList<Integer>();
            results.add(resultsThisLevel);
        }
        else {
            resultsThisLevel = results.get(depth - 1);
        }

        resultsThisLevel.add(node.val);
        levelOrderHelper(node.left, depth + 1, results);
        levelOrderHelper(node.right, depth + 1, results);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        levelOrderHelper(root, 1, results);
        return results;
    }


    public List<List<Integer>> levelOrderWithQueue(TreeNode root) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (root != null) {
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int qsize = queue.size();  // number of nodes at this level of depth
                List<Integer> nodesAtLevelList = new ArrayList<Integer>();

                // add all nodes at this level to the new list, that will become part of the results
                for (int i = 0; i < qsize; i++) {
                    TreeNode node = queue.poll();
                    nodesAtLevelList.add(node.val);

                    // offer nodes at the next level to the queue, for the next time we start the while loop
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                results.add(nodesAtLevelList);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrder btlo = new BinaryTreeLevelOrder();
        TreeNode tree1 = new TreeNode(3);
        TreeNode left1 = new TreeNode(9);
        tree1.left = left1;
        TreeNode right1 = new TreeNode(20);
        tree1.right = right1;
        TreeNode left2 = new TreeNode(15);
        right1.left = left2;
        TreeNode right2 = new TreeNode(7);
        right1.right = right2;

        System.out.println("BFS (with recursion): " + btlo.levelOrder(tree1));
        System.out.println("    DFS (with queue): " + btlo.levelOrderWithQueue(tree1));
    }
}