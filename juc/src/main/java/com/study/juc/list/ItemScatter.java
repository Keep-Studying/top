/**
 * Study.com Inc. Copyright (c) 2019-2021 All Rights Reserved.
 */
package com.study.juc.list;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 实现一个对商品列表按商家抽取的算法
 * 1、要求
 * (1)同一轮的抽取结果要遵循原始排序
 * (2)不足抽取个数的商家抽完为止
 * (3)抽取后剩下的商品列表保持原始顺序跟随在抽取列表后面
 * (4)itemList.size()<=5000，round<=10，roundCount<=5
 *
 * @author study
 * @version : SelectSample.java, v 0.1 2021年04月22日 23:04 study Exp $
 */
public class ItemScatter {

    /**
     * 对商品列表按商家进行抽取，若无法抽取原样返回
     *
     * @param itemList      原始商品列表
     * @param round         抽取轮数
     * @param roundPerCount 每轮抽取商品数
     * @return 抽取之后的商品列表
     */
    public static List<ItemDO> scatter(List<ItemDO> itemList, int round, int roundPerCount) {
        // TODO
        //1.先对这批itemList里面的元素按照商家进行拆分，找到商家及对应商品的位置
        //用来区分商家
        //Set<long> set =  new HashSet<>();

        //用map<long,List>来保存商家及商品在itemList中的位置
        Map<Long,List<Long>> map =new HashMap<>();
        for(int i = 0;i < itemList.size();i++){
            //不包含，说明该商家集合还没有生成
            if(!map.containsKey(itemList.get(i).getUserId())){
                //将商家id放到set中
                //set.put(itemList.get(i).getUserId());

                //生成list，并放入第一个商品的位置
                List<Long> list= new ArrayList();
                list.add(new Long(i));
                map.put(itemList.get(i).getUserId(),list);
            }else{
                //直接从map中获取商家对应的集合，往后面添加即可
                map.get(itemList.get(i).getUserId()).add(new Long(i));
            }
        }

        //2.然后按照商家个数，抽取轮数及每轮抽取的商品数，
        //从第一步拿到的拆分结果中,按照商家去拆分好的集合中取roundPerCount个(不够时抽完为止)，
        //一共抽取round轮，选出来的元素按照顺序插入到一个新的集合中
        List<ItemDO> result = new ArrayList<>();
        //抽取round轮
        for(int j=0;j < round;j++){
            //Entry<long,List> entrys = map.keySet();

            //对商家集合进行遍历
            for(long userId:map.keySet()){
                //对value进行判空
                if(map.get(userId) != null){
                    List<Long> list = map.get(userId);
                    //每个商家抽取roundPerCount个，如果不够，则取完
                    for(int k = 0;k<roundPerCount && k<list.size();k++){
                        //如果已经放过了，就将list的对应位置设置一个标识，防止重复放
                        if(list.get(k) != -1){
                            result.add(itemList.get(new Long(list.get(k)).intValue()));
                            //将已经放过的位置设置为-1
                            list.set(k,new Long(-1));
                        }

                    }
                    //list中元素是否全部都为-1，是的话，说明该集合中都已经被取走了，
                    boolean flag = true;
                    for(int h= 0;h<list.size();h++){
                        if(list.get(h) != -1){
                            flag = false;
                        }
                    }
                    //如果为true，则将该商家的集合设置为null，防止无效遍历
                    if(flag){
                        map.put(userId,null);
                    }
                }
            }
        }

        result.sort((k1,k2)->{
            return itemList.indexOf(k1) - itemList.indexOf(k2);
        });

        for (ItemDO item:itemList) {
            if(!result.contains(item)){
                result.add(item);
            }
        }
        return result;

    }


    @Test
    public void test(){
        ArrayList<ItemDO> itemDOS = new ArrayList<>();
        ItemDO itemDO = new ItemDO();
        itemDO.setItemId(2);
        itemDO.setUserId(2);
        ItemDO itemDO1 = new ItemDO();
        itemDO1.setItemId(1);
        itemDO1.setUserId(2);
        itemDOS.add(itemDO);
    }

    @Data
    class ItemDO {
        private long itemId;
        private long userId;
    }
}