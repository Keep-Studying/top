/**
 * Study.com Inc. Copyright (c) 2019-2020 All Rights Reserved.
 */
package com.study.algorithm.graph;

/**
 * 图（Graph）是一种非线性数据结构
 *
 * 度（Degree）：一个顶点的度是指与该顶点相关联的边的条数，顶点v的度记作d(v)。
 *
 * 二元组的定义
 * 图G是一个有序二元组(V,E)，其中V称为顶集(Vertices Set)，E称为
 * 边集(Edges set)，E与V不相交。它们亦可写成V(G)和E(G)。E的元素都是
 * 二元组，用(x,y)表示，其中x,y∈V
 *
 * 如果给图的每条边规定一个方向，那么得到的图称为有向图。在有向图中，与
 * 一个节点相关联的边有出边和入边之分。相反，边没有方向的图称为无向图。
 *
 * 存储:
 * 1.邻接矩阵：数组：浪费空间，但是速度块。数据不大 优先选用数组
 * 2.邻接表：链表：节省空间，但是速度慢
 *
 * @author study
 * @version : Graph.java, v 0.1 2020年07月13日 22:25 study Exp $
 */
public interface Graph {
}