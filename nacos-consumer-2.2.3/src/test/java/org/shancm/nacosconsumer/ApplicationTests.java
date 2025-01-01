package org.shancm.nacosconsumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(16);

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");

        List<Integer> numbers = new ArrayList<>(8);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        int sign = 0;

        for (String s : list) {
            System.out.println(s + numbers.get(sign));
            sign++;
            if(3==sign){
                sign=0;
            }
        }
    }

}
