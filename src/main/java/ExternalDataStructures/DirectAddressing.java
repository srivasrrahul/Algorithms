package ExternalDataStructures;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by rasrivastava on 2/9/16.
 */

public class DirectAddressing {
    String createStream(String key,String value) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(key.length());
        stringBuilder.append(key);
        stringBuilder.append(value.length());
        stringBuilder.append(value);
        return stringBuilder.toString();

    }


    long addKeyValueToMainFile(String key,String value) {
        FileOutputStream out = null;
        long baseOffSet = -1;
        try {
            out = new FileOutputStream(fileName,true);
            out.write(createStream(key,value).getBytes());
            baseOffSet = out.getChannel().position();
            System.out.println(out.getChannel().position());
            out.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return baseOffSet;
    }

    void addKeyValue(String key,String value) {
        long offset = addKeyValueToMainFile(key,value);

    }
    public DirectAddressing(String fileName) {
        this.fileName = fileName;
        this.indexFileName = fileName + ".index";
    }



    private String fileName;
    private String indexFileName;

    public static void main(String[] args) {
        DirectAddressing directAddressing = new DirectAddressing("test.txt");
        directAddressing.addKeyValue("hi","123");
    }
}
