package backoffice.testcases.operations;

import backoffice.common.BOConstants;
import backoffice.pages.bo.operations.BannerManagementPage;
import backoffice.pages.bo.operations.component.BannerConfirmPopup;
import backoffice.pages.bo.operations.component.NewBannerPopup;
import backoffice.utils.operations.BannerManagementUtils;
import baseTest.BaseCaseTest;
import com.paltech.utils.DateUtils;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import java.util.Arrays;
import java.util.List;

import static common.MeritoConstant.MEMBER_URL_SUFFIX;

public class BannerManagementTest extends BaseCaseTest {

    /**
     * @title: There is no http responded error returned
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Operations > Banner Management
     * 2. Filter Search button
     * @expect: 1. There is no http responded error returned
     */
    @Test(groups = {"http_request"})
    public void BO_Operations_BannerManagement_001() {
        log("@title: There is no http responded error returned");
        log("Step 1: Navigate Operations > Banner Management");
        BannerManagementPage page = backofficeHomePage.navigateBannerManagement();

        log("Step 2: Filter Search button");
        page.filter("Mobile", "", "");

        log("Verify: There is no http requests error");
        Assert.assertTrue(hasHTTPRespondedOK(), "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that this page loading is successful
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Operations > Banner Management
     * @expect: 1. Items on Brand dropdown-box are loaded correctly
     * 2. Items on Status dropdown-box are loaded correctly
     * 3. Column names on this table are correct
     */
//    @Test(groups = {"smoke"})
    public void BO_Operations_BannerManagement_008() {
        log("@title: Validate that this page loading is successful");
        List<String> lstBrandNames = BannerManagementUtils.getBrandNames();
        lstBrandNames.add(0, "All");

        log("Step 1: Navigate Operations > Banner Management");
        BannerManagementPage page = backofficeHomePage.navigateBannerManagement();

        page.ddbBrand.isDisplayed();
        boolean isBrandCorrect = page.ddbBrand.areOptionsMatched(lstBrandNames);
        boolean isStatusCorrect = page.ddbStatus.areOptionsMatched(BOConstants.Operations.BannerManagement.DDB_STATUS);
        List<String> lstHeaderNames = page.tblBanner.getHeaderNameOfRows();
        int expectedTotalColumns = BOConstants.Operations.BannerManagement.TABLE_HEADER.size();

        log("Verify 1: Items on Brand dropdown-box are loaded correctly");
        Assert.assertTrue(isStatusCorrect, "ERROR: At least an item on Status ddb is incorrect");

        log("Verify 2: Items on Status dropdown-box are loaded correctly");
        Assert.assertTrue(isBrandCorrect, "ERROR: At least an item on Brand ddb is incorrect");

        log("Verify 3: Column names on this table are correct");
        Assert.assertEquals(lstHeaderNames.size(), expectedTotalColumns, String.format("ERROR: The expected no of columns is '%s' but found '%s'", expectedTotalColumns, lstHeaderNames.size()));
        for (int i = 0; i < expectedTotalColumns; i++) {
            String observed = lstHeaderNames.get(i);
            String expected = BOConstants.Operations.BannerManagement.TABLE_HEADER.get(i);
            Assert.assertEquals(observed, expected, String.format("ERROR: The expected column name is '%s' but found '%s'", expected, observed));
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data display correctly when filter active banner for SAT Sport
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Operations > Banner Management
     * 2. Select Home type,  Select SAT Sport and Status Active and click Search button
     * @expect: 1. Verify data in each rows in Brand column contains SAT Sport and Status is ACTIVE
     */
//    @Test(groups = {"smoke"})
    public void BO_Operations_BannerManagement_003() {
        log("@title: Validate data display correctly when filter active banner for SAT Sport");
        String status = BOConstants.Operations.BannerManagement.DDB_STATUS.get(1);

        log("Step 1: Navigate Operations > Banner Management");
        BannerManagementPage page = backofficeHomePage.navigateBannerManagement();

        log("Step 2. Select Home type,  Select SAT Sport and Status Active and click Search button");
        page.filter("Home", "SAT Sport", status);

        List<String> lstStatus = page.tblBannerManagement.getColumn(page.colStatus, false);

        log("Verify 1. Verify data in each rows in Brand column contains SAT Sport and Status is ACTIVE");
        for (String observed : lstStatus) {
            Assert.assertEquals(observed, status.toUpperCase(), String.format("ERROR: The expected status is '%s' but found '%s'", status.toUpperCase(), observed));
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data display correctly when filter Inactive banner for SAT Sport
     * @pre-condition: 1. Log in successfully
     * @steps: 1: Navigate Operations > Banner Management
     * 2. Select Home type,  Select SAT Sport and Status Inactive and click Search button
     * @expect: 1. Verify data in each rows in Brand column contains SAT Sport and Status is INACTIVE
     */
//    @Test(groups = {"smoke"})
    public void BO_Operations_BannerManagement_004() {
        log("@title: Validate data display correctly when filter Inactive banner for SAT Sport");
        String status = BOConstants.Operations.BannerManagement.DDB_STATUS.get(2);
        String expectedBrand = "SAT Sport";

        log("Step 1: Navigate Operations > Banner Management");
        BannerManagementPage page = backofficeHomePage.navigateBannerManagement();

        log("Step 2. Select Home type,  Select SAT Sport and Status Inactive and click Search buttons");
        page.filter("Home", expectedBrand, status);

        List<String> lstStatus = page.tblBannerManagement.getColumn(page.colStatus, false);
        List<String> lstBrand = page.tblBannerManagement.getColumn(page.colBrands, false);

        log("Verify 1: Values at Status column are " + status);
        for (String observed : lstStatus) {
            Assert.assertEquals(observed, status.toUpperCase(), String.format("ERROR: The expected status is '%s' but found '%s'", status.toUpperCase(), observed));
        }
        for (String brand : lstBrand) {
            Assert.assertTrue(brand.contains(expectedBrand), String.format("ERROR: The expected Brand contains '%s' but found '%s'", expectedBrand, brand));
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate data display correctly when filter active banner for Word Exchange
     * @pre-condition: 1. Log in successfully
     * @steps: 1: Navigate Operations > Banner Management
     * 2. Select Home type,  Select Word Exchange and Status Inactive and click Search button
     * @expect: 1. Verify data in each rows in Brand column contains World Exchange and Status is INACTIVE otherwise display No record found
     */
//    @Test(groups = {"smoke"})
    public void BO_Operations_BannerManagement_005() {
        log("@title: Validate data display correctly when filter active banner for Word Exchange");
        String status = BOConstants.Operations.BannerManagement.DDB_STATUS.get(1);
        String expectedBrand = "World Exchange";

        log("Step 1: Navigate Operations > Banner Management");
        BannerManagementPage page = backofficeHomePage.navigateBannerManagement();

        log("Step 2. Select Home type,  Select Word Exchange and Status active and click Search button");
        page.filter("Home", expectedBrand, status);

        List<String> lstStatus = page.tblBannerManagement.getColumn(page.colStatus, true);
        List<String> lstBrand = page.tblBannerManagement.getColumn(page.colBrands, true);

        log("Verify 1. Verify data in each rows in Brand column contains World Exchange and Status is ACTIVE otherwise display No record found");
        for (String observed : lstStatus) {
            Assert.assertEquals(observed, status.toUpperCase(), String.format("ERROR: The expected status is '%s' but found '%s'", status.toUpperCase(), observed));
        }
        for (String brand : lstBrand) {
            Assert.assertTrue(brand.contains(expectedBrand), String.format("ERROR: The expected Brand contains '%s' but found '%s'", expectedBrand, brand));
        }
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can create new banner with inactive status for a brand
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Access Operations >Banner Management
     * 2. Click Create Banner
     * 3. Upload an image and select Inactive Status then select a brand
     * 4. Select Sequent, Valid From, Valid To and click Save button and click Ok on success message
     * 5. Click on Update date button
     * 6. Update banner popup display and update brands name: Add Fair Exchange then click Save button
     * 7. Click Delete link
     * @expect: 1. Verify a popup display  with the message: Banner Created Successfully
     * 2.The added image is display correctly info
     * 3. A message "Banner Updated" display and click Ok button. Verify brand of banner is updated: Brand column display World Exchange and FairExchange
     * 4. A message "Banner Deleted " display. click Ok and the banner is deleted
     */
//    @Test(groups = {"smoke"})
    @Parameters("username")
    public void BO_Operations_BannerManagement_006(String username) throws InterruptedException {
        log("@title: Validate data display correctly when filter active banner for Word Exchange");
        String status = BOConstants.Operations.BannerManagement.DDB_STATUS.get(2);
        String todayDate = DateUtils.getDate(0, "dd/MM/YYYY", BOConstants.GMT_FOUR);
        String todayExpected = DateUtils.getDate(0, "dd-MM-YYYY", BOConstants.GMT_FOUR);
        String tomorrow = DateUtils.getDate(1, "dd/MM/YYYY", BOConstants.GMT_FOUR);
        String tomorrowExpected = DateUtils.getDate(1, "dd-MM-YYYY", BOConstants.GMT_FOUR);
        String fileName = "test.png";

        log("Step 1: Navigate Operations > Banner Management");
        BannerManagementPage page = backofficeHomePage.navigateBannerManagement();

        log("Step 2. Click Create Banner");
        log("Step 3. Upload an image and select Inactive Status then select a brand");
        log("Step 4. Select Sequent, Valid From, Valid To and click Save button and click Ok on success message");
        page.selectType("Home");
        page.createNewBanner().submitNewBannerInfo(fileName, Arrays.asList("World Exchange"), status, "1", todayDate, tomorrow);
        BannerConfirmPopup popup = new BannerConfirmPopup();
        String message = popup.getContentMessage();
        popup.clickOK();
        log("Verify 1. Verify a popup display  with the message: Banner Created Successfully");
        Assert.assertEquals(message, BOConstants.Operations.BannerManagement.SUCCESS_MESSAGE_CREATE, "FAILED! The success message is not correct");

        log("Verify 2.The added image is display correctly info");
        page.filter("Home", Arrays.asList("World Exchange").get(0), status);
        Assert.assertTrue(page.verifyBannerInfo(fileName, Arrays.asList("World Exchange"), status, "-", todayExpected, tomorrowExpected, username), "FAILED! Banner added is incorrect info");


        log("Step 5. Click on Update date button");
        log("Step 6. Update banner popup display and update brands name: Add Fair Exchange then click Save button");
        NewBannerPopup newBannerPopup = (NewBannerPopup) page.action(BannerManagementPage.Actions.UPDATE, fileName);
        newBannerPopup.submitNewBannerInfo("", Arrays.asList("FairExchange"), "", "", "", tomorrow);

        log("Verify 3. A message \"Banner Updated\" display and click Ok button. Verify brand of banner is updated: Brand column display World Exchange and FairExchange");
        message = popup.getContentMessage();
        popup.clickOK();
        Assert.assertEquals(message, BOConstants.Operations.BannerManagement.SUCCESS_MESSAGE_UPDATE, "FAILED! The Update success message is not correct");
        Assert.assertTrue(page.verifyBannerInfo(fileName, Arrays.asList("FairExchange", "World Exchange"), status, "-", todayExpected, tomorrowExpected, username), "FAILED! Banner added is incorrect info");

        log("Step 7. Click Delete link");
        page.action(BannerManagementPage.Actions.DELETE, fileName);
        message = popup.getContentMessage();
        popup.clickOK();

        log("Verify 4. A message \"Banner Deleted \" display. click Ok and the banner is deleted");
        Assert.assertEquals(message, BOConstants.Operations.BannerManagement.SUCCESS_MESSAGE_DELETE, "FAILED! The Delete message is not correct");
        Assert.assertFalse(page.verifyBannerInfo(fileName, null, "", "", "", "", ""), "FAILED! Banner cannot delete");

        log("INFO: Executed completely");
    }

    /**
     * @title: Validate Home banner display correctly when login member site
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Access Operations >Banner Management
     * 2. Get Home popup of JIO Exchange in active status
     * 3. Login JIO member site > Home page
     * @expect: 1. Verify the active banners is display correctly
     */
//    @Test(groups = {"smoke"})
    @Parameters({"satMemberLoginID", "memberPassword"})
    public void BO_Operations_BannerManagement_007(String satMemberLoginID, String memberPassword) throws Exception {
        log("@title: Validate Home banner display correctly when login member site");
        String status = BOConstants.Operations.BannerManagement.DDB_STATUS.get(1);
        String brand = "SAT Sport";

        log("Step 1: Navigate Operations > Banner Management");
        BannerManagementPage page = backofficeHomePage.navigateBannerManagement();

        log("Step 2. Get Home popup of SAT Sport in active status");
        page.filter("Home", brand, status);
        List<String> activeBannerStillValidOnMemberSite = page.getBanners();

        page.logout().txtUsername.isDisplayed();
       /* log("Step 3. Login SAT member site > Home page");
        DriverManager.getDriver().getToAvoidTimeOut(environment.getSatDashboardURL());
        Helper.loginFairExchange(environment.getSatSOSURL(),environment.getSatDashboardURL(),satMemberLoginID,memberPassword,true);

        log("Verify 1. Verify the active banners is display correctly");
        satMBbackofficeHomePage  = new pages.sat.tabexchange.backofficeHomePage();
        satMBbackofficeHomePage.waitMenuLoading();
        List<String> lstBanner = satMBbackofficeHomePage.getBannerImageUrl();
        for(int i =0; i<lstBanner.size(); i++){
            Assert.assertTrue(lstBanner.get(i).contains(activeBannerStillValidOnMemberSite.get(i)),"FAILED! Banner list is incorrect");
        }*/
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that data returned is correct after filtering a brand
     * @pre-condition: 1. Log in successfully
     * @steps: 1. Navigate Operations > Banner Management
     * 2. Filter a brand name
     * @expect: 1. Values at Brand column are correct
     */
    @Test(groups = {"regression_invalid"})
    public void BO_Operations_BannerManagement_0012() {
        log("@title: Validate that data returned is correct after filtering Active status");
        List<String> lstBrandNames = BannerManagementUtils.getBrandNames();
        String brandName = lstBrandNames.get(1);

        log("Step 1: Navigate Operations > Banner Management");
        BannerManagementPage page = backofficeHomePage.navigateBannerManagement();

        log(String.format("Step 2: Filter %s brand", brandName));
        page.filter("Mobile", brandName, "");

        List<String> lstBrands = page.tblBannerManagement.getColumn(page.colBrands, false);

        log("Verify 1: Values at Brand column are " + brandName);
        for (String observed : lstBrands) {
            Assert.assertTrue(observed.contains(brandName), String.format("ERROR: The expected brand name is '%s' but found '%s'", brandName, observed));
        }
        log("INFO: Executed completely");
    }

    @TestRails(id = "1643")
    @Test(groups = {"regression"})
    public void BO_Operations_BannerManagement_1643() {
        log("@title: Validate can search and navigate to page correctly");
        log("Step 1: Navigate Operations > Banner Management");
        BannerManagementPage page = backofficeHomePage.navigateBannerManagement();

        log("Verify page title displays with Banner Management");
        Assert.assertEquals("Banner Management", page.lblTitlePage.getText(), "FAILED! Page title is not displayed correctly, actual: " + page.lblTitlePage.getText());
        log("INFO: Executed completely");
    }

    @TestRails(id = "1644")
    @Test(groups = {"regression"})
    public void BO_Operations_BannerManagement_1644() {
        log("@title: Validate can switch to old view/ new view");
        log("Step 1: Navigate Operations > Banner Management");
        BannerManagementPage page = backofficeHomePage.navigateBannerManagement();

        log("Step 2: Switch to New View");
        page.switchView("New View");
        log("Verify switch to New View successfully");
        Assert.assertEquals("nav-link active", page.lblNewView.getAttribute("className"), "FAILED! Switch to New View unsuccessfully");
        log("Step 3: Switch to Old View");
        page.switchView("Old View");
        log("Verify switch to Old View successfully");
        Assert.assertEquals("nav-link active", page.lblOldView.getAttribute("className"), "FAILED! Switch to Old View unsuccessfully");

        log("INFO: Executed completely");
    }

    @TestRails(id = "1645")
    @Test(groups = {"regression"})
    public void BO_Operations_BannerManagement_1645() {
        String expectedBrand = "SAT Sport";
        String status = BOConstants.Operations.BannerManagement.DDB_STATUS.get(1);
        log("@title: Validate can filter brand old view");
        log("Step 1: Navigate Operations > Banner Management");
        BannerManagementPage page = backofficeHomePage.navigateBannerManagement();

        log("Step 2: Switch to Old View");
        page.switchView("Old View");
        page.waitSpinIcon();
        log("Step 3: Select Type = Home, Brand = SAT Sport, Status = Active and submit");
        page.filter("Home", expectedBrand, status);

        List<String> lstStatus = page.getListData(page.colStatus, true);
        List<String> lstBrand = page.getListData(page.colBrands, true);

        log("Verify 3. Verify data in each rows in Brand column contains SAT Sport and Status is ACTIVE");
        for (String observed : lstStatus) {
            Assert.assertEquals(observed, status.toUpperCase(), String.format("ERROR: The expected status is '%s' but found '%s'", status.toUpperCase(), observed));
        }
        for (String brand : lstBrand) {

            Assert.assertTrue(brand.contains(expectedBrand), String.format("ERROR: The expected Brand contains '%s' but found '%s'", expectedBrand, brand));
        }
        log("INFO: Executed completely");
    }

    @TestRails(id = "1646")
    @Test(groups = {"regression"})
    public void BO_Operations_BannerManagement_1646() {
        String expectedBrand = "FairExchange";
        String expectedTheme = "All";
        String status = BOConstants.Operations.BannerManagement.DDB_STATUS.get(1);
        log("@title: Validate can filter brand new view");
        log("Step 1: Navigate Operations > Banner Management");
        BannerManagementPage page = backofficeHomePage.navigateBannerManagement();

        log("Step 2: Switch to New View");
        page.switchView("New View");
        page.waitSpinIcon();
        log("Step 3: Select Type = Home, Brand = FairExchange, Theme = ALL, Status = Active and submit");
        page.filter("Home", expectedBrand, expectedTheme, status);

        List<String> lstStatus = page.getListData(page.colStatus + 1, true);
        List<String> lstBrand = page.getListData(page.colBrands + 1, true);
        List<String> lstTheme = page.getListData(page.colTheme, true);

        log("Verify 3. Verify data in each rows in Brand column contains FairExchange and Theme = All and Status is ACTIVE");
        for (String observed : lstStatus) {
            Assert.assertEquals(observed, status.toUpperCase(), String.format("ERROR: The expected Status is '%s' but found '%s'", status.toUpperCase(), observed));
        }
        for (String brand : lstBrand) {

            Assert.assertTrue(brand.contains(expectedBrand), String.format("ERROR: The expected Brand contains '%s' but found '%s'", expectedBrand, brand));
        }
        for (String theme : lstTheme) {

            Assert.assertEquals(theme.replace(" ", ""), expectedTheme, String.format("ERROR: The expected Theme is '%s' but found '%s'", expectedTheme, theme));
        }
        log("INFO: Executed completely");
    }

    @TestRails(id = "1647")
    @Test(groups = {"regression"})
    @Parameters({"feMemberLoginId", "feMemberLoginPwd", "language", "currency"})
    public void BO_Operations_BannerManagement_1647(String feMemberLoginId, String feMemberLoginPwd, String language, String currency) throws Exception {
        String expectedBrand = "FairExchange";
        String status = BOConstants.Operations.BannerManagement.DDB_STATUS.get(1);
        log("@title: Validate Old View - FairExchange show correctly banner as sequence setting");
        log("Step 1: Navigate Operations > Banner Management and get all active banner valid till today");
        BannerManagementPage page = backofficeHomePage.navigateBannerManagement();

        log("Step 2: Observe sequence setting for Old VIew, Type = Home, Brand = FairExchange, Status = Active");
        page.switchView("Old View");
        page.filter("Home", expectedBrand, status);
        page.waitSpinIcon();
        List<String> lstImgSrc = page.getListBanners(page.colBanner, page.colValidTill);
        page.logout();
        memberLoginURL = defineURL("fairexchange", MEMBER_URL_SUFFIX.get("fairexchange"));
        log("Step 3: Navigate to before login of FairExchange old view and observe banner show on Home before login");
        loginMember(feMemberLoginId, feMemberLoginPwd, false, language, currency, false);
        log("Verify 3. Verify Banner show correctly with sequence set from BO");
        List<String> lstMemberImgSrcBefore = landingPage.getListBanners("old view");
        for (int i = 0; i < lstMemberImgSrcBefore.size(); i++) {
            Assert.assertTrue(lstMemberImgSrcBefore.get(i).contains(lstImgSrc.get(i)));
        }
        log("Verify 4. Login to Home and observe banner");
        memberHomePage = landingPage.login(feMemberLoginId, StringUtils.decrypt(feMemberLoginPwd), true);
        log("Verify 5. Verify Banner show correctly with sequence set from BO");
        List<String> lstMemberImgSrcAfter = landingPage.getListBanners("old view");
        for (int i = 0; i < lstMemberImgSrcAfter.size(); i++) {
            Assert.assertTrue(lstMemberImgSrcAfter.get(i).contains(lstImgSrc.get(i)));
        }

        log("INFO: Executed completely");
    }

    @TestRails(id = "1648")
    @Test(groups = {"regression"})
    @Parameters({"feMemberLoginId", "feMemberLoginPwd", "language", "currency"})
    public void BO_Operations_BannerManagement_1648(String feMemberLoginId, String feMemberLoginPwd, String language, String currency) throws Exception {
        String expectedBrand = "FairExchange";
        String status = BOConstants.Operations.BannerManagement.DDB_STATUS.get(1);
        log("@title: Validate New View - FairExchange show correctly banner as sequence setting");
        log("Step 1: Navigate Operations > Banner Management and get all active banner valid till today");
        BannerManagementPage page = backofficeHomePage.navigateBannerManagement();

        log("Step 2: Observe sequence setting for New VIew, Type = Home, Brand = FairExchange, Status = Active");
        page.switchView("New View");
        page.filter("Home", expectedBrand, "All", status);
        List<String> lstImgSrc = page.getListBanners(page.colBanner, page.colValidTill + 2);
        page.logout();

        log("Step 3: Navigate to before login of FairExchange new view and observe banner show on Home before login");
        memberLoginURL = defineURL("fairexchangeplus", MEMBER_URL_SUFFIX.get("fairexchangeplus"));
        loginMember(feMemberLoginId, feMemberLoginPwd, false, language, currency, false);
        log("Verify 3. Verify Banner show correctly with sequence set from BO");
        List<String> lstMemberImgSrcBefore = landingPage.getListBanners("new view");
        for (int i = 0; i < lstMemberImgSrcBefore.size(); i++) {
            Assert.assertTrue(lstMemberImgSrcBefore.get(i).contains(lstImgSrc.get(i)));
        }
        log("Verify 4. Login to Home and observe banner");
        memberHomePage = landingPage.login(feMemberLoginId, StringUtils.decrypt(feMemberLoginPwd), true);
        log("Verify 4. Verify Banner show correctly with sequence set from BO");
        List<String> lstMemberImgSrcAfter = landingPage.getListBanners("new view");
        for (int i = 0; i < lstMemberImgSrcAfter.size(); i++) {
            Assert.assertTrue(lstMemberImgSrcAfter.get(i).contains(lstImgSrc.get(i)));
        }

        log("INFO: Executed completely");
    }

}
