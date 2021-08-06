package com.study.spark.rdd;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * Spark RDD function
 *
 * @author boyan
 * @version : SparkApp.java, v 0.1 2021年06月15日 2:42 下午 boyan Exp $
 */
public class WordsCount {
    private static final Pattern SPACE = Pattern.compile(" ");

    public static void main(String[] args) {
        //初始化SparkContext，需要设置两个参数：
        // 集群url：先使用本地模式
        // 应用名：
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("sparkDemo");
        //启用公平调度时，需将spark.shceduler.mode属性设置为FAIR
        sparkConf.set("spark.shceduler.mode","FAIR");
        sparkConf.set("spark.shceduler.mode","pool1");
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

        //创建一个名为lines的RDD
        JavaRDD<String> lines = sparkContext.textFile("/usr/local/data", 4).cache();

        //map将源数据的每个元素传给函数func进行格式化，返回一个新的分布式数据集
        //Map通过函数传递源的每个元素，并形成新的分布式数据集
        lines.map(new Function<String, Object>() {
            @Override
            public Object call(String s) throws Exception {
                return s;
            }
        });

        //flatMap将所有的原始元素传给函数func进行格式化
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                //SPACE.split(s)将每个元素用空格分割，返回iterator
                return Arrays.asList(SPACE.split(s)).iterator();
            }
        });

        //words.mapToPair函数在单词拆分的基础上对每个单词实例计数为1
        JavaPairRDD<String, Integer> wordsOnes = words.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) {
                return new Tuple2<String, Integer>(s, 1);
            }
        });

        //测试 start

        //sortByKey函数维护元素的顺序。它接收键值对(K，V)作为输入，按升序或降序对元素进行排序，并按顺序生成数据集
        JavaPairRDD<String, Integer> sortByKey = wordsOnes.sortByKey();
        System.out.println(sortByKey.collect().toString());

        //groupByKey函数是一种经常使用的转换操作，它执行数据的混乱。
        //它接收键值对(K，V)作为输入，基于键对值进行分组，并生成(K，Iterable)对的数据集作为输出
        JavaPairRDD<String, Iterable<Integer>> groupByKey = wordsOnes.groupByKey();
        System.out.println(groupByKey.collect().toString());

        //测试 end

        //reduceByKey函数把key相同的键值对通过value进行累加
        JavaPairRDD<String, Integer> wordsCounts = wordsOnes.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer value, Integer toValue) throws Exception {
                return value + toValue;
            }
        });

        //将最后的统计结果写到新的文件中
        wordsCounts.saveAsTextFile("/usr/local/data1");
    }
}