package PathUtils;

import java.util.*;

public class Decipher {
    public int decipher(int val) {
        return 0;
    }

    public int encrypt(int val) {
        String oldBin = Integer.toBinaryString(val);

        StringBuilder sb = new StringBuilder();
        for (int i = oldBin.length() - 1; i >= 0; i--) {
            sb.append(oldBin.charAt(i));
        }

        List<String> blocks = new ArrayList<>();
        for (int i = 0; i < sb.length(); i += 3) {
            blocks.add(sb.substring(i, Math.min(sb.length(), i + 3)));
        }

        System.out.println(blocks);

        // for(int i = oldBin.length(); i > 0; i -= 3) {
        //     blocks.add(oldBin.substring(Math.max(0, i - 3), i));
        // }

        List<String> newBlocks = new ArrayList<>();
        for (String block : blocks) {
            int blockValue = Integer.parseInt(block, 2);
            newBlocks.add(String.format("%3s", Integer.toBinaryString(blockValue - 1)).replace(' ', '0'));
        }

        System.out.println(newBlocks);

        String newBin = String.join("", newBlocks);
        return Integer.parseInt(newBin, 2);
    }

    public static void main(String[] args) {
        Decipher d = new Decipher();
        // for(int i = 0; i < 10; i++) {
        //     for(int j = 0; j < 10; j++) {
        //         int value = 10202 + i * 1000 + j * 10;
        //         System.out.println(d.encrypt(value));
        //         System.out.println("value = " + value + " equals 17355 = " + (d.encrypt(value) == 17355));
        //     }
        // }
        System.out.println(d.encrypt(17355));
        
        
    }
}
