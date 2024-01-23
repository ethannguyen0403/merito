package agentsite.pages.agentmanagement.proteus.createdownlineagent.commissionsettingsection;

import controls.Table;

import java.util.List;

public class CommissionSectionPS38Agent extends CommissionSectionPS38{
    protected Table tblCommission = Table.xpath("//div[@id='PROTEUS-commission-settings']//table", 7);

    @Override
    public List<String> getTableHeader() {
        return tblCommission.getHeaderNameOfRows();
    }

    @Override
    public List<String> getTableColumnList(int colIndex) {
        return tblCommission.getColumn(colIndex, false);
    }

}
