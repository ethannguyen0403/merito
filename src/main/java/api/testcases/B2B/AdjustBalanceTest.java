package api.testcases.B2B;

import api.objects.B2B.resultObj.BalanceObj;
import api.testcases.BaseCaseAPI;
import api.utils.B2B.appUitls.AdjustBalanceUtils;
import api.utils.B2B.appUitls.GetTokenUtils;
import api.utils.B2B.appUitls.PlayerInfoUtils;
import api.utils.testraildemo.TestRails;
import com.paltech.utils.DoubleUtils;
import com.paltech.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

public class AdjustBalanceTest extends BaseCaseAPI {

    @TestRails(id = "451")
    @Parameters({"agentKey", "authorization", "userId", "username"})
    @Test(groups = {"smoke"})
    public void BB007_AdjustBalanceTest_451(String agentKey, String authorization, String userId, String username) throws UnsupportedEncodingException {
        log("@title:Validate can deposit for b2b player account");
        log("Precondition Step: init data");
        String playerInfoJson = String.format("{\"userId\":\"%s\"}", userId);
        String transactionIdRandome = StringUtils.generateNumeric(12);
        Double amount = 1.1;
        String json = String.format("{\"userId\":\"%s\",\"amount\":\"%.2f\",\"transactionId\":\"%s\"}", userId, amount, transactionIdRandome);

        log("Step 1 Get valid token");
        String token = GetTokenUtils.getTokenValue(agentKey, authorization).getMessage();

        log("Step: Get balance info before deposit");
        double balanceBeforeDeposit = PlayerInfoUtils.getPlayerInfo(agentKey, token, playerInfoJson).getBalance();
        double balanceAfterDeposit = Double.valueOf(balanceBeforeDeposit + amount);

        log("Step 2 Call api to deposit balance for  b2b account, deposit with amount > 0");
        log(String.format("Current balance of the account %s is %.2f and deposit with amount %.2f", userId, balanceBeforeDeposit, amount));
        BalanceObj result = AdjustBalanceUtils.adjustBalance(agentKey, token, json);

        log("Verify 1 Validate deposit success");
        Assert.assertEquals(result.getResult(), 1, "FAILED! Success result should be 1");
        Assert.assertEquals(result.getUserId(), userId, "FAILED! Incorrect User Id");
        Assert.assertEquals(result.getAmount(), amount, "FAILED! Amount is incorrect");
        Assert.assertEquals(result.getBalanceBefore(), balanceBeforeDeposit, "FAILED! Balance Before deposit is incorrect");
        Assert.assertEquals(result.getBalanceAfter(), DoubleUtils.roundUpWithTwoPlaces(balanceAfterDeposit), "FAILED! Balance after deposit is incorrect");
        Assert.assertEquals(result.getTransactionId(), Long.parseLong(transactionIdRandome), "FAILED! Incorrect User Id");

        log(String.format("Verify 2 Validate balance in Player info is correct %.2f", balanceAfterDeposit));
        double balanceInfoAfterDeposit = PlayerInfoUtils.getPlayerInfo(agentKey, token, playerInfoJson).getBalance();
        Assert.assertEquals(DoubleUtils.roundUpWithTwoPlaces(balanceAfterDeposit), DoubleUtils.roundUpWithTwoPlaces(balanceInfoAfterDeposit), "FAILED! Balance after deposit when call Player info is incorrect");
        log("INFO: Executed completely");

    }

    @TestRails(id = "452")
    @Parameters({"agentKey", "authorization", "userId"})
    @Test(groups = {"smoke"})
    public void BB007_AdjustBalanceTest_002(String agentKey, String authorization, String userId) throws UnsupportedEncodingException {
        log("@title:Validate can withdraw for b2b player account");
        log("Precondition Step: init data");
        String playerInfoJson = String.format("{\"userId\":\"%s\"}", userId);
        String transactionIdRandome = StringUtils.generateNumeric(12);
        double amount = -1.1;
        String json = String.format("{\"userId\":\"%s\",\"amount\":\"%.2f\",\"transactionId\":\"%s\"}", userId, amount, transactionIdRandome);

        log("Step 1 Get valid token");
        String token = GetTokenUtils.getTokenValue(agentKey, authorization).getMessage();

        log("Step: Get balance info before withdraw");
        double balanceBeforeWithdraw = PlayerInfoUtils.getPlayerInfo(agentKey, token, playerInfoJson).getBalance();
        double balanceAfterWithdraw = Double.valueOf(balanceBeforeWithdraw + amount);

        log("Step 2 Call api to withdraw balance for  b2b account, withdraw with amount < 0");
        log(String.format("Current balance of the account %s is %.2f and withdraw with amount %.2f", userId, balanceBeforeWithdraw, amount));
        BalanceObj result = AdjustBalanceUtils.adjustBalance(agentKey, token, json);

        log("Verify 1 Validate withdraw success");
        Assert.assertEquals(result.getResult(), 1, "FAILED! Success result should be 1");
        Assert.assertEquals(result.getUserId(), userId, "FAILED! Incorrect User Id");
        Assert.assertEquals(result.getAmount(), amount, "FAILED! Amount is incorrect");
        Assert.assertEquals(result.getBalanceBefore(), balanceBeforeWithdraw, "FAILED! Balance Before Withdraw is incorrect");
        Assert.assertEquals(result.getBalanceAfter(), DoubleUtils.roundUpWithTwoPlaces(balanceAfterWithdraw), "FAILED! Balance after Withdraw is incorrect");
        Assert.assertEquals(result.getTransactionId(), Long.parseLong(transactionIdRandome), "FAILED! Incorrect User Id");

        log(String.format("Verify 2 Validate balance in Player info is correct %.2f", balanceAfterWithdraw));
        double balanceInfoAfterWithdraw = PlayerInfoUtils.getPlayerInfo(agentKey, token, playerInfoJson).getBalance();
        Assert.assertEquals(DoubleUtils.roundUpWithTwoPlaces(balanceInfoAfterWithdraw), DoubleUtils.roundUpWithTwoPlaces(balanceAfterWithdraw), "FAILED! Balance after Withdraw when call Player info is incorrect");
        log("INFO: Executed completely");

    }


}
