package Program;

import Program.File.Upload;
import Program.Parameters.ParameterAnalysis;
import Program.Sort.MergeSort;
import Program.Sort.MergeSortNum;
import Program.Sort.MergeSortStr;

import java.util.Set;


public class SortingDemo {
    public static void main(String args[])
    {
        MergeSort mergeSort;
        String nameOutFile;
        Set<String> nameInFile;

        Upload upload = new Upload();
        ParameterAnalysis parameterAnalysis = new ParameterAnalysis(args);

        nameInFile = parameterAnalysis.nameInFile();
        nameOutFile = parameterAnalysis.nameOutFile();

        upload.uploader(nameInFile);


        if(parameterAnalysis.isdTypeInt())
        {
            mergeSort = new MergeSortNum(upload, nameOutFile);
        }
        else
        {
            mergeSort = new MergeSortStr(upload, nameOutFile);
        }

        if (parameterAnalysis.isDownOrder())
        {
            mergeSort.descendingSorting();
        }
        else
            {
                mergeSort.ascendingSorting();
            }
    }
    }


