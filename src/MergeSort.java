import java.util.Random;

/**
 *
 * @author klaus
 */
public class MergeSort {


    private static void mergeRanges(int[] values, int first, int mid, int last){
        int size = last + 1 - first;
        int[] temp = new int[size];
        int i1 = first, i2 = mid+1, tempIndex = 0;
        while(i1 <= mid && i2 <= last){
            if(values[i1] < values[i2]){
                temp[tempIndex] = values[i1];
                i1++;
            }
            else{
                temp[tempIndex] = values[i2];
                i2++;
            }
            tempIndex++;
        }
        while(i1 <= mid){
            temp[tempIndex] = values[i1];
            i1++;
            tempIndex++;
        }
        while(i2 <= last){
            temp[tempIndex] = values[i2];
            i2++;
            tempIndex++;
        }
        System.arraycopy(temp, 0, values, first, size);
    }

    private static void sortRange(int[] values, int first, int last){
        if(last > first){
            int mid = (first + last) / 2;
            sortRange(values, first, mid);
            sortRange(values, mid + 1, last);
            mergeRanges(values, first, mid, last);
        }
    }

    public static void sort(int[] values){
        sortRange(values, 0, values.length - 1);
    }
    
    public static int[] randomValues(int howMany){
        int[] result = new int[howMany];
        Random random = new Random();
        for(int i=0; i<howMany; i++)
           result[i] = random.nextInt() % (10 * howMany);
        return result;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numValues = 10000;
        boolean printResults = false;
        
        
        int[] a = randomValues(numValues);
        if(printResults){
            System.out.print("Before sorting: ");
            for(int i=0;i<numValues; i++)
                System.out.print(a[i] + " ");
            System.out.println();
        }
        long start = System.currentTimeMillis();
        sort(a);
        long now = System.currentTimeMillis();
        double elapsed = (now - start) / 1000.0;
        if(printResults){
            System.out.print("After sorting: ");
            for(int i=0;i<numValues; i++)
                System.out.print(a[i] + " ");
            System.out.println();
        }
    	System.out.println();
        System.out.println("Elapsed time = " + elapsed + " seconds");    
    }    
}
