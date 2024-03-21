package backoffice.pages.bo.system;

import backoffice.controls.bo.ATable;
import backoffice.pages.bo.home.HomePage;
import backoffice.pages.bo.system.productmaintenance.MaintenanceDetailsPopup;
import com.paltech.element.common.Link;

import java.util.ArrayList;
import java.util.List;

public class ProductMaintenancePage extends HomePage {
    public int colProductName = 2;
    public int colStatus = 3;
    public int colMaintenanceMessage = 4;
    private int totalColumns = 5;
    public ATable tblProductMaintenance = ATable.xpath("//div[@class='custom-table']", totalColumns);
    private int colAction = 5;

    public MaintenanceDetailsPopup action(String productName) {
        List<String> lstProducts = tblProductMaintenance.getColumn(colProductName, false);
        for (int i = 0; i < lstProducts.size(); i++) {
            String product = lstProducts.get(i);
            if (product.equals(productName)) {
                Link lnk = (Link) tblProductMaintenance.getControlOfCell(1, colAction, i + 1, "i[@class='fas fa-pen']");
                if (lnk == null) {
                    System.err.println("DEBUG: Cannot get Action button of production name " + productName);
                    return null;
                }
                lnk.click();
                // waiting for loading
                lnk.isInvisible(2);
                return new MaintenanceDetailsPopup();
            }
        }
        System.out.println("DEBUG: There is no production name " + productName);
        return null;
    }

    public boolean isProductActive(String product){
        List<ArrayList<String>> lstProduct = tblProductMaintenance.getRows(false);
        String status ="";
        for(int i = 0; i < lstProduct.size(); i ++){
            if(lstProduct.get(i).get(colProductName-1).equalsIgnoreCase(product))
            {
                status = lstProduct.get(i).get(colStatus-1);
                break;
            }
        }
        System.out.println(String.format("The Maintenance status of the product %s is %s",product,status));
        if(!status.isEmpty())
            if(status.equals("Active"))
                return true;
        return false;
    }
}
