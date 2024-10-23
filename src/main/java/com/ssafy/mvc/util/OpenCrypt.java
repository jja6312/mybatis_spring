package com.ssafy.mvc.util;

import com.ssafy.mvc.model.dto.member.Member;
import com.ssafy.mvc.model.service.MemberService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

//@Aspect
//@Component
public class OpenCrypt {

    public OpenCrypt() {}

//    @Pointcut("execution(* com.ssafy.mvc.model.service.MemberServiceImpl.createMember(..))")
    public void encryptPointCut(){
    }

//    @Pointcut("execution(* com.ssafy.mvc.model.service.MemberServiceImpl.login(..))")
    public void decryptPointCut(){
    }

//    @Around("encryptPointCut()")
//    public void encryptPw(ProceedingJoinPoint pjt){
//        System.out.println("aop의 encryptPw호출됨");
//        Object[] args = pjt.getArgs();
//        Member member = (Member) args[0];
//        String password = member.getPassword();
//        String salt = UUID.randomUUID().toString();
//
//        byte[] hashPw = getSHA256(password, salt);// 암호화
//        String hashPw2 = byteArrayToHex(hashPw);// 암호화2 ?
//        member.setEncryptedPassword(hashPw2);
//
//        try {
////            pjt.proceed();
//            // AOP에서 salt 값을 함께 전달하여 createMember 호출
//            ((MemberService) pjt.getTarget()).createMemberWithSalt(member, salt);
//        }catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//
//    }



    public static byte[] getSHA256(String source, String salt) {
        byte byteData[] = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(source.getBytes());
            md.update(salt.getBytes());
            byteData = md.digest();
            System.out.println("원문: " + source + "   SHA-256: " + byteData.length + "," + byteArrayToHex(byteData));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return byteData;
    }

    //    @Around("myPointCut2()")
    public static byte[] generateKey(String algorithm, int keySize) throws NoSuchAlgorithmException {

        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(keySize);
        SecretKey key = keyGenerator.generateKey();
        return key.getEncoded();
    }

    public static String aesEncrypt(String msg, byte[] key) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        String iv = "AAAAAAAAAAAAAAAA";
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(iv.getBytes()));
        byte[] encrypted = cipher.doFinal(msg.getBytes());
        return byteArrayToHex(encrypted);

    }

    public static String aesDecrypt(String msg, byte[] key) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        String iv = "AAAAAAAAAAAAAAAA";
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(iv.getBytes()));
        byte[] encrypted = hexToByteArray(msg);
        byte[] original = cipher.doFinal(encrypted);
        return new String(original);
    }

    public static byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() == 0) {
            return null;
        }

        byte[] ba = new byte[hex.length() / 2];
        for (int i = 0; i < ba.length; i++) {
            ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return ba;
    }

    // byte[] to hex
    public static String byteArrayToHex(byte[] ba) {
        if (ba == null || ba.length == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer(ba.length * 2);
        String hexNumber;
        for (int x = 0; x < ba.length; x++) {
            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);

            sb.append(hexNumber.substring(hexNumber.length() - 2));
        }
        return sb.toString();
    }


}
