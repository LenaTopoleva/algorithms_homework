package lesson5;

import java.util.ArrayList;

// Написать программу «Задача о рюкзаке» с помощью рекурсии.
class Item {
    int weight;
    int cost;

    public Item(int weight, int cost){
        this.weight = weight;
        this.cost = cost;
    }
}

class Knapsack {
    int capacity;

    public Knapsack(int capacity) {this.capacity = capacity;}
}

public class KnapsackProblem  {
    // Список всех возможных комбинаий предметов в рюкзаке:
    private ArrayList<Item> itemsCombinations;

    public int getMaxCost(Knapsack knapsack, ArrayList<Item> items){
        itemsCombinations = new ArrayList<>();
        // Кладем в список пустую комбинацию, чтобы изменить ее при первой итерации:
        Item noItems = new Item(0, 0);
        itemsCombinations.add(noItems);

        // Рекурсивный поиск всех возможных комбинаций предметов:
        getAllCombinations(items, 0);
        return  maxCost(itemsCombinations, knapsack.capacity);
    }

    private void getAllCombinations(ArrayList<Item> items, int currentIndex){
        if(currentIndex == items.size()){
            return;
        }
        // Каждый предмет можно либо положить в рюкзак, либо нет, поэтому для первого предмета
        // оставляем пустую комбинацию и добавляем комбинацию, в которой лежит первый предмет и т.д.
        // Т.к. нас интересует только максимальная общая стоимость и общий вес, создаем из всех
        // предметов комбинации единый предмет, суммирующий значения полей его составялющих
        for (int i = 0; i < itemsCombinations.size(); i += 2) {
            Item currCombination = itemsCombinations.get(i);
            Item newItem = new Item(currCombination.weight + items.get(currentIndex).weight,
                    currCombination.cost + items.get(currentIndex).cost);
            itemsCombinations.add(i + 1, newItem);
        }
        getAllCombinations(items, currentIndex + 1);
    }

    private int maxCost(ArrayList<Item> itemsCombinations, int capacity){
        int maxCost = 0;
        for (int i = 0; i < itemsCombinations.size() ; i++) {
            if(itemsCombinations.get(i).weight <= capacity && itemsCombinations.get(i).cost > maxCost)
                maxCost = itemsCombinations.get(i).cost;
        }
        return maxCost;
    }

    public static void main(String[] args) {
        Knapsack knapsack = new Knapsack(25);
        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item(10,10));
        items.add(new Item(15, 14));
        items.add(new Item(3,3));
        items.add(new Item(16,15));
        items.add(new Item(1,30));

        System.out.println(new KnapsackProblem().getMaxCost(knapsack, items));
    }
}
