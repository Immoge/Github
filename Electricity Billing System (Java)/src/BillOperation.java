
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BillOperation {
   private ArrayList<Owner> ownerAL; //arraylist
      
   public BillOperation() {
        ownerAL = new ArrayList<>();
    }
   
   public int calculateTotalUnit(int currentReading, int previousReading){      
        int totalUnit = currentReading - previousReading;     
        return totalUnit;
        }//calculateTotalUnit
  
    public double calculateCurrentCharge(int totalUnit){
        double currentCharge = 0.0;
        
        if(totalUnit >=1 && totalUnit <= 200){
            currentCharge = totalUnit * 0.218;
        }
        else if (totalUnit >=201 && totalUnit <= 300){
            currentCharge = 43.6 + ((totalUnit - 200) * 0.334);
        }
        else if (totalUnit >=301 && totalUnit <= 600){
            currentCharge = 77 + ((totalUnit - 300) * 0.516);
        }
        else if (totalUnit >=601 && totalUnit <= 900){
            currentCharge = 231.8 + ((totalUnit - 600) * 0.546);
        }
        else if (totalUnit >= 901){
            currentCharge = 395.6 + ((totalUnit - 900) * 0.571);  
        }
         return currentCharge;
     }//calculateCurrentCharg
   
    public double calculateTotalBill(double currentCharge, double arreas){
        double totalBill;
        return totalBill = arreas + currentCharge;
    }//calculateTotalBill
    public void addRecord(Owner owner) { 
        try {
            File outFileName = new File("ElectricBillRecord.txt");
            FileWriter fw = new FileWriter(outFileName, true); //add true because we want to append, add record at the end of the file
            PrintWriter pw = new PrintWriter(fw);
            pw.println(owner.getAccountNo());
            pw.println(owner.getBillDate());
            pw.println(owner.getOwnerName());
            pw.println(owner.getOwnerAddress());
            pw.println(owner.getArrears());
            pw.println(owner.getPreviousMeterReading());
            pw.println(owner.getCurrentMeterReading());
            pw.println(owner.getTotalUnit());
            pw.println(owner.getCurrentCharge());
            pw.println(owner.getTotalBill());
            pw.close();
        }//try
        catch (IOException ex) {
            ex.getMessage();
        }//catch       
        ownerAL.add(owner);
    }//calculateTotalBill
    
    public String displayAllRecord() {
        //read all records in file
        StringBuilder stringBuilder = new StringBuilder();      
        try {
            File inFileName = new File("ElectricBillRecord.txt");
            FileReader fr = new FileReader(inFileName);//cannot add true because true will be extend the textfile
            BufferedReader br = new BufferedReader(fr);  
            String line = br.readLine();
            while (line != null) {
                
                String accountNo = line;
                String billDate = br.readLine();
                String ownerName = br.readLine();
                String ownerAddress = br.readLine();
                String arrears = br.readLine();
                String previousMeterReading = br.readLine();
                String currentMeterReading = br.readLine();
                String totalUnit = br.readLine();
                String currentCharge = br.readLine();
                String totalBill = br.readLine();               
                line = br.readLine();
                
                String output = (accountNo + "\t" + billDate + "\t" + ownerName + "\t" + ownerAddress + "\t" + arrears + "\t" + previousMeterReading +
                                "\t" + currentMeterReading + "\t" + totalUnit + "\t" + currentCharge + "\t" + totalBill + "\n");
                stringBuilder.append(output);
            }
            br.close();
        }//try
        catch (FileNotFoundException excep) {
            System.out.println("File is not found");
            excep.getStackTrace();
            excep.getMessage();
        }//catch
        catch (IOException ioEx) {
            ioEx.getStackTrace();
            ioEx.getMessage();
        }//catch     
        String outputFinal = stringBuilder.toString(); //convert stringBuilder into string
        return outputFinal;
    }//displayAllRecord

    public Owner searchRecord(String searchAccountNo) { 
        Owner owr = null;
        
        //read all records in file
        try {
            File inFileName = new File("ElectricBillRecord.txt");
            FileReader fr = new FileReader(inFileName);
            BufferedReader br = new BufferedReader(fr);
            
            String line = br.readLine();
            while (line != null) {
                String accountNo = line;
                String billDate = br.readLine();
                String ownerName = br.readLine();
                String ownerAddress = br.readLine();
                String arrears = br.readLine();
                String previousMeterReading = br.readLine();
                String currentMeterReading = br.readLine();
                String totalUnit = br.readLine();
                String currentCharge = br.readLine();
                String totalBill = br.readLine();               
                
                //Compare searchAccountNo and accountNo
                if (accountNo.equals(searchAccountNo)) {
                    owr = new Owner(accountNo, billDate, ownerName, ownerAddress, arrears, previousMeterReading, currentMeterReading, totalUnit,
                                    currentCharge, totalBill);
                }              
                line = br.readLine();
            }
            br.close();
        } //try
        catch (FileNotFoundException excep) {
            System.out.println("File is not found");
            excep.getStackTrace();
            excep.getMessage();
        } //catch
        catch (IOException ioEx) {
            ioEx.getStackTrace();
            ioEx.getMessage();
        }//catch
        return owr;
    }//searchRecord

    public void editRecord(Owner owner) {
        ArrayList<Owner> ownerAL = new ArrayList<>();
        Owner owr;
        //read all records and store into arraylist
        try {
            File inFileName = new File("ElectricBillRecord.txt");
            FileReader fr = new FileReader(inFileName);
            BufferedReader br = new BufferedReader(fr);
            
            String line = br.readLine();
            while (line != null) {
                
                String accountNo = line;
                String billDate = br.readLine();
                String ownerName = br.readLine();
                String ownerAddress = br.readLine();
                String arrears = br.readLine();
                String previousMeterReading = br.readLine();
                String currentMeterReading = br.readLine();
                String totalUnit = br.readLine();
                String currentCharge = br.readLine();
                String totalBill = br.readLine();               
                
                owr =  new Owner(accountNo, billDate, ownerName, ownerAddress, arrears, previousMeterReading, currentMeterReading, totalUnit,
                                 currentCharge, totalBill); //add record into arraylist
                ownerAL.add(owr);
                line = br.readLine();
            }//while
            br.close();
        }//try
        catch (FileNotFoundException excep) {
            System.out.println("File is not found");
            excep.getStackTrace();
            excep.getMessage();
        } //catch
        catch (IOException ioEx) {
            ioEx.getStackTrace();
            ioEx.getMessage();
        }//catch
        
        //Compare searchAccountNo and accountNo
        String searchAccountNo = owner.getAccountNo();
        for (int i = 0; i < ownerAL.size(); i++) {
            if (ownerAL.get(i).getAccountNo().equals(searchAccountNo)) {

                //update other data in array list
                ownerAL.get(i).setAccountNo(owner.getAccountNo());
                ownerAL.get(i).setBillDate(owner.getBillDate());
                ownerAL.get(i).setOwnerName(owner.getOwnerName());
                ownerAL.get(i).setOwnerAddress(owner.getOwnerAddress());
                ownerAL.get(i).setArrears(owner.getArrears());
                ownerAL.get(i).setPreviousMeterReading(owner.getPreviousMeterReading());
                ownerAL.get(i).setCurrentMeterReading(owner.getCurrentMeterReading());
                ownerAL.get(i).setTotalUnit(owner.getTotalUnit());
                ownerAL.get(i).setCurrentCharge(owner.getCurrentCharge());
                ownerAL.get(i).setTotalBill(owner.getTotalBill());               
            }
        }//fpr

        //save all record into file
        try {
            File outFileName = new File("ElectricBillRecord.txt");
            FileWriter fw = new FileWriter(outFileName);
            PrintWriter pw = new PrintWriter(fw);          
            
            for (int i = 0; i < ownerAL.size(); i++) {
                pw.println(ownerAL.get(i).getAccountNo());
                pw.println(ownerAL.get(i).getBillDate());
                pw.println(ownerAL.get(i).getOwnerName());
                pw.println(ownerAL.get(i).getOwnerAddress());
                pw.println(ownerAL.get(i).getArrears());
                pw.println(ownerAL.get(i).getPreviousMeterReading());
                pw.println(ownerAL.get(i).getCurrentMeterReading());
                pw.println(ownerAL.get(i).getTotalUnit());
                pw.println(ownerAL.get(i).getCurrentCharge());
                pw.println(ownerAL.get(i).getTotalBill());           
            }//for
            pw.close();
        } catch (IOException ex) {
            ex.getMessage();
        }//catch
    }//editRecord
    
    public void deleteRecord(String searchAccountNo) {     
        ArrayList<Owner> ownerAL = new ArrayList<>();
        Owner owr; //owr - owner

        //read all records and store into arraylist
        try {
            File inFileName = new File("ElectricBillRecord.txt");
            FileReader fr = new FileReader(inFileName);
            BufferedReader br = new BufferedReader(fr);            
            String line = br.readLine();
            while (line != null) {
                
                String accountNo = line;
                String billDate = br.readLine();
                String ownerName = br.readLine();
                String ownerAddress = br.readLine();
                String arrears = br.readLine();
                String previousMeterReading = br.readLine();
                String currentMeterReading = br.readLine();
                String totalUnit = br.readLine();
                String currentCharge = br.readLine();
                String totalBill = br.readLine();   
                
                owr =  new Owner(accountNo, billDate, ownerName, ownerAddress, arrears, previousMeterReading, currentMeterReading, totalUnit,
                                 currentCharge, totalBill); //add record into arraylist
                ownerAL.add(owr);
                
                line = br.readLine();
            }//while
            br.close();
        } //try
        catch (FileNotFoundException excep) {
            System.out.println("File is not found");
            excep.getStackTrace();
            excep.getMessage();
        }//catch
        catch (IOException ioEx) {
            ioEx.getStackTrace();
            ioEx.getMessage();
        }//catch
        
        for (int i = 0; i < ownerAL.size(); i++) {
            if (ownerAL.get(i).getAccountNo().equalsIgnoreCase(searchAccountNo)) {
                //delete record in arraylist
                ownerAL.remove(i);
            }//end if
        }//end for loop

        //save all record into file
        try {
            File outFileName = new File("ElectricBillRecord.txt");
            FileWriter fw = new FileWriter(outFileName);
            PrintWriter pw = new PrintWriter(fw);
            
            for (int i = 0; i < ownerAL.size(); i++) {
                pw.println(ownerAL.get(i).getAccountNo());
                pw.println(ownerAL.get(i).getBillDate());
                pw.println(ownerAL.get(i).getOwnerName());
                pw.println(ownerAL.get(i).getOwnerAddress());
                pw.println(ownerAL.get(i).getArrears());
                pw.println(ownerAL.get(i).getPreviousMeterReading());
                pw.println(ownerAL.get(i).getCurrentMeterReading());
                pw.println(ownerAL.get(i).getTotalUnit());
                pw.println(ownerAL.get(i).getCurrentCharge());
                pw.println(ownerAL.get(i).getTotalBill()); 
            }//end for loop
            pw.close();
        }//try
        catch (IOException ex) {
            ex.getMessage();
        }//catch
    }//deleteRecord
}//class BillOperation
