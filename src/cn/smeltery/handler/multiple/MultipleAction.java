package cn.smeltery.handler.multiple;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.AbstractAction;

import java.util.ArrayList;
import java.util.List;

public class MultipleAction extends AbstractAction {
    
    @Override
    public void onListPostExecute(List<TableRowMap> list, String id) 
        throws RichClientWebException {
        for (int i = 0; i < 5; i++) {
            TableRowMap tab1 = new TableRowMap();
            tab1.put("loop1_key1", "loop1_key1_" + i);
            tab1.put("loop1_key2", "loop1_key2_" + i);
            tab1.put("loop1_key3", "loop1_key3_" + i);
            tab1.put("loop1_key4", "loop1_key4_" + i);
            List<TableRowMap> list2 = new ArrayList<TableRowMap>();
            if (i < 3) {
                for (int j = 0; j < (i + 1); j++) {
                    TableRowMap tab2 = new TableRowMap();
                    tab2.put("loop2_key1", "loop2_key1_" + j);
                    tab2.put("loop2_key2", "loop2_key2_" + j);
                    List<TableRowMap> list3 = new ArrayList<TableRowMap>();
                    for (int k = 0; k < (j + 1); k++) {
                        TableRowMap tab3 = new TableRowMap();
                        tab3.put("loop3_key1", "loop3_key1_" + k);
                        tab3.put("loop3_key2", "loop3_key2_" + k);
                        list3.add(tab3);
                    }
                    tab2.setDatasList(list3);
                    list2.add(tab2);
                }
            }
            tab1.setDatasList(list2);
            list.add(tab1);
        }
    }

}
