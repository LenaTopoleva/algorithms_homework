package lesson8;

import java.util.Random;

public class TestChainingHashMap {
    public static void main(String[] args) {
        Random random = new Random();

        ChainingHashMap<Integer, String> map = new ChainingHashMap<>(5);
        for (int i = 0; i < 7 ; i++) {
            map.put(random.nextInt(100), "a");
        }

        map.put(333, "value 333");
        map.put(111, "value 111");
        System.out.println(map);

        System.out.println("Remove element with index: 333; removed value: " + map.remove(333));

        System.out.println(map);

    }
}
