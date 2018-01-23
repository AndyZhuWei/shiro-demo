package com.andy.shiro.chapter5;

import junit.framework.Assert;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.*;
import org.junit.Test;

/**
 * Created by pc on 2018/1/23.
 */
public class CodecAndCryptoTest {

    @Test
    public void testBase64() {
        String str = "hello";
        String base64Encoded = Base64.encodeToString(str.getBytes());
        String str2 = Base64.decodeToString(base64Encoded);
        Assert.assertEquals(str,str2);
    }

    @Test
    public void testHex() {
        String str = "hello";
        String base64Encoded = Hex.encodeToString(str.getBytes());
        String str2 = new String(Hex.decode(base64Encoded));
        Assert.assertEquals(str,str2);
    }

    @Test
    public void testCodecSupport() {
        String str = "hello";
        byte[] bytes = CodecSupport.toBytes(str,"utf-8");
        String str2 = CodecSupport.toString(bytes,"utf-8");
        Assert.assertEquals(str,str2);
    }

    @Test
    public void testMd5() {
        String str = "hello";
        String salt = "123";
        String md5 = new Md5Hash(str,salt).toString();//还可以转换为toBase64()/toHex()
        System.out.println(md5);
    }

    @Test
    public void testSha1() {
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha1Hash(str,salt).toString();
        System.out.println(sha1);
    }

    @Test
    public void testSha256() {
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha256Hash(str, salt).toString();
        System.out.println(sha1);

    }

    @Test
    public void testSha384() {
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha384Hash(str, salt).toString();
        System.out.println(sha1);

    }

    @Test
    public void testSha512() {
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha512Hash(str, salt).toString();
        System.out.println(sha1);

    }




}
