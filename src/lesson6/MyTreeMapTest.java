package lesson6;

import java.util.Random;

// 1. Создать и запустить программу для построения двоичного дерева.
// В цикле построить 100.000 деревьев с глубиной в 6 уровней. Данные, которыми необходимо заполнить узлы деревьев,
// представляются в виде чисел типа int.
// Число, которое попадает в узел, должно генерироваться случайным образом в диапазоне от -100 до 100.
// 2. Проанализировать, какой процент созданных деревьев являются несбалансированными.

public class MyTreeMapTest {

    public static MyTreeMap<Integer, Integer> createTreeMapWithHeight(int height){
        Random rnd = new Random();
        MyTreeMap<Integer, Integer> treeMap = new MyTreeMap<>();

        while(treeMap.height() <= height){
            Integer nextKey = rnd.nextInt(200) - 100;
            Integer nextValue = rnd.nextInt(10);
            treeMap.put(nextKey, nextValue);
        }
        treeMap.trimLastTreeLevel();
        return treeMap;
    }

    public static void main(String[] args) {
        int balancedTrees = 0;
        int notBalancedTrees = 0;

        for (int i = 0; i < 1000_000; i++) {
            MyTreeMap<Integer, Integer> newTreeMap = createTreeMapWithHeight(6);
            if(newTreeMap.isBalanced()) balancedTrees++;
            else notBalancedTrees++;
        }
        System.out.println("Отношение сбалансированных деревьев к несбалансированным = "
                + ((double) balancedTrees / notBalancedTrees)
                + ". Сбалансированных: " + balancedTrees
                + ", несбалансированных: " + notBalancedTrees);
    }
}
