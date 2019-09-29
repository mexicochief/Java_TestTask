package Program.File;

import java.io.*;
import java.nio.file.spi.FileTypeDetector;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public  class Upload {

    private List<BufferedReader> forLineCount ;
    private List<BufferedReader> bufferedReaders;


    public Upload()
    {
        this.forLineCount = new ArrayList<>();
        this.bufferedReaders = new ArrayList<>();
    }

    //загружает файлы
    public void uploader(Set<String> path)
    {
        try
        {
            uploadFile(path);
        } catch (FileNotFoundException e) {
            System.out.println("Error: FileNotFound");
            e.printStackTrace();
        }
    }

    public void uploadFile(Set<String> path) throws FileNotFoundException
    {
            for (String a : path)
            {
                String tempPath = "resources\\" + a;
                bufferedReaders.add(new BufferedReader(new FileReader(tempPath)));
                forLineCount.add(new BufferedReader(new FileReader(tempPath)));
            }

    }

    // считает количество файлов
    public int howManyFile()
    {
        return bufferedReaders.size();
    }


    // возвращает строку из файла, аргумент pos отвечает за номер файла
    public String scanStr(int pos)
    {
       try
       {
           return getStr(pos);
       }catch (IOException e)
       {
           e.printStackTrace();
           return null;
       }
    }

    private String getStr(int pos) throws IOException
    {
        BufferedReader bufferedReader = bufferedReaders.get(pos);
        String line = bufferedReader.readLine();


        if(line != null && line.isEmpty())
        {
            return scanStr(pos);
        }
        else if(line != null)
        {
            String result = line;
            result = result.trim();
            return result;
        }
        else
        {
            bufferedReader.close();
            return null;
        }
        // тут обработать ошибку
    }

    // считает количество строк в файле
    public Integer lineCount(int pos)
    {
        try
        {
            return howManyLine(pos);
        }catch (IOException e)
        {
            e.printStackTrace();
            return null;

        }
    }

    private int howManyLine(int pos) throws IOException
    {
        BufferedReader reader = forLineCount.get(pos);
        int countLine = 0;


        while((reader.readLine()) != null)
        {
            countLine++;
        }
        reader.close();

        return countLine;
    }




    // возвращет int из файла, аргумент pos отвечает за номер файла
    public Integer scanInt(int pos)
    {
        try {
            return getInt(pos);
        }catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private Integer getInt(int pos) throws IOException
    {
        BufferedReader bufferedReader = bufferedReaders.get(pos);
        String line;

        if((line = bufferedReader.readLine()) != null)
        {
            line = line.replaceAll("\\s+","");
            Integer result = toInteger(line,pos); // тут ошибка если неверный формат(обработать)
            return result;
        }
        else
        {
            bufferedReader.close();
            return null;
        }
    }

    // меняет тип на int
    private Integer toInteger(String str,int pos)
    {
        try
        {
            return Integer.parseInt(str);
        }catch (NumberFormatException e)
        {
             return scanInt(pos);
        }

    }

    // считает количество строк в котрых есть целые числа
    public Integer lineCountInt(int pos)
    {
        try
        {
            return howManyIntLine(pos);
        }catch (IOException e)
        {
            e.printStackTrace();
            return null;

        }
    }

    private int howManyIntLine(int pos) throws IOException
    {
        BufferedReader reader = forLineCount.get(pos);
        int countLine = 0;
        String line;

        while((line = reader.readLine()) != null)
        {
            if (isInteger(line))
            {
                countLine++;
            }
        }
        reader.close();

        return countLine;
    }

    // проверяет тип
    private boolean isInteger(String str)
    {
        try
        {
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e)
        {
            return false;
        }

    }


    // взвращете bufferedReaders к позиции с которой начиналась запись в буфер
    public void reset(int pos)
    {
        try {
            bufferedReaders.get(pos).reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // выделяет место в буфере
    public void mark(int pos)
    {
        try {
            bufferedReaders.get(pos).mark(25);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
