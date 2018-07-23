package com.project.lgaming;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

public class Encrypting {
    /**
     * Подписывает строку
     * @param message - Строка для подписи
     * @return подпись в Base64

     */
    public String sign(String message) throws SignatureException{
        try {
            Signature sign = Signature.getInstance("SHA1withRSA");
            PrivateKey pk = Convertors.PemToPrivateKey("D:\\OwnProjects\\Vacancy\\lgaming task\\src\\main\\resources\\private.pem");
            sign.initSign(pk);
            sign.update(message.getBytes("UTF-8"));
//            return new String(Base64.encodeBase64(sign.sign()),"UTF-8"); encodeBase64() doesn't exist
            return new String(Base64.getEncoder().encode(sign.sign()),"UTF-8");
        } catch (Exception ex) {
            throw new SignatureException(ex);
        }
    }

    /**
     * Проверяет подпись
     * @param message строка для проверки
     * @param signature подись в Base64
     * @return true если подпись верна
     * @throws java.security.SignatureException

     */
    public boolean verify(String message, String signature) throws SignatureException{
        try {
            Signature sign = Signature.getInstance("SHA1withRSA");
            PublicKey pk = Convertors.PemToPublicKey("D:\\OwnProjects\\Vacancy\\lgaming task\\src\\main\\resources\\public.pem");
            sign.initVerify(pk);
            sign.update(message.getBytes("UTF-8"));
//            return sign.verify(Base64.decodeBase64(signature.getBytes("UTF-8"))); decodeBase64() doesn't exist
            return sign.verify(Base64.getDecoder().decode(signature.getBytes("UTF-8")));
        } catch (Exception ex) {
            throw new SignatureException(ex);
        }
    }


}
