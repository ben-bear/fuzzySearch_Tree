package single;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Parallel {
    public List<Integer> searchTree(TreeNode node, String mode, double accracy, int coreNum) {
        List<Integer> returnList = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(coreNum);
        System.out.println("***¥À ±”–Core***" + coreNum);
        if (coreNum < 4) {
            experiment ex1 = new experiment(node.left, mode, accracy);
            experiment ex2 = new experiment(node.right, mode, accracy);
            FutureTask<List<Integer>> f1 = new FutureTask<>(ex1);
            FutureTask<List<Integer>> f2 = new FutureTask<>(ex2);
            executor.submit(f1);
            executor.submit(f2);
            try {
                returnList.addAll(f1.get());
                returnList.addAll(f2.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } else if (coreNum < 8) {
            experiment ex1 = new experiment(node.left.left, mode, accracy);
            experiment ex2 = new experiment(node.left.right, mode, accracy);
            experiment ex3 = new experiment(node.right.left, mode, accracy);
            experiment ex4 = new experiment(node.right.right, mode, accracy);
            FutureTask<List<Integer>> f1 = new FutureTask<>(ex1);
            FutureTask<List<Integer>> f2 = new FutureTask<>(ex2);
            FutureTask<List<Integer>> f3 = new FutureTask<>(ex3);
            FutureTask<List<Integer>> f4 = new FutureTask<>(ex4);
            executor.submit(f1);
            executor.submit(f2);
            executor.submit(f3);
            executor.submit(f4);
            try {
                returnList.addAll(f1.get());
                returnList.addAll(f2.get());
                returnList.addAll(f3.get());
                returnList.addAll(f4.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            experiment ex1 = new experiment(node.left.left.left, mode, accracy);
            experiment ex2 = new experiment(node.left.left.right, mode, accracy);
            experiment ex3 = new experiment(node.left.right.left, mode, accracy);
            experiment ex4 = new experiment(node.left.right.right, mode, accracy);
            experiment ex5 = new experiment(node.right.left.left, mode, accracy);
            experiment ex6 = new experiment(node.right.left.right, mode, accracy);
            experiment ex7 = new experiment(node.right.right.left, mode, accracy);
            experiment ex8 = new experiment(node.right.right.right, mode, accracy);
            FutureTask<List<Integer>> f1 = new FutureTask<>(ex1);
            FutureTask<List<Integer>> f2 = new FutureTask<>(ex2);
            FutureTask<List<Integer>> f3 = new FutureTask<>(ex3);
            FutureTask<List<Integer>> f4 = new FutureTask<>(ex4);
            FutureTask<List<Integer>> f5 = new FutureTask<>(ex5);
            FutureTask<List<Integer>> f6 = new FutureTask<>(ex6);
            FutureTask<List<Integer>> f7 = new FutureTask<>(ex7);
            FutureTask<List<Integer>> f8 = new FutureTask<>(ex8);
            executor.submit(f1);
            executor.submit(f2);
            executor.submit(f3);
            executor.submit(f4);
            executor.submit(f5);
            executor.submit(f6);
            executor.submit(f7);
            executor.submit(f8);
            try {
                returnList.addAll(f1.get());
                returnList.addAll(f2.get());
                returnList.addAll(f3.get());
                returnList.addAll(f4.get());
                returnList.addAll(f5.get());
                returnList.addAll(f6.get());
                returnList.addAll(f7.get());
                returnList.addAll(f8.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return returnList;
    }
}
