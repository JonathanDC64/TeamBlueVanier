import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.codec.binary.Base64;

public class CipherUtils
{

    private static byte[] key = {
            0x74, 0x68, 0x69, 0x73, 0x49, 0x73, 0x41, 0x53, 0x65, 0x63, 0x72, 0x65, 0x74, 0x4b, 0x65, 0x79
    };//"thisIsASecretKey";

    public static String encrypt(String strToEncrypt)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            final String encryptedString = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes()));
            return encryptedString;
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
        return null;

    }

    public static String decrypt(String strToDecrypt)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            final String decryptedString = new String(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
            return decryptedString;
        }
        catch (Exception e)
        {
          e.printStackTrace();

        }
        return null;
    }


    public static void main(String args[])
    {

        CommandLineParser parser = new PosixParser();
        Options options = new Options();
        Option help = new Option("help", "Display help");
        Option encrypt = new Option("encrypt", true, " - string to encrypt");
        Option decrypt = new Option("decrypt", true, " - string to decrypt");
        options.addOption(help);
        options.addOption(encrypt);
        options.addOption(decrypt);
        try
        {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("encrypt"))
            {
                final String strToEncrypt = cmd.getOptionValue("encrypt");
                final String encryptedStr = CipherUtils.encrypt(strToEncrypt.trim());
                System.out.println("String to Encrypt : " + strToEncrypt);
                System.out.println("Encrypted : " + encryptedStr);
            }
            else if (cmd.hasOption("decrypt"))
            {
                final String strToDecrypt = cmd.getOptionValue("decrypt");
                final String decryptedStr = CipherUtils.decrypt(strToDecrypt.trim());
                System.out.println("String To Decrypt : " + strToDecrypt);
                System.out.println("Decrypted : " + decryptedStr);
            }
            else
            {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("[-h] [-encrypt ]", options);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}