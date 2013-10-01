public class PrintPrimes {
  int numberOfPrimes;
  int rowsPerPage; 
  int columnsPerPage;
  int WW;
  int ORDMAX;
  int listOfPrimes[];

  public PrintPrimes(int numberOfPrimes, int rowsPerPage, int columnsPerPage, int WW, int ORDMAX) {
    this.numberOfPrimes   = numberOfPrimes;
    this.rowsPerPage  = rowsPerPage;
    this.columnsPerPage  = columnsPerPage;
    this.WW  = WW;
    this.ORDMAX = ORDMAX;
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
      int MULT[] = new int[ORDMAX + 1];

      int j = 1;
      int ORD = 2;
      int primeSquared = 9;

      for(int primesFoundSoFar = 2; primesFoundSoFar <= numberOfPrimes; primesFoundSoFar++) {
        do {
          j = j + 2;
          if (j == primeSquared) {
            ORD = ORD + 1;
            primeSquared = listOfPrimes[ORD] * listOfPrimes[ORD];
            MULT[ORD - 1] = j;
          }
          n = 2;
          isJPrime = true;
          while (n < ORD && isJPrime) {
            while (MULT[n] < j)
              MULT[n] = MULT[n] + listOfPrimes[n] + listOfPrimes[n];
            if (MULT[n] == j)
              isJPrime = false;
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
                               " Prime Numbers --- Page " + pageNumber);
          System.out.println("");
          for (int ROWOFFSET = pageOffset; ROWOFFSET < pageOffset + rowsPerPage; ROWOFFSET++){
            for (int c = 0; c < columnsPerPage; c++)
              if (ROWOFFSET + c * rowsPerPage <= numberOfPrimes)
                System.out.format("%10d", listOfPrimes[ROWOFFSET + c * rowsPerPage]);
            System.out.println("");
          }
          System.out.println("\f");
          pageNumber = pageNumber + 1;
          pageOffset = pageOffset + rowsPerPage * columnsPerPage;
        }
    }
}

					 
