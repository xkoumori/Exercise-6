public class CryptoTest {

    public static void main(String[] args) throws Exception {
   // handling of args[] for null is not required. For no command line arguments
   // args.length = 0
   // Check if all arguments have been passed
      if (args.length != 2) {
        System.out.println("Type in the Interactions Pane: java CryptoTest file1.txt file2.txt");
      }
      else {
  
        String inFile = args[0];
        String outFile = args[1];
  
        CryptoEngine cryptoEngine = new CryptoEngine(inFile, outFile);
        cryptoEngine.encrypt();
        cryptoEngine.decrypt();
      }
    }
}
