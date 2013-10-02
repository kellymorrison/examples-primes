public class PrintPrimes {
  int numberOfPrimes;
  int rowsPerPage; 
  int columnsPerPage;
  int ordMax;
  int listOfPrimes[];
  
  /* this variable is only called in constructor and no where after
  didn't delete in case another class calls this constructor */
  int variable; 

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

  private void calculateOddPrimes() {
      boolean isJPrime;
      int tempIndex;
      int oddNonPrimes[] = new int[ordMax + 1];
      int j = 1;
      int ord = 2;
      int primeSquared = 9;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          j = j + 2;
          if (j == primeSquared) {
            ord++;
            primeSquared = listOfPrimes[ord] * listOfPrimes[ord];
            oddNonPrimes[ord - 1] = j;
          }
          tempIndex = 2;
          isJPrime = true;
          while (tempIndex < ord && isJPrime) {
            while (oddNonPrimes[tempIndex] < j) {
              oddNonPrimes[tempIndex] = oddNonPrimes[tempIndex] + listOfPrimes[tempIndex] + listOfPrimes[tempIndex];
            }
            if (oddNonPrimes[tempIndex] == j) {
              isJPrime = false;
            }
            tempIndex = tempIndex + 1;
          }
        } while (!isJPrime);
        listOfPrimes[primesFoundSoFar] = j;
      }
    }

    public void printPrimes() {
        int pageNumber = 1;
        int pageOffset = 1;
        while (pageOffset <= numberOfPrimes) {
          System.out.println("The First " + numberOfPrimes +
                               " Prime Numbers --- Page " + pageNumber + "\n");
          for (int rowOffset = pageOffset; rowOffset < pageOffset + rowsPerPage; rowOffset++) {
            for (int c = 0; c < columnsPerPage; c++) {
              if (rowOffset + c * rowsPerPage <= numberOfPrimes) {
                System.out.format("%10d", listOfPrimes[rowOffset + c * rowsPerPage]);
              }
            }
            System.out.println("");
          }
          System.out.println("\f");
          pageNumber++;
          pageOffset = pageOffset + rowsPerPage * columnsPerPage;
        }
    }
}
					 
