package Program.Sort;

import Program.File.Upload;



import java.util.HashMap;
import java.util.Map;

public class MergeSortNum extends MergeSort
{
    private Map<Integer,Integer> currentElmInt = new HashMap<>(); // здесь key - номер файла, value - значение строки


    public MergeSortNum(Upload upload,String nameOut)
    {
            super(upload,nameOut);
    }



    public void ascendingSorting()
    {
        Integer tempMin;
        int pos;
        Integer tempElement;
        Integer lastMin = null;

        ascendingFilling();

        while (currentElmInt.size() > 0)
        {
            tempMin = findMin().getValue();
            pos = findMin().getKey();

            if(lastMin == null || lastMin <= tempMin)   // условие исключающее ошибку в сортировке исходных файлов
                                                        // *кроме случая, когда порядок нарушен в первом элементе
            {
                write(tempMin);
                lastMin = tempMin;
            }

            tempElement =  getUpload().scanInt(pos);

            if(tempElement == null)
            {
                currentElmInt.remove(pos);
                continue;
            }

            currentElmInt.put(pos, tempElement);
            writerFlush();
        }

        writerClose();

    }

    // заполняет currentElmInt первыми строками из файлов
    private void ascendingFilling()
    {
        for (int temp = 0; temp < getUpload().howManyFile(); temp++)
        {
            int quantity = getUpload().lineCountInt(temp);

            if(quantity == 1)
            {
                this.currentElmInt.put(temp, getUpload().scanInt(temp));
                continue;
            }
            else if(quantity == 0)
            {
                continue;
            }

            findBreachInOrderA(temp);
        }

    }

    // Проверяет нарушен ли порядок в первом элементе
    private void findBreachInOrderA(int fileNumber)
    {
        getUpload().mark(fileNumber);

        if(getUpload().scanInt(fileNumber) <= getUpload().scanInt(fileNumber))
        {
            getUpload().reset(fileNumber);
            this.currentElmInt.put(fileNumber, getUpload().scanInt(fileNumber));
        }
        else
        {
            getUpload().reset(fileNumber);
            getUpload().scanInt(fileNumber);
            this.currentElmInt.put(fileNumber, getUpload().scanInt(fileNumber));
        }

    }

    // находит мин. значение в currentElmInt
    private Map.Entry<Integer,Integer> findMin() // находит мин. значение в currentElmInt
    {
        Map.Entry<Integer,Integer> minElement = null;
        Integer tempMin = null;

        for (Map.Entry<Integer,Integer> entry : currentElmInt.entrySet())
        {
            if (tempMin == null || tempMin >= entry.getValue())
            {
                tempMin = entry.getValue();
                minElement = entry;
            }
        }
        return minElement;
    }



    public void descendingSorting()
    {
        Integer tempMin;
        int pos;
        Integer tempElement;
        Integer lastMin = null;

        decreaseFilling();

        while (currentElmInt.size() > 0)
        {
            tempMin = findMax().getValue();
            pos = findMax().getKey();

            if(lastMin == null || lastMin >= tempMin)   // условие исключающее ошибку в сортировке исходных файлов
            // *кроме случая, когда порядок нарушен в первом элементе
            {
                write(tempMin);
                lastMin = tempMin;
            }

            tempElement =  getUpload().scanInt(pos);

            if(tempElement == null)
            {
                currentElmInt.remove(pos);
                continue;
            }

            currentElmInt.put(pos, tempElement);
        }

        writerClose();

    }

    private void decreaseFilling()
    {
        for (int temp = 0; temp < getUpload().howManyFile(); temp++)
        {
            int quantity = getUpload().lineCountInt(temp);
            if(quantity == 1)
            {
                this.currentElmInt.put(temp, getUpload().scanInt(temp));
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

        if(getUpload().scanInt(fileNumber) >= getUpload().scanInt(fileNumber))
        {
            getUpload().reset(fileNumber);
            this.currentElmInt.put(fileNumber, getUpload().scanInt(fileNumber));
        }
        else
        {
            getUpload().reset(fileNumber);
            getUpload().scanInt(fileNumber);
            this.currentElmInt.put(fileNumber, getUpload().scanInt(fileNumber));
        }
    }

    private Map.Entry<Integer,Integer> findMax()
    {
        Map.Entry<Integer,Integer> maxElement = null;
        Integer tempMax = null;

        for (Map.Entry<Integer,Integer> entry : currentElmInt.entrySet())
        {
            if (tempMax == null || tempMax <= entry.getValue())
            {
                tempMax = entry.getValue();
                maxElement = entry;
            }
        }
        return maxElement;
    }

}
