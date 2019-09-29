package Program.Sort;

import Program.File.Upload;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MergeSortStr extends MergeSort {
    private Map<Integer,String> currentElmStr = new HashMap<>(); // здесь key - номер файла, value - значение строки


    public MergeSortStr(Upload upload, String nameOut)
    {
        super(upload, nameOut);
    }



    public void ascendingSorting()
    {
        String tempMin;        // текущее минимальное значение
        int pos;               // ключ currentElmStr(номер текущего файла)
        String lastMin;        // пред. минимальное значение


        lastMin = null;

        ascendingFilling();

        // сравниваем элемнты массива м/у собой и записываем в файл меньший
        while (currentElmStr.size() > 0)
        {
            tempMin = findMin().getValue();
            pos = findMin().getKey();

            if(lastMin == null || hasFirstNumLower(toInt(lastMin),toInt(tempMin)))
            {

                write(tempMin); // записываем данные в выходной файл
                lastMin = tempMin;
            }

            String tempElement =  getUpload().scanStr(pos);

            // проверем: не закончился ли файл ?
            if(tempElement == null)
            {
                currentElmStr.remove(pos);
                continue;
            }
            // если не закончился то заносим этот элемент в список
            currentElmStr.put(pos, tempElement);
        }

        writerClose();

    }

    // заполняет currentElmInt первыми строками из файлов
    private void ascendingFilling()
    {
        for (int temp = 0; temp < getUpload().howManyFile(); temp++)
        {
            // Тут скорее всего можно сделать проще!
            int quantity = getUpload().lineCount(temp);
            if(quantity == 1)
            {
                this.currentElmStr.put(temp, getUpload().scanStr(temp));
                continue;
            }
            else if(quantity == 0)
            {
                continue;
            }

            hasBreachInOrderA(temp);
        }

    }

    // Проверяет нарушен ли порядок в первом элементе
    private void hasBreachInOrderA(int fileNumber)
    {
        getUpload().mark(fileNumber);

        if(hasFirstNumLower(toInt(getUpload().scanStr(fileNumber)), toInt(getUpload().scanStr(fileNumber))))
        {
            getUpload().reset(fileNumber);
            this.currentElmStr.put(fileNumber, getUpload().scanStr(fileNumber));
        }
        else
        {
            getUpload().reset(fileNumber);
            getUpload().scanStr(fileNumber);
            this.currentElmStr.put(fileNumber, getUpload().scanStr(fileNumber));
        }
        // тут исключить случай, где ошибка в первом элементе


    }

    private Map.Entry<Integer,String> findMin()
    {
        Map.Entry<Integer,String> minElement = null;
        String tempMin = null;

        for (Map.Entry<Integer,String> entry : currentElmStr.entrySet())
        {
            if (tempMin == null || hasFirstNumLower(toInt(entry.getValue()),toInt(tempMin)))
            {
                tempMin = entry.getValue();
                minElement = entry;
            }
        }
        return minElement;
    }



    public void descendingSorting()
    {
        String tempMax;        // для хранения минимального значения
        int pos;
        String lastMax;         // хранит прошлое минимальное значение


        lastMax = null;

        descendingFilling();

        // сравниваем элемнты массива м/у собой и записываем в файл меньший
        while (currentElmStr.size() > 0)
        {
            tempMax = findMax().getValue();
            pos = findMax().getKey();

            if(lastMax == null || hasFirstNumLower(toInt(tempMax),toInt(lastMax)))
            {

                write(tempMax); // записываем данные в выходной файл
                lastMax = tempMax;
            }

            String tempElement =  getUpload().scanStr(pos); // оформить получше

            // проверем: не закончился ли файл ?
            if(tempElement == null)
            {
                currentElmStr.remove(pos);
                continue;
            }
            // если не закончился то заносим этот элемент в список
            currentElmStr.put(pos, tempElement);
        }

        writerClose();

    }

    private void descendingFilling()
    {
        for (int temp = 0; temp < getUpload().howManyFile(); temp++)
        {
            // Тут скорее всего можно сделать проще!
            int quantity = getUpload().lineCount(temp);
            if(quantity == 1)
            {
                this.currentElmStr.put(temp, getUpload().scanStr(temp));
                continue;
            }
            else if(quantity == 0)
            {
                continue;
            }

            findBreachInOrderD(temp);
        }

    }

    private void findBreachInOrderD(int fileNumber)
    {
        getUpload().mark(fileNumber);

        if(!hasFirstNumLower(toInt(getUpload().scanStr(fileNumber)), toInt(getUpload().scanStr(fileNumber))))
        {
            getUpload().reset(fileNumber);
            this.currentElmStr.put(fileNumber, getUpload().scanStr(fileNumber));
        }
        else
        {
            getUpload().reset(fileNumber);
            getUpload().scanStr(fileNumber);
            this.currentElmStr.put(fileNumber, getUpload().scanStr(fileNumber));
        }

    }

    private Map.Entry<Integer,String> findMax()
    {
        Map.Entry<Integer,String> MaxElement = null;
        String tempMax = null;

        for (Map.Entry<Integer,String> entry : currentElmStr.entrySet())
        {
            if (tempMax == null || hasFirstNumLower(toInt(tempMax),toInt(entry.getValue())))
            {
                tempMax = entry.getValue();
                MaxElement = entry;
            }
        }
        return MaxElement;
    }



    // преобразует строку в набор чисел (в ascii представлении)
    private ArrayList<Integer> toInt(String str)
    {
        str = str.toLowerCase();                         // Сортировка без учета регистра(сделать нормально)
        ArrayList<Integer> result = new ArrayList<>();
        char[] temp = str.toCharArray();



        if (str == null)
        {
            return null;
        }


        for(char tempChar:temp)
        {
            result.add(Integer.valueOf(Character.valueOf(tempChar)));
        }
        return result;
    }

    // Сравнивает два набора
    private boolean hasFirstNumLower(ArrayList<Integer> first, ArrayList<Integer> second)
    {
        int count = Math.min(first.size(),second.size());
        int temp = 0;



        while(temp < count)
        {
            if(first.get(temp) < second.get(temp))
            {
                return true;
            }
            else if(first.get(temp) > second.get(temp))
            {
                return false;
            }
            else
            {
                temp++;
            }
        }
        return true;



    }


}
