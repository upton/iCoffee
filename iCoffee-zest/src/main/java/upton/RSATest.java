package upton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class RSATest {

    public static void main(String[] args) throws Exception {
        // first need create key pair
        // createKeyPair();
        encryptAndDecrypt();
    }
    
    private static void createKeyPair() throws Exception{
        // 产生钥匙对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        saveKey(publicKey, "public.key");
        saveKey(privateKey, "private.key");
    }

    /**
     * 公钥加密
     * 
     * @throws Exception
     */
    private static void encryptAndDecrypt() throws Exception {
        // 公钥加密
        Cipher cipher = Cipher.getInstance("RSA");
        
        Key publicKey = readKey("public.key");
        
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // 前面做加密时都是用默认的编码，如果加密的数据是中文会出现乱码，现在改成中文的数据进行测试以下
        String source  = "我是要被加密的数据！by upton@zhe800.com";
        System.out.println("source      : " + source);
        byte[] encrypt_results = cipher.doFinal(source.getBytes("UTF-8"));
        
        
        System.out.println("source hex  : " +bytesToHex(source.getBytes("UTF-8")));
        System.out.println("encrypt hex : " + bytesToHex(encrypt_results));

        // 私钥解密
        
        Key privateKey = readKey("private.key");

        Cipher cipher2 = Cipher.getInstance("RSA");
        
        cipher2.init(Cipher.DECRYPT_MODE, privateKey);
        
        // 不要忘记了加编码格式，不然乱码
        byte[] decrypt_results = cipher2.doFinal(encrypt_results);
        System.out.println("result      : " + new String(decrypt_results, "UTF-8"));
    }

    /**
     * 保存密钥的方法
     * 
     * @param key
     * @param keyName
     * @throws Exception
     */
    public static void saveKey(Key key, String keyName) throws Exception {
        FileOutputStream foskey = new FileOutputStream(keyName);
        ObjectOutputStream oos = new ObjectOutputStream(foskey);
        oos.writeObject(key);
        oos.close();
        foskey.close();
    }

    /**
     * 读取密钥的方法
     * 
     * @param keyName
     * @return Key
     * @throws Exception
     */
    public static Key readKey(String keyName) throws Exception {
        FileInputStream fiskey = new FileInputStream(keyName);
        ObjectInputStream oiskey = new ObjectInputStream(fiskey);
        Key key = (Key) oiskey.readObject();
        oiskey.close();
        fiskey.close();
        return key;
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
