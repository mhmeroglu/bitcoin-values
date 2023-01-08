//-----------------------------------------------------
// Title: Q2
// Author: Mehmet Eroğlu
// ID: 37177526200
// Section: 2
// Assignment: 1
// Description: this class indicates whether the
// given bitcoin values are consecutive.
//-----------------------------------------------------

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String fileName = "src//example-price.txt";

        File file = new File(fileName);

        checkConsecutiveDays(file);
    }

    static void checkConsecutiveDays(File file) throws Exception {
//--------------------------------------------------------
// Summary: In this method, reads txt file
// and printing consecutive days of bitcoin values.
// Precondition: File is for txt file to access.
// Postcondition: Bitcoin values are compared in stack
// and consecutive days values are added to queue and printed.
//--------------------------------------------------------
        BufferedReader br = new BufferedReader(new FileReader(file));
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();

        String line;
        int count = 0;
        //Reads txt file
        while ((line = br.readLine()) != null) {
            String[] reader = line.split(" ");
            //added first bitcoin value in stack and
            // first consecutive days added to queue.
            stack.push(Integer.parseInt(reader[0]));
            queue.add(1);

            for (int i = 1; i < reader.length; i++) {
                //bitcoin values are entered into the stack one by one
                //except for the first day bitcoin value
                // because we added the first day value above
                stack.push(Integer.parseInt(reader[i]));

                //Checking the last value added to the stack with other values
                //and increased the count accordingly if the values are consecutive
                if (stack.peek() <= stack.get(i - 1)) {

                    for (int j = stack.size() - 1; j >= 0; j--) {
                        if (stack.get(i) > stack.get(j)) {
                            break;
                        }
                        if (stack.get(j) >= stack.get(i)) {
                            count++;
                        }
                    }
                    if (count >= 1) {
                        queue.add(count);
                    } else {
                        queue.add(1);
                    }
                    count = 0;

                } else {
                    queue.add(1);
                }
            }
        }
        System.out.println(stack);
        System.out.println(queue);
    }
}
