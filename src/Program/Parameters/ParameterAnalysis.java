package Program.Parameters;

import java.util.HashSet;
import java.util.Set;

public class ParameterAnalysis
{
    private boolean dTypeInt;
    private boolean dTypeStr;
    private boolean downOrder;
    private boolean upOrder;
    private int numberParameter;
    private String[] args;

    public ParameterAnalysis(String[] args)
    {
        this.args = args;
      parametersSeter(args);
    }

    private void parametersSeter(String[] args)
    {
        numberParameter = 0;
        while (!allЗarametersSet())
        {
            switch (args[numberParameter])
            {
                case ("-i"):
                    dTypeInt = true;
                    break;
                case ("-s"):
                    dTypeStr = true;
                    break;
                case ("-a"):
                    upOrder = true;
                    break;
                case ("-d"):
                    downOrder = true;
                    break;
                default:
                    upOrder = true;
                    numberParameter--;
            }
            numberParameter++;
        }
    }

    private boolean allЗarametersSet()
    {
        if((dTypeInt || dTypeStr) && (downOrder || upOrder))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String nameOutFile()
    {
        return args[numberParameter];
    }

    public  Set<String> nameInFile()
    {
        Set<String> nameInFile = new HashSet<>();
        for(int i = numberParameter + 1; i < args.length; i++)
        {
            nameInFile.add(args[i]);
        }

        return nameInFile;

    }

    public boolean isdTypeInt() {
        return dTypeInt;
    }

    public boolean isdTypeStr() {
        return dTypeStr;
    }

    public boolean isDownOrder() {
        return downOrder;
    }

    public boolean isUpOrder() {
        return upOrder;
    }
}
