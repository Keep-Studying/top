package com.study.spark.rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Spark RDD function
 *
 * @author boyan
 * @version : SparkApp.java, v 0.1 2021年06月15日 2:42 下午 boyan Exp $
 */
public class SparkApp {

    /**
     * Map通过函数传递源的每个元素，并形成新的分布式数据集
     */
    @Test
    public void map() {
        //初始化SparkContext，需要设置两个参数：
        // 集群url：先使用本地模式
        // 应用名：
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("sparkDemo");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<Integer> parallelize = sparkContext.parallelize(Arrays.asList(1, 2, 3));
        //打印 parallelize 的collect值
        System.out.println(parallelize.collect().toString());

        JavaRDD<Object> map = parallelize.map(new Function<Integer, Object>() {
            @Override
            public Object call(Integer v1) throws Exception {
                return v1 + 10;
            }
        });
        System.out.println(map.collect().toString());
    }

    /**
     * Filter函数返回一个新数据集，该数据集是通过选择函数返回true的源元素而形成的。 因此，它仅检索满足给定条件的元素
     */
    @Test
    public void filter() {
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("sparkDemo");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<Integer> parallelize = sparkContext.parallelize(Arrays.asList(1, 2, 3));
        //打印 parallelize 的collect值
        System.out.println(parallelize.collect().toString());

        JavaRDD<Integer> filter = parallelize.filter(new Function<Integer, Boolean>() {
            @Override
            public Boolean call(Integer v1) throws Exception {
                return v1 != 2;
            }
        });
        System.out.println(filter.collect().toString());
    }

    /**
     * count函数返回数据集中存在的元素数
     */
    @Test
    public void count() {
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("sparkDemo");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<Integer> parallelize = sparkContext.parallelize(Arrays.asList(1, 2, 3));
        //打印 parallelize 的collect值
        System.out.println(parallelize.collect().toString());

        long count = parallelize.count();
        System.out.println(count);
    }

    /**
     * Distinct函数返回提供的数据集中的不同元素
     */
    @Test
    public void distinct() {
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("sparkDemo");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<Integer> parallelize = sparkContext.parallelize(Arrays.asList(1, 2, 3, 2, 4));
        //打印 parallelize 的collect值
        System.out.println(parallelize.collect().toString());

        JavaRDD<Integer> distinct = parallelize.distinct();
        System.out.println(distinct.collect().toString());
    }

    /**
     * Union函数返回一个新数据集，其中包含不同数据集中存在的元素组合
     */
    @Test
    public void union() {
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("sparkDemo");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<Integer> parallelize1 = sparkContext.parallelize(Arrays.asList(1, 2, 3));
        //打印 parallelize1 的collect值
        System.out.println(parallelize1.collect().toString());

        JavaRDD<Integer> parallelize2 = sparkContext.parallelize(Arrays.asList(4, 5, 6));
        //打印 parallelize2 的collect值
        System.out.println(parallelize2.collect().toString());

        JavaRDD<Integer> parallelize = parallelize1.union(parallelize2);
        System.out.println(parallelize.collect().toString());
    }

    /**
     * Intersection函数返回一个新数据集，其中包含不同数据集中存在的元素的交集。 因此，它只返回一行。此函数的行为与SQL中的INTERSECT查询类似
     */
    @Test
    public void intersection() {
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("sparkDemo");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<Integer> parallelize1 = sparkContext.parallelize(Arrays.asList(1, 2, 3));
        //打印 parallelize1 的collect值
        System.out.println(parallelize1.collect().toString());

        JavaRDD<Integer> parallelize2 = sparkContext.parallelize(Arrays.asList(2, 3, 4, 5, 6));
        //打印 parallelize2 的collect值
        System.out.println(parallelize2.collect().toString());

        JavaRDD<Integer> parallelize = parallelize1.intersection(parallelize2);
        System.out.println(parallelize.collect().toString());
    }

    /**
     * Cartesian函数生成两个数据集的笛卡尔积，并返回所有可能的对组合。 这里，一个数据集的每个元素与另一个数据集的每个元素配对
     */
    @Test
    public void cartesian() {
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("sparkDemo");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<Integer> parallelize1 = sparkContext.parallelize(Arrays.asList(1, 2, 3));
        //打印 parallelize1 的collect值
        System.out.println(parallelize1.collect().toString());

        JavaRDD<Integer> parallelize2 = sparkContext.parallelize(Arrays.asList(4, 5, 6));
        //打印 parallelize2 的collect值
        System.out.println(parallelize2.collect().toString());

        //JavaRDD<Integer> parallelize = parallelize1.cartesian();
        //System.out.println(parallelize.collect().toString());
    }

    /**
     * First函数始终返回数据集的第一个元素。它类似于take(1)
     */
    @Test
    public void first() {
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("sparkDemo");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<Integer> parallelize = sparkContext.parallelize(Arrays.asList(1, 2, 3, 2, 4));
        //打印 parallelize 的collect值
        System.out.println(parallelize.collect().toString());

        Integer first = parallelize.first();
        System.out.println(first);
    }

    /**
     * take函数的行为类似于数组。它接收一个整数值(比方说，n)作为参数，并返回数据集的前n个元素
     */
    @Test
    public void take() {
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("sparkDemo");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<Integer> parallelize = sparkContext.parallelize(Arrays.asList(1, 2, 3, 2, 4));
        //打印 parallelize 的collect值
        System.out.println(parallelize.collect().toString());

        List<Integer> take = parallelize.take(3);
        System.out.println(take.toString());
    }
}