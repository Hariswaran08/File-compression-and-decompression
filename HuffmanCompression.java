import java.io.*;
import java.util.*;

class HuffmanNode {
    int data;
    char c;
    HuffmanNode left, right;
}

class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.data - y.data;
    }
}

public class HuffmanCompression {
    public static void printCode(HuffmanNode root, String s) {
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println(root.c + ":" + s);
            return;
        }
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    public static void main(String[] args) throws IOException {
        String testString = "This is a test string for Huffman coding";
        char[] charArray = testString.toCharArray();

        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : charArray) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(freqMap.size(), new MyComparator());
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            HuffmanNode hn = new HuffmanNode();
            hn.c = entry.getKey();
            hn.data = entry.getValue();
            hn.left = null;
            hn.right = null;
            pq.add(hn);
        }

        HuffmanNode root = null;
        while (pq.size() > 1) {
            HuffmanNode x = pq.poll();
            HuffmanNode y = pq.poll();
            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.c = '-';
            f.left = x;
            f.right = y;
            root = f;
            pq.add(f);
        }

        printCode(root, "");

        // Compression
        String encodedString = "";
        for (char c : charArray) {
            encodedString += getCode(root, c);
        }
        System.out.println("Encoded String: " + encodedString);

        // Decompression
        StringBuilder decodedString = new StringBuilder();
        HuffmanNode temp = root;
        for (int i = 0; i < encodedString.length(); i++) {
            char code = encodedString.charAt(i);
            if (code == '0') {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
            if (temp.left == null && temp.right == null) {
                decodedString.append(temp.c);
                temp = root;
            }
        }
        System.out.println("Decoded String: " + decodedString);
    }

    public static String getCode(HuffmanNode root, char c) {
        if (root == null) return "";
        if (root.c == c) return "";
        String leftCode = getCode(root.left, c);
        if (leftCode != null) return "0" + leftCode;
        String rightCode = getCode(root.right, c);
        if (rightCode != null) return "1" + rightCode;
        return null;
    }
}
