package cn.imhtb.ad.mysql.listener;

import cn.imhtb.ad.mysql.TemplateHolder;
import cn.imhtb.ad.mysql.dto.BinlogRowData;
import cn.imhtb.ad.mysql.dto.TableTemplate;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 69 70
 * @author PinTeh
 * @date 2019/8/12
 */
@Slf4j
@Component
public class AggregationListener implements BinaryLogClient.EventListener {

    private String dbName;
    private String tableName;

    private Map<String, IListener> listenerMap = new HashMap<>();

    @Autowired
    private TemplateHolder templateHolder;

    //生成k的方法
    private String genKey(String dbName, String tableName){
        return dbName + ":" + tableName;
    }

    public void register(String _dbName, String _tableName, IListener IListener){
        log.info("register : {}-{}",_dbName,_tableName);
        this.listenerMap.put(genKey(_dbName,_tableName),IListener);
    }

    @Override
    public void onEvent(Event event) {
        EventType type = event.getHeader().getEventType();
        log.debug("event type: {}",type);

        //如果类型是TABLE_MAP
        if(type == EventType.TABLE_MAP){
            TableMapEventData data = event.getData();
            this.tableName = data.getTable();
            this.dbName = data.getDatabase();
            return;
        }

        if(type != EventType.EXT_UPDATE_ROWS
                && type != EventType.EXT_WRITE_ROWS
                && type != EventType.EXT_DELETE_ROWS){
            return;
        }

        //表名和库名是否已经完成填充
        if(StringUtils.isEmpty(dbName) || StringUtils.isEmpty(tableName)){
            log.error("no meta data event");
            return;
        }

        //找出对应表有兴趣的监听器
        String key = genKey(this.dbName,this.tableName);
        IListener listener = this.listenerMap.get(key);
        if( null == listener){
            log.debug("skip {}",key);
            return;
        }

        log.info("trigger event: {}",type.name());

        try{
            BinlogRowData rowData = buildRowData(event.getData());
            if(rowData == null){
                return;
            }

            rowData.setEventType(type);
            listener.onEvent(rowData);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }finally {
            this.dbName ="";
            this.tableName = "";
        }
    }

    private List<Serializable[]> getAfterValues(EventData eventData){

        //插入
        if(eventData instanceof WriteRowsEventData){
            return ((WriteRowsEventData) eventData).getRows();
        }

        //更新
        if(eventData instanceof UpdateRowsEventData){
            return ((UpdateRowsEventData) eventData).getRows().stream()
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
        }

        if(eventData instanceof DeleteRowsEventData){
            return ((DeleteRowsEventData) eventData).getRows();
        }

        return Collections.emptyList();
    }

    private BinlogRowData buildRowData(EventData eventData){

        TableTemplate table = templateHolder.getTable(tableName);

        if(null == table){
            log.warn("table {} not found",tableName);
            return null;
        }

        List<Map<String,String>> afterMapList = new ArrayList<>();

        for(Serializable[] after : getAfterValues(eventData)){
            Map<String,String> afterMap = new HashMap<>();
            int colLen = after.length;
            for(int ix = 0; ix< colLen; ++ix){
                //取出当前位置对应的列名
                String colName = table.getPosMap().get(ix);

                //如果没有则说明不关心这个列
                if(null == colName){
                    log.debug("ignore position: {}",ix);
                    continue;
                }
            }

            afterMapList.add(afterMap);
        }

        BinlogRowData rowData = new BinlogRowData();
        rowData.setAfter(afterMapList);
        rowData.setTable(table);

        return rowData;
    }
}
