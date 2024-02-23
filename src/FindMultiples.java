import static java.util.Arrays.sort;
import java.util.Random;

/**
 *
 * @author klaus
 */
public class FindMultiples {
    public static int findDuplicate(int[] numbers){
        for(int i = 0; i < numbers.length; i++)
            for(int j = 0; j < i; j++)
                if(numbers[i] == numbers[j])
                    return i;
	return -1;  // Failure case
    }

    public static int findTriplicate(int[] numbers){
        // TO DO
        return -1;  // Failure case
    }
    public static int findDuplicateSorted(int[] numbers){
        // TO DO
        return -1;  // Failure case
    }
    public static int findTriplicateSorted(int[] numbers){
        // TO DO
        return -1;  // Failure case
    }

    public static int[] randomValues(int howMany){
        int[] result = new int[howMany];
        Random random = new Random();
		for(int i=0; i<howMany-2; i++)
			result[i] = i;
		int r = random.nextInt(howMany);
		result[howMany-1] = r;
		result[howMany-2] = r;
        for(int range=howMany; range>=2; range--){
			int index = random.nextInt(range);
			int temp = result[index];
			result[index] = result[range-1];
			result[range-1] = temp;
		}
        return result;
    }

    public static void main(String[] args) {
        // How many values to generate
        int numValues = 100000;
        // Whether to look for triplicate values
        boolean triplicates = false;
        // Whether to use sorted data
        boolean sortData = false;
        // Whether to print data. Only use with small numbers of values.
        boolean printData = false;
        
        // Create some data, either sorted or unsorted
        int[] data = randomValues(numValues);
        if(sortData)
            sort(data);        
        // Optionally print the data (to check correctness)
        if(printData){
            System.out.print("Input values: ");
            for (int datum : data) System.out.print(datum + " ");
            System.out.println();
        }
        int result = 0;

        long start = System.currentTimeMillis();
        if(triplicates)
            if(sortData)
                result = findTriplicateSorted(data);
            else
                result = findTriplicate(data);
        else
            if(sortData)
                result = findDuplicateSorted(data);
            else
                result = findDuplicate(data);
        long now = System.currentTimeMillis();
        double elapsed = (now - start) / 1000.0;
        if(result >= 0)
            System.out.println("Result: " + result + " (value: " + data[result] + ")");
        else
            System.out.println("Result: nothing found");
        System.out.println("Elapsed time = " + elapsed + " seconds");        

    }
    
}
