import java.util.ArrayList;
import java.util.Random;

import static jdk.nashorn.internal.parser.TokenType.IF;

public class monkeyKingSolution {
    public static void main(String[] args) {
        final int size = 100000;

//        double monkeysPower[] = { 0, 20, 16, 10, 10, 4};
//        int monkeysFight[][] = {{ 2, 3 },{ 3, 4 },{ 3, 5 },{ 4, 5 },{ 1, 5 }};

        double monkeysPower[] = new double[size + 1];
        int monkeysFight[][] = new int[size][2];

        Random rand = new Random();
        for(int i = 0; i<monkeysPower.length; i++) {
            monkeysPower[i] = rand.nextInt(1000) + 1;
        }
        for (int i = 0; i<monkeysFight.length; ++i) {
            int x, y;
            do {
                x = rand.nextInt(size) + 1;
                y = rand.nextInt(size) + 1;
            }while (x == y);

            monkeysFight[i][0] = x;
            monkeysFight[i][1] = y;
        }

        long begin = System.currentTimeMillis();

        beginToFight(monkeysPower, monkeysFight);

        long end = System.currentTimeMillis();
        System.out.println("time:" + (end-begin) / 1000.0 + "s");
    }

    private static void beginToFight(double monkeysPower[], int monkeysFight[][]) {
        ArrayList<Node> monkeys = new ArrayList<Node>();
        monkeys.add(null);
        for(int i = 1; i < monkeysPower.length; ++i) {
            monkeys.add(new Node(monkeysPower[i]));
        }
        for (int[] i : monkeysFight) {
            System.out.println(monkeys.get(i[0]).fight(monkeys.get(i[1])));
        }
    }
}
