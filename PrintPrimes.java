public class PrintPrimes {
  int numberOfPrimes;
  int rowsPerPage; 
  int columnsPerPage;
  int WW;
  int ordMax;
  int listOfPrimes[];

  public PrintPrimes(int numberOfPrimes, int rowsPerPage, int columnsPerPage, int WW, int ordMax) {
    this.numberOfPrimes   = numberOfPrimes;
    this.rowsPerPage  = rowsPerPage;
    this.columnsPerPage  = columnsPerPage;
    this.WW  = WW;
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
      int n;
      int mult[] = new int[ordMax + 1];

      int j = 1;
      int ord = 2;
      int primeSquared = 9;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          j = j + 2;
          if (j == primeSquared) {
            ord++;
            primeSquared = listOfPrimes[ord] * listOfPrimes[ord];
            mult[ord - 1] = j;
          }
          n = 2;
          isJPrime = true;
          while (n < ord && isJPrime) {
            while (mult[n] < j) {
              mult[n] = mult[n] + listOfPrimes[n] + listOfPrimes[n];
            }
            if (mult[n] == j) {
              isJPrime = false;
            }
            n = n + 1;
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

					 
