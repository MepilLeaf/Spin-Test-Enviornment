import java.util.*;

public class Spinner_Test_Enviornment {
    public static void main(String[] args) {
        boolean runCon = true;
        Scanner in = new Scanner(System.in);
        while (runCon == true) {
            runCon = run(in);
        }
    }

    public static boolean run(Scanner in) {
        int[][] data = new int[4][5]; // 0 > amt of each | 1 > spins till first | 2 > average spins for | 3 > prev ind
                                      // common 1 | uncommon 2 | rare 3 | legend 4 | myth 5
        System.out.print("How many spins would you like to perform?\n\t>");
        int spins = in.nextInt();
        for (int i = 0; i < spins; i++) {
            String res = check(roll(1, 2500));
            if (res.equals("common")) {
                processSpinRes(0, i, data);
            } else if (res.equals("uncommon")) {
                processSpinRes(1, i, data);
            } else if (res.equals("rare")) {
                processSpinRes(2, i, data);
            } else if (res.equals("legend")) {
                processSpinRes(3, i, data);
            } else if (res.equals("myth")) {
                processSpinRes(4, i, data);
            }
        }
        System.out.println("Amount spun of each:" + formatDataset(data, 0));
        System.out.println("Spins untill first of each:" + formatDataset(data, 1));
        System.out.println("Average spins until next of each:" + formatDataset(data, 2));
        System.out.print("If you wish to end the program enter end, if not enter anything else here\n\t>");
        if (in.next().toLowerCase().equals("end")) {
            return false;
        } else {
            return true;
        }
    }

    public static String formatDataset(int[][] data, int type) {
        String[] dataString = Arrays.toString(data[type]).split(",");
        for (int i = 0; i < dataString.length; i++) {
            dataString[i] = dataString[i].replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "");
            if (dataString[i].equals("0"))
                dataString[i] = "[none of this rarity were spun]";
        }
        return "\n\tcommon:" + dataString[0] + "\n\tuncommon:" + dataString[1] + "\n\trare:" + dataString[2]
                + "\n\tlegend:" + dataString[3] + "\n\tmyth:" + dataString[4];
    }

    public static int[][] processSpinRes(int type, int ind, int[][] data) {
        data[0][type]++;
        if (data[1][type] == 0)
            data[1][type] = ind;
        if (data[2][type] != 0) {
            data[2][type] = ((ind - data[3][type]) + data[2][type]) / 2;
        } else {
            data[2][type] = ind;
        }
        data[3][type] = ind;
        return data;
    }

    public static int roll(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String check(int num) {
        if (num == 1) {
            return "myth";
        } else if (num == 2 || num == 3) {
            return "legend";
        } else if (num >= 4 && num <= 13) {
            return "rare";
        } else if (num >= 15 && num <= 264) {
            return "uncommon";
        } else {
            return "common";
        }
    }
}

/*
 * Uncommon 1/10 => 250/2500
 * 
 * Rare: 1/250 => 10/2500
 * 
 * Legend: 1/1250 => 2/2500
 * 
 * Myth: 1/2500 => 1/2500
 */