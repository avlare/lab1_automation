import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String input = "Abracadabra";
        String filename = "res.txt";
        System.out.println("===Message Digest===");
        task1(input, filename);
        System.out.println();
        System.out.println("===Secure Random===");
        task2(input, filename);
        System.out.println();
        task3();
    }

    public static void task1(String input, String filename) throws NoSuchAlgorithmException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            String[] algsMD = {"MD5", "SHA-1", "SHA-256"};
            for (String alg : algsMD) {
                String hash = messageDigestHash(input, alg);
                System.out.println(alg + ": " + hash);
                writer.write(alg + ": " + hash);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String messageDigestHash(String str, String alg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(alg);
        md.update(str.getBytes());
        byte[] hash = md.digest();
        return bytesToHex(hash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder str = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            str.append(String.format("%02X", b));
        }
        return str.toString();
    }

    public static void task2(String input, String filename) throws NoSuchAlgorithmException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            String[] algsSR = {"SHA1PRNG", "DRBG", "Windows-PRNG"};
            for (String alg : algsSR) {
                String hash = secureRandomHash(input, alg);
                System.out.println(alg + ": " + hash);
                writer.write(alg + ": " + hash);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String secureRandomHash(String str, String alg) throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance(alg);
        sr.setSeed(str.getBytes());
        byte[] hash = new byte[16];
        sr.nextBytes(hash);
        return bytesToHex(hash);
    }

    public static void task3(){
        HashMap<Cat, Integer> allCats = new HashMap<Cat, Integer>();
        System.out.println("Correct equals and hashCode methods");
        System.out.println("Create cats: ");
        Cat first = new Cat("Lucas", 5, "Orange");
        System.out.println(first + ", hashcode: " + first.hashCode());
        Cat second = new Cat("Simon", 2, "Gray");
        System.out.println(second + ", hashcode: " + second.hashCode());
        Cat third = new Cat("Lucas", 5, "Orange");
        System.out.println(third + ", hashcode: " + third.hashCode());
        Cat fourth = new Cat("Lucas", 5, "Red");
        System.out.println(fourth + ", hashcode: " + fourth.hashCode());

        System.out.println("\nAre first and third equal? " + first.equals(third));
        System.out.println("Are first and fourth equal? " + first.equals(fourth));
        allCats.put(first, 1);
        allCats.put(second, 2);
        allCats.put(third, 3);
        allCats.put(fourth, 4);
        System.out.println();

        System.out.println("Cats after added in map: ");
        for (Map.Entry<Cat, Integer> entry : allCats.entrySet()) {
            System.out.println(entry.getKey() + "; Number: " + entry.getValue() + ", It's hash code:" + entry.getKey().hashCode());
        }
        System.out.println();

        HashMap<Dog, Integer> allDogs = new HashMap<Dog, Integer>();
        System.out.println("Incorrect equals and hashCode methods");
        System.out.println("Create dogs: ");
        Dog one = new Dog("Rex", 7, "White");
        System.out.println(one  + ", hashcode: " + one.hashCode());
        Dog two = new Dog("Jack", 2, "Green");
        System.out.println(two  + ", hashcode: " + two.hashCode());
        Dog three = new Dog("Miumiu", 5, "Orange");
        System.out.println(three + ", hashcode: " + three.hashCode());

        System.out.println("\nAre first and third equal? " + one.equals(three));

        allDogs.put(one, 1);
        allDogs.put(two, 2);
        allDogs.put(three, 3);
        System.out.println();

        System.out.println("Dogs after added in map: ");
        for (Map.Entry<Dog, Integer> entry : allDogs.entrySet()) {
            System.out.println(entry.getKey() + "; Number: " + entry.getValue() + ", It's hash code:" + entry.getKey().hashCode());
        }
    }

}
