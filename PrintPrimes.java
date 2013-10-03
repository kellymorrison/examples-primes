/*
 * Kelly Morrison
 * 260459446
 * ECSE 321 Assignment 2
 * 3/10/2013
 * Prime Calculator
 */

public class PrintPrimes {
  int numberOfPrimes;
  int rowsPerPage;
  int columnsPerPage;
  int variable; 
  int ordMax;
  int listOfPrimes[];

  public PrintPrimes(int numberOfPrimes, int rowsPerPage, int columnsPerPage, int variable, int ordMax) {
    this.numberOfPrimes   = numberOfPrimes;
    this.rowsPerPage  = rowsPerPage;
    this.columnsPerPage  = columnsPerPage;
    this.variable  = variable;
    this.ordMax = ordMax;
    this.listOfPrimes = new int[numberOfPrimes + 1];
  }

  public static void main(String[] args) {
      PrintPrimes printPrimes = new PrintPrimes(300, 50, 4, 10, 30);
      printPrimes.calculatePrimes();
      printPrimes.printPrimes();
  }

  public void calculatePrimes() {
      /* Two is the only even prime. All other prime numbers are odd.
       * To simplify the code, we simply add 2 as a prime number, and
       * delegate the task of finding all odd prime numbers to a helper
       * function.
       */
      listOfPrimes[1] = 2;
      calculateOddPrimes();
  }
  
/* Calculates all the odd prime numbers by incrementing through odd numbers (beginning at 3) 
 * and proceeds to store the multiples of the current odd number into the array multOfPrimes. 
 * Future odd numbers are compared with the composite numbers of multOfPrimes and if they are
 *  equal, the current odd number is not prime and is incremented and the comparison restarts. 
 */
  private void calculateOddPrimes() {
      boolean isJPrime;
      int tempIndex;
      int multOfPrimes[] = new int[ordMax + 1]; 		//array stores odd multiples of primes found so far
      int cur = 1;
      int indexOfPrime = 2;
      int primeSquared = 9;
      
      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          cur = cur + 2; 								//incrementing through odd numbers 
          if (cur == primeSquared) {					//if true, primeSquared is updated
            indexOfPrime++;
            primeSquared = listOfPrimes[indexOfPrime] * listOfPrimes[indexOfPrime];
            multOfPrimes[indexOfPrime - 1] = cur;
          }
          tempIndex = 2;
          isJPrime = true; 
          while (tempIndex < indexOfPrime && isJPrime) {
          	/*when j is greater than the current composite number, the composite number that corresponds 
          	 * to the most recently found number is incremented by 2*that current prime
          	 */
              while (multOfPrimes[tempIndex] < cur) {
                multOfPrimes[tempIndex] = multOfPrimes[tempIndex] + 2*listOfPrimes[tempIndex];
              }
              //compares j with composite numbers and if equal, j is not prime and the loop is exited 
              if (multOfPrimes[tempIndex] == cur) {
                isJPrime = false;
              }
              tempIndex++;
            } 
        } while (!isJPrime);
        //j is prime at this point
        listOfPrimes[primesFoundSoFar] = cur;
      }
    }
  
    public void printPrimes() {
        int pageNumber = 1;
        int pageOffset = 1;
        while (pageOffset <= numberOfPrimes) {
          System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + pageNumber + "\n");
          formatPrint(pageOffset);
          System.out.println("\f");
          pageNumber++;
          pageOffset = pageOffset + rowsPerPage * columnsPerPage;
        }
    }
    
    // Formats the columns and rows for printing
    public void formatPrint(int pageOffset) {
        for (int rowOffset = pageOffset; rowOffset < pageOffset + rowsPerPage; rowOffset++) {
            for (int c = 0; c < columnsPerPage; c++) {
              if (rowOffset + c * rowsPerPage <= numberOfPrimes) {
                System.out.format("%10d", listOfPrimes[rowOffset + c * rowsPerPage]);
              }
            }
            System.out.println("");
          }
    }
}
					 
