package backoffice.pages.bo.operations;

import backoffice.common.BOConstants;
import backoffice.controls.Table;
import backoffice.pages.bo._components.AppConfirmPopup;
import backoffice.pages.bo.home.HomePage;
import backoffice.utils.operations.PerformanceUtils;
import com.paltech.element.common.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.Keys;
import org.testng.Assert;


import java.util.*;

public class PerformancePage extends HomePage {
    public Button btnCreateManageLine = Button.xpath("//div[@id='header']//button[@name='create-lines']");
    public com.paltech.element.common.DropDownBox ddpLine = com.paltech.element.common.DropDownBox.name("lines");
    public TextBox txtFrom = TextBox.xpath("//div[@id='header']//input[@name='from-date']");
    public TextBox txtTo = TextBox.xpath("//div[@id='header']//input[@name='to-date']");
    public Button btnLastWeek = Button.xpath("//div[@id='header']//button[@name='lastweek']");
    public Button btnLast30Days = Button.xpath("//div[@id='header']//button[@name='last30d']");
    public Button btnLast90Days = Button.xpath("//div[@id='header']//button[@name='last90d']");
    public Button btnLast365Days = Button.xpath("//div[@id='header']//button[@name='last365d']");
    public Button btnSubmit = Button.xpath("//div[@id='header']//button[@name='submit']");
    public AppConfirmPopup confirmPopup = AppConfirmPopup.xpath("//app-confirm-dialog");

    /*************
     * PT SETTING
     *************/
    public Label lblPTSetting = Label.xpath("//app-pt-setting/parent::div[1]//div[contains(@class,'text-primary')]");
    public Icon icPTSettingCollapse = Icon.xpath("//app-pt-setting/parent::div[1]//i[contains(@class,'collapse show')]");
    public Label lblNoOfBEt = Label.xpath("//input[@id='noOfBet']//preceding::label[1]");
    public TextBox txtNoOfBet = TextBox.id("noOfBet");
    public TextBox txtMemberWinLossFrom = TextBox.xpath("//app-pt-setting//input[@name='from-date']");
    public TextBox txtMemberWinLossTo = TextBox.xpath("//app-pt-setting//input[@name='to-date']");
    public DropDownBox ddbCurrencyType = DropDownBox.xpath("//app-pt-setting//select[@name='currencies']");
    public Button btnSubmitPTSetting = Button.xpath("//app-pt-setting//button[@name='submit']");
    public Table tblPTSetting = Table.xpath("//app-pt-setting//table[@class='ptable table-striped']", 20);
    int PTcol = 18;
    /*************
     * LINE OVERVIEW
     *************/
    public Label lblLineOverview = Label.xpath("//app-line-overview/parent::div[1]//div[contains(@class,'text-primary')]");
    public Icon icLineOverviewCollapse = Icon.xpath("//app-line-overview/parent::div[1]//i[contains(@class,'collapse show')]");
    public Button btnMemberTree = Button.xpath("//app-line-overview//button[contains(@class,'btn-member-tree')]");
    public Label lblLineOverviewNote = Label.xpath("//app-line-overview//i[contains(@class,'fa-info-circle')]/parent::div/span");
    public Label lblGeneralInformation = Label.xpath("//app-line-overview//div[@class='row']/div[1]/div[@class='mb-2']");
    public Table tblGeneralInformation1 = Table.xpath("//app-line-overview//div[@class='row']/div[1]//table[1]", 2);
    public Table tblGeneralInformation2 = Table.xpath("//app-line-overview//div[@class='row']/div[1]//table[2]", 3);
    public Label lblTurnover = Label.xpath("//app-line-overview//div[@class='row']/div[2]/div[@class='mb-2'][1]");
    public Table tblTurnover = Table.xpath("//app-line-overview//div[@class='row']/div[2]//table[1]", 3);
    public Label lblAverageStake = Label.xpath("//app-line-overview//div[@class='row']/div[2]/div[@class='mb-2'][2]");
    public Table tblAverageStake = Table.xpath("//app-line-overview//div[@class='row']/div[2]//table[2]", 3);
    public Label lblWinLoss = Label.xpath("//app-line-overview//div[@class='row']/div[3]/div[@class='mb-2'][2]");
    public Table tblWinLoss = Table.xpath("//app-line-overview//div[@class='row']/div[3]//table[2]", 4);
    public Label lblPTByTurnover = Label.xpath("//app-line-overview//div[@class='row']/div[3]/div[@class='mb-2'][1]");
    public Table tblPTByTurnover = Table.xpath("//app-line-overview//div[@class='row']/div[3]//table[1]", 4);
    public Label lblPerformance = Label.xpath("//app-line-overview//div[@class='row']/div[4]/div[@class='mb-2'][1]");
    public Table tblPerformance = Table.xpath("//app-line-overview//div[@class='row']/div[4]//table[1]", 4);

    /*************
     * TOP PERFORMERS
     *************/
    public Label lblTopPerformance = Label.xpath("//app-top-performers/parent::div[1]//div[contains(@class,'text-primary')]");
    public Icon icTopPerformanceCollapse = Icon.xpath("//app-top-performers/parent::div[1]//i[contains(@class,'collapse show')]");
    public Tab tabTurnover = Tab.xpath("//app-top-performers//section[@id='tabs']//a[@name='turnover']");
    public Tab tabWinLoss = Tab.xpath("//app-top-performers//section[@id='tabs']//a[@name='winloss']");
    public Tab tabPerformance = Tab.xpath("//app-top-performers//section[@id='tabs']//a[@name='performance']");
    public Label lblTopPerformanceNote = Label.xpath("//app-top-performers//i[contains(@class,'fa-info-circle')]/parent::div/span");
    public Button btnMember = Button.xpath("//app-top-performers//button[@name='member']");
    public Button btnLine = Button.xpath("//app-top-performers//button[@name='line']");
    public Table tblTopPerformer = Table.xpath("//app-top-performers//table", 9);


    /*************
     * Create New Line section UI
     *************/
    public Label lblCreateNewLine = Label.xpath("//div[@class='text-primary font-weight-bold mw-fit mr-2']");
    public Button btnBack = Button.xpath("//div[@class='text-primary font-weight-bold mw-fit ml-2']");
    public DropDownBox ddbBrand = DropDownBox.xpath(String.format("//span[text()='%s']/parent::div/following::select[1]", BOConstants.Reports.Performance.BRAND));
    public DropDownBox ddbLevel = DropDownBox.xpath(String.format("//span[text()='%s']/parent::div/following::select[1]", BOConstants.Reports.Performance.LEVEL));
    public DropDownBox ddbUplineId = DropDownBox.xpath(String.format("//span[text()='%s']/parent::div/following::select[1]", BOConstants.Reports.Performance.UPLINE_ID));
    public TextBox txtMappedAccountID = TextBox.xpath(String.format("//span[text()='%s']/parent::div/following::input[1]", BOConstants.Reports.Performance.MAPPED_ACCOUNT_ID));
    public TextBox txtLineName = TextBox.xpath(String.format("//span[text()='%s']/parent::div/following::input[1]", BOConstants.Reports.Performance.LINE_NAME));
    public Button btnCreate = Button.xpath("//button[contains(@class,'btn btn-sm btn-core ml-2')]");

    public Table tblLine = Table.xpath("//table[@class='ptable table-striped']", 6);
    int totalCol = 6;
    int colBrand = 1;
    int colLevel = 2;
    int colLine = 3;
    int colUpLine = 4;
    int colMappedAccount = 5;
    String txtSearch = "//input[@placeholder='%s']";
    public TextBox txtSearchBrand = TextBox.xpath(String.format(txtSearch, BOConstants.Reports.Performance.SEARCH_BRAND));
    public TextBox txtSearchLevel = TextBox.xpath(String.format(txtSearch, BOConstants.Reports.Performance.SEARCH_LEVEL));
    public TextBox txtSearchLine = TextBox.xpath(String.format(txtSearch, BOConstants.Reports.Performance.SEARCH_LINE));
    public TextBox txtSearchUplineID = TextBox.xpath(String.format(txtSearch, BOConstants.Reports.Performance.SEARCH_UPLINE_ID));
    public TextBox txtSearhID = TextBox.xpath(String.format(txtSearch, BOConstants.Reports.Performance.SEARCH_MAPPED_ACCOUNT_ID));

    /*************
     * Create New Line ACTIONS
     *************/
    public void clickOnCreateManageLineButton(){
        btnCreateManageLine.click();
        try{
            Thread.sleep(500);//wait for locator visible in view port
        }catch (Exception e){
        }
    }

    public String searchMappedAccountID(String mappedAccount) {
        txtSearhID.type(true, mappedAccount);
        txtSearhID.type(false, Keys.ARROW_DOWN);
        Label firstItemSuggest = Label.xpath("(//div[contains(@class, 'completer-dropdown-holder')]//completer-list-item)[1]");
        if (firstItemSuggest.isDisplayed()) {
            mappedAccount = firstItemSuggest.getText().trim();
            firstItemSuggest.click();
        } else {
            mappedAccount = "";
        }
        return mappedAccount;
    }

    public void createNewLine(String brandName, String level, String lineName, String uplineID, String mappedAccount){
        clickOnCreateManageLineButton();
        if(!brandName.isEmpty()){
            ddbBrand.selectByVisibleText(brandName);
        }
        if(!level.isEmpty()){
            ddbLevel.selectByVisibleText(level);
        }
        if(!lineName.isEmpty()){
            txtLineName.type(true, lineName);
        }
        if(!uplineID.isEmpty()){
            ddbUplineId.selectByVisibleText(uplineID);
        }
        if(!mappedAccount.isEmpty()){
            searchMappedAccountID(mappedAccount);
        }
        btnCreate.click();
        confirmPopup.confirm();
    }

    public void searchLineInManagePage(String brandName, String level, String lineName, String uplineID, String mappedAccount){
        if(!brandName.isEmpty()){
            txtSearchBrand.type(true, brandName);
        }
        if(!level.isEmpty()){
            txtSearchLevel.type(true, level);
        }
        if(!lineName.isEmpty()){
            txtSearchLine.type(true, lineName);
        }
        if(!uplineID.isEmpty()){
            txtSearchBrand.type(true, uplineID);
        }
        if(!mappedAccount.isEmpty()){
            txtSearhID.type(true, mappedAccount);
        }
        try{
            Thread.sleep(150);//wait for locator visible in view port
        }catch (Exception e){
        }
    }

    public String getCurrentPTShow(int index) {
    return tblPTSetting.getControlOfCell(1,PTcol, index,"input").getAttribute("value");
    }

    private int findRowLineIndex(String lineName){
        int rowIndex = 1;
        while (true) {
            Label lblLineName = Label.xpath(tblLine.getxPathOfCell(1, colLine, rowIndex, null));
            if (lblLineName.isDisplayed()) {
                if (lblLineName.getText().contains(lineName)) {
                    System.out.println("FOUND correct line Name at row index: " + rowIndex);
                    break;
                } else {
                    rowIndex++;
                    continue;
                }
            } else {
                System.err.println("NOT found any correct line Name");
                rowIndex = -1;
                break;
            }
        }
        return rowIndex;
    }

    public boolean isLineCorrect(String brandName, String level, String lineName, String uplineID, String mappedAccount) {
        searchLineInManagePage(brandName, level, lineName, uplineID, mappedAccount);
        boolean isExist = true;
        int rowIndex = findRowLineIndex(lineName);
        if (rowIndex == -1) {
            System.out.println("Failed to find line with line name: " + lineName);
            return false;
        }
        List<String> itemsList = Arrays.asList(brandName, level, lineName, uplineID, mappedAccount);
        //Last colum contains edit button should not verify it
        for (int i = 0; i < totalCol - 2; i++) {
            String lblLineColText = Label.xpath(tblLine.getxPathOfCell(1, i + 1, rowIndex, null)).getText().trim();
            if (itemsList.get(i).equals("")) {
                continue; // skip verifying for empty params
            } else {
                if (!itemsList.get(i).equalsIgnoreCase(lblLineColText)) {
                    isExist = false;
                    System.out.println("Line is not correct value with: " + lblLineColText);
                    break;
                }
            }
        }
        return isExist;
    }

    public void editLine(String brandName, String level, String lineNameOld, String lineNameNew, String uplineID, String mappedAccount) {
        int rowIndex = findRowLineIndex(lineNameOld);
        Label.xpath(tblLine.getxPathOfCell(1, totalCol, rowIndex, "i[@class='far fa-edit fa-edit-custom mr-3']")).click();
        if (!brandName.isEmpty()) {
            ddbBrand.selectByVisibleText(brandName);
        }
        if (!level.isEmpty()) {
            ddbLevel.selectByVisibleText(level);
        }
        if (!lineNameNew.isEmpty()) {
            txtLineName.type(true, lineNameNew);
        }
        if (!uplineID.isEmpty()) {
            ddbUplineId.selectByVisibleText(uplineID);
        }
        if (!mappedAccount.isEmpty()) {
            searchMappedAccountID(mappedAccount);
        }
        btnCreate.click();
    }

    public void deleteLine(String lineName){
        int rowIndex = findRowLineIndex(lineName);
        if(rowIndex==-1){
            System.out.println("Failed to find line with line name: "+lineName);
            return;
        }
        Label.xpath(tblLine.getxPathOfCell(1, totalCol, rowIndex, "i[@class='fas fa-trash-alt fa-trash-custom']")).click();
        confirmPopup.confirm();
    }

    /*************
     * PT SETTING ACTIONS
     *************/
    public void searchLine(String lineName){
        ddpLine.selectByVisibleText(lineName);
        btnSubmitPTSetting.click();
        agentsite.pages.HomePage.waitingLoadingSpinner();
    }

    public Map<String, List<String>> getMemberMaxPT(boolean isDistinct) {
        String allLineID = "-1";
        Map<String, List<String>> memberMaxPT = new HashMap<>();
        JSONArray memberInfoRes = PerformanceUtils.getLineMemberInfo(allLineID);
        if (Objects.nonNull(memberInfoRes)) {
            for (int i = 0; i < memberInfoRes.length(); i++) {
                Set<String> lisMaxPTSet = new HashSet<>();
                List<String> listMaxPT = new ArrayList<>();
                JSONObject sportsPTRes = memberInfoRes.getJSONObject(i);
                JSONArray maxPTSettingAr = sportsPTRes.getJSONArray("sportMaxPT");
                for (int x = 0; x < maxPTSettingAr.length(); x++) {
                    JSONObject sportPT = maxPTSettingAr.getJSONObject(x);
                    String pt = String.format("%.0f", sportPT.getDouble("takenPT"));
                    lisMaxPTSet.add(pt);
                    listMaxPT.add(pt);
                }
                String memberName = memberInfoRes.getJSONObject(i).getString("userCode");
                //apply in case which all settings are the same
                if (!isDistinct && lisMaxPTSet.size() == 1) {
                    memberMaxPT.put(memberName, Arrays.asList(lisMaxPTSet.iterator().next()));
                    return memberMaxPT;
                }
                //apply in case distinct setting
                if (isDistinct && lisMaxPTSet.size() > 1) {
                    memberMaxPT.put(memberName, listMaxPT);
                    return memberMaxPT;
                }
            }
        }
        return memberMaxPT;
    }

    public int findRowIndexBaseOnMemberName(String memberName){
        int rowIndex = 1;
        while (true) {
            Label lblMemberName = Label.xpath(tblPTSetting.getxPathOfCell(1, 2, rowIndex, null));
            if (lblMemberName.isDisplayed()) {
                if (lblMemberName.getText().contains(memberName)) {
                    System.out.println("FOUND correct member Name at row index: " + rowIndex);
                    break;
                } else {
                    rowIndex++;
                    continue;
                }
            } else {
                System.err.println("NOT found any correct line Name");
                rowIndex = -1;
                break;
            }
        }
        return rowIndex;
    }

    public void verifyMaxPTSettingShowCorrect(boolean isDistinct){
        Map<String, List<String>> memberMaxPTEntries =  getMemberMaxPT(isDistinct);
        String memberName = memberMaxPTEntries.keySet().toArray()[0].toString();
        int rowIndex = findRowIndexBaseOnMemberName(memberName);
        String maxPTAmount = Label.xpath(String.format("(%s)[2]", tblPTSetting.getxPathOfCell(1, PTcol, rowIndex, "span"))).getText().trim();
        String expectedPT = isDistinct? "-" : memberMaxPTEntries.get(memberName).get(0);
        Assert.assertEquals(maxPTAmount, expectedPT, "FAILED! Max PT amount is not show correct") ;
    }
}
