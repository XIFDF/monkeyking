import java.util.ArrayList;
import java.util.Random;

public class monkeyKingSolution {
    public static void main(String[] args) {
        final int size = 6;

        double monkeysPower[] = {0, 50, 16, 10, 10, 4, 40};
        int monkeysFight[][] = {{2, 6}, {3, 4}, {1, 5}, {6, 5}, {1, 3}, {4, 6}};

//        double monkeysPower[] = new double[size + 1];
//        int monkeysFight[][] = new int[size][2];
//
//        Random rand = new Random();
//        for(int i = 0; i<monkeysPower.length; i++) {
//            monkeysPower[i] = rand.nextInt(1000) + 1;
//        }
//        for (int i = 0; i<monkeysFight.length; ++i) {
//            int x, y;
//            do {
//                x = rand.nextInt(size) + 1;
//                y = rand.nextInt(size) + 1;
//            }while (x == y);
//
//            monkeysFight[i][0] = x;
//            monkeysFight[i][1] = y;
//        }

        long begin = System.currentTimeMillis();

        beginToFight(monkeysPower, monkeysFight);

        long end = System.currentTimeMillis();
        System.out.println("time:" + (end-begin) / 1000.0 + "s");
    }

    private static void beginToFight(double monkeysPower[], int monkeysFight[][]) {
        ArrayList<ShewHeap<Double>> monkeysFrom = new ArrayList<ShewHeap<Double>>();
        for(double i = 0; i < monkeysPower.length + 1; ++i) {
            monkeysFrom.add(null);
        }
        ShewHeap<Double> h1;
        ShewHeap<Double> h2;

        for (int[] i : monkeysFight) {
            if (monkeysFrom.get(i[0]) == null) {
                h1 = new ShewHeap<Double>();
                h1.insert(monkeysPower[i[0]]);
                monkeysFrom.set(i[0], h1);
            }
            else h1 = monkeysFrom.get(i[0]);

            if (monkeysFrom.get(i[1]) == null) {
                h2 = new ShewHeap<Double>();
                h2.insert(monkeysPower[i[1]]);
                monkeysFrom.set(i[1], h1);
            }
            else h2 = monkeysFrom.get(i[1]);

            if (h1.merge(h2) == -1) {
                System.out.println(-1);
            }
            else {
                for (int index = 0; index < monkeysFrom.size(); ++index){
                    if (monkeysFrom.get(index) == h2)
                        monkeysFrom.set(index, h1);
                }

                double print = h1.getMax() / 2;
                System.out.println(print);
                h1.deleteMax();
                h1.insert(print);
            }
        }
    }
}
