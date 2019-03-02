import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Sum {
    public static void main(String[] args) {
        if (args.length != 0) {
            for (String s : args) {
                try (InputStream is = new FileInputStream(new File(s))) {
                    System.out.println(hashCode(is) + " *" + s);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            try {
                System.out.println(hashCode(System.in) + " *-");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String hashCode(InputStream is) throws IOException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] buf = new byte[1 << 16];
            int a;
            while ((a = is.read(buf)) != -1) {
                md.update(buf, 0, a);
            }
            byte[] digest = md.digest();
            return (bytesToHex(digest).toUpperCase());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("That algorythm doesn't exist");
            return "";
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
