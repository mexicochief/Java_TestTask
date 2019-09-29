package Program.Sort;

import Program.File.Upload;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

abstract public class MergeSort {

    private BufferedWriter writer;
    private Upload upload;


    public MergeSort(Upload upload, String nameOut)  {

        FileWriter file = null;
        try
        {
            file = new FileWriter("resources/" + nameOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.writer = new BufferedWriter(file);
            this.upload = upload;

    }

    abstract public void ascendingSorting();
    abstract public void descendingSorting();




    protected Upload getUpload() {
        return upload;
    }



    protected void write(int Element)
    {
        try
        {
            writer.write(Element + "\n");
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    protected void write(String Element)
    {
        try {
            writer.write(Element + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void writerFlush()
    {
        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void writerClose()
    {
        try
        {
            writer.close();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
