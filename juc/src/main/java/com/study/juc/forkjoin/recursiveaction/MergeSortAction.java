/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.juc.forkjoin.recursiveaction;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

/**
 * @author study
 * @version : MergeSortAction.java, v 0.1 2020年09月05日 15:20 study Exp $
 */
public class MergeSortAction extends RecursiveAction {

    private static final Logger LOGGER = Logger.getLogger(MergeSortAction.class.getName());
    private final            int    threshold;
    private                  int[]  arrayToSort;

    public MergeSortAction(final int[] arrayToSort, final int threshold) {
        this.arrayToSort = arrayToSort;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        if(arrayToSort.length <= threshold){
            // sequential sort
            Arrays.sort(arrayToSort);
            return;
        }

        // Sort halves in parallel
        int midpoint = arrayToSort.length / 2;
        int[] leftArray = Arrays.copyOfRange(arrayToSort, 0, midpoint);
        int[] rightArray = Arrays.copyOfRange(arrayToSort, midpoint, arrayToSort.length);

        MergeSortAction left = new MergeSortAction(leftArray, threshold);
        MergeSortAction right = new MergeSortAction(rightArray, threshold);

        //invokeAll(left, right);
        left.fork();
        right.fork();

        left.join();
        right.join();

        // 排序的数组进行归并
        arrayToSort = MergeSortMain.merge(left.getSortedArray(), right.getSortedArray());
    }

    public int[] getSortedArray() {
        return arrayToSort;
    }
}