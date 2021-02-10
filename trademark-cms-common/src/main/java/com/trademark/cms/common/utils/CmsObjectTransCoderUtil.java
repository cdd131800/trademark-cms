package com.trademark.cms.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class CmsObjectTransCoderUtil {

    public static byte[] serialize(Object value) {
        if (null == value) {
            return new byte[0];
        }
        byte[] rv = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(value);
            os.close();
            bos.close();
            rv = bos.toByteArray();
        } catch (IOException e) {
            log.error("CmsObjectTransCoderUtil :: serialize error :", e);
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
                if (null != bos) {
                    bos.close();
                }
            } catch (Exception e) {
                log.error("CmsObjectTransCoderUtil :: serialize error :", e);
            }
        }
        return rv;
    }

    public static Object deserialize(byte[] in) {
        Object rv = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null ;
        try{
            if(null != in){
                bis=new ByteArrayInputStream(in);
                is = new ObjectInputStream(bis);
                rv = is.readObject();
                is.close();
                bis.close();
            }
        }catch (Exception e){
            log.error("CmsObjectTransCoderUtil :: deserialize error :", e);
        }finally {
            try{
                if (null != is) {
                    is.close();
                }
                if (null != bis) {
                    bis.close();
                }
            }catch (Exception e){
                log.error("CmsObjectTransCoderUtil :: deserialize error :", e);
            }
        }

        return rv;
    }
}
