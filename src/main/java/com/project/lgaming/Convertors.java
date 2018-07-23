package com.project.lgaming;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;

import java.security.spec.X509EncodedKeySpec;

public class Convertors {

    /**
     * Convert xml file to String
     * @param fileName - path oof xml file
     * @return
     */
    static String XMLToString(String fileName)
    {
        try{
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            InputStream inputStream = new FileInputStream(new File(fileName));
            org.w3c.dom.Document doc = documentBuilderFactory.newDocumentBuilder().parse(inputStream);
            StringWriter stw = new StringWriter();
            Transformer serializer = TransformerFactory.newInstance().newTransformer();
            serializer.transform(new DOMSource(doc), new StreamResult(stw));
            return stw.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Cinvert InputStream to String
     * @param is - InputStream
     * @return
     */
    static String IStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    /**
     * Convert .pem file to a PrivateKey
     * @param fileName - .pem file peth
     * @return
     * @throws IOException
     * @throws GeneralSecurityException
     */
    static PrivateKey PemToPrivateKey(String fileName) throws IOException, GeneralSecurityException {
        PrivateKey pk = new PrivateKeyReader(fileName).getPrivateKey();
        return pk;
    }

    static PublicKey PemToPublicKey(String fileName) throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(fileName));

        X509EncodedKeySpec spec =
                new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

}
