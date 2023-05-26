
public class Owner {
    private String accountNo;
    private String billDate;
    private String ownerName;
    private String ownerAddress;
    private String arrears;
    private String previousMeterReading;
    private String currentMeterReading;
    private String totalUnit;
    private String currentCharge;
    private String totalBill;

    public Owner(String accountNo, String billDate, String ownerName, String ownerAddress, String arrears, String previousMeterReading, String currentMeterReading, String totalUnit, String currentCharge, String totalBill) {
        this.accountNo = accountNo;
        this.billDate = billDate;
        this.ownerName = ownerName;
        this.ownerAddress = ownerAddress;
        this.arrears = arrears;
        this.previousMeterReading = previousMeterReading;
        this.currentMeterReading = currentMeterReading;
        this.totalUnit = totalUnit;
        this.currentCharge = currentCharge;
        this.totalBill = totalBill;
    }//Construtor
    
    //Setter and Getter Method
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getArrears() {
        return arrears;
    }

    public void setArrears(String arrears) {
        this.arrears = arrears;
    }

    public String getPreviousMeterReading() {
        return previousMeterReading;
    }

    public void setPreviousMeterReading(String previousMeterReading) {
        this.previousMeterReading = previousMeterReading;
    }

    public String getCurrentMeterReading() {
        return currentMeterReading;
    }

    public void setCurrentMeterReading(String currentMeterReading) {
        this.currentMeterReading = currentMeterReading;
    }

    public String getTotalUnit() {
        return totalUnit;
    }

    public void setTotalUnit(String totalUnit) {
        this.totalUnit = totalUnit;
    }

    public String getCurrentCharge() {
        return currentCharge;
    }

    public void setCurrentCharge(String currentCharge) {
        this.currentCharge = currentCharge;
    }

    public String getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(String totalBill) {
        this.totalBill = totalBill;
    }

}//Class Owner