public class Util 
{
    private Util(){}

    public static int[] toIntArray(String strArray[])
    {
        int intArray[] = new int[strArray.length];
        for(int i = 0; i < strArray.length; i++)
        {
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        return intArray;
    }

    public static void printMatrix(int matrix[][])
    {
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix.length; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void setBoolMatrixToTrue(boolean matrix[][])
    {
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0; j < matrix.length; j++)
            {
                matrix[i][j] = true;
            }
        }
    }
}
