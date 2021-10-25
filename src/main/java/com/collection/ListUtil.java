package com.collection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 分割集合
 *
 * @author qianjinlong
 * @email 1277977370@qq.com
 * @date 2021/9/8
 */
public class ListUtil {

    /**
     * 步数
     *
     * @param originalListSize 原始列表长度
     * @param subListMaxSize   子列表最大长度
     * @return 新列表长度
     */
    public static int stepCount(int originalListSize, int subListMaxSize) {
        return (originalListSize + subListMaxSize - 1) / subListMaxSize;
    }

    /**
     * 分割 List
     * 例如:
     * List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
     * List<List<Integer>> lists = subList(list, 3);
     * lists = [[1, 2, 3], [4, 5, 6], [7, 8, 9], [10, 11, 12], [13, 14, 15], [16, 17]]
     *
     * @param originalList   原始列表
     * @param subListMaxSize 子集合最大长度
     * @param <T>            原始集合类型
     * @return 分割后的集合
     */
    public static <T> List<List<T>> subList(List<T> originalList, int subListMaxSize) {
        // 步数(新集合长度)
        int stepCount = stepCount(originalList.size(), subListMaxSize);
        // 无限流
        return Stream.iterate(0, i -> i + 1).limit(stepCount)
                .map(o -> originalList.stream().skip((long) o * subListMaxSize).limit(subListMaxSize).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        List<List<Integer>> lists = subList(list, 3);
        // [[1, 2, 3], [4, 5, 6], [7, 8, 9], [10, 11, 12], [13, 14, 15], [16, 17]]
        System.out.println(lists);
    }

}
