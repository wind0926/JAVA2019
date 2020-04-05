# 面试题32 - III. 从上到下打印二叉树 III

### 题目链接

#### [面试题32 - III. 从上到下打印二叉树 III](https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/)



### 题目描述

请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。

 

例如:
给定二叉树: [3,9,20,null,null,15,7],

```java
    3
   / \
  9  20
    /  \
   15   7
```

返回其层次遍历结果：

```java
[
  [3],
  [20,9],
  [15,7]
]
```


提示：

```
节点总数 <= 1000
```





### 题解

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
  List<List<Integer>> lists = new ArrayList<>();
        int level=0;
        if (root==null){
            return lists;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            ArrayList<Integer> list = new ArrayList<>();
            int size=queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    if (poll.right!=null){
                        queue.offer(poll.right);
                    }
                    if (poll.left!=null){
                        queue.offer(poll.left);
                    }
                    list.add(poll.val);

                }
                if (level%2==0){
                    Collections.reverse(list);
                }

            lists.add(list);
            level++;
        }
        return lists;
    }
}
```

